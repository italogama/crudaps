package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import br.com.agencia.model.Usuario;
import conexaoBanco.ConexaoMySQL;
import gui.StatusServer;
import model.Cliente;

import javax.swing.JComboBox;


public class TelaPrincipal extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtCpf;
	private JTextField txtNascimento;

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
		lblNome.setBounds(20, 129, 46, 14);
		contentPane.add(lblNome);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(20, 154, 46, 14);
		contentPane.add(lblCpf);
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(20, 207, 46, 14);
		contentPane.add(lblSexo);
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento:");
		lblDataDeNascimento.setBounds(20, 182, 128, 14);
		contentPane.add(lblDataDeNascimento);
		
		txtNome = new JTextField();
		txtNome.setBounds(62, 124, 164, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		txtCpf = new JTextField();
		txtCpf.setBounds(62, 150, 163, 20);
		contentPane.add(txtCpf);
		txtCpf.setColumns(10);
		
		txtNascimento = new JTextField();
		txtNascimento.setBounds(141, 179, 86, 20);
		contentPane.add(txtNascimento);
		txtNascimento.setColumns(10);
		
		JComboBox cboSexo = new JComboBox();
		cboSexo.setBounds(62, 204, 71, 20);
		contentPane.add(cboSexo);
		
		JButton btnInserir = new JButton("");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente cliente = new Cliente();

				cliente.setNome(txtNome.getText());
				cliente.setCpf(txtCpf.getText());
				cliente.setSexo(cboSexo.getSelectedItem());
				cliente.setDate(txtNascimento.getText());

				int adicionado = 0;

				try {
					adicionado = regraUsuario.adicionarOrAlterar(cliente);
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.getMessage());
				}

				if (adicionado > 0) {
					JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso!!");
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
		btnConsultar.setToolTipText("Consultar");
		btnConsultar.setIcon(new ImageIcon("img\\read.png"));
		btnConsultar.setBounds(126, 294, 80, 80);
		contentPane.add(btnConsultar);
		
		JButton btnAtualizar = new JButton("");
		btnAtualizar.setToolTipText("Atualizar");
		btnAtualizar.setIcon(new ImageIcon("img\\update.png"));
		btnAtualizar.setBounds(241, 294, 80, 80);
		contentPane.add(btnAtualizar);
		
		JButton btnDeletar = new JButton("");
		btnDeletar.setToolTipText("Deletar");
		btnDeletar.setIcon(new ImageIcon("img\\delete.png"));
		btnDeletar.setBounds(357, 294, 80, 80);
		contentPane.add(btnDeletar);
		t.start();
	}
}
