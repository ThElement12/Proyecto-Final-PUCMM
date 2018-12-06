package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Logico.Comision;
import Logico.Evento;
import Logico.PUCMM;
import Logico.Participante;
import Logico.Persona;
import Logico.Trabajo;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;

public class RegTrabajo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField txtJuez;
	private JTextField txtEvento;
	private static DefaultTableModel model;
	private static Object[] fila;
	private int index;
	private String select = "";
	private JButton btnAsignar;
	private JButton cancelButton;
	private Comision miComision = new Comision(null, null);
	private Evento miEvento;
	private JTextField txtNumComision;


	public RegTrabajo(Comision comision, Evento evento) {
		setTitle("Asignar Trabajos Comision ");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.miComision = comision;
		this.miEvento = evento;
		setBounds(100, 100, 589, 369);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.setBackground(new Color(190,209,201));
		
		JPanel pnl_info_setter = new JPanel();
		pnl_info_setter.setBorder(new TitledBorder(null, "Informaci\u00F3n General", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnl_info_setter.setBounds(10, 11, 553, 78);
		contentPanel.add(pnl_info_setter);
		pnl_info_setter.setLayout(null);
		pnl_info_setter.setBackground(new Color(190,209,201));
		
		JComboBox<Object> cbxPosicion = new JComboBox<Object>();
		cbxPosicion.setFont(new Font("SansSerif", Font.PLAIN, 12));
		cbxPosicion.setModel(new DefaultComboBoxModel<Object>(new String[] {"<Seleccione>", "L\u00EDder", "Co-L\u00EDder", "Orador 1", "Orador 2", "Organizador 1", "Organizador 2"}));
		cbxPosicion.setBounds(415, 47, 128, 20);
		pnl_info_setter.add(cbxPosicion);
		
		JLabel lblPosicin = new JLabel("Posici\u00F3n:");
		lblPosicin.setBounds(359, 50, 62, 14);
		pnl_info_setter.add(lblPosicin);
		
		JLabel lblJuezDeLa = new JLabel("Juez De la Comisi\u00F3n:");
		lblJuezDeLa.setBounds(10, 50, 128, 14);
		pnl_info_setter.add(lblJuezDeLa);
		
		txtJuez = new JTextField();
		txtJuez.setFont(new Font("SansSerif", Font.PLAIN, 11));
		txtJuez.setText(miComision.getMisMiembros().get(0).getNombre());
		txtJuez.setEditable(false);
		txtJuez.setBounds(130, 47, 86, 25);
		pnl_info_setter.add(txtJuez);
		txtJuez.setColumns(10);
		
		JLabel lblEvento = new JLabel("Evento:");
		lblEvento.setBounds(10, 22, 46, 14);
		pnl_info_setter.add(lblEvento);
		
		txtEvento = new JTextField();
		txtEvento.setFont(new Font("SansSerif", Font.PLAIN, 11));
		txtEvento.setText(miEvento.getNombre());
		txtEvento.setEditable(false);
		txtEvento.setBounds(52, 19, 86, 25);
		pnl_info_setter.add(txtEvento);
		txtEvento.setColumns(10);
		
		JLabel lblComisinNum = new JLabel("Comisi\u00F3n num.:");
		lblComisinNum.setBounds(322, 21, 99, 16);
		pnl_info_setter.add(lblComisinNum);
		
		txtNumComision = new JTextField();
		txtNumComision.setText(String.valueOf(miComision.getId()));
		txtNumComision.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtNumComision.setEditable(false);
		txtNumComision.setBounds(415, 15, 122, 29);
		pnl_info_setter.add(txtNumComision);
		txtNumComision.setColumns(10);
		
		JPanel pnl_Tabla = new JPanel();
		pnl_Tabla.setBorder(new TitledBorder(null, "Participantes", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(59, 59, 59)));
		pnl_Tabla.setBounds(10, 100, 553, 186);
		contentPanel.add(pnl_Tabla);
		pnl_Tabla.setLayout(new BorderLayout(0, 0));
		pnl_Tabla.setBackground(new Color(190,209,201));
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				index = table.getSelectedRow();
				if(index >= 0 && !cbxPosicion.getSelectedItem().toString().equalsIgnoreCase("<Seleccione>")) {
					select = table.getValueAt(index, 0).toString();
					btnAsignar.setEnabled(true);
				}
			}
		});
		String columNames[] = {"Id","C�dula","Nombre"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columNames);
		table.setModel(model);
		loadTable();
		pnl_Tabla.add(table, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			buttonPane.setBackground(new Color(190,209,201));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			btnAsignar = new JButton("Asignar Posici\u00F3n");
			btnAsignar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Participante miParticipante =(Participante) PUCMM.pucmm().searchById(select);
					Trabajo miTrabajo = searchTrabajoByPosition(cbxPosicion.getSelectedItem().toString());
					if(miTrabajo.isDisponible()) {
						miTrabajo.setParticipante(miParticipante);
						miParticipante.agregarTrabajo(miTrabajo);
						miComision.getMisTrabajos().add(miTrabajo);
						
						JOptionPane.showMessageDialog(null, "OperacionCompletada con �xito", "Informaci�n", JOptionPane.INFORMATION_MESSAGE);
					}
					
					else {
						JOptionPane.showMessageDialog(null, "La posici�n ya est� ocupada", "Informaci�n", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
			btnAsignar.setEnabled(false);
			btnAsignar.setFont(new Font("SansSerif", Font.PLAIN, 12));
			
			buttonPane.add(btnAsignar);
			{
				cancelButton = new JButton("Salir");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setFont(new Font("SansSerif", Font.PLAIN, 12));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private void loadTable() {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		
		for(Persona persona : miComision.getMisMiembros()) {
			if(persona instanceof Participante) {
				fila[0] = persona.getId();
				fila[1] = persona.getCedula();
				fila[2] = persona.getNombre();
			}
			model.addRow(fila);
			
		}	
	}
	
	private Trabajo searchTrabajoByPosition(String nombre) {
		Trabajo miTrabajo = null;
		boolean finded = false;
		int i = 0;
		while(i < miComision.getMisTrabajos().size() && !finded) {
			if(miComision.getMisTrabajos().get(i).getPosicion().equalsIgnoreCase(nombre)) {
				miTrabajo = miComision.getMisTrabajos().get(i);
				finded = true;
			}
			
			else {
				i ++;
			}
		}
		
		return miTrabajo;
	}
}
