package org.interfaces;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.bd.dao.ClienteDAO;
import org.bd.dao.ClienteRequisitoDAO;
import org.bd.dao.ProyectoDAO;
import org.bd.dao.RequisitoDAO;
import org.bd.dao.UsuarioDAO;
import org.bd.dao.UsuarioProyectoDAO;
import org.objects.Cliente;
import org.objects.Proyecto;
import org.objects.Requisito;
import org.objects.Usuario;
import org.objects.relations.ClienteRequisito;
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
	private JTextField textField;

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
		setIconImage(new ImageIcon("Assets/icono.png").getImage());
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
		JLabel labelAddProyecto = new JLabel("Modificar Proyecto");
		if(p == null)
			labelAddProyecto = new JLabel("Añadir Proyecto");
		labelAddProyecto.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelAddProyecto.setHorizontalAlignment(SwingConstants.CENTER);
		labelAddProyecto.setBounds(259, 0, 284, 43);
		desktopPane.add(labelAddProyecto);

		JLabel lblNewLabel_1 = new JLabel("Nombre del proyecto");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(346, 112, 116, 14);
		contentPane.add(lblNewLabel_1);

		txtNombrePro = new JTextField();
		txtNombrePro.setBounds(276, 137, 255, 20);
		if(p != null) txtNombrePro.setText(p.getNombreProyecto());
		contentPane.add(txtNombrePro);
		txtNombrePro.setColumns(10);

		JLabel lblVacio = new JLabel("Nombre del proyecto no puede estar vacío");
		lblVacio.setHorizontalAlignment(SwingConstants.CENTER);
		lblVacio.setForeground(Color.RED);
		lblVacio.setBounds(276, 202, 255, 14);
		contentPane.add(lblVacio);
		
		JLabel lblProyectoExistente = new JLabel("El nombre del proyecto ya existe");
		lblProyectoExistente.setHorizontalAlignment(SwingConstants.CENTER);
		lblProyectoExistente.setForeground(Color.RED);
		lblProyectoExistente.setBounds(276, 202, 255, 14);
		contentPane.add(lblProyectoExistente);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(276, 251, 255, 0);
		contentPane.add(scrollPane);

		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		UsuarioDAO udao = new UsuarioDAO();
		UsuarioProyectoDAO updao = new UsuarioProyectoDAO();
		ArrayList<JCheckBox> checkBoxes = new ArrayList<>();
		for(Usuario user : udao.listar()) {
			JCheckBox checkBoxUsuario = new JCheckBox(user.getUserName());
			if(updao.existeRelacion(p, user)) {
				checkBoxUsuario.setSelected(true);
			}
			checkBoxes.add(checkBoxUsuario);
			
			
			panel.add(checkBoxUsuario);
			if(scrollPane.getHeight() < 150) {
				scrollPane.setBounds(scrollPane.getX(), scrollPane.getY(), scrollPane.getWidth(), scrollPane.getHeight() + 25);
			}
		}

		JButton botonAddPro = new JButton("Modificar");
		if(p == null) {
			botonAddPro = new JButton("Añadir");
		}
		botonAddPro.setBounds(433, 412, 98, 23);
		contentPane.add(botonAddPro);
		botonAddPro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblVacio.setVisible(false);
				
				lblProyectoExistente.setVisible(false);

				if(!txtNombrePro.getText().isBlank()) {
					ProyectoDAO pdao = new ProyectoDAO();
					if(p != null) {
						if(p.getNombreProyecto() != txtNombrePro.getText()) {
							p.setNombreProyecto(txtNombrePro.getText());
							pdao.modificar(p);
						}
						for(JCheckBox jcb : checkBoxes) {
							if(jcb.isSelected()) {
								Usuario user = udao.obtenerUsuarioPorNombre(jcb.getText());
								if(jcb.getText().equals(user.getUserName()))
									updao.insertar(new UsuarioProyecto(p.getIdProyecto(), user.getUserName()));

							} else {
								if(updao.existeRelacion(p, udao.obtenerUsuarioPorNombre(jcb.getText()))) {
									updao.eliminar(new UsuarioProyecto(p.getIdProyecto(), jcb.getText()));
								}
								
							}
						}
						ListaProyectosAdmin listaProyectosAdmin = new ListaProyectosAdmin(u);
						listaProyectosAdmin.setVisible(true);
						dispose();
					} else {
						Proyecto ptemp = pdao.obtenerProyectoPorNombre(txtNombrePro.getText());
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

		textField.setColumns(10);

		JButton botonVolver = new JButton("Volver");
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaProyectosAdmin listaProyectosAdmin = new ListaProyectosAdmin(u);
				listaProyectosAdmin.setVisible(true);
				dispose();
			}
		});
		botonVolver.setBounds(276, 412, 98, 23);
		contentPane.add(botonVolver);

	}
}
