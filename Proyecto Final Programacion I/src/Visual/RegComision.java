package Visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Logico.Comision;
import Logico.Evento;
import Logico.Juez;
import Logico.PUCMM;
import Logico.Participante;
import Logico.Persona;

import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.Toolkit;

public class RegComision extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static DefaultTableModel Jmodel;
	private static DefaultTableModel Pmodel;
	private static Object[] Jfila;
	private static Object[] Pfila;
	private static Object[] Sfila;
	private ArrayList<Persona> miPersona = new ArrayList<>();
	private static DefaultTableModel model = new DefaultTableModel();
	private int Jindex = 0;
	private int index = -1;
	private String Jselect = "";
	private String Pselect = "";
	private String selecte = "";
	private JTextField txtJuezPrincipal = new JTextField();
	private JTable tableJueces;
	private JTable tableParticipant;
	private JTable tableseleccionado;
	private	static Evento evento;
	private JButton btnAsignarPrincipal = new JButton("Asignar Juez");
	private JButton btnAgregarParticipante = new JButton("Agregar Part.");
	private JButton btnQuitarPart = new JButton("Quitar  Part.");
	private JButton btnQuitarPrincipal = new JButton("Quitar Juez");
	private JButton btnRegistrar = new JButton("Registrar");
	private JTextField txtTema;
	private Juez miJuez;

	 
	public RegComision(Evento miEvento) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegComision.class.getResource("/img/Icono_pucmm.jpg")));
		setTitle("Registar Comisi\u00F3n");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		evento = miEvento;
		btnAsignarPrincipal.setEnabled(false);
		btnAgregarParticipante.setEnabled(false);
		btnQuitarPart.setEnabled(false);
		btnQuitarPrincipal.setEnabled(false);
		
		setBounds(100, 100, 638, 432);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.setBackground(new Color(190,209,201));
		
		JPanel panelJueces = new JPanel();
		panelJueces.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelJueces.setBounds(416, 47, 196, 82);
		contentPanel.add(panelJueces);
		panelJueces.setLayout(null);
		panelJueces.setBackground(new Color(190,209,201));
		
		JLabel lblJuezPrincipal = new JLabel("Juez Principal");
		lblJuezPrincipal.setHorizontalAlignment(SwingConstants.CENTER);
		lblJuezPrincipal.setBounds(45, 11, 89, 16);
		panelJueces.add(lblJuezPrincipal);
		txtJuezPrincipal.setEditable(false);
		
	
		txtJuezPrincipal.setBounds(10, 38, 167, 28);
		panelJueces.add(txtJuezPrincipal);
		txtJuezPrincipal.setColumns(10);
		{
			JPanel panelParticipante = new JPanel();
			panelParticipante.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			panelParticipante.setBounds(416, 140, 196, 211);
			panelParticipante.setBackground(new Color(190,209,201));
			contentPanel.add(panelParticipante);
			panelParticipante.setLayout(null);
			
			JLabel lblParticipantes = new JLabel("Participantes: ");
			lblParticipantes.setHorizontalAlignment(SwingConstants.CENTER);
			lblParticipantes.setBounds(48, 4, 97, 23);
			panelParticipante.add(lblParticipantes);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(14, 26, 172, 179);
			panelParticipante.add(scrollPane);
			
			tableseleccionado = new JTable();
			tableseleccionado.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			tableseleccionado.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int index = tableseleccionado.getSelectedRow();
					if(index >= 0) {
						selecte = tableseleccionado.getValueAt(index, 0).toString();
						btnQuitarPart.setEnabled(true);
					}
				}
			});
			model = new DefaultTableModel();
			String[] columnNames = {"Id","Nombre"};
			model.setColumnIdentifiers(columnNames);
			tableseleccionado.setModel(model);
			scrollPane.setViewportView(tableseleccionado);
		
		}
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(6, 47, 241, 296);
		contentPanel.add(panel);
		panel.setLayout(null);
		panel.setBackground(new Color(190,209,201));
		
		JLabel lblJuecesDisponibles = new JLabel("Jueces Disponibles");
		lblJuecesDisponibles.setHorizontalAlignment(SwingConstants.CENTER);
		lblJuecesDisponibles.setBounds(67, 7, 116, 16);
		panel.add(lblJuecesDisponibles);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 34, 221, 107);
		panel.add(scrollPane);
		
		tableJueces = new JTable();
		tableJueces.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tableJueces.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tableJueces.getSelectedRow();
				if(index >= 0) {
					Jselect = tableJueces.getValueAt(index, 0).toString();
					btnAsignarPrincipal.setEnabled(true);
					
				}
			}
		});
		Jmodel = new DefaultTableModel();
		String[] columnNamesJuez = {"Id","Nombre","Area"};
		Jmodel.setColumnIdentifiers(columnNamesJuez);
		tableJueces.setModel(Jmodel);
		scrollPane.setViewportView(tableJueces);
		loadjueces();
		
		JLabel lblParticipanteDisponible = new JLabel("Participantes Disponibles");
		lblParticipanteDisponible.setHorizontalAlignment(SwingConstants.CENTER);
		lblParticipanteDisponible.setBounds(48, 152, 152, 16);
		panel.add(lblParticipanteDisponible);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(6, 188, 221, 97);
		panel.add(scrollPane_1);
		
		tableParticipant = new JTable();
		tableParticipant.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tableParticipant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tableParticipant.getSelectedRow();
				if(index >= 0) {
					Pselect = tableParticipant.getValueAt(index, 0).toString();
					btnAgregarParticipante.setEnabled(true);
				}
			}
		});
		Pmodel = new DefaultTableModel();
		String[] columnNamesPart = {"Id","Nombre"};
		Pmodel.setColumnIdentifiers(columnNamesPart);
		tableParticipant.setModel(Pmodel);
		scrollPane_1.setViewportView(tableParticipant);
		loadparticipantes();
		
		btnAsignarPrincipal.setEnabled(false);
		btnAsignarPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miJuez = (Juez)PUCMM.pucmm().searchById(Jselect);
				txtJuezPrincipal.setText(miJuez.getNombre());
				loadjueces();
				btnQuitarPrincipal.setEnabled(true);
			}
		});
		btnAsignarPrincipal.setBounds(274, 86, 116, 28);
		contentPanel.add(btnAsignarPrincipal);
				
		btnAgregarParticipante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Participante miPart =(Participante) PUCMM.pucmm().searchById(Pselect);
				if(!miPersona.contains(miPart)) {
					miPersona.add(miPart);
				}
				loadSeleccionados();
				loadparticipantes();
			}
		});
		btnAgregarParticipante.setBounds(274, 244, 132, 28);
		contentPanel.add(btnAgregarParticipante);
		
		btnQuitarPart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 0;
				boolean encontrado = false;
				while(i < miPersona.size() && !encontrado) {
					if(miPersona.get(i).getId() == Integer.parseInt(selecte)) {
						model.removeRow(i);
						miPersona.remove(i);
						encontrado = true;
					}
					
					i++;
				}
			
			}
		});
		btnQuitarPart.setBounds(274, 283, 132, 28);
		contentPanel.add(btnQuitarPart);
				
		btnQuitarPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtJuezPrincipal.setText("");
				miJuez = null;
				btnQuitarPrincipal.setEnabled(false);
				
			}
		});
		btnQuitarPrincipal.setBounds(274, 125, 116, 28);
		contentPanel.add(btnQuitarPrincipal);
		
		JLabel lblTemaPrincipal = new JLabel("Tema Principal:");
		lblTemaPrincipal.setBounds(6, 19, 93, 16);
		contentPanel.add(lblTemaPrincipal);
		
		txtTema = new JTextField();
		txtTema.setBounds(111, 13, 231, 28);
		contentPanel.add(txtTema);
		txtTema.setColumns(10);
		Jmodel = new DefaultTableModel();
		String [] JCNames = {"Cédula", "Nombre","Area"};
		Jmodel.setColumnIdentifiers(JCNames);
		Pmodel = new DefaultTableModel();
		String [] PMNames = {"Cédula","Nombre"};
		

		Pmodel.setColumnIdentifiers(PMNames);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			buttonPane.setBackground(new Color(190,209,201));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				
				
				btnRegistrar.addActionListener(new ActionListener() {
				

					public void actionPerformed(ActionEvent e) {
						
						if(txtTema.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Por favor complete los campos obligatorios", "ERROR!", JOptionPane.WARNING_MESSAGE);
						}
						else if(txtJuezPrincipal.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "No asigno ningun Juez", "ERROR!", JOptionPane.WARNING_MESSAGE);
						}
						else if(model.getRowCount() == 0) {
							JOptionPane.showMessageDialog(null, "No asigno ningun Participante", "ERROR!", JOptionPane.WARNING_MESSAGE);
						}
						else {
							int option = JOptionPane.showConfirmDialog(null, "Desea crear esta comision?", "Confirmar", JOptionPane.WARNING_MESSAGE);
							if(option == JOptionPane.OK_OPTION) {
								miPersona.add(0,miJuez);
								setOcupado();
								Comision comision = new Comision(evento.getArea(), txtTema.getText());
								comision.setMisMiembros(miPersona);
								miEvento.getMisComisiones().add(comision);
								
								JOptionPane.showMessageDialog(null, "Operacion Satisfactoria", "Realizado", JOptionPane.INFORMATION_MESSAGE);
								
								dispose();
								
						
							}
							
						}
						
					}
				});
				buttonPane.add(btnRegistrar);
				btnCancelar.setActionCommand("OK");
				buttonPane.add(btnCancelar);
				getRootPane().setDefaultButton(btnCancelar);
			}
		}
	}

	private void loadSeleccionados() {
		model.setRowCount(0);
		Sfila = new Object[model.getColumnCount()];
		
		for(Persona elegidos : miPersona) {
			if(elegidos instanceof Participante) {
				Sfila[0] = elegidos.getId();
				Sfila[1] = elegidos.getNombre();
				model.addRow(Sfila);
			}
		}
		
	}
	private static void loadjueces() {
		Jmodel.setRowCount(0);
		Jfila = new Object[Jmodel.getColumnCount()];
		
		for (Persona juez : PUCMM.pucmm().getMisPersonas()) {
			if(juez instanceof Juez) {
				if(juez.isdisponible()) {
					if(juez.getArea().equalsIgnoreCase(evento.getArea())){
						Jfila[0] = juez.getId(); 
						Jfila[1] = juez.getNombre();
						Jfila[2] = juez.getArea();
						
						Jmodel.addRow(Jfila);
					}
				}
			}
		}
		
	}
	private static void loadparticipantes() {
		Pmodel.setRowCount(0);
		Pfila = new Object[Pmodel.getColumnCount()];
		
		for (Persona persona : PUCMM.pucmm().getMisPersonas()) {
			if(persona instanceof Participante) {
				if(persona.isdisponible()) {
					if(persona.getArea().equalsIgnoreCase(evento.getArea())) {
						Pfila[0] = persona.getId();
						Pfila[1] = persona.getNombre();
					
						Pmodel.addRow(Pfila);
					}
					
				}
			
			}
			
		}
	}
	private void setOcupado() {
		for(Persona persona : miPersona) {
			persona.setdisponible(false);
		}
	}
	
	
	
}
