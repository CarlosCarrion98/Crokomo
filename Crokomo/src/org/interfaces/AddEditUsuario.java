package org.interfaces;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import org.bd.dao.UsuarioDAO;
import org.objects.Usuario;

import javax.swing.JLabel;
import javax.swing.JDesktopPane;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class AddEditUsuario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5643047139612025173L;
	private JPanel contentPane;
	private JTextField txtNombreUser;
	private JTextField textFieldContrasena;

	/**
	 * Launch the application.
	 */
	//	public static void main(String[] args) {
	//		EventQueue.invokeLater(new Runnable() {
	//			public void run() {
	//				try {
	//					AddRequisito frame = new AddRequisito();
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
	public AddEditUsuario(Usuario u, Usuario user) {
		setIconImage(new ImageIcon("Assets/icono.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1920, 950);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.LIGHT_GRAY);
		desktopPane.setBounds(0, 0, 1920, 60);
		contentPane.add(desktopPane);
		JLabel labelAddUsuario = new JLabel("Modificar Usuario");
		if(user == null)
			labelAddUsuario = new JLabel("Añadir Usuario");
		labelAddUsuario.setFont(new Font("Tahoma", Font.PLAIN, 25));
		labelAddUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		labelAddUsuario.setBounds(598, 10, 284, 43);
		desktopPane.add(labelAddUsuario);

		JLabel lblNewLabel_1 = new JLabel("Nombre del usuario");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(632, 129, 210, 23);
		contentPane.add(lblNewLabel_1);

		txtNombreUser = new JTextField();
		txtNombreUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtNombreUser.setBounds(590, 162, 300, 30);
		if(user != null) txtNombreUser.setText(user.getUserName());
		contentPane.add(txtNombreUser);
		txtNombreUser.setColumns(10);
		
		JLabel lblContrasena = new JLabel("Contraseña");
		lblContrasena.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblContrasena.setBounds(683, 277, 107, 23);
		contentPane.add(lblContrasena);
		
		textFieldContrasena = new JPasswordField();
		textFieldContrasena.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldContrasena.setColumns(10);
		textFieldContrasena.setBounds(590, 310, 300, 30);
		if(user != null) {
			textFieldContrasena.setText(user.getPassword());
			textFieldContrasena.setEnabled(false);
			JButton btnCambiarContrasea = new JButton("Cambiar contraseña");
			btnCambiarContrasea.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnCambiarContrasea.setBounds(953, 305, 250, 40);
			btnCambiarContrasea.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					textFieldContrasena.setEnabled(true);
					textFieldContrasena.setText("");
				}
			});
			contentPane.add(btnCambiarContrasea);
		}
		contentPane.add(textFieldContrasena);
		
		JComboBox<String> cbRol = new JComboBox<String>();
		cbRol.setBounds(661, 463, 148, 21);
		cbRol.addItem("User");
		cbRol.addItem("Admin");
		cbRol.setSelectedIndex(0);
		if(user != null) {
			if(user.getRol().equals("admin")) {
				cbRol.setSelectedIndex(1);
			}
		}
		contentPane.add(cbRol);
		
		JLabel lblRol = new JLabel("Privilegios");
		lblRol.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRol.setBounds(688, 425, 95, 23);
		contentPane.add(lblRol);
		
		//Label Errores

		JLabel lblVacio = new JLabel("Nombre del usuario no puede estar vacío");
		lblVacio.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblVacio.setHorizontalAlignment(SwingConstants.CENTER);
		lblVacio.setForeground(Color.RED);
		lblVacio.setBounds(543, 202, 401, 39);
		lblVacio.setVisible(false);
		contentPane.add(lblVacio);

		JLabel lblUsuarioExistente = new JLabel("El nombre del usuario ya existe");
		lblUsuarioExistente.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUsuarioExistente.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuarioExistente.setForeground(Color.RED);
		lblUsuarioExistente.setBounds(576, 202, 328, 39);
		lblUsuarioExistente.setVisible(false);
		contentPane.add(lblUsuarioExistente);
		
		JLabel lblVacioPass = new JLabel("Contraseña no puede estar vacía");
		lblVacioPass.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblVacioPass.setHorizontalAlignment(SwingConstants.CENTER);
		lblVacioPass.setForeground(Color.RED);
		lblVacioPass.setBounds(543, 350, 401, 39);
		lblVacioPass.setVisible(false);
		contentPane.add(lblVacioPass);
		
		UsuarioDAO udao = new UsuarioDAO();
		JButton botonAddPro = new JButton("Modificar");
		if(user == null) {
			botonAddPro = new JButton("Añadir");
		}
		botonAddPro.setFont(new Font("Tahoma", Font.PLAIN, 20));
		botonAddPro.setBounds(825, 600, 250, 40);
		contentPane.add(botonAddPro);
		botonAddPro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblVacio.setVisible(false);

				lblUsuarioExistente.setVisible(false);
				
				lblVacioPass.setVisible(false);
				
				Usuario userTemp = udao.obtenerUsuarioPorNombre(txtNombreUser.getText());
				if(!txtNombreUser.getText().isBlank()) {
					if(!textFieldContrasena.getText().isBlank()) {
						if(user == null) {
							if(userTemp == null) {
								udao.insertar(new Usuario(txtNombreUser.getText(), textFieldContrasena.getText(), cbRol.getSelectedItem().toString(), null));
								ListaProyectosAdmin listaProyectosAdmin = new ListaProyectosAdmin(u);
								listaProyectosAdmin.setVisible(true);
								dispose();
							} else {
								lblUsuarioExistente.setVisible(true);
							}
						} else {
							if(user.getUserName().equals(txtNombreUser.getText()) || userTemp == null) {
								udao.modificar(new Usuario(txtNombreUser.getText(), textFieldContrasena.getText(), cbRol.getSelectedItem().toString(), null), user.getUserName());
								ListaProyectosAdmin listaProyectosAdmin = new ListaProyectosAdmin(u);
								listaProyectosAdmin.setVisible(true);
								dispose();
							} else {
								lblUsuarioExistente.setVisible(true);
							}
						}
						
					} else {
						lblVacioPass.setVisible(true);
					}
				} else {
					lblVacio.setVisible(true);
				}
				
			}
		});


		JButton botonVolver = new JButton("Volver");
		botonVolver.setFont(new Font("Tahoma", Font.PLAIN, 20));
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaProyectosAdmin listaProyectosAdmin = new ListaProyectosAdmin(u);
				listaProyectosAdmin.setVisible(true);
				dispose();
			}
		});
		botonVolver.setBounds(400, 600, 250, 40);
		contentPane.add(botonVolver);
		
		
		
		
		
	}
}
