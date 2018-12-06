package Visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Logico.Evento;
import Logico.PUCMM;
import Logico.Recurso;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ListRecursos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] fila;
	private String selecte;
	private ArrayList<Recurso> misRecursos = new ArrayList<>();
	private Recurso miRecurso = null;
	private JButton btnRegistrar = new JButton("Registrar");
	
	public ListRecursos(Evento evento) {
		
		if(evento != null) {
			btnRegistrar.setText("Agregar");
			btnRegistrar.setEnabled(false);
		}
		setBounds(100, 100, 578, 353);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		contentPanel.setBackground(new Color(190,209,201));//lol klk
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBackground(new Color(190,209,201));
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				table = new JTable();
				table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int index = table.getSelectedRow();
						if(index >= 0) {
							selecte = table.getValueAt(index, 0).toString();
							if(evento != null) {
								if(PUCMM.pucmm().searchRecursoById(Integer.parseInt(selecte)).isDisponible()) {
									btnRegistrar.setEnabled(true);
								}
								else {
									btnRegistrar.setEnabled(false);
								}
							}
							
						}
					}
				});
				model = new DefaultTableModel();
				String[] columnNames = {"Id","Modelo", "Tipo","Estado"};
				model.setColumnIdentifiers(columnNames);
				table.setModel(model);
				scrollPane.setViewportView(table);
				loadRecursos();
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setBackground(new Color(190,209,201));
			{
				
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(evento == null) {
							RegRecursos regRecurso = new RegRecursos();
							regRecurso.setModal(true);
							regRecurso.setVisible(true);
						}
						else {
							miRecurso = PUCMM.pucmm().searchRecursoById(Integer.parseInt(selecte));
							PUCMM.pucmm().searchRecursoById(Integer.parseInt(selecte)).setDisponible(false);
							evento.setMisRecursos(miRecurso);
							loadRecursos();
						}
					}
				});
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
			}
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {						
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	private static void loadRecursos() {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		
		for(Recurso recurso: PUCMM.pucmm().getMisRecursos()) {
			
			fila[0] = recurso.getId();
			fila[1] = recurso.getModelo();
			fila[2] = recurso.getTipo();
			if(recurso.isDisponible()) {
				fila[3] = "Disponible";
			}
			else {
				fila[3] = "No Disponible";
			}
		
			model.addRow(fila);
		}
		
	}
}
