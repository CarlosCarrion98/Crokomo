package org.interfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import org.bd.dao.ClienteDAO;
import org.bd.dao.ClienteRequisitoDAO;
import org.bd.dao.RequisitoDAO;
import org.objects.Cliente;
import org.objects.Proyecto;
import org.objects.Requisito;
import org.objects.Usuario;
import org.objects.relations.ClienteRequisito;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JDesktopPane;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ScrollPaneConstants;

public class ProyectoInterfaz extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tablaRequisitosUsuarios;
	private JTextField textFieldEsfuerzo;
	private JTable nombresClientesTabla;

	/**
	 * Launch the application.
	 */
	//	public static void main(String[] args) {
	//		EventQueue.invokeLater(new Runnable() {
	//			public void run() {
	//				try {
	//					ProyectoInterfaz frame = new ProyectoInterfaz();
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
	public ProyectoInterfaz(Usuario u, Proyecto p) {
		setIconImage(new ImageIcon("Assets/icono.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel Requisitos = new JLabel("Requisitos");
		Requisitos.setHorizontalAlignment(SwingConstants.CENTER);
		Requisitos.setBounds(221, 63, 79, 14);
		contentPane.add(Requisitos);

		JLabel lblNewLabel = new JLabel("Clientes");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(53, 217, 69, 14);
		contentPane.add(lblNewLabel);

		JButton addCliente = new JButton("Añadir");
		addCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddCliente addCliente = new AddCliente(u, p);
				addCliente.setVisible(true);
				dispose();
			}
		});
		addCliente.setBounds(33, 234, 89, 23);
		contentPane.add(addCliente);

		JButton addRequisito = new JButton("Añadir");
		addRequisito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddRequisito addRequisito = new AddRequisito(u, p);
				addRequisito.setVisible(true);
				dispose();
			}
		});
		addRequisito.setBounds(297, 59, 89, 23);
		contentPane.add(addRequisito);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(213, 87, 399, 288);
		contentPane.add(scrollPane);
		

		ClienteDAO cdao = new ClienteDAO();
		ClienteRequisitoDAO crdao = new ClienteRequisitoDAO();
		RequisitoDAO rdao = new RequisitoDAO();
		int contador = 0;
		ArrayList<Cliente> clientesProyecto = cdao.listarPorProyecto(p);
		String[][] valores = new String[clientesProyecto.size()][];
		String[][] nombresClientes = new String[clientesProyecto.size()][];
		ArrayList<Requisito> requisitosTabla = new ArrayList<>(rdao.listarPorProyecto(p));
		String[] nombresRequisitos = new String[requisitosTabla.size()];
		for(int i = 0; i < requisitosTabla.size(); i++) {
			nombresRequisitos[i] = requisitosTabla.get(i).getNombreRequisito();
		}

		for(Cliente c : clientesProyecto) {
			nombresClientes[contador] = new String[1];
			nombresClientes[contador][0] = c.getNombreCliente();
			valores[contador] = new String[requisitosTabla.size()];
			for(int i = 0; i < requisitosTabla.size(); i++) {
				valores[contador][i] = crdao.listarPorClienteYRequisito(c, requisitosTabla.get(i)).toString();
			}
			contador++;
		}
		
		tablaRequisitosUsuarios = new JTable();
		tablaRequisitosUsuarios.setBorder(null);
		tablaRequisitosUsuarios.setModel(new DefaultTableModel(valores, nombresRequisitos));
		scrollPane.setViewportView(tablaRequisitosUsuarios);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(null);
		scrollPane_1.setBounds(142, 87, 73, 288);
		contentPane.add(scrollPane_1);
		
		nombresClientesTabla = new JTable();
		nombresClientesTabla.setBorder(null);
		nombresClientesTabla.setModel(new DefaultTableModel(nombresClientes, new String[]{"Clientes"}));
		scrollPane_1.setViewportView(nombresClientesTabla);
//		rowHeaderTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//		rowHeaderTable.setSize(new Dimension(50, tablaRequisitosUsuarios.getHeight()));
		
//		scrollPane.getRowHeader().setSize(new Dimension(50, tablaRequisitosUsuarios.getHeight()));
//		scrollPane.getViewport().setLocation(50, 0);
		
		
		textFieldEsfuerzo = new JTextField();
		textFieldEsfuerzo.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldEsfuerzo.setText("15");
		textFieldEsfuerzo.setBounds(681, 59, 30, 20);
		contentPane.add(textFieldEsfuerzo);
		textFieldEsfuerzo.setColumns(10);

		JDesktopPane desktopPane = new JDesktopPane();
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

		JLabel labelNombreProyecto = new JLabel(p.getNombreProyecto());
		labelNombreProyecto.setHorizontalAlignment(SwingConstants.CENTER);
		labelNombreProyecto.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelNombreProyecto.setBounds(229, 0, 284, 43);
		desktopPane.add(labelNombreProyecto);

		JLabel labelEsfuerzo = new JLabel("Esfuerzo maximo");
		labelEsfuerzo.setBounds(559, 62, 112, 14);
		contentPane.add(labelEsfuerzo);

		JButton botonOrganizar = new JButton("Mostrar Soluciones");
		botonOrganizar.setBounds(30, 393, 148, 23);
		contentPane.add(botonOrganizar);

		JButton botonDetalles = new JButton("Detalles");
		botonDetalles.setBounds(336, 427, 89, 23);
		contentPane.add(botonDetalles);
		
		
	}
	
	public void resizeColumnWidth(JTable table) {
	    final TableColumnModel columnModel = table.getColumnModel();
	    for (int column = 0; column < table.getColumnCount(); column++) {
	        int width = 15; // Min width
	        for (int row = 0; row < table.getRowCount(); row++) {
	            TableCellRenderer renderer = table.getCellRenderer(row, column);
	            Component comp = table.prepareRenderer(renderer, row, column);
	            width = Math.max(comp.getPreferredSize().width +1 , width);
	        }
	        if(width > 300)
	            width=300;
	        columnModel.getColumn(column).setPreferredWidth(width);
	    }
	}
}
