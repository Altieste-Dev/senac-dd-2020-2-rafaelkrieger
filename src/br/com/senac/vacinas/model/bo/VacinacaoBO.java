package br.com.senac.vacinas.model.bo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.senac.vacinas.model.dao.VacinacaoDAO;
import br.com.senac.vacinas.model.exception.CamposVaziosException;
import br.com.senac.vacinas.model.exception.EstagioException;
import br.com.senac.vacinas.model.exception.VacinacaoException;
import br.com.senac.vacinas.model.vo.PessoaVO;
import br.com.senac.vacinas.model.vo.VacinacaoVO;

public class VacinacaoBO {
	
	VacinacaoDAO dao = new VacinacaoDAO();

	public String salvar(VacinacaoVO vacinacao) {
		String mensagem = "";
		boolean valido = true;
			
		try {
			this.validarVacinacao(vacinacao.getPessoa());		
		} catch (VacinacaoException excecao) {
			valido = false;
			mensagem = excecao.getMessage();
		}
		
		
		if (valido) {
			vacinacao = dao.inserir(vacinacao);
			mensagem = "Salvo com sucesso! Id gerado: " + vacinacao.getIdVacinacao();		
		}		
		
		return mensagem;			
	}
	

	private void validarVacinacao(PessoaVO pessoa) throws VacinacaoException{
		if (dao.pesquisarPorIdPessoa(pessoa.getIdPessoa()) != null) {
			if (dao.pesquisarPorIdPessoa(pessoa.getIdPessoa()).getDataVacinacao().plusYears(1).isAfter(LocalDate.now())) {
				throw new VacinacaoException("Paciente só pode tomar uma vacina por ano");
			}
		}		
	}

}
