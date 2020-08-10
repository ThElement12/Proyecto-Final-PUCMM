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
import Logico.PUCMM;

import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.border.BevelBorder;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;

public class ListEventos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] fila;
	private JButton btnModificar;
	private String select;
	private JButton btnEliminar;
	private int index;
	private JButton btnReporte;
	private JComboBox cbxTipo = new JComboBox();
	private JSpinner spnFechaMaxima = new JSpinner();
	private JSpinner spnFechaMinima = new JSpinner();


	
	public ListEventos() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListEventos.class.getResource("/img/Icono_pucmm.jpg")));
		setTitle("Eventos ");
		setBounds(100, 100, 735, 476);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			panel.setBackground(new Color(190,209,201));
			
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(ListEventos.class.getResource("/img/RegEVento.jpg")));
			label.setBounds(6, 51, 102, 328);
			panel.add(label);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBackground(new Color(190,209,201));
			scrollPane.setBounds(114, 51, 595, 328);
			panel.add(scrollPane);
			{
				table = new JTable();
				table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						index = table.getSelectedRow();
						if(index >= 0) {
							select = table.getValueAt(index, 0).toString();
							btnModificar.setEnabled(true);
							btnEliminar.setEnabled(true);
							btnReporte.setEnabled(true);
						}
					}
				});
				model = new DefaultTableModel();
				String[] columnNames = {"Id","Nombre del Evento","Area","Campus","Lugar","Fecha Inicio", "Fecha Fin", "Horario", "Cant. Comisiones", "Cant. Recursos"};
				model.setColumnIdentifiers(columnNames);
				table.setModel(model);
				scrollPane.setViewportView(table);
				
				JLabel lblDesde = new JLabel("Desde:");
				lblDesde.setBounds(6, 18, 55, 16);
				panel.add(lblDesde);
				
				JLabel lblHasta = new JLabel("Hasta:");
				lblHasta.setBounds(200, 18, 55, 16);
				panel.add(lblHasta);
				
				JLabel lblTipo = new JLabel("Tipo:");
				lblTipo.setBounds(394, 18, 55, 16);
				panel.add(lblTipo);
				spnFechaMinima.setModel(new SpinnerDateModel());
				JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(spnFechaMinima, "dd/MM/yyy");
				spnFechaMinima.setEditor(timeEditor);
				spnFechaMinima.setValue(new Date());

				spnFechaMinima.setBounds(73, 12, 115, 28);
				panel.add(spnFechaMinima);
				spnFechaMaxima.setModel(new SpinnerDateModel());
				JSpinner.DateEditor te = new JSpinner.DateEditor(spnFechaMaxima, "dd/MM/yyy");
				spnFechaMaxima.setEditor(te);
				spnFechaMaxima.setValue(new Date());
				
				spnFechaMaxima.setBounds(267, 12, 115, 28);
				panel.add(spnFechaMaxima);
				
				cbxTipo.setModel(new DefaultComboBoxModel(new String[] {"Todos", "Fisica", "Quimica", "Biologia/Medicina", "Mercadeo/Administracion", "Informatica/Redes"}));
				cbxTipo.setBounds(433, 13, 140, 26);
				panel.add(cbxTipo);
				
				JButton btnBuscar = new JButton("Buscar");
				btnBuscar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						loadEventos((Date)spnFechaMinima.getValue(),(Date)spnFechaMaxima.getValue(),cbxTipo.getSelectedItem().toString());

					}
				});
				btnBuscar.setBounds(596, 12, 90, 28);
				panel.add(btnBuscar);
			}
			
			
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			buttonPane.setBackground(new Color(190,209,201));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RegEvent registrarEvento = new RegEvent(null);
						registrarEvento.setModal(true);
						registrarEvento.setVisible(true);
						loadEventos((Date)spnFechaMinima.getValue(),(Date)spnFechaMaxima.getValue(),cbxTipo.getSelectedItem().toString());
					}
				});
				{
					btnModificar = new JButton("Modificar");
					btnModificar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Evento miEvento = PUCMM.pucmm().searchEventoById(select);
							RegEvent unEvento = new RegEvent(miEvento);
							unEvento.setModal(true);
							unEvento.setVisible(true);
							loadEventos((Date)spnFechaMinima.getValue(),(Date)spnFechaMaxima.getValue(),cbxTipo.getSelectedItem().toString());
						}
					});
					{
						btnEliminar = new JButton("Eliminar");
						btnEliminar.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								PUCMM.pucmm().removeEventoById(select);
								JOptionPane.showMessageDialog(null, "Operacion completada con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
								loadEventos((Date)spnFechaMinima.getValue(),(Date)spnFechaMaxima.getValue(),cbxTipo.getSelectedItem().toString());
							}
						});
						btnEliminar.setEnabled(false);
						buttonPane.add(btnEliminar);
					}
					btnModificar.setEnabled(false);
					
					buttonPane.add(btnModificar);
				}
				
				btnReporte = new JButton("Hacer Reporte");
				btnReporte.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Evento miEvento = PUCMM.pucmm().searchEventoById(select);
						miEvento.hacerReporte();
						JOptionPane.showMessageDialog(null, "El Reporte se ha realizado con éxito", "información", JOptionPane.INFORMATION_MESSAGE);
					}
				});
				btnReporte.setEnabled(false);
				buttonPane.add(btnReporte);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cerrar");
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
	private static void loadEventos(Date min, Date max, String area) {
		model.setRowCount(0);
		DateFormat horaformat = new SimpleDateFormat("HH");
		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyy");
		fila = new Object[model.getColumnCount()];
		
		for (Evento evento : PUCMM.pucmm().getEventosByFecha(min, max, area)) {
			fila[0] = evento.getId();
			fila[1] = evento.getNombre();
			fila[2] = evento.getArea();
			fila[3] = evento.getCampus();
			fila[4] = evento.getLugar();
			fila[5] = dateformat.format(evento.getFechaIni()).toString();
			fila[6] = dateformat.format(evento.getFechaFin()).toString();
			fila[7] = horaformat.format(evento.getHorarioInicio()).toString() + "-" + horaformat.format(evento.getHorarioFin()).toString();
			fila[8] = evento.getMisComisiones().size();
			fila[9] = evento.getMisRecursos().size();
			
			model.addRow(fila);
		}
		
	}
}
