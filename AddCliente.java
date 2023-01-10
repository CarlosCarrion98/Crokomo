package org.interfaces;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
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
		setIconImage(new ImageIcon("Assets/icono.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0, 1920, 950);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.LIGHT_GRAY);
		desktopPane.setBounds(0, 0, 1920, 60);
		contentPane.add(desktopPane);
		
		JLabel labelAddCliente = new JLabel("Añadir Cliente");
		labelAddCliente.setFont(new Font("Tahoma", Font.PLAIN, 25));
		labelAddCliente.setHorizontalAlignment(SwingConstants.CENTER);
		labelAddCliente.setBounds(620, 0, 284, 60);
		desktopPane.add(labelAddCliente);
		
		JLabel labelNombreCliente = new JLabel("Nombre de cliente");
		labelNombreCliente.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelNombreCliente.setHorizontalAlignment(SwingConstants.CENTER);
		labelNombreCliente.setBounds(645, 200, 244, 30);
		contentPane.add(labelNombreCliente);
		
		txtNombreCliente = new JTextField();
		txtNombreCliente.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtNombreCliente.setBounds(620, 240, 300, 30);
		contentPane.add(txtNombreCliente);
		txtNombreCliente.setColumns(10);
		
		JLabel labelPeso = new JLabel("Peso");
		labelPeso.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelPeso.setHorizontalAlignment(SwingConstants.CENTER);
		labelPeso.setBounds(695, 300, 143, 30);
		contentPane.add(labelPeso);
		
		txtPeso = new JTextField();
		txtPeso.setBounds(620, 340, 300, 30);
		contentPane.add(txtPeso);
		txtPeso.setColumns(10);
		
		JLabel lblErrorVacio = new JLabel("Nombre y/o peso vacío");
		lblErrorVacio.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblErrorVacio.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrorVacio.setForeground(Color.RED);
		lblErrorVacio.setBounds(620, 400, 300, 30);
		lblErrorVacio.setVisible(false);
		contentPane.add(lblErrorVacio);
		
		JButton botonAddCliente = new JButton("Añadir");
		botonAddCliente.setFont(new Font("Tahoma", Font.PLAIN, 20));
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
		botonAddCliente.setBounds(870, 500, 250, 40);
		contentPane.add(botonAddCliente);
		
		JButton botonVolver = new JButton("Volver");
		botonVolver.setFont(new Font("Tahoma", Font.PLAIN, 20));
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProyectoInterfaz proyecto = new ProyectoInterfaz(u, p);
				proyecto.setVisible(true);
				dispose();
			}
		});
		botonVolver.setBounds(420, 500, 250, 40);
		contentPane.add(botonVolver);
		
		
	}
}
