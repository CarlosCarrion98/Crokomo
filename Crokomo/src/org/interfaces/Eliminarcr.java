package org.interfaces;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.bd.dao.ClienteDAO;
import org.bd.dao.ProyectoDAO;
import org.bd.dao.RequisitoDAO;
import org.bd.dao.UsuarioDAO;
import org.objects.Cliente;
import org.objects.Proyecto;
import org.objects.Requisito;
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

public class Eliminarcr extends JFrame {

	private static final long serialVersionUID = 1L;
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
	public Eliminarcr(Usuario u, Proyecto p) {
		setIconImage(new ImageIcon("Assets/icono.png").getImage());
		ClienteDAO cdao = new ClienteDAO();
		RequisitoDAO rdao = new RequisitoDAO();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1920, 950);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel tituloListaClientes = new JLabel("Lista de clientes");
		tituloListaClientes.setBounds(150, 91, 250, 30);
		tituloListaClientes.setHorizontalAlignment(SwingConstants.CENTER);
		tituloListaClientes.setFont(new Font("Tahoma", Font.PLAIN, 25));
		contentPane.add(tituloListaClientes);
		
		JLabel tituloListaRequisitos = new JLabel("Lista de requisitos");
		tituloListaRequisitos.setBounds(920, 91, 250, 30);
		tituloListaRequisitos.setHorizontalAlignment(SwingConstants.CENTER);
		tituloListaRequisitos.setFont(new Font("Tahoma", Font.PLAIN, 25));
		contentPane.add(tituloListaRequisitos);
		
		JButton botonVolver = new JButton("Volver");
		botonVolver.setFont(new Font("Tahoma", Font.PLAIN, 25));
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProyectoInterfaz proyecto = new ProyectoInterfaz(u, p);
				proyecto.setVisible(true);
				dispose();
			}
		});
		botonVolver.setBounds(20, 10, 250, 40);
		contentPane.add(botonVolver);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(0, 0, 1920, 60);
		desktopPane.setBackground(Color.LIGHT_GRAY);
		contentPane.add(desktopPane);
		
		JButton botonEliminarCliente = new JButton("Eliminar");
		JButton botonEliminarRequisito = new JButton("Eliminar");
		
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
		
		JScrollPane scrollPaneClientes = new JScrollPane();
		scrollPaneClientes.setBounds(70, 151, 400, 500);
		contentPane.add(scrollPaneClientes);
		
		JScrollPane scrollPaneRequisitos = new JScrollPane();
		scrollPaneRequisitos.setBounds(840, 151, 400, 500);
		contentPane.add(scrollPaneRequisitos);
		
		ArrayList<Cliente> clientes = new ArrayList<Cliente>(cdao.listar());
		Cliente[] clientesprimitivos = new Cliente[clientes.size()];
		for(int i = 0; i < clientesprimitivos.length; i++) {
			clientesprimitivos[i] = clientes.get(i);
		}
		JList<Cliente> listaClientes = new JList<>(clientesprimitivos);
		listaClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaClientes.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(listaClientes.getSelectedIndex() != -1) {
					botonEliminarCliente.setEnabled(true);
				} else {
					botonEliminarCliente.setEnabled(false);
				}
			}
		});
		listaClientes.setFont(new Font("Tahoma", Font.PLAIN, 20));
		listaClientes.setModel(new AbstractListModel<Cliente>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Cliente[] values = clientesprimitivos;
			public int getSize() {
				return values.length;
			}
			public Cliente getElementAt(int index) {
				return values[index];
			}
		});
		scrollPaneClientes.setViewportView(listaClientes);
	
		ArrayList<Requisito> requisitos = new ArrayList<Requisito>(rdao.listar());
		Requisito[] requisitosPrimitivos = new Requisito[requisitos.size()];
		for(int i = 0; i < requisitosPrimitivos.length; i++) {
			requisitosPrimitivos[i] = requisitos.get(i);
		}
		JList<Requisito> listaRequisitos = new JList<>(requisitosPrimitivos);
		listaRequisitos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaRequisitos.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(listaRequisitos.getSelectedIndex() != -1) {
					botonEliminarRequisito.setEnabled(true);
				} else {
					botonEliminarRequisito.setEnabled(false);
				}
			}
		});
		listaRequisitos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		listaRequisitos.setModel(new AbstractListModel<Requisito>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Requisito[] values = requisitosPrimitivos;
			public int getSize() {
				return values.length;
			}
			public Requisito getElementAt(int index) {
				return values[index];
			}
		});
		scrollPaneRequisitos.setViewportView(listaRequisitos);
	
		botonEliminarCliente.setBounds(490, 365, 250, 40);
		botonEliminarCliente.setFont(new Font("Tahoma", Font.PLAIN, 25));
		botonEliminarCliente.setEnabled(false);
		botonEliminarCliente.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Cliente c = listaClientes.getSelectedValue();
				clientes.remove(listaClientes.getSelectedIndex());
				Cliente[] clientesPrimitivosTemp = new Cliente[clientes.size()];
				for(int i = 0; i < clientesPrimitivosTemp.length; i++) {
					clientesPrimitivosTemp[i] = clientes.get(i);
				}
				listaClientes.setModel(new AbstractListModel<Cliente>() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;
					Cliente[] values = clientesPrimitivosTemp;
					public int getSize() {
						return values.length;
					}
					public Cliente getElementAt(int index) {
						return values[index];
					}
				});
				scrollPaneClientes.setViewportView(listaClientes);
				cdao.eliminar(c);
			}
		});
		contentPane.add(botonEliminarCliente);
		
		botonEliminarRequisito.setBounds(1260, 365, 250, 40);
		botonEliminarRequisito.setFont(new Font("Tahoma", Font.PLAIN, 25));
		botonEliminarRequisito.setEnabled(false);
		botonEliminarRequisito.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Requisito requisito = listaRequisitos.getSelectedValue();
				requisitos.remove(listaRequisitos.getSelectedIndex());
				Requisito[] requisitosPrimitivosTemp = new Requisito[requisitos.size()];
				for(int i = 0; i < requisitosPrimitivosTemp.length; i++) {
					requisitosPrimitivosTemp[i] = requisitos.get(i);
				}
				listaRequisitos.setModel(new AbstractListModel<Requisito>() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;
					Requisito[] values = requisitosPrimitivosTemp;
					public int getSize() {
						return values.length;
					}
					public Requisito getElementAt(int index) {
						return values[index];
					}
				});
				scrollPaneRequisitos.setViewportView(listaRequisitos);
				rdao.eliminar(requisito);
			}
		});
		contentPane.add(botonEliminarRequisito);
		
	}
}
