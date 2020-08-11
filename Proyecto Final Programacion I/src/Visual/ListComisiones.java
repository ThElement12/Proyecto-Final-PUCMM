package Visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Logico.Comision;
import Logico.Evento;
import Logico.PUCMM;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class ListComisiones extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static DefaultTableModel model;
	private static Object[] fila;
	private String selecte;
	private JTable table;
	private JButton btnQuitar = new JButton("Quitar");
	private JButton btnAgregar = new JButton("Agregar");
	private JButton btnAsignarTrabajo = new JButton("Asignar Trabajo");

	public ListComisiones(Evento evento,String area) {
		setTitle("Lista de Comisiones");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListComisiones.class.getResource("/img/Icono_pucmm.jpg")));
		setBounds(100, 100, 582, 337);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setBackground(new Color(190,209,201));
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
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
							btnQuitar.setEnabled(true);
							btnAsignarTrabajo.setEnabled(true);
						}
					}
				});
				model = new DefaultTableModel();
				String[] columnNames = {"Id","Area", "Tema","Juez", "CantidadParticipantes"};
				model.setColumnIdentifiers(columnNames);
				table.setModel(model);
				scrollPane.setViewportView(table);
				loadComision(evento);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			buttonPane.setBackground(new Color(190,209,201));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				
				btnAgregar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RegComision regcomision = new RegComision(evento, area);
						regcomision.setModal(true);
						regcomision.setVisible(true);
						loadComision(evento);
						RegEvent.loadText();

					}
				});
				{
					
					btnQuitar.setEnabled(false);
					btnQuitar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							evento.quitarComision(evento.searchPosComByComId(Integer.parseInt(selecte)));
							btnQuitar.setEnabled(false);
							loadComision(evento);
							RegEvent.loadText();
						}
					});
					{
						btnAsignarTrabajo.setEnabled(false);
						btnAsignarTrabajo.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								
								RegTrabajo regTrabajo = new RegTrabajo(evento, evento.getMisComisiones().get(evento.searchPosComByComId(Integer.parseInt(selecte))));
								regTrabajo.setModal(true);
								regTrabajo.setVisible(true);
							}
						});
						buttonPane.add(btnAsignarTrabajo);
					}
					buttonPane.add(btnQuitar);
				}
				btnAgregar.setActionCommand("OK");
				buttonPane.add(btnAgregar);
				getRootPane().setDefaultButton(btnAgregar);
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
	public static void loadComision(Evento miEvento) {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		
		for(Comision comision: miEvento.getMisComisiones()) {
			fila[0] = comision.getId();
			fila[1] = comision.getArea();
			fila[2] = comision.getTema();
			fila[3] = comision.getJuez().getNombre();
			fila[4] = comision.getMisMiembros().size();
			
			model.addRow(fila);
		}
		
	}

}
