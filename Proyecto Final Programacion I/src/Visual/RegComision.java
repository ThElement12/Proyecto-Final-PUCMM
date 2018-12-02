package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
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
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

public class RegComision extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static DefaultTableModel Jmodel;
	private static DefaultTableModel Pmodel;
	private static Object[] Jfila;
	private static Object[] Pfila;
	private static ArrayList<Persona> misPersonas = PUCMM.pucmm().getMisPersonas();
	private JList<String> listSeleccionados = new JList<String>();
	private static DefaultListModel<String> model = new DefaultListModel<>();
	private int Jindex = 0;
	private int cant = 0;
	private int index = -1;
	private String Jselect = "";
	private String Pselect = "";
	private JTextField txtJuezPrincipal = new JTextField();
	private JTextField txtJuezSecundario1 = new JTextField();
	private JTextField txtJuezSecundario2 = new JTextField();
	private JTable tableJueces;
	private JTable tableParticipant;
	private	static Evento evento;
	private JButton btnAsignarPrincipal = new JButton("Asignar Principal");
	private JButton btnAsignarSecundario = new JButton("Asignar Secundario");
	private JButton btnAgregarParticipante = new JButton("Agregar Part.");
	private JButton btnQuitarPart = new JButton("Quitar  Part.");
	private JButton btnQuitarPrincipal = new JButton("Quitar Principal");
	private JButton btnQuitarSecundario = new JButton("Quitar Secundario");
	private JTextField txtTema;
	
	 
	public RegComision(Evento miEvento) {
		evento = miEvento;
		setBounds(100, 100, 916, 523);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		if(Jselect.isEmpty()) {
			btnAsignarPrincipal.setEnabled(false);
			btnAsignarSecundario.setEnabled(false);
		}
		else {
			btnAsignarPrincipal.setEnabled(true);
			btnAsignarSecundario.setEnabled(true);
		}
		
		if(txtJuezPrincipal.getText().isEmpty()) {
			btnQuitarPrincipal.setEnabled(false);
		}
		else {
			btnQuitarPrincipal.setEnabled(true);
		}
		if(Pselect.isEmpty()) {
			btnAgregarParticipante.setEnabled(false);
		}
		else {
			btnAgregarParticipante.setEnabled(true);
		}
		if(index == -1) {
			btnQuitarPart.setEnabled(false);
		}
		else {
			btnQuitarPart.setEnabled(true);
		}
		
		
		
		JPanel panelJueces = new JPanel();
		panelJueces.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelJueces.setBounds(562, 47, 332, 147);
		contentPanel.add(panelJueces);
		panelJueces.setLayout(null);
		
		JLabel lblJuezPrincipal = new JLabel("Juez Principal");
		lblJuezPrincipal.setBounds(123, 6, 89, 16);
		panelJueces.add(lblJuezPrincipal);
		txtJuezPrincipal.setEditable(false);
		
	
		txtJuezPrincipal.setBounds(106, 27, 122, 28);
		panelJueces.add(txtJuezPrincipal);
		txtJuezPrincipal.setColumns(10);
		
		JLabel lblJuecesSecundarios = new JLabel("Jueces Secundarios");
		lblJuecesSecundarios.setBounds(106, 60, 122, 16);
		panelJueces.add(lblJuecesSecundarios);
		
	
		txtJuezSecundario1.setEditable(false);
		txtJuezSecundario1.setBounds(24, 88, 122, 28);
		panelJueces.add(txtJuezSecundario1);
		txtJuezSecundario1.setColumns(10);
		
		
		txtJuezSecundario2.setEditable(false);
		txtJuezSecundario2.setBounds(177, 88, 122, 28);
		panelJueces.add(txtJuezSecundario2);
		txtJuezSecundario2.setColumns(10);
		{
			JPanel panelParticipante = new JPanel();
			panelParticipante.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			panelParticipante.setBounds(562, 206, 332, 230);
			contentPanel.add(panelParticipante);
			panelParticipante.setLayout(null);
			
			JLabel lblParticipantes = new JLabel("Participantes: ");
			lblParticipantes.setHorizontalAlignment(SwingConstants.CENTER);
			lblParticipantes.setBounds(113, 6, 97, 23);
			panelParticipante.add(lblParticipantes);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(6, 38, 320, 186);
			panelParticipante.add(scrollPane);
			
			
			scrollPane.setViewportView(listSeleccionados);
		}
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(6, 47, 354, 389);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblJuecesDisponibles = new JLabel("Jueces Disponibles");
		lblJuecesDisponibles.setBounds(122, 6, 116, 16);
		panel.add(lblJuecesDisponibles);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 34, 342, 136);
		panel.add(scrollPane);
		
		tableJueces = new JTable();
		tableJueces.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tableJueces.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tableJueces.getSelectedRow();
				if(index >= 0) {
					Jselect = tableJueces.getValueAt(index, 1).toString();
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
		lblParticipanteDisponible.setBounds(122, 180, 152, 16);
		panel.add(lblParticipanteDisponible);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(6, 208, 342, 175);
		panel.add(scrollPane_1);
		
		tableParticipant = new JTable();
		tableParticipant.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tableParticipant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tableParticipant.getSelectedRow();
				if(index >= 0) {
					Pselect = tableParticipant.getValueAt(index, 1).toString();
					btnAgregarParticipante.setEnabled(true);
				}
			}
		});
		Pmodel = new DefaultTableModel();
		String[] columnNamesPart = {"Id","Nombre","Area"};
		Pmodel.setColumnIdentifiers(columnNamesPart);
		tableParticipant.setModel(Pmodel);
		scrollPane_1.setViewportView(tableParticipant);
		loadparticipantes();
		
		
		btnAsignarPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtJuezPrincipal.setText(Pselect);	
			}
		});
		btnAsignarPrincipal.setBounds(390, 101, 132, 28);
		contentPanel.add(btnAsignarPrincipal);
		
		btnAsignarSecundario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Jindex == 0) {
					txtJuezSecundario1.setText(Jselect);
					Jindex = 1;
					
				}
				else if(Jindex == 1) {
					txtJuezSecundario2.setText(Jselect);
					Jindex = 0;
				}
				
			}
		});
		btnAsignarSecundario.setBounds(390, 177, 136, 28);
		contentPanel.add(btnAsignarSecundario);
		
		
		btnAgregarParticipante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				model.addElement(Pselect);
				listSeleccionados.setModel(model);
				
				
			}
		});
		btnAgregarParticipante.setBounds(390, 332, 132, 28);
		contentPanel.add(btnAgregarParticipante);
		
	
		btnQuitarPart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index = listSeleccionados.getSelectedIndex();
				listSeleccionados.remove(index);
			}
		});
		btnQuitarPart.setBounds(390, 372, 132, 28);
		contentPanel.add(btnQuitarPart);
		
		
		btnQuitarPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtJuezPrincipal.setText("");
			}
		});
		btnQuitarPrincipal.setBounds(390, 130, 132, 28);
		contentPanel.add(btnQuitarPrincipal);
		
		btnQuitarSecundario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Jindex == 0) {
					txtJuezSecundario2.setText("");
					
				}
				else if(Jindex == 1) {
					txtJuezSecundario1.setText("");
				}
				
			}
		});
		btnQuitarSecundario.setBounds(390, 207, 136, 28);
		contentPanel.add(btnQuitarSecundario);
		
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
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				
				JButton btnRegistrar = new JButton("Registrar");
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Comision comision = new Comision(miEvento.getArea(), txtTema.getText());
						miEvento.getMisComisiones().add(comision);
					}
				});
				buttonPane.add(btnRegistrar);
				btnCancelar.setActionCommand("OK");
				buttonPane.add(btnCancelar);
				getRootPane().setDefaultButton(btnCancelar);
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
						Jfila[0] =Integer.toString(juez.getId()); 
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
						Pfila[0] = Integer.toString(persona.getId());
						Pfila[1] = persona.getNombre();
						Pfila[2] = persona.getArea();
						
						Pmodel.addRow(Pfila);
					}
					
				}
			
			}
		
		
		}
	
	}
}
