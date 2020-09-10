package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import model.vo.DespesaVO;

public class DespesaDAO {
	
	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public int cadastrarDespesaDAO(DespesaVO despesaVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;
		
		String query = "INSERT INTO despesa (idusuario, descricao, valor, datavencimento, datapagamento, categoria) VALUES ('"
				+ despesaVO.getIdUsuario() + "', '"
				+ despesaVO.getDescricao() + "', '"
				+ despesaVO.getValor() + "', '"
				+ despesaVO.getDataVencimento() + "',";
				
				if (despesaVO.getDataPagamento() != null) {
					query = query + " '" + despesaVO.getDataPagamento() + "',";
				} else {
					query = query + " " + null + ",";
				}
				
				query = query + " '" +despesaVO.getCategoria()+ "')";
				
		
		try {
			resultado = stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Erro ao executar a query de cadastro de despesa");
			System.out.println("Erro: "+e.getMessage());;
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}		
		return resultado;
	}

	public ArrayList<DespesaVO> consultarDespesasUsuarioDAO(DespesaVO despesaVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;	
		ArrayList<DespesaVO> listaDespesasVO = new ArrayList<DespesaVO>();			
		
		String query = "SELECT iddespesa, idusuario, descricao, valor, datavencimento, datapagamento, categoria "
				+"FROM despesa WHERE idusuario = "+despesaVO.getIdUsuario();
		
		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				DespesaVO despesa = new DespesaVO();
				despesa.setId(Integer.parseInt(resultado.getString(1)));
				despesa.setIdUsuario(Integer.parseInt(resultado.getString(2)));
				despesa.setDescricao(resultado.getString(3));
				despesa.setValor(Double.parseDouble(resultado.getString(4)));
				despesa.setDataVencimento(LocalDate.parse(resultado.getString(5), dateFormatter));
				if (resultado.getString(6) != null) {
					despesa.setDataPagamento(LocalDate.parse(resultado.getString(6), dateFormatter));
				}
				despesa.setCategoria(resultado.getString(7));
				listaDespesasVO.add(despesa);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a query de consulta de todas as despesas de um usuário");
			System.out.println("Erro: "+e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
			
		}		
		return listaDespesasVO;
	}

	public DespesaVO consultarDespesaDAO(DespesaVO despesaVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;	
		DespesaVO despesa = null;			
		
		String query = "SELECT iddespesa, idusuario, descricao, valor, datavencimento, datapagamento, categoria "
				+"FROM despesa WHERE iddespesa = "+despesaVO.getId();
		
		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				despesa = new DespesaVO();
				despesa.setId(Integer.parseInt(resultado.getString(1)));
				despesa.setIdUsuario(Integer.parseInt(resultado.getString(2)));
				despesa.setDescricao(resultado.getString(3));
				despesa.setValor(Double.parseDouble(resultado.getString(4)));
				despesa.setDataVencimento(LocalDate.parse(resultado.getString(5), dateFormatter));
				if (resultado.getString(6) != null) {
					despesa.setDataPagamento(LocalDate.parse(resultado.getString(6), dateFormatter));
				}
				despesa.setCategoria(resultado.getString(7));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a query de consulta de uma despesa específica");
			System.out.println("Erro: "+e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
			
		}		
		return despesa;
	}

	public boolean existeRegistroPorIdDespesa(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;		
		
		String query = "SELECT iddespesa FROM despesa WHERE iddespesa = '" + id + "' ";
		try {
			resultado = stmt.executeQuery(query);
			if(resultado.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a verificação da existência de despesa por ID");
			System.out.println("Erro: "+ e.getMessage());			
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return false;
	}

	public int atualizarDespesaDAO(DespesaVO despesaVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;
		
		String query = "UPDATE despesa SET "
				+ "idusuario = "+despesaVO.getIdUsuario() + ", "
				+ "descricao = '"+despesaVO.getDescricao() + "', "
				+ "valor = "+despesaVO.getValor() + ", "
				+ "datavencimento = '"+despesaVO.getDataVencimento() + "',";
				
				if (despesaVO.getDataPagamento() != null) {
					query = query + "datapagamento = '" + despesaVO.getDataPagamento() + "',";
				} else {
					query = query + "datapagamento = " + null + ",";
				}
				
				query = query + "categoria = '" +despesaVO.getCategoria()+ "' WHERE iddespesa = "+despesaVO.getId();
				
		
		try {
			resultado = stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Erro ao executar a query de atualização de despesa");
			System.out.println("Erro: "+e.getMessage());;
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}		
		return resultado;
	}

	public int excluirDespesaDAO(DespesaVO despesaVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		int resultado = 0;
		
		String query = "DELETE FROM despesa WHERE iddespesa = "+despesaVO.getId();
		
		try {
			resultado = stmt.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Erro ao executar a query de exclusão de despesa");
			System.out.println("Erro: "+e.getMessage());;
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return resultado;		
	}

}
