package org.interfaces;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.objects.Usuario;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JDesktopPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PaginaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JDesktopPane desktopPane = new JDesktopPane();
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					PaginaPrincipal frame = new PaginaPrincipal();
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
	public PaginaPrincipal(Usuario u) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel labelIconoLogin = new JLabel("IconoLogin");
		labelIconoLogin.setBackground(Color.DARK_GRAY);
		labelIconoLogin.setHorizontalAlignment(SwingConstants.CENTER);
		labelIconoLogin.setBounds(210, 80, 300, 300);
		contentPane.add(labelIconoLogin);
		
		ImageIcon logo = new ImageIcon("Assets/icono.png");
		Icon icono = new ImageIcon(logo.getImage().getScaledInstance(labelIconoLogin.getWidth(), labelIconoLogin.getHeight(), Image.SCALE_DEFAULT));
		labelIconoLogin.setIcon(icono);
		
		JLabel lblBienvenidoACrokomo = new JLabel("Bienvenido a Crokomo");
		lblBienvenidoACrokomo.setBounds(299, 391, 211, 14);
		contentPane.add(lblBienvenidoACrokomo);
		desktopPane.setBackground(Color.LIGHT_GRAY);
		desktopPane.setBounds(0, 0, 734, 43);
		contentPane.add(desktopPane);
		
		JButton botonProyectos = new JButton("Mis Proyectos");
		botonProyectos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaProyectosUsuario listaproyectos = new ListaProyectosUsuario(u);
				listaproyectos.setVisible(true);
				dispose();
			}
		});
		botonProyectos.setBounds(32, 11, 133, 23);
		desktopPane.add(botonProyectos);
		
		JButton botonCerrarSesion = new JButton("Cerrar Sesion");
		botonCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.setVisible(true);
				dispose();
			}
		});
		botonCerrarSesion.setBounds(608, 11, 116, 23);
		desktopPane.add(botonCerrarSesion);
		
	}
}
