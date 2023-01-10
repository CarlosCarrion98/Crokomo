package org.interfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.bd.dao.UsuarioProyectoDAO;
import org.objects.Proyecto;
import org.objects.Usuario;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JDesktopPane;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class ListaProyectosUsuario extends JFrame {

	private JPanel contentPane;

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
	public ListaProyectosUsuario(Usuario u) {
		setIconImage(new ImageIcon("Assets/icono.png").getImage());
		UsuarioProyectoDAO updao = new UsuarioProyectoDAO();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1920, 950);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel tituloListaProyectos = new JLabel("Lista de proyectos");
		tituloListaProyectos.setBounds(660, 91, 250, 30);
		tituloListaProyectos.setHorizontalAlignment(SwingConstants.CENTER);
		tituloListaProyectos.setFont(new Font("Tahoma", Font.PLAIN, 25));
		contentPane.add(tituloListaProyectos);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(0, 0, 1920, 60);
		desktopPane.setBackground(Color.LIGHT_GRAY);
		contentPane.add(desktopPane);
		
		JButton botonProyectos = new JButton("Mis Proyectos");
		botonProyectos.setBounds(32, 11, 250, 40);
		botonProyectos.setFont(new Font("Tahoma", Font.PLAIN, 25));
		botonProyectos.setEnabled(false);
		desktopPane.add(botonProyectos);
		
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
		
		JLabel labelNombreUsuario = new JLabel(u.getUserName());
		labelNombreUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		labelNombreUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelNombreUsuario.setBounds(721, 25, 100, 25);
		desktopPane.add(labelNombreUsuario);
		
		JList<Proyecto> list = new JList<Proyecto>();
		list.setToolTipText("");
		list.setFont(new Font("Tahoma", Font.PLAIN, 25));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(411, 176, 245, -193);
		contentPane.add(list);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(600, 151, 400, 500);
		contentPane.add(scrollPane);
		
		JLabel labelAviso = new JLabel("");
		labelAviso.setHorizontalAlignment(SwingConstants.CENTER);
		labelAviso.setForeground(Color.RED);
		labelAviso.setBounds(225, 410, 269, 14);
		contentPane.add(labelAviso);
		
		ArrayList<Proyecto> proyectos = new ArrayList<Proyecto>(updao.listarProyectosPorUsuario(u));
		Proyecto[] proyectosPrimitivos = new Proyecto[proyectos.size()];
		for(int i = 0; i < proyectosPrimitivos.length; i++) {
			proyectosPrimitivos[i] = proyectos.get(i);
		}
		JList<Proyecto> listaProyectos = new JList<>(proyectosPrimitivos);
		listaProyectos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
		scrollPane.setViewportView(listaProyectos);
		
		JButton botonSeleccionarProyecto = new JButton("Seleccionar");
		botonSeleccionarProyecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listaProyectos.getSelectedIndex() != -1)
				{
					Proyecto p = listaProyectos.getSelectedValue();
					ProyectoInterfaz proyectoInterfaz = new ProyectoInterfaz(u, p);
					proyectoInterfaz.setVisible(true);
					dispose();
				} else
				{
					labelAviso.setText("Seleccione un proyecto");
				}
			}
		});
		botonSeleccionarProyecto.setBounds(650, 700, 250, 40);
		contentPane.add(botonSeleccionarProyecto);

	}
}
