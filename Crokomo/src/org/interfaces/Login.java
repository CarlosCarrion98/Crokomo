package org.interfaces;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.bd.dao.UsuarioDAO;
import org.objects.Usuario;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5828689827346423414L;
	private Usuario usuarioLogin;
	private UsuarioDAO usuarioDAOlogin = new UsuarioDAO();
	private JPanel contentPane;
	private JTextField textFieldUsuario;
	private JLabel LabelContrasena;
	private JButton BotonLogin;
	private JLabel LabelIconoLogin;
	private JPasswordField textFieldContrasena;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(400, 145, 240, 20);
		contentPane.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		JLabel LabelUsuario = new JLabel("Usuario");
		LabelUsuario.setBounds(400, 120, 121, 14);
		contentPane.add(LabelUsuario);
		
		LabelContrasena = new JLabel("Contrasena");
		LabelContrasena.setBounds(400, 208, 121, 14);
		contentPane.add(LabelContrasena);
		
		LabelIconoLogin = new JLabel("IconoLogin");
		LabelIconoLogin.setBounds(50, 75, 325, 325);
		contentPane.add(LabelIconoLogin);
		
		ImageIcon logo = new ImageIcon("Assets/icono.png");
		Icon icono = new ImageIcon(logo.getImage().getScaledInstance(LabelIconoLogin.getWidth(), LabelIconoLogin.getHeight(), Image.SCALE_DEFAULT));
		LabelIconoLogin.setIcon(icono);
		
		JLabel labelAvisoLogin = new JLabel("");
		labelAvisoLogin.setForeground(Color.RED);
		labelAvisoLogin.setBounds(400, 362, 240, 14);
		contentPane.add(labelAvisoLogin);
		
		textFieldContrasena = new JPasswordField();
		textFieldContrasena.setBounds(400, 233, 240, 20);
		contentPane.add(textFieldContrasena);
		
		BotonLogin = new JButton("Iniciar Sesion");
		BotonLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = String.valueOf(textFieldContrasena.getPassword());
				usuarioLogin = new Usuario(textFieldUsuario.getText(),password,"",null);
				if(textFieldUsuario.getText().isBlank() || (password.isBlank()))
					labelAvisoLogin.setText("Usuario y / o contrasena vacios");
				else if (!usuarioDAOlogin.login(usuarioLogin).equals("Failed"))
						{
					PaginaPrincipal pagPrincipal = new PaginaPrincipal(usuarioLogin);
					pagPrincipal.setVisible(true);
					dispose();
				} else
				{
					labelAvisoLogin.setText("Usuario y / o contrasena erroneos");
				}
			}
		});
		BotonLogin.setBounds(457, 279, 121, 23);
		contentPane.add(BotonLogin);
	
	}
}
