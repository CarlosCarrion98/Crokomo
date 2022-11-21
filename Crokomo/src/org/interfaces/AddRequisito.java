package org.interfaces;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.objects.Proyecto;
import org.objects.Usuario;

import javax.swing.JLabel;
import javax.swing.JDesktopPane;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddRequisito extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5643047139612025173L;
	private JPanel contentPane;
	private JTextField txtNombreReq;
	private JTextField txtEsfuerzo;

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
	public AddRequisito(Usuario u, Proyecto p) {
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
		
		JLabel labelAddRequisito = new JLabel("Añadir Requisito");
		labelAddRequisito.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelAddRequisito.setHorizontalAlignment(SwingConstants.CENTER);
		labelAddRequisito.setBounds(259, 0, 284, 43);
		desktopPane.add(labelAddRequisito);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre de requisito");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(346, 148, 116, 14);
		contentPane.add(lblNewLabel_1);
		
		txtNombreReq = new JTextField();
		txtNombreReq.setBounds(276, 173, 255, 20);
		contentPane.add(txtNombreReq);
		txtNombreReq.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Esfuerzo");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(346, 242, 116, 14);
		contentPane.add(lblNewLabel_2);
		
		txtEsfuerzo = new JTextField();
		txtEsfuerzo.setBounds(276, 267, 255, 20);
		contentPane.add(txtEsfuerzo);
		txtEsfuerzo.setColumns(10);
		
		JButton botonAddReq = new JButton("Añadir");
		botonAddReq.setBounds(433, 341, 98, 23);
		contentPane.add(botonAddReq);
		
		JButton botonVolver = new JButton("Volver");
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProyectoInterfaz proyecto = new ProyectoInterfaz(u, p);
				proyecto.setVisible(true);
				dispose();
			}
		});
		botonVolver.setBounds(276, 341, 98, 23);
		contentPane.add(botonVolver);
	}
}
