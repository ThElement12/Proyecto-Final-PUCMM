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
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Logico.Comision;
import Logico.Persona;

public class RegTrabajo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField txtJuez;
	private JTextField txtEvento;
	private static DefaultTableModel model;
	private static Object[] fila;
	
	

	/**
	 * Create the dialog.
	 */
	public RegTrabajo(Comision miComision) {
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
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "L\u00EDder", "Co-L\u00EDder", "Orador 1", "Orador 2", "Organizador 1", "Organizador 2"}));
		comboBox.setBounds(415, 47, 128, 20);
		pnl_info_setter.add(comboBox);
		
		JLabel lblPosicin = new JLabel("Posici\u00F3n:");
		lblPosicin.setBounds(359, 50, 62, 14);
		pnl_info_setter.add(lblPosicin);
		
		JLabel lblJuezDeLa = new JLabel("Juez De la Comisi\u00F3n:");
		lblJuezDeLa.setBounds(10, 50, 128, 14);
		pnl_info_setter.add(lblJuezDeLa);
		
		txtJuez = new JTextField();
		txtJuez.setEditable(false);
		txtJuez.setBounds(130, 47, 86, 20);
		pnl_info_setter.add(txtJuez);
		txtJuez.setColumns(10);
		
		JLabel lblEvento = new JLabel("Evento:");
		lblEvento.setBounds(10, 22, 46, 14);
		pnl_info_setter.add(lblEvento);
		
		txtEvento = new JTextField();
		txtEvento.setEditable(false);
		txtEvento.setBounds(52, 19, 86, 20);
		pnl_info_setter.add(txtEvento);
		txtEvento.setColumns(10);
		
		JPanel pnl_Tabla = new JPanel();
		pnl_Tabla.setBorder(new TitledBorder(null, "Participantes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnl_Tabla.setBounds(10, 100, 553, 186);
		contentPanel.add(pnl_Tabla);
		pnl_Tabla.setLayout(new BorderLayout(0, 0));
		
		table = new JTable();
		pnl_Tabla.add(table, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			
			JButton btnAsignar = new JButton("Asignar Posici\u00F3n");
			buttonPane.add(btnAsignar);
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
