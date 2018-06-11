package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


import model.Cliente;
import negocio.RegraCliente;
import java.awt.Font;


public class TelaPrincipal extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtCpf;
	private JTextField txtNascimento;
	private JComboBox cboSexo;
	private RegraCliente regraCliente;
	private JTextField txtCpfConsulta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaPrincipal() {
		
		regraCliente = new RegraCliente();
		
		setTitle(".: CRUD APS  :.");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(480, 190, 486, 442);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStatus = new JLabel("");
		lblStatus.setBounds(374, 37, 63, 31);
		contentPane.add(lblStatus);


		/* Criação de Thread para verificar status do server */
		Thread t = new Thread(new StatusServer(lblStatus));
		
		JLabel lblStatusServer = new JLabel("Status server");
		lblStatusServer.setBounds(364, 11, 96, 14);
		contentPane.add(lblStatusServer);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(20, 165, 46, 14);
		contentPane.add(lblNome);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(20, 190, 46, 14);
		contentPane.add(lblCpf);
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(20, 243, 46, 14);
		contentPane.add(lblSexo);
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento:");
		lblDataDeNascimento.setBounds(20, 218, 128, 14);
		contentPane.add(lblDataDeNascimento);
		
		txtNome = new JTextField();
		txtNome.setBounds(62, 160, 164, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		txtCpf = new JTextField();
		txtCpf.setBounds(62, 186, 163, 20);
		contentPane.add(txtCpf);
		txtCpf.setColumns(10);
		
		txtNascimento = new JTextField();
		txtNascimento.setBounds(141, 215, 86, 20);
		contentPane.add(txtNascimento);
		txtNascimento.setColumns(10);
		
		cboSexo = new JComboBox();
		cboSexo.setModel(new DefaultComboBoxModel(new String[] { "", "M", "F" }));
		cboSexo.setSelectedIndex(0);
		cboSexo.setBounds(62, 240, 71, 20);
		contentPane.add(cboSexo);
		
		JButton btnInserir = new JButton("");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente cliente = new Cliente();

				cliente.setNome(txtNome.getText());
				cliente.setCpf(txtCpf.getText());
				cliente.setSexo(cboSexo.getSelectedItem().toString());
				if (!txtNascimento.getText().isEmpty()) {
				cliente.setNascimento(new Date(txtNascimento.getText()));
				}
				
				int adicionado = 0;

				try {
					adicionado = regraCliente.adicionarOrAlterar(cliente,"I");
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}

				if (adicionado > 0) {
					JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!!");
					txtNome.setText(null);
					txtCpf.setText(null);
					cboSexo.setSelectedItem(null);
					txtNascimento.setText(null);
				}
				
			}
		});
		btnInserir.setToolTipText("Inserir");
		btnInserir.setIcon(new ImageIcon("img\\insert.png"));
		btnInserir.setBounds(20, 294, 80, 80);
		contentPane.add(btnInserir);
		
		JButton btnConsultar = new JButton("");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente cliente = null;
				try {
					cliente = regraCliente.consultaCliente(txtCpfConsulta.getText());
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					return;
				}
				
				if (cliente == null) {
					JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
					return;
				}else {
					JOptionPane.showMessageDialog(null, "Consulta Realizada");
				}	
					
				txtNome.setText(cliente.getNome());
				txtCpf.setText(cliente.getCpf());
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				String dataFormatada = formato.format(cliente.getNascimento());
				txtNascimento.setText(dataFormatada);
				cboSexo.setSelectedItem(cliente.getSexo().toString());
			}
		});
		btnConsultar.setToolTipText("Consultar");
		btnConsultar.setIcon(new ImageIcon("img\\read.png"));
		btnConsultar.setBounds(126, 294, 80, 80);
		contentPane.add(btnConsultar);
		
		JButton btnAtualizar = new JButton("");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Cliente cliente = new Cliente();
				
				cliente.setNome(txtNome.getText());
				cliente.setCpf(txtCpf.getText());
				cliente.setSexo(cboSexo.getSelectedItem().toString());
				if (!txtNascimento.getText().isEmpty()) {
					cliente.setNascimento(new Date(txtNascimento.getText()));
				}
				int adicionado = 0;
				try {
					adicionado = regraCliente.adicionarOrAlterar(cliente,"U");
				} catch (Exception exx) {
					exx.printStackTrace();
					JOptionPane.showMessageDialog(null, exx.getMessage());
				}

				if (adicionado > 0) {
					JOptionPane.showMessageDialog(null, "Dados do cliente alterados com sucesso!!");
					txtNome.setText(null);
					txtCpf.setText(null);
					txtNascimento.setText(null);
					cboSexo.setSelectedItem(null);
				}

			}
		});
		btnAtualizar.setToolTipText("Atualizar");
		btnAtualizar.setIcon(new ImageIcon("img\\update.png"));
		btnAtualizar.setBounds(241, 294, 80, 80);
		contentPane.add(btnAtualizar);
		
		JButton btnDeletar = new JButton("");
		btnDeletar.addActionListener(new ActionListener() {
			// METODO PARA REMOVER CLIENTE
			public void actionPerformed(ActionEvent e) {
				int apagado = regraCliente.remover(txtCpfConsulta.getText());
				if (apagado > 0) {
					JOptionPane.showMessageDialog(null, "Cliente removido com sucesso!");
					txtNome.setText(null);
					txtCpf.setText(null);
					txtNascimento.setText(null);
					cboSexo.setSelectedItem(null);
				} else {
					JOptionPane.showMessageDialog(null, "Cliente não informado!");
				}
			}
		});
		btnDeletar.setToolTipText("Deletar");
		btnDeletar.setIcon(new ImageIcon("img\\delete.png"));
		btnDeletar.setBounds(357, 294, 80, 80);
		contentPane.add(btnDeletar);
		
		txtCpfConsulta = new JTextField();
		txtCpfConsulta.setBounds(123, 70, 128, 20);
		contentPane.add(txtCpfConsulta);
		txtCpfConsulta.setColumns(10);
		
		JLabel lblConsultaPorCpf = new JLabel("Consulta por CPF:");
		lblConsultaPorCpf.setBounds(13, 73, 102, 14);
		contentPane.add(lblConsultaPorCpf);
		
		JLabel lblCadastro = new JLabel("CADASTRO ");
		lblCadastro.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCadastro.setBounds(188, 118, 179, 31);
		contentPane.add(lblCadastro);
		
		JLabel lblConsulta = new JLabel("CONSULTA");
		lblConsulta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblConsulta.setBounds(188, 33, 179, 31);
		contentPane.add(lblConsulta);
		
		JButton btnListarTodos = new JButton("Listar Todos ");
		btnListarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaListar telalistar = new TelaListar();
				telalistar.setVisible(true);
			}
		});
		btnListarTodos.setBounds(293, 79, 120, 23);
		contentPane.add(btnListarTodos);
		t.start();
	}
}
