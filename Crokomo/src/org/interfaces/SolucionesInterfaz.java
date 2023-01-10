package org.interfaces;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.bd.dao.ClienteDAO;
import org.bd.dao.ClienteRequisitoDAO;
import org.bd.dao.RequisitoDAO;
import org.objects.Cliente;
import org.objects.Proyecto;
import org.objects.Requisito;
import org.objects.Solucion;
import org.objects.Usuario;
import org.utility.calculo.Formulas;
import org.utility.calculo.Mochila;
import org.utility.comparator.RequisitoComparator;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
	public SolucionesInterfaz(Usuario u, Proyecto p, int esfuerzoMaximo) {
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
					ProyectoInterfaz proyectoInterfaz = new ProyectoInterfaz(u, p);
					proyectoInterfaz.setVisible(true);
					dispose();
			}
		});
		botonVolver.setBounds(287, 388, 139, 21);
		contentPane.add(botonVolver);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(176, 102, 372, 276);
		contentPane.add(scrollPane);
		String[] nombresTabla = {
				"Soluciones", "Requisitos", "Satisfacci\u00F3n"
		};
		ArrayList<Solucion> soluciones = new ArrayList<>();
		ArrayList<Requisito> auxiliares = new ArrayList<>();
		RequisitoDAO rdao = new RequisitoDAO();
		ClienteRequisitoDAO crdao = new ClienteRequisitoDAO();
		ClienteDAO cdao = new ClienteDAO();
		ArrayList<Requisito> requisitosProyecto = rdao.listarPorProyecto(p);
		ArrayList<Cliente> clientesProyecto = cdao.listarPorProyecto(p);
 		for(Requisito r : requisitosProyecto) {
 			if(r.getEsfuerzo() > esfuerzoMaximo) {
 				auxiliares.add(r);
 			}
 			r.setRelaciones(crdao.listarPorRequisito(r));
			Formulas.calcularSatisfaccion(r, clientesProyecto);
		}
 		for(Requisito r : auxiliares)
 			requisitosProyecto.remove(r);
		Solucion temp;
		while(requisitosProyecto.size() != 0) {
			temp = new Mochila().mochilaSolucion(requisitosProyecto, esfuerzoMaximo);
			soluciones.add(temp);
			for(Requisito r : temp.getRequisitos()) {
				if(requisitosProyecto.contains(r))
					requisitosProyecto.remove(r);
			}
			requisitosProyecto.trimToSize();
		}
		
		String[][] contenidoTabla = new String[soluciones.size()][nombresTabla.length];
		for(int i = 0; i < contenidoTabla.length; i++) {
			ArrayList<Requisito> requisitosSolucionActual = soluciones.get(i).getRequisitos();
			requisitosSolucionActual.sort(new RequisitoComparator());
			contenidoTabla[i][0] = "S" + (i+1);
			contenidoTabla[i][1] = String.format("%s(%d)", requisitosSolucionActual.get(0).getNombreRequisito(), requisitosSolucionActual.get(0).getSatisfaccion());
			for(int j = 1; j < requisitosSolucionActual.size(); j++) {
				contenidoTabla[i][1] += String.format(", %s(%d)", requisitosSolucionActual.get(j).getNombreRequisito(), requisitosSolucionActual.get(j).getSatisfaccion());
			}
			contenidoTabla[i][2] = Integer.toString(Formulas.satisfaccionSolucion(requisitosSolucionActual));
		}
		
		table = new JTable();
		table.setModel(new DefaultTableModel(contenidoTabla, nombresTabla));
		
		table.setAlignmentX(JTable.CENTER_ALIGNMENT);
		table.setAlignmentY(JTable.CENTER_ALIGNMENT);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.getColumn("Requisitos").setMinWidth(200);
		scrollPane.setViewportView(table);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.LIGHT_GRAY);
		desktopPane.setBounds(0, 0, 750, 43);
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
		
		JLabel labelNombreProyecto = new JLabel((String) null);
		labelNombreProyecto.setHorizontalAlignment(SwingConstants.CENTER);
		labelNombreProyecto.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelNombreProyecto.setBounds(229, 0, 284, 43);
		desktopPane.add(labelNombreProyecto);
	}
}
