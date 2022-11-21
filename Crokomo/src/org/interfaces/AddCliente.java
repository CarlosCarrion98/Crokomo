package org.interfaces;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.bd.dao.ClienteDAO;
import org.objects.Cliente;
import org.objects.Proyecto;
import org.objects.Usuario;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddCliente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6158987608124936930L;
	private JPanel contentPane;
	private JTextField txtNombreCliente;
	private JTextField txtPeso;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AddCliente frame = new AddCliente();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public AddCliente(Usuario u, Proyecto p) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.LIGHT_GRAY);
		desktopPane.setBounds(0, 0, 734, 43);
		contentPane.add(desktopPane);
		
		JLabel labelAddCliente = new JLabel("Añadir Cliente");
		labelAddCliente.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelAddCliente.setHorizontalAlignment(SwingConstants.CENTER);
		labelAddCliente.setBounds(259, 0, 284, 43);
		desktopPane.add(labelAddCliente);
		
		JLabel labelNombreCliente = new JLabel("Nombre de cliente");
		labelNombreCliente.setHorizontalAlignment(SwingConstants.CENTER);
		labelNombreCliente.setBounds(346, 148, 116, 14);
		contentPane.add(labelNombreCliente);
		
		txtNombreCliente = new JTextField();
		txtNombreCliente.setBounds(276, 173, 255, 20);
		contentPane.add(txtNombreCliente);
		txtNombreCliente.setColumns(10);
		
		JLabel labelPeso = new JLabel("Peso");
		labelPeso.setHorizontalAlignment(SwingConstants.CENTER);
		labelPeso.setBounds(346, 242, 116, 14);
		contentPane.add(labelPeso);
		
		txtPeso = new JTextField();
		txtPeso.setBounds(276, 267, 255, 20);
		contentPane.add(txtPeso);
		txtPeso.setColumns(10);
		
		JLabel lblErrorVacio = new JLabel("Nombre y/o peso vacío");
		lblErrorVacio.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrorVacio.setForeground(Color.RED);
		lblErrorVacio.setBounds(335, 297, 137, 13);
		lblErrorVacio.setVisible(false);
		contentPane.add(lblErrorVacio);
		
		JButton botonAddCliente = new JButton("Añadir");
		botonAddCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ClienteDAO cdao = new ClienteDAO();
				if(txtNombreCliente.getText().isBlank() || txtPeso.getText().isBlank()) {
					lblErrorVacio.setVisible(true);
				} else {
					Cliente c = new Cliente(0, Integer.parseInt(txtPeso.getText()), txtNombreCliente.getText(), p.getIdProyecto());
					cdao.insertar(c);
					ProyectoInterfaz proyecto = new ProyectoInterfaz(u, p);
					proyecto.setVisible(true);
					dispose();
				}
				
				
			}
		});
		botonAddCliente.setBounds(433, 341, 98, 23);
		contentPane.add(botonAddCliente);
		
		JButton botonVolver = new JButton("Volver");
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProyectoInterfaz proyecto = new ProyectoInterfaz(u, p);
				proyecto.setVisible(true);
				dispose();
			}
		});
		botonVolver.setBounds(276, 341, 98, 23);
		contentPane.add(botonVolver);
		
		
	}
}
