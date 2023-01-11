package org.interfaces;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.bd.dao.ClienteDAO;
import org.objects.Cliente;
import org.objects.Proyecto;
import org.objects.Usuario;
import javax.swing.JScrollPane;

public class ProyectoEliminar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	public ProyectoEliminar(Usuario u, Proyecto p) {
		setIconImage(new ImageIcon("Assets/icono.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1920, 950);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(0, 0, 1920, 60);
		desktopPane.setBackground(Color.LIGHT_GRAY);
		contentPane.add(desktopPane);
		
		JButton botonProyectos = new JButton("Mis Proyectos");
		botonProyectos.setBounds(32, 11, 250, 40);
		botonProyectos.setFont(new Font("Tahoma", Font.PLAIN, 25));
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
		
		JPanel scrollPane = new JPanel();
		scrollPane.setBounds(200, 121, 550, 500);
		ClienteDAO clienteDao = new ClienteDAO();
		ArrayList<JCheckBox> checkBoxes = new ArrayList<>();
		for (Cliente cliente : clienteDao.listarPorProyecto(p)){
			JCheckBox checkBoxCliente = new JCheckBox(cliente.getNombreCliente());
			checkBoxes.add(checkBoxCliente);
		}
		//scrollPane.add(checkBoxes);
		
		contentPane.add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(829, 121, 550, 500);
		contentPane.add(scrollPane_1);
		
	}
}
