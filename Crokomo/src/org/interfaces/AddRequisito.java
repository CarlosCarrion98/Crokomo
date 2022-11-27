package org.interfaces;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.bd.dao.ClienteDAO;
import org.bd.dao.ClienteRequisitoDAO;
import org.bd.dao.RequisitoDAO;
import org.objects.Cliente;
import org.objects.Proyecto;
import org.objects.Requisito;
import org.objects.Usuario;
import org.objects.relations.ClienteRequisito;

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

public class AddRequisito extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5643047139612025173L;
	private JPanel contentPane;
	private JTextField txtNombreReq;
	private JTextField txtEsfuerzo;
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
	public AddRequisito(Usuario u, Proyecto p) {
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

		JLabel labelAddRequisito = new JLabel("Añadir Requisito");
		labelAddRequisito.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelAddRequisito.setHorizontalAlignment(SwingConstants.CENTER);
		labelAddRequisito.setBounds(259, 0, 284, 43);
		desktopPane.add(labelAddRequisito);

		JLabel lblNewLabel_1 = new JLabel("Nombre de requisito");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(346, 53, 116, 14);
		contentPane.add(lblNewLabel_1);

		txtNombreReq = new JTextField();
		txtNombreReq.setBounds(276, 78, 255, 20);
		contentPane.add(txtNombreReq);
		txtNombreReq.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Esfuerzo");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(346, 147, 116, 14);
		contentPane.add(lblNewLabel_2);

		txtEsfuerzo = new JTextField();
		txtEsfuerzo.setBounds(276, 172, 255, 20);
		contentPane.add(txtEsfuerzo);
		txtEsfuerzo.setColumns(10);

		JLabel lblVacio = new JLabel("Nombre y/o Esfuerzo no pueden estar vacíos");
		lblVacio.setHorizontalAlignment(SwingConstants.CENTER);
		lblVacio.setForeground(Color.RED);
		lblVacio.setBounds(276, 202, 255, 14);
		contentPane.add(lblVacio);

		JLabel lblErrorNumerico = new JLabel("Esfuerzo debe ser un valor numérico");
		lblErrorNumerico.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrorNumerico.setForeground(Color.RED);
		lblErrorNumerico.setBounds(276, 202, 241, 13);
		contentPane.add(lblErrorNumerico);
		lblVacio.setVisible(false);
		lblErrorNumerico.setVisible(false);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(276, 251, 255, 0);
		contentPane.add(scrollPane);

		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		ClienteDAO cdao = new ClienteDAO();
		ArrayList<JTextField> textFields = new ArrayList<>();
		ArrayList<JCheckBox> checkBoxes = new ArrayList<>();
		for(Cliente c : cdao.listarPorProyecto(p)) {
			JCheckBox checkBoxCliente = new JCheckBox(c.getNombreCliente());
			checkBoxes.add(checkBoxCliente);

			textField = new JTextField();
			textField.setEnabled(false);
			textFields.add(textField);
			checkBoxCliente.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if(textFields.get(checkBoxes.lastIndexOf(checkBoxCliente)).isEnabled())
						textFields.get(checkBoxes.lastIndexOf(checkBoxCliente)).setEnabled(false);
					else {
						textFields.get(checkBoxes.lastIndexOf(checkBoxCliente)).setEnabled(true);
					}

				}

			});
			panel.add(checkBoxCliente);
			panel.add(textField);
			if(scrollPane.getHeight() < 150) {
				scrollPane.setBounds(scrollPane.getX(), scrollPane.getY(), scrollPane.getWidth(), scrollPane.getHeight() + 25);
			}
		}

		JButton botonAddReq = new JButton("Añadir");
		botonAddReq.setBounds(433, 412, 98, 23);
		contentPane.add(botonAddReq);
		botonAddReq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblVacio.setVisible(false);
				lblErrorNumerico.setVisible(false);

				if(txtNombreReq.getText().isBlank() || txtEsfuerzo.getText().isBlank()) {
					lblVacio.setVisible(true);
				} else {
					try {
						int esfuerzo = Integer.parseInt(txtEsfuerzo.getText());
						RequisitoDAO rdao = new RequisitoDAO();
						rdao.insertar(new Requisito(0, esfuerzo, txtNombreReq.getText(), null, null));
						Requisito temp = rdao.obtenerRequisitoPorNombre(txtNombreReq.getText());
						ClienteRequisitoDAO crdao = new ClienteRequisitoDAO();
						for(JCheckBox jcb : checkBoxes) {
							if(jcb.isSelected()) {
								Cliente c = cdao.obtenerClientePorNombre(jcb.getText());
								if(jcb.getText().equals(c.getNombreCliente()))
									crdao.insertar(new ClienteRequisito(c.getIdCliente(), temp.getIdRequisito(), 
											Integer.parseInt(textFields.get(checkBoxes.indexOf(jcb)).getText())));

							}
						}
						ProyectoInterfaz proyecto = new ProyectoInterfaz(u, p);
						proyecto.setVisible(true);
						dispose();
					} catch (NumberFormatException nfe){
						lblErrorNumerico.setVisible(true);
					}
				}

			}
		});

		textField.setColumns(10);

		JButton botonVolver = new JButton("Volver");
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProyectoInterfaz proyecto = new ProyectoInterfaz(u, p);
				proyecto.setVisible(true);
				dispose();
			}
		});
		botonVolver.setBounds(276, 412, 98, 23);
		contentPane.add(botonVolver);

	}
}
