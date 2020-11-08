package br.com.senac.vacinas.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.com.senac.vacinas.controller.PesquisadorController;
import br.com.senac.vacinas.controller.PessoaController;
import br.com.senac.vacinas.model.vo.PesquisadorVO;
import br.com.senac.vacinas.model.vo.PessoaVO;
import java.awt.SystemColor;

public class AddPessoa extends JPanel {

	private JTextField textFieldNome;
	private JTextField textFieldInst;
	private JFormattedTextField formattedTextFieldCpf;
	private JFormattedTextField formattedTextFieldData;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private String sexoSelecionado;
	private boolean isVoluntario = false;
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/uuuu");
	
	
	/**
	 * Create the panel.
	 */
	public AddPessoa() {
		
		setBounds(100, 100, 450, 450);		
		this.setBackground(new Color(32, 178, 170));
		this.setBorder(null);		
		this.setLayout(null);
		
		JLabel lblNome = new JLabel("NOME");
		lblNome.setBounds(10, 0, 381, 24);
		lblNome.setForeground(Color.DARK_GRAY);
		lblNome.setFont(new Font("Segoe UI", Font.BOLD, 14));
		this.add(lblNome);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setForeground(Color.DARK_GRAY);
		lblCpf.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblCpf.setBounds(10, 58, 375, 24);
		this.add(lblCpf);
		
		JLabel lbDataNasc = new JLabel("DATA DE NASCIMENTO");
		lbDataNasc.setForeground(Color.DARK_GRAY);
		lbDataNasc.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lbDataNasc.setBounds(10, 115, 363, 24);
		this.add(lbDataNasc);
		
		final JLabel lblInst = new JLabel("INSTITUIÇÃO");
		lblInst.setForeground(Color.DARK_GRAY);
		lblInst.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblInst.setBounds(10, 270, 424, 24);
		this.add(lblInst);
		lblInst.setVisible(false);
		
		JLabel lblSexo = new JLabel("SEXO");
		lblSexo.setForeground(Color.DARK_GRAY);
		lblSexo.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblSexo.setBounds(10, 170, 363, 24);
		this.add(lblSexo);
		
		final JRadioButton rdbtnMasc = new JRadioButton("MASCULINO");
		buttonGroup.add(rdbtnMasc);
		rdbtnMasc.setForeground(Color.DARK_GRAY);
		rdbtnMasc.setFont(new Font("Segoe UI", Font.BOLD, 12));
		rdbtnMasc.setBackground(SystemColor.menu);
		rdbtnMasc.setBounds(10, 200, 109, 23);
		this.add(rdbtnMasc);
		
		final JRadioButton rdbtnFem = new JRadioButton("FEMININO");
		buttonGroup.add(rdbtnFem);
		rdbtnFem.setForeground(Color.DARK_GRAY);
		rdbtnFem.setFont(new Font("Segoe UI", Font.BOLD, 12));
		rdbtnFem.setBackground(SystemColor.menu);
		rdbtnFem.setBounds(123, 201, 109, 23);
		this.add(rdbtnFem);
		
		textFieldNome = new JTextField();
		textFieldNome.setFont(new Font("Dialog", Font.PLAIN, 14));
		textFieldNome.setBounds(10, 26, 414, 30);
		this.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		try {
			MaskFormatter mascaraCpf = new MaskFormatter("###.###.###-##");			
			MaskFormatter mascaraData = new MaskFormatter("##/##/####");
		
		formattedTextFieldCpf = new JFormattedTextField(mascaraCpf);
		formattedTextFieldCpf.setFont(new Font("Dialog", Font.PLAIN, 14));
		formattedTextFieldCpf.setBounds(10, 81, 414, 30);
		this.add(formattedTextFieldCpf);
		
		formattedTextFieldData = new JFormattedTextField(mascaraData);
		formattedTextFieldData.setFont(new Font("Dialog", Font.PLAIN, 14));
		formattedTextFieldData.setBounds(10, 140, 414, 30);		
		this.add(formattedTextFieldData);
		
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro no sistema, entre em contato com o administrador.");
			System.out.println("Causa da exceção: " + e.getMessage());
		}
				
		final JCheckBox chckbxVoluntario = new JCheckBox("VOLUNTÁRIO");
		chckbxVoluntario.setForeground(Color.DARK_GRAY);
		chckbxVoluntario.setBackground(SystemColor.menu);
		chckbxVoluntario.setFont(new Font("Segoe UI", Font.BOLD, 14));
		chckbxVoluntario.setBounds(84, 240, 148, 23);
		this.add(chckbxVoluntario);		
		
		final JCheckBox chckbxPesq = new JCheckBox("PESQUISADOR");		
		chckbxPesq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxPesq.isSelected()) {
					lblInst.setVisible(true);
					textFieldInst.setVisible(true);
				} else {
					lblInst.setVisible(false);
					textFieldInst.setVisible(false);
				}
			}
		});
		chckbxPesq.setForeground(Color.DARK_GRAY);
		chckbxPesq.setFont(new Font("Segoe UI", Font.BOLD, 14));
		chckbxPesq.setBackground(SystemColor.menu);
		chckbxPesq.setBounds(243, 240, 148, 23);
		this.add(chckbxPesq);		
			
		textFieldInst = new JTextField();
		textFieldInst.setFont(new Font("Dialog", Font.PLAIN, 14));
		textFieldInst.setColumns(10);
		textFieldInst.setBounds(10, 301, 414, 30);
		this.add(textFieldInst);		
		textFieldInst.setVisible(false);
		
		JButton btnSalvarPessoa = new JButton("SALVAR");
		btnSalvarPessoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PessoaVO pessoa = new PessoaVO();
				PesquisadorVO pesquisador = new PesquisadorVO();
				pessoa.setNome(textFieldNome.getText());
				pessoa.setCpf(obterNumerosCpf(formattedTextFieldCpf.getText()));
				pessoa.setDataNascimento(obterData(formattedTextFieldData.getText()));				
				
				if (rdbtnMasc.isSelected()) {
					sexoSelecionado = "M";
				}else if (rdbtnFem.isSelected()){
					sexoSelecionado = "F";
				}else {
					sexoSelecionado = null;
				}
				pessoa.setSexo(sexoSelecionado);
				
				if (chckbxVoluntario.isSelected()) {
					isVoluntario = true;
				} else {
					isVoluntario = false;
				}
				pessoa.setVoluntario(isVoluntario);				
							
				PessoaController pessoaController = new PessoaController();
				String mensagem = pessoaController.salvar(pessoa);
				JOptionPane.showMessageDialog(null, mensagem);
				
				if (chckbxPesq.isSelected()) {
					pesquisador.setInstituicao(textFieldInst.getText());
					pesquisador.setIdPessoa(pessoa.getIdPessoa());
					pesquisador.setNome(pessoa.getNome());
					PesquisadorController pesquisadorController = new PesquisadorController();
					pesquisadorController.salvar(pesquisador);	
				}
				
				textFieldNome.setText("");
				formattedTextFieldCpf.setText("");
				formattedTextFieldData.setText("");
				rdbtnFem.setSelected(false);
				rdbtnMasc.setSelected(false);
				chckbxPesq.setSelected(false);
				chckbxVoluntario.setSelected(false);
				textFieldInst.setVisible(false);
				lblInst.setVisible(false);
				
			}

			private String obterNumerosCpf(String cpf) {
				String digito = cpf.replace(".", "");
				String novoCpf = digito.replace("-", "");
				return novoCpf;				
			}
			
			private boolean validarData(String strDate) {
				DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);				
			    try {
			    	LocalDate date = LocalDate.parse(strDate, dateFormatter);
			        return true;
			    } catch (DateTimeParseException e) {			    	
			    	return false;			       
			    } 
			}
			
			private LocalDate obterData(String dataNascimento) {
				LocalDate data = null;				
				if (validarData(dataNascimento)) {
					data = LocalDate.parse(dataNascimento, dateFormat);
				}	
				return data;
			}
				
		});
		btnSalvarPessoa.setFont(new Font("Dialog", Font.BOLD, 14));
		btnSalvarPessoa.setBounds(123, 349, 177, 35);
		this.add(btnSalvarPessoa);		

	}

}