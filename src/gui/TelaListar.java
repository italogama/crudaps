package gui;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import conexaoBanco.ConexaoMySQL;
import negocio.RegraListar;
import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class TelaListar extends JFrame {
	
	RegraListar regraListar = null;
	private JPanel contentPane;
	private JTable table;

	public TelaListar() {
		regraListar = new RegraListar();

		setTitle(".: Listar Clientes :.");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400, 200, 481, 381);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 65, 459, 234);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		
		consulta(regraListar.consultar());
	
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVoltar.setBounds(380, 321, 89, 23);
		contentPane.add(btnVoltar);
		
		JLabel lblNewLabel_1 = new JLabel("Lista de Clientes");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(176, 11, 202, 39);
		contentPane.add(lblNewLabel_1);

		setResizable(false);
	}
	
	public void consulta(ResultSet rs) {
		table.setModel(DbUtils.resultSetToTableModel(rs));
		table.getTableHeader().getColumnModel().getColumn(0).setHeaderValue("CPF");
		
		table.getTableHeader().getColumnModel().getColumn(1).setHeaderValue("Nome");
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getTableHeader().getColumnModel().getColumn(2).setHeaderValue("Sexo");
		table.getColumnModel().getColumn(2).setPreferredWidth(5);
		table.getTableHeader().getColumnModel().getColumn(3).setHeaderValue("Data de Nascimento");
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		repaint();
	}
}
