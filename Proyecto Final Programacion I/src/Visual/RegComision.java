package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

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

public class RegComision extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTextField txtIdEvent;
	private JTextField txtNombre;
	private JTextField txtArea;
	private JTextField txtCantArea;
	private static DefaultTableModel Jmodel;
	private static DefaultTableModel Pmodel;
	private static Object[] Jfila;
	private static Object[] Pfila;
	private static ArrayList<Persona> misPersonas;
	private JTable tblJuez;
	private JTable tblPart;
	private int Jindex;
	private int Pindex;
	private JButton btnSelect;
	private String Jselect = "";
	private String Pselect = "";

	 
	public RegComision(Evento miEvento) {
		setBounds(100, 100, 691, 438);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel pnl_Info = new JPanel();
		pnl_Info.setBounds(6, 6, 663, 91);
		contentPanel.add(pnl_Info);
		pnl_Info.setLayout(null);
		{
			JLabel lblId = new JLabel("Id:");
			lblId.setBounds(6, 6, 22, 16);
			pnl_Info.add(lblId);
		}
		
		txtIdEvent = new JTextField();
		txtIdEvent.setText(miEvento.getId());
		txtIdEvent.setEditable(false);
		txtIdEvent.setBounds(27, 4, 86, 20);
		pnl_Info.add(txtIdEvent);
		txtIdEvent.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(6, 31, 64, 14);
		pnl_Info.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setText(miEvento.getNombre());
		txtNombre.setEditable(false);
		txtNombre.setBounds(62, 28, 148, 20);
		pnl_Info.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblAreasDisp = new JLabel("Seleccionar Area:");
		lblAreasDisp.setBounds(370, 7, 104, 14);
		pnl_Info.add(lblAreasDisp);
		
		JComboBox cbxSelAr = new JComboBox();
		cbxSelAr.setBounds(473, 4, 148, 20);
		pnl_Info.add(cbxSelAr);
		
		JLabel lblArea = new JLabel("Area:");
		lblArea.setBounds(6, 57, 35, 14);
		pnl_Info.add(lblArea);
		
		txtArea = new JTextField();
		txtArea.setText(miEvento.getArea());
		txtArea.setEditable(false);
		txtArea.setBounds(38, 54, 141, 20);
		pnl_Info.add(txtArea);
		txtArea.setColumns(10);
		
		JLabel lblCantDeAreas = new JLabel("Cant. de Comisiones Disp.:");
		lblCantDeAreas.setBounds(335, 34, 144, 14);
		pnl_Info.add(lblCantDeAreas);
		
		txtCantArea = new JTextField();
		txtCantArea.setEditable(false);
		txtCantArea.setBounds(473, 28, 35, 20);
		pnl_Info.add(txtCantArea);
		txtCantArea.setColumns(10);
		
		JPanel pnl_Juez = new JPanel();
		pnl_Juez.setBounds(6, 109, 266, 251);
		contentPanel.add(pnl_Juez);
		pnl_Juez.setLayout(new BorderLayout(0, 0));
		
		tblJuez = new JTable();
		pnl_Juez.add(tblJuez, BorderLayout.CENTER);
		tblJuez.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Jindex = tblJuez.getSelectedRow();
				if(Jindex >= 0) {
					Jselect = tblJuez.getValueAt(Jindex, 0).toString();
					btnSelect.setEnabled(true);
				}
			}
			
		});
		Jmodel = new DefaultTableModel();
		String [] JCNames = {"Cédula", "Nombre","Area"};
		Jmodel.setColumnIdentifiers(JCNames);
		
		btnSelect = new JButton("A\u00F1adir");
		btnSelect.setEnabled(false);
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnSelect.setBounds(274, 213, 89, 23);
		contentPanel.add(btnSelect);
		
		JPanel panel = new JPanel();
		panel.setBounds(363, 109, 306, 251);
		contentPanel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		tblPart = new JTable();
		tblPart.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Pindex = tblPart.getSelectedRow();
				if(Pindex >= 0) {
					Pselect = tblPart.getValueAt(Pindex, 0).toString();
					btnSelect.setEnabled(true);
				}
			}
		});
		Pmodel = new DefaultTableModel();
		String [] PMNames = {"Cédula","Nombre"};
		Pmodel.setColumnIdentifiers(PMNames);
		panel.add(tblPart, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Salir");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
	private static void filterP() {
		for(int i = 0; i < PUCMM.pucmm().getMisPersonas().size(); i ++) {
			if(PUCMM.pucmm().getMisPersonas().get(i).getEvento().getId().equalsIgnoreCase(txtIdEvent.getText()) && !PUCMM.pucmm().getMisPersonas().get(i).isSelecte()) {
				misPersonas.add(PUCMM.pucmm().getMisPersonas().get(i));
			}
		}
	}
	
	private static void loadJuez() {
		Jmodel.setRowCount(0);
		Jfila = new Object[Jmodel.getColumnCount()];
		for(int i = 0; i < misPersonas.size(); i ++) {
				if(misPersonas.get(i) instanceof Juez) {
					Jfila[0] = misPersonas.get(i).getCedula();
					Jfila[1] = misPersonas.get(i).getNombre();
					Jfila[2] = misPersonas.get(i).getArea();
					Jmodel.addRow(Jfila);
			}
		}
	}
	
	private static void loadPart() {
		Pmodel.setRowCount(0);
		Pfila = new Object[Pmodel.getColumnCount()];
		for(int i = 0; i < misPersonas.size(); i++) {
			if(misPersonas.get(i) instanceof Participante) {
				Pfila[0] = misPersonas.get(i).getCedula();
				Pfila[1] = misPersonas.get(i).getNombre();
				Pmodel.addRow(Pfila);
			}
		}
	}
}
