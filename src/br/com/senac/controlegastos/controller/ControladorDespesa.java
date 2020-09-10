package br.com.senac.controlegastos.controller;

import java.util.ArrayList;

import br.com.senac.controlegastos.model.bo.DespesaBO;
import br.com.senac.controlegastos.model.vo.DespesaVO;

public class ControladorDespesa {

	public void cadastrarDespesaController(DespesaVO despesaVO) {
		DespesaBO despesaBO = new DespesaBO();
		despesaBO.cadastrarDespesaBO(despesaVO);
		
	}

	public ArrayList<DespesaVO> consultarDespesasUsuarioController(DespesaVO despesaVO) {
		DespesaBO despesaBO = new DespesaBO();
		return despesaBO.consultarDespesasUsuarioBO(despesaVO);
	}

	public DespesaVO consultarDespesaController(DespesaVO despesaVO) {
		DespesaBO despesaBO = new DespesaBO();
		return despesaBO.consultarDespesaBO(despesaVO);
	}

	public void atualizarDespesaController(DespesaVO despesaVO) {
		DespesaBO despesaBO = new DespesaBO();
		despesaBO.atualizarDespesaBO(despesaVO);		
	}

	public void excluirDespesaController(DespesaVO despesaVO) {
		DespesaBO despesaBO = new DespesaBO();
		despesaBO.excluirDespesaBO(despesaVO);			
	}
	
}