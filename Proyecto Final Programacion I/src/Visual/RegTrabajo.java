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
	private Comision miComision;
	private Evento miEvento;
	

	/**
	 * Create the dialog.
	 */
	public RegTrabajo(Comision miComision, Evento miEvento) {
		this.miComision = miComision;
		this.miEvento = miEvento;
		setBounds(100, 100, 589, 369);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel pnl_info_setter = new JPanel();
		pnl_info_setter.setBorder(new TitledBorder(null, "Informaci\u00F3n General", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnl_info_setter.setBounds(10, 11, 553, 78);
		contentPanel.add(pnl_info_setter);
		pnl_info_setter.setLayout(null);
		
		JComboBox<Object> cbxPosicion = new JComboBox<Object>();
		cbxPosicion.setFont(new Font("Malgun Gothic", Font.ITALIC, 12));
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
		txtJuez.setFont(new Font("Malgun Gothic", Font.ITALIC, 11));
		txtJuez.setText(miComision.getMisMiembros().get(0).getNombre());
		txtJuez.setEditable(false);
		txtJuez.setBounds(130, 47, 86, 25);
		pnl_info_setter.add(txtJuez);
		txtJuez.setColumns(10);
		
		JLabel lblEvento = new JLabel("Evento:");
		lblEvento.setBounds(10, 22, 46, 14);
		pnl_info_setter.add(lblEvento);
		
		txtEvento = new JTextField();
		txtEvento.setFont(new Font("Malgun Gothic", Font.ITALIC, 11));
		txtEvento.setText(miEvento.getNombre());
		txtEvento.setEditable(false);
		txtEvento.setBounds(52, 19, 86, 25);
		pnl_info_setter.add(txtEvento);
		txtEvento.setColumns(10);
		
		JPanel pnl_Tabla = new JPanel();
		pnl_Tabla.setBorder(new TitledBorder(null, "Participantes", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(59, 59, 59)));
		pnl_Tabla.setBounds(10, 100, 553, 186);
		contentPanel.add(pnl_Tabla);
		pnl_Tabla.setLayout(new BorderLayout(0, 0));
		
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
		String columNames[] = {"Id","Cédula","Nombre"};
		model.setColumnIdentifiers(columNames);
		table.setModel(model);
		loadTable();
		pnl_Tabla.add(table, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			btnAsignar = new JButton("Asignar Posici\u00F3n");
			btnAsignar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Participante miParticipante =(Participante) PUCMM.pucmm().searchById(select);
					Trabajo miTrabajo = searchTrabajoByPosition(cbxPosicion.getSelectedItem().toString());
					if(miTrabajo.isDisponible()) {
						miTrabajo.setParticipante(miParticipante);
						miParticipante.agregarTrabajo(miTrabajo);
						JOptionPane.showMessageDialog(null, "OperacionCompletada con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
					}
					
					else {
						JOptionPane.showMessageDialog(null, "La posición ya está ocupada", "Información", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
			btnAsignar.setEnabled(false);
			btnAsignar.setFont(new Font("Malgun Gothic", Font.ITALIC, 12));
			
			buttonPane.add(btnAsignar);
			{
				cancelButton = new JButton("Salir");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setFont(new Font("Malgun Gothic", Font.ITALIC, 12));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private static void loadTable() {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		
		for(int i = 0; i < RegComision.getMiPersona().size(); i ++) {
			if(RegComision.getMiPersona().get(i) instanceof Participante) {
				fila[0] = RegComision.getMiPersona().get(i).getId();
				fila[1] = RegComision.getMiPersona().get(i).getCedula();
				fila[2] = RegComision.getMiPersona().get(i).getNombre();
			
				model.addRow(fila);
			}
		}
	}
	
	private Trabajo searchTrabajoByPosition(String nombre) {
		Trabajo miTrabajo = null;
		boolean finded = false;
		int i = 0;
		while(i < 6 && !finded) {
			if(miComision.getMisTrabajos()[i].getPosicion().equalsIgnoreCase(nombre)) {
				miTrabajo = miComision.getMisTrabajos()[i];
				finded = true;
			}
			
			else {
				i ++;
			}
		}
		
		return miTrabajo;
	}
}
