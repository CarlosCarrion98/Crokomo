package org.interfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.objects.Proyecto;
import org.objects.Usuario;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class SolucionesInterfaz extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7905479548143806184L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					SolucionesInterfaz frame = new SolucionesInterfaz();
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
	public SolucionesInterfaz(Usuario u, Proyecto p) {
		setIconImage(new ImageIcon("Assets/icono.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelTitulo = new JLabel("Soluciones");
		labelTitulo.setBounds(317, 79, 159, 13);
		contentPane.add(labelTitulo);
		
		JButton botonVolver = new JButton("Volver al proyecto");
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		botonVolver.setBounds(287, 388, 139, 21);
		contentPane.add(botonVolver);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(228, 102, 258, 276);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Soluciones", "Requisitos", "Satisfacci\u00F3n"
			}
		));
		scrollPane.setViewportView(table);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.LIGHT_GRAY);
		desktopPane.setBounds(0, 0, 750, 43);
		contentPane.add(desktopPane);
		
		JButton botonProyectos = new JButton("Mis Proyectos");
		botonProyectos.setBounds(32, 11, 133, 23);
		desktopPane.add(botonProyectos);
		
		JButton botonCerrarSesion = new JButton("Cerrar Sesion");
		botonCerrarSesion.setBounds(608, 11, 116, 23);
		desktopPane.add(botonCerrarSesion);
		
		JLabel labelNombreProyecto = new JLabel((String) null);
		labelNombreProyecto.setHorizontalAlignment(SwingConstants.CENTER);
		labelNombreProyecto.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelNombreProyecto.setBounds(229, 0, 284, 43);
		desktopPane.add(labelNombreProyecto);
	}
}
