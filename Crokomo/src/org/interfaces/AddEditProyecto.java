package org.interfaces;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.bd.dao.ProyectoDAO;
import org.bd.dao.UsuarioDAO;
import org.bd.dao.UsuarioProyectoDAO;
import org.objects.Proyecto;
import org.objects.Usuario;
import org.objects.relations.UsuarioProyecto;

import javax.swing.JLabel;
import javax.swing.JDesktopPane;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import java.awt.GridLayout;

public class AddEditProyecto extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5643047139612025173L;
	private JPanel contentPane;
	private JTextField txtNombrePro;

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
	public AddEditProyecto(Usuario u, Proyecto p) {
		setIconImage(new ImageIcon(getClass().getClassLoader().getResource("icono.png")).getImage());
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
		JLabel labelAddProyecto = new JLabel("Modificar Proyecto");
		if(p == null)
			labelAddProyecto = new JLabel("Añadir Proyecto");
		labelAddProyecto.setFont(new Font("Tahoma", Font.PLAIN, 25));
		labelAddProyecto.setHorizontalAlignment(SwingConstants.CENTER);
		labelAddProyecto.setBounds(598, 10, 284, 43);
		desktopPane.add(labelAddProyecto);

		JLabel lblNewLabel_1 = new JLabel("Nombre del proyecto");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(632, 129, 210, 23);
		contentPane.add(lblNewLabel_1);

		txtNombrePro = new JTextField();
		txtNombrePro.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtNombrePro.setBounds(590, 162, 300, 30);
		if(p != null) txtNombrePro.setText(p.getNombreProyecto());
		contentPane.add(txtNombrePro);
		txtNombrePro.setColumns(10);

		JLabel lblVacio = new JLabel("Nombre del proyecto no puede estar vacío");
		lblVacio.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblVacio.setHorizontalAlignment(SwingConstants.CENTER);
		lblVacio.setForeground(Color.RED);
		lblVacio.setBounds(545, 202, 401, 39);
		lblVacio.setVisible(false);
		contentPane.add(lblVacio);

		JLabel lblProyectoExistente = new JLabel("El nombre del proyecto ya existe");
		lblProyectoExistente.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblProyectoExistente.setHorizontalAlignment(SwingConstants.CENTER);
		lblProyectoExistente.setForeground(Color.RED);
		lblProyectoExistente.setBounds(576, 202, 328, 39);
		lblProyectoExistente.setVisible(false);
		contentPane.add(lblProyectoExistente);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(660, 280, 150, 0);
		contentPane.add(scrollPane);

		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setBounds(610, 277, 236, 0);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		UsuarioDAO udao = new UsuarioDAO();
		UsuarioProyectoDAO updao = new UsuarioProyectoDAO();
		ArrayList<JCheckBox> checkBoxes = new ArrayList<>();
		for(Usuario user : udao.listar()) {
			JCheckBox checkBoxUsuario = new JCheckBox(user.getUserName());
			checkBoxUsuario.setFont(new Font("Tahoma", Font.PLAIN, 20));
			if(p != null) {
				if(updao.existeRelacion(p, user)) {
					checkBoxUsuario.setSelected(true);
				}
			}

			checkBoxes.add(checkBoxUsuario);

			panel.add(checkBoxUsuario);
			if(scrollPane.getHeight() < 240) {
				scrollPane.setBounds(scrollPane.getX(), scrollPane.getY(), scrollPane.getWidth(), scrollPane.getHeight() + 40);
			}
		}

		JButton botonAddPro = new JButton("Modificar");
		botonAddPro.setFont(new Font("Tahoma", Font.PLAIN, 20));
		if(p == null) {
			botonAddPro = new JButton("Añadir");
			botonAddPro.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		botonAddPro.setBounds(825, 600, 250, 40);
		contentPane.add(botonAddPro);
		botonAddPro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblVacio.setVisible(false);

				lblProyectoExistente.setVisible(false);
				ProyectoDAO pdao = new ProyectoDAO();
				Proyecto ptemp = pdao.obtenerProyectoPorNombre(txtNombrePro.getText());
				if(!txtNombrePro.getText().isBlank()) {

					if(p != null) {
						if(!p.getNombreProyecto().equals(txtNombrePro.getText())) {
							p.setNombreProyecto(txtNombrePro.getText());
							if(ptemp == null) {
								pdao.modificar(p);
							} else {
								lblProyectoExistente.setVisible(true);
							}
							
						}
						for(JCheckBox jcb : checkBoxes) {
							if(jcb.isSelected()) {
								Usuario user = udao.obtenerUsuarioPorNombre(jcb.getText());
								if(!updao.existeRelacion(p, user)) {
									if(jcb.getText().equals(user.getUserName()))
										updao.insertar(new UsuarioProyecto(p.getIdProyecto(), user.getUserName()));
								}
								

							} else {
								if(updao.existeRelacion(p, udao.obtenerUsuarioPorNombre(jcb.getText()))) {
									updao.eliminar(new UsuarioProyecto(p.getIdProyecto(), jcb.getText()));
								}

							}
						}
						if(ptemp == null || p.getNombreProyecto().equals(txtNombrePro.getText())) {
							ListaProyectosAdmin listaProyectosAdmin = new ListaProyectosAdmin(u);
							listaProyectosAdmin.setVisible(true);
							dispose();
						}
						
					} else {

						if(ptemp == null) {
							pdao.insertar(new Proyecto(0, txtNombrePro.getText(), null));
							Proyecto temp = pdao.obtenerProyectoPorNombre(txtNombrePro.getText());
							for(JCheckBox jcb : checkBoxes) {
								if(jcb.isSelected()) {
									Usuario user = udao.obtenerUsuarioPorNombre(jcb.getText());
									if(jcb.getText().equals(user.getUserName()))
										updao.insertar(new UsuarioProyecto(temp.getIdProyecto(), user.getUserName()));

								}
							}
							ListaProyectosAdmin listaProyectosAdmin = new ListaProyectosAdmin(u);
							listaProyectosAdmin.setVisible(true);
							dispose();
						} else {
							lblProyectoExistente.setVisible(true);
						}
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
