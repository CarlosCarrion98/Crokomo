package org.interfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
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
		setBounds(0, 0, 1920, 950);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel Requisitos = new JLabel("Requisitos");
		Requisitos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Requisitos.setHorizontalAlignment(SwingConstants.CENTER);
		Requisitos.setBounds(721, 91, 100, 25);
		contentPane.add(Requisitos);

		JLabel lblNewLabel = new JLabel("Clientes");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(77, 423, 100, 25);
		contentPane.add(lblNewLabel);

		JButton addCliente = new JButton("Añadir");
		addCliente.setFont(new Font("Tahoma", Font.PLAIN, 20));
		addCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddCliente addCliente = new AddCliente(u, p);
				addCliente.setVisible(true);
				dispose();
			}
		});
		addCliente.setBounds(10, 458, 250, 40);
		contentPane.add(addCliente);

		JButton addRequisito = new JButton("Añadir");
		addRequisito.setFont(new Font("Tahoma", Font.PLAIN, 20));
		addRequisito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddRequisito addRequisito = new AddRequisito(u, p);
				addRequisito.setVisible(true);
				dispose();
			}
		});
		addRequisito.setBounds(650, 120, 250, 40);
		contentPane.add(addRequisito);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(411, 176, 813, 550);
		contentPane.add(scrollPane);


		ClienteDAO cdao = new ClienteDAO();
		ClienteRequisitoDAO crdao = new ClienteRequisitoDAO();
		RequisitoDAO rdao = new RequisitoDAO();
		int contador = 0;
		ArrayList<Cliente> clientesProyecto = cdao.listarPorProyecto(p);
		String[][] valores = new String[clientesProyecto.size()][];
		String[][] nombresClientes = new String[clientesProyecto.size()][];
		String[][] nombresCliDisplay = new String[clientesProyecto.size()][];
		ArrayList<Requisito> requisitosTabla = new ArrayList<>(rdao.listarPorProyecto(p));
		String[] nombresRequisitos = new String[requisitosTabla.size()];
		String[] nombresRequisitosDis = new String[requisitosTabla.size()];
		for(int i = 0; i < requisitosTabla.size(); i++) {
			nombresRequisitos[i] = requisitosTabla.get(i).getNombreRequisito();
			nombresRequisitosDis[i] = requisitosTabla.get(i).getNombreRequisito() + " (" + requisitosTabla.get(i).getEsfuerzo() + ")";
		}

		for(Cliente c : clientesProyecto) {
			nombresClientes[contador] = new String[1];
			nombresCliDisplay[contador] = new String[1];
			nombresClientes[contador][0] = c.getNombreCliente();
			nombresCliDisplay[contador][0] = c.getNombreCliente() + " (" + c.getPeso() + ")";
			valores[contador] = new String[requisitosTabla.size()];
			for(int i = 0; i < requisitosTabla.size(); i++) {
				ClienteRequisito relacionCargada = crdao.listarPorClienteYRequisito(c, requisitosTabla.get(i));
				if(relacionCargada != null)
					valores[contador][i] = relacionCargada.toString();
				else
					valores[contador][i] = Integer.toString(0);
			}
			contador++;
		}

		tablaRequisitosUsuarios = new JTable();
		tablaRequisitosUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tablaRequisitosUsuarios.setBorder(null);
		tablaRequisitosUsuarios.setModel(new DefaultTableModel(valores, nombresRequisitosDis));
		tablaRequisitosUsuarios.setRowHeight(30);
		tablaRequisitosUsuarios.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				int columnIndex = e.getColumn();
				int rowIndex = e.getFirstRow();
				int valorNumerico;
				String valor = (String) tablaRequisitosUsuarios.getValueAt(rowIndex, columnIndex);
				try {
					valorNumerico = Integer.parseInt(valor);
					String nombreCliente = nombresClientes[rowIndex][0];
					Cliente c = cdao.obtenerClientePorNombre(nombreCliente);
					String nombreRequisito = nombresRequisitos[columnIndex];
					Requisito r = rdao.obtenerRequisitoPorNombre(nombreRequisito);
					ClienteRequisito cr = new ClienteRequisito(c.getIdCliente(), r.getIdRequisito(), valorNumerico);
					ClienteRequisito crBD = crdao.listarPorClienteYRequisito(c, r);
					if(crBD == null) crdao.insertar(cr);
					else crdao.modificar(cr);
					
				} catch (NumberFormatException nfe) {
					valorNumerico = 0;
				}

			}
		});
		scrollPane.setViewportView(tablaRequisitosUsuarios);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(null);
		scrollPane_1.setBounds(270, 176, 150, 550);
		contentPane.add(scrollPane_1);

		nombresClientesTabla = new JTable();
		nombresClientesTabla.setFont(new Font("Tahoma", Font.PLAIN, 16));
		nombresClientesTabla.setBorder(null);
		nombresClientesTabla.setRowHeight(30);
		nombresClientesTabla.setModel(new DefaultTableModel(nombresCliDisplay, new String[]{"Clientes"}));
		scrollPane_1.setViewportView(nombresClientesTabla);
		//		rowHeaderTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//		rowHeaderTable.setSize(new Dimension(50, tablaRequisitosUsuarios.getHeight()));

		//		scrollPane.getRowHeader().setSize(new Dimension(50, tablaRequisitosUsuarios.getHeight()));
		//		scrollPane.getViewport().setLocation(50, 0);


		textFieldEsfuerzo = new JTextField();
		textFieldEsfuerzo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldEsfuerzo.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldEsfuerzo.setText("15");
		textFieldEsfuerzo.setBounds(1436, 140, 30, 30);
		contentPane.add(textFieldEsfuerzo);
		textFieldEsfuerzo.setColumns(10);

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.LIGHT_GRAY);
		desktopPane.setBounds(0, 0, 1920, 60);
		contentPane.add(desktopPane);

		JButton botonProyectos = new JButton("Mis Proyectos");
		botonProyectos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		botonProyectos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaProyectosUsuario listaproyectos = new ListaProyectosUsuario(u);
				listaproyectos.setVisible(true);
				dispose();
			}
		});
		botonProyectos.setBounds(32, 11, 250, 40);
		desktopPane.add(botonProyectos);

		JButton botonCerrarSesion = new JButton("Cerrar Sesion");
		botonCerrarSesion.setFont(new Font("Tahoma", Font.PLAIN, 20));
		botonCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.setVisible(true);
				dispose();
			}
		});
		botonCerrarSesion.setBounds(1260, 11, 250, 40);
		desktopPane.add(botonCerrarSesion);

		JLabel labelEsfuerzo = new JLabel("Esfuerzo maximo");
		labelEsfuerzo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelEsfuerzo.setBounds(1266, 143, 160, 25);
		contentPane.add(labelEsfuerzo);

		JButton botonSoluciones = new JButton("Mostrar Soluciones");
		botonSoluciones.setFont(new Font("Tahoma", Font.PLAIN, 20));
		botonSoluciones.setBounds(650, 750, 250, 40);
		botonSoluciones.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					SolucionesInterfaz soluciones = new SolucionesInterfaz(u, p, Integer.parseInt(textFieldEsfuerzo.getText()));
					soluciones.setVisible(true);
					dispose();
				} catch (NumberFormatException nfe) {
					
				}
				
			}
		});
		contentPane.add(botonSoluciones);


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
