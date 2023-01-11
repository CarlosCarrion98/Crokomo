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
import java.awt.Font;

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
		setIconImage(new ImageIcon("src/Assets/icono.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1920, 950);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldUsuario.setBounds(550, 565, 450, 40);
		contentPane.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		JLabel LabelUsuario = new JLabel("Usuario");
		LabelUsuario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		LabelUsuario.setBounds(718, 515, 120, 30);
		contentPane.add(LabelUsuario);
		
		LabelContrasena = new JLabel("Contrasena");
		LabelContrasena.setFont(new Font("Tahoma", Font.PLAIN, 20));
		LabelContrasena.setBounds(718, 640, 120, 30);
		contentPane.add(LabelContrasena);
		
		LabelIconoLogin = new JLabel("IconoLogin");
		LabelIconoLogin.setBounds(563, 35, 400, 400);
		contentPane.add(LabelIconoLogin);
		
		ImageIcon logo = new ImageIcon("src/Assets/icono.png");
		Icon icono = new ImageIcon(logo.getImage().getScaledInstance(LabelIconoLogin.getWidth(), LabelIconoLogin.getHeight(), Image.SCALE_DEFAULT));
		LabelIconoLogin.setIcon(icono);
		
		JLabel labelAvisoLogin = new JLabel("");
		labelAvisoLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelAvisoLogin.setForeground(Color.RED);
		labelAvisoLogin.setBounds(614, 446, 450, 40);
		contentPane.add(labelAvisoLogin);
		
		textFieldContrasena = new JPasswordField();
		textFieldContrasena.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldContrasena.setBounds(550, 685, 450, 40);
		contentPane.add(textFieldContrasena);
		
		BotonLogin = new JButton("Iniciar Sesion");
		BotonLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		BotonLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = String.valueOf(textFieldContrasena.getPassword());
				usuarioLogin = new Usuario(textFieldUsuario.getText(),password,"",null);
				if(textFieldUsuario.getText().isBlank() || (password.isBlank()))
					labelAvisoLogin.setText("Usuario y / o contrasena vacios");
				else if (!usuarioDAOlogin.login(usuarioLogin).equals("Failed") )
						{
					if (usuarioDAOlogin.login(usuarioLogin).equals("user")){
						ListaProyectosUsuario misProyectos = new ListaProyectosUsuario(usuarioLogin);
						misProyectos.setVisible(true);
					}else {
						ListaProyectosAdmin misProyectos = new ListaProyectosAdmin(usuarioLogin);
						misProyectos.setVisible(true);
					}
					dispose();
				} else
				{
					labelAvisoLogin.setText("Usuario y / o contrasena erroneos");
				}
			}
		});
		BotonLogin.setBounds(650, 740, 250, 40);
		contentPane.add(BotonLogin);
	
	}
}
