package Visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Logico.Evento;
import Logico.Juez;
import Logico.PUCMM;
import Logico.Persona;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.border.BevelBorder;

public class ListPersonas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] fila;
	private String selecte;
	private JButton btnRegistrar = new JButton("Registrar");
	
	public ListPersonas() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListPersonas.class.getResource("/img/Icono_pucmm.jpg")));
		setTitle("Lista de Personas");
		setBounds(100, 100, 570, 343);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		contentPanel.setBackground(new Color(190,209,201));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				table = new JTable();
				table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
				model = new DefaultTableModel();
				String[] columnNames = {"Cedula","Nombre","Telefono", "Area","Cargo", "Estado"};
				model.setColumnIdentifiers(columnNames);
				table.setModel(model);
				scrollPane.setViewportView(table);
				loadPersonas();
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
						RegPersona regpersona = new RegPersona(true);
						regpersona.setModal(true);
						regpersona.setVisible(true);
					}
				});
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}
	public static void loadPersonas() {

		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		
		for (Persona persona : PUCMM.pucmm().getMisPersonas()) {
			fila[0] = persona.getCedula();
			fila[1] = persona.getNombre();
			fila[2] = persona.getNumero();
			fila[3] = persona.getArea();
			fila[4] = (persona instanceof Juez) ? "Juez" : "Participante";
			fila[5] = (persona.isdisponible()) ? "Disponible" : "No disponible";

			model.addRow(fila);
		}
		
	
		
	}

}
