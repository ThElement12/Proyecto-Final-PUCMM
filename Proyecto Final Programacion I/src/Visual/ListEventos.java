package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Logico.Evento;
import Logico.PUCMM;

import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ListEventos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] fila;
	public String selecte;
	
	public ListEventos() {
		setBounds(100, 100, 735, 425);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(ListEventos.class.getResource("/img/RegEVento.jpg")));
			label.setBounds(0, 0, 104, 335);
			panel.add(label);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBounds(114, 0, 585, 343);
			panel.add(scrollPane);
			{
				table = new JTable();
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int index = table.getSelectedRow();
						if(index >= 0) {
							selecte = table.getValueAt(index, 0).toString();
						}
					}
				});
				model = new DefaultTableModel();
				String[] columnNames = {"Id","Nombre del Evento","Area","Campus","Lugar","Fecha Inicio", "Fecha Fin", "Horario"};
				model.setColumnIdentifiers(columnNames);
				table.setModel(model);
				scrollPane.setViewportView(table);
				loadEventos();
			}
			
			
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	public static void loadEventos() {
		model.setRowCount(0);
		DateFormat dateformat = new SimpleDateFormat("HH");
		fila = new Object[model.getColumnCount()];
		for (Evento evento : PUCMM.pucmm().getMisEventos()) {
			fila[0] = evento.getId();
			fila[1] = evento.getNombre();
			fila[2] = evento.getArea();
			fila[3] = evento.getCampus();
			fila[4] = evento.getLugar();
			fila[5] = evento.getFechaIni().toString();
			fila[6] = evento.getFechaFin().toString();
			fila[7] = dateformat.format(evento.getHorarioInicio()).toString() + "-" + dateformat.format(evento.getHorarioFin()).toString();
			
		}
		model.addRow(fila);
	}
	
}
