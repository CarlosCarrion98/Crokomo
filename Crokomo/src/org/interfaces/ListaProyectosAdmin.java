package org.interfaces;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.bd.dao.ProyectoDAO;
import org.bd.dao.UsuarioDAO;
import org.objects.Proyecto;
import org.objects.Usuario;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JDesktopPane;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class ListaProyectosAdmin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JButton botonModificarProyecto = new JButton("Modificar");
	JButton botonModificarUsuario = new JButton("Modificar");
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ListaProyectosUsuario frame = new ListaProyectosUsuario();
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
	public ListaProyectosAdmin(Usuario u) {
		setIconImage(new ImageIcon("Assets/icono.png").getImage());
		ProyectoDAO pdao = new ProyectoDAO();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1920, 950);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel tituloListaProyectos = new JLabel("Lista de proyectos");
		tituloListaProyectos.setBounds(150, 91, 250, 30);
		tituloListaProyectos.setHorizontalAlignment(SwingConstants.CENTER);
		tituloListaProyectos.setFont(new Font("Tahoma", Font.PLAIN, 25));
		contentPane.add(tituloListaProyectos);
		
		JLabel tituloListaUsuarios = new JLabel("Lista de usuarios");
		tituloListaUsuarios.setBounds(920, 91, 250, 30);
		tituloListaUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		tituloListaUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 25));
		contentPane.add(tituloListaUsuarios);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(0, 0, 1920, 60);
		desktopPane.setBackground(Color.LIGHT_GRAY);
		contentPane.add(desktopPane);
		
		
		
		JButton botonEliminarProyecto = new JButton("Eliminar");
		JButton botonEliminarUsuario = new JButton("Eliminar");
		
		JButton botonCerrarSesion = new JButton("Cerrar Sesion");
		botonCerrarSesion.setFont(new Font("Tahoma", Font.PLAIN, 25));
		botonCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.setVisible(true);
				dispose();
			}
			
		});
		botonCerrarSesion.setBounds(1260, 11, 250, 40);
		desktopPane.add(botonCerrarSesion);
		
		JLabel labelNombreUsuario = new JLabel("Hola " + u.getUserName());
		labelNombreUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		labelNombreUsuario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelNombreUsuario.setBounds(680, 25, 200, 25);
		desktopPane.add(labelNombreUsuario);
		
//		JList<Proyecto> list = new JList<Proyecto>();
//		list.setToolTipText("");
//		list.setFont(new Font("Tahoma", Font.PLAIN, 25));
//		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		list.setBounds(111, 176, 245, -193);
//		contentPane.add(list);
		
		JScrollPane scrollPaneProyectos = new JScrollPane();
		scrollPaneProyectos.setBounds(70, 151, 400, 500);
		contentPane.add(scrollPaneProyectos);
		
		JScrollPane scrollPaneUsuarios = new JScrollPane();
		scrollPaneUsuarios.setBounds(840, 151, 400, 500);
		contentPane.add(scrollPaneUsuarios);
		
		ArrayList<Proyecto> proyectos = new ArrayList<Proyecto>(pdao.listar());
		Proyecto[] proyectosPrimitivos = new Proyecto[proyectos.size()];
		for(int i = 0; i < proyectosPrimitivos.length; i++) {
			proyectosPrimitivos[i] = proyectos.get(i);
		}
		JList<Proyecto> listaProyectos = new JList<>(proyectosPrimitivos);
		listaProyectos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaProyectos.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(listaProyectos.getSelectedIndex() != -1) {
					botonModificarProyecto.setEnabled(true);
					botonEliminarProyecto.setEnabled(true);
				} else {
					botonModificarProyecto.setEnabled(false);
					botonEliminarProyecto.setEnabled(false);
				}
			}
		});
		listaProyectos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		listaProyectos.setModel(new AbstractListModel<Proyecto>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Proyecto[] values = proyectosPrimitivos;
			public int getSize() {
				return values.length;
			}
			public Proyecto getElementAt(int index) {
				return values[index];
			}
		});
		scrollPaneProyectos.setViewportView(listaProyectos);
		
		UsuarioDAO udao = new UsuarioDAO();
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>(udao.listar());
		Usuario[] usuariosPrimitivos = new Usuario[usuarios.size()];
		for(int i = 0; i < usuariosPrimitivos.length; i++) {
			usuariosPrimitivos[i] = usuarios.get(i);
		}
		JList<Usuario> listaUsuarios = new JList<>(usuariosPrimitivos);
		listaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaUsuarios.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(listaUsuarios.getSelectedIndex() != -1) {
					botonModificarUsuario.setEnabled(true);
					botonEliminarUsuario.setEnabled(true);
				} else {
					botonModificarUsuario.setEnabled(false);
					botonEliminarUsuario.setEnabled(false);
				}
			}
		});
		listaUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 20));
		listaUsuarios.setModel(new AbstractListModel<Usuario>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Usuario[] values = usuariosPrimitivos;
			public int getSize() {
				return values.length;
			}
			public Usuario getElementAt(int index) {
				return values[index];
			}
		});
		scrollPaneUsuarios.setViewportView(listaUsuarios);
		
		
		
		botonModificarProyecto.setBounds(490, 285, 250, 40);
		botonModificarProyecto.setFont(new Font("Tahoma", Font.PLAIN, 25));
		botonModificarProyecto.setEnabled(false);
		botonModificarProyecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listaProyectos.getSelectedIndex() != -1)
				{
					Proyecto p = listaProyectos.getSelectedValue();
					AddEditProyecto edit = new AddEditProyecto(u, p);
					edit.setVisible(true);
					dispose();
				}
			}
		});
		contentPane.add(botonModificarProyecto);
		
		botonModificarUsuario.setBounds(1260, 285, 250, 40);
		botonModificarUsuario.setFont(new Font("Tahoma", Font.PLAIN, 25));
		botonModificarUsuario.setEnabled(false);
		botonModificarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listaProyectos.getSelectedIndex() != -1)
				{
					Usuario user = listaUsuarios.getSelectedValue();
				}
			}
		});
		contentPane.add(botonModificarUsuario);
		
		botonEliminarProyecto.setBounds(490, 365, 250, 40);
		botonEliminarProyecto.setFont(new Font("Tahoma", Font.PLAIN, 25));
		botonEliminarProyecto.setEnabled(false);
		botonEliminarProyecto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Proyecto p = listaProyectos.getSelectedValue();
				proyectos.remove(listaProyectos.getSelectedIndex());
				Proyecto[] proyectosPrimitivosTemp = new Proyecto[proyectos.size()];
				for(int i = 0; i < proyectosPrimitivosTemp.length; i++) {
					proyectosPrimitivosTemp[i] = proyectos.get(i);
				}
				listaProyectos.setModel(new AbstractListModel<Proyecto>() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;
					Proyecto[] values = proyectosPrimitivosTemp;
					public int getSize() {
						return values.length;
					}
					public Proyecto getElementAt(int index) {
						return values[index];
					}
				});
				scrollPaneProyectos.setViewportView(listaProyectos);
				pdao.eliminar(p);
			}
		});
		contentPane.add(botonEliminarProyecto);
		
		botonEliminarUsuario.setBounds(1260, 365, 250, 40);
		botonEliminarUsuario.setFont(new Font("Tahoma", Font.PLAIN, 25));
		botonEliminarUsuario.setEnabled(false);
		botonEliminarUsuario.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Usuario user = listaUsuarios.getSelectedValue();
				usuarios.remove(listaUsuarios.getSelectedIndex());
				Usuario[] usuariosPrimitivosTemp = new Usuario[usuarios.size()];
				for(int i = 0; i < usuariosPrimitivosTemp.length; i++) {
					usuariosPrimitivosTemp[i] = usuarios.get(i);
				}
				listaUsuarios.setModel(new AbstractListModel<Usuario>() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;
					Usuario[] values = usuariosPrimitivosTemp;
					public int getSize() {
						return values.length;
					}
					public Usuario getElementAt(int index) {
						return values[index];
					}
				});
				scrollPaneUsuarios.setViewportView(listaUsuarios);
				udao.eliminar(user);
			}
		});
		contentPane.add(botonEliminarUsuario);
		
		JButton botonCrearProyecto = new JButton("Crear Proyecto");
		botonCrearProyecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddEditProyecto add = new AddEditProyecto(u, null);
				add.setVisible(true);
				dispose();
			}
		});
		botonCrearProyecto.setFont(new Font("Tahoma", Font.PLAIN, 20));
		botonCrearProyecto.setBounds(140, 700, 250, 40);
		contentPane.add(botonCrearProyecto);
		
		JButton botonCrearUsuario = new JButton("Crear Usuario");
		botonCrearUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		botonCrearUsuario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		botonCrearUsuario.setBounds(930, 700, 250, 40);
		contentPane.add(botonCrearUsuario);

	}
}
