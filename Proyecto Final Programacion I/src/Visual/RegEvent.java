package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import Logico.Comision;
import Logico.Evento;
import Logico.PUCMM;
import Logico.Persona;
import Logico.Recurso;

import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import java.awt.Toolkit;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.SwingConstants;
import java.awt.Font;

public class RegEvent extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtId;
	private JTextField txtNombre;
	private JComboBox<String> cbxArea = new JComboBox<>();
	private JComboBox<String> cbxCampus = new JComboBox<>();
	private JComboBox<String> cbxLugar = new JComboBox<>();
	private JPanel panelVariosDias = new JPanel();
	private JSpinner spnFechaFin = new JSpinner();
	private JSpinner spnFechaInicio = new JSpinner();
	private JSpinner spnHoraIni = new JSpinner();
	private JSpinner spnHoraFin = new JSpinner();
	private JPanel panelImagen = new JPanel();
	private JPanel panelReg = new JPanel();
	private static Evento evento = new Evento(null,null,null,null,null,null,null,null,null);
	private SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
	private final JButton btnRegistrar = new JButton("Registrar");
	private final JLabel label_2 = new JLabel("*");
	private final JLabel lblCamposObligatorios = new JLabel("* Campos Obligatorios");
	private static JTextField txtCantidadComisiones = new JTextField();
	private static JTextField txtCantidadRecursos = new JTextField();
	JButton btnGestionarRecursos = new JButton("Gestionar...");
	JButton btnGestionarCom = new JButton("Gestionar...");

	
	public RegEvent(Evento miEvento) {
		if(miEvento == null) {
			setTitle("Registrar Nuevo Evento");
		}
		
		else {
			evento = miEvento;
			setTitle("Modificar un Evento");
			btnGestionarCom.setEnabled(true);
		}
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegEvent.class.getResource("/img/Icono_pucmm.jpg")));
		setBounds(100, 100, 719, 455);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		contentPanel.setBackground(new Color(190,209,201));
		{
			panelReg.setBounds(119, 6, 578, 139);
			panelReg.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			panelReg.setBackground(new Color(190,209,201));
			contentPanel.add(panelReg);
			panelReg.setLayout(null);
			
			JLabel lblId = new JLabel("ID: ");
			lblId.setBounds(6, 18, 29, 16);
			panelReg.add(lblId);
			
			txtId = new JTextField();
			txtId.setEditable(false);
			if(miEvento == null) {
				txtId.setText(Integer.toString(PUCMM.pucmm().getCantEventos() + 1));
			}
			
			else {
				txtId.setText(evento.getId());
			}
			txtId.setBounds(47, 12, 102, 28);
			panelReg.add(txtId);
			txtId.setColumns(10);
			
			JLabel lblNombre = new JLabel("Nombre: ");
			lblNombre.setBounds(228, 18, 55, 16);
			panelReg.add(lblNombre);
			txtNombre = new JTextField();
			txtNombre.setBounds(282, 12, 244, 28);
			if(miEvento != null) {
				txtNombre.setText(evento.getNombre());
			}
			panelReg.add(txtNombre);
			txtNombre.setColumns(10);
			
			JLabel lblArea = new JLabel("Area:");
			lblArea.setBounds(6, 46, 41, 16);
			panelReg.add(lblArea);
			cbxArea.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(!cbxArea.getSelectedItem().equals("<Seleccione>")) {
						btnGestionarCom.setEnabled(true);
					}else {
						btnGestionarCom.setEnabled(false);
					}
				}
			});
			
			cbxArea.setModel(new DefaultComboBoxModel<String>(new String[] {"<Seleccione>", "Fisica", "Quimica", "Biologia/Medicina", "Mercadeo/Administracion", "Informatica/Redes"}));
			cbxArea.setBounds(47, 46, 156, 26);
			if(miEvento != null) {
				cbxArea.setSelectedItem(evento.getArea());
			}
			panelReg.add(cbxArea);
		}
		
		JLabel lblLugar = new JLabel("Lugar:");
		lblLugar.setBounds(6, 90, 55, 16);
		panelReg.add(lblLugar);
		
		
		cbxLugar.setModel(new DefaultComboBoxModel<String>(new String[] {"<Seleccione>", "Multiuso", "Teatro", "Anfiteatro", "Auditorio I", "Sede Postgrado", "Sala Reuniones (PA)"}));
		cbxLugar.setBounds(60, 85, 144, 26);
		if(miEvento != null) {
			cbxLugar.setSelectedItem(evento.getLugar());
		}
		panelReg.add(cbxLugar);
		
		JLabel lblCamps = new JLabel("Camp\u00FAs:");
		lblCamps.setBounds(228, 51, 55, 16);
		panelReg.add(lblCamps);
		
		
		cbxCampus.setModel(new DefaultComboBoxModel<String>(new String[] {"CSTI", "CSTA"}));
		cbxCampus.setBounds(294, 46, 64, 26);
		if(miEvento != null) {
			cbxCampus.setSelectedItem(evento.getCampus());
		}
		panelReg.add(cbxCampus);
		
		JLabel campoObligatorio = new JLabel("*");
		campoObligatorio.setBounds(538, 18, 55, 16);
		panelReg.add(campoObligatorio);
		
		JLabel label_1 = new JLabel("*");
		label_1.setBounds(215, 51, 29, 16);
		panelReg.add(label_1);
		label_2.setBounds(216, 90, 55, 16);
		
		panelReg.add(label_2);
		if(miEvento != null) {
			txtCantidadComisiones.setText(String.valueOf(evento.getMisComisiones().size()));
			txtCantidadRecursos.setText(String.valueOf(evento.getMisRecursos().size()));

		}
		else {
			txtCantidadComisiones.setText(String.valueOf(0));
			txtCantidadRecursos.setText(String.valueOf(0));

		}
		
		
		panelVariosDias.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelVariosDias.setBackground(new Color(190,209,201));
		panelVariosDias.setBounds(119, 157, 578, 130);
		contentPanel.add(panelVariosDias);
		panelVariosDias.setLayout(null);
		
		JLabel lblFechaInicio = new JLabel("Fecha Inicio:");
		lblFechaInicio.setBounds(6, 21, 74, 16);
		panelVariosDias.add(lblFechaInicio);
		
		
		spnFechaInicio.setModel(new SpinnerDateModel());
		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(spnFechaInicio, "dd/MM/yyy");
		spnFechaInicio.setEditor(timeEditor);
		spnFechaInicio.setValue(new Date());
		spnFechaInicio.setBounds(92, 15, 148, 28);
		panelVariosDias.add(spnFechaInicio);
		
		JLabel lblFechaFinalizacin = new JLabel("Fecha Finalizaci\u00F3n:");
		lblFechaFinalizacin.setBounds(283, 21, 115, 16);
		panelVariosDias.add(lblFechaFinalizacin);
		
		spnFechaFin.setModel(new SpinnerDateModel());
		JSpinner.DateEditor te = new JSpinner.DateEditor(spnFechaFin, "dd/MM/yyy");
		spnFechaFin.setEditor(te);
		spnFechaFin.setBounds(410, 15, 148, 28);
		panelVariosDias.add(spnFechaFin);
		
		JLabel lblHoraDeInicio = new JLabel("Hora de Inicio:");
		lblHoraDeInicio.setBounds(6, 70, 84, 16);
		panelVariosDias.add(lblHoraDeInicio);
		
		
		spnHoraIni.setModel(new SpinnerDateModel());
		JSpinner.DateEditor horaIni = new JSpinner.DateEditor(spnHoraIni, "HH:mm:ss");
		spnHoraIni.setEditor(horaIni);
		spnHoraIni.setBounds(92, 64, 148, 28);
		try {
			spnHoraIni.setValue(formatoHora.parse("8:00"));
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
		panelVariosDias.add(spnHoraIni);
		
		JLabel lblHoraDeFinalizacion = new JLabel("Hora de Finalizacion:");
		lblHoraDeFinalizacion.setBounds(283, 70, 115, 16);
		panelVariosDias.add(lblHoraDeFinalizacion);
		
		
		spnHoraFin.setModel(new SpinnerDateModel());
		JSpinner.DateEditor horaFin = new JSpinner.DateEditor(spnHoraFin, "HH:mm:ss");
		spnHoraFin.setEditor(horaFin);
		spnHoraFin.setBounds(410, 64, 148, 28);
		try {
			spnHoraFin.setValue(formatoHora.parse("8:00"));
		} catch (ParseException e2) {
			
			e2.printStackTrace();
		}
		panelVariosDias.add(spnHoraFin);
		panelImagen.setBounds(6, 6, 104, 362);
		panelImagen.setBackground(new Color(190,209,201));
		contentPanel.add(panelImagen);
		panelImagen.setLayout(new BorderLayout(0, 0));
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(RegEvent.class.getResource("/img/RegEVento.jpg")));
		panelImagen.add(label, BorderLayout.WEST);
		
		JPanel panelComisionRecursos = new JPanel();
		panelComisionRecursos.setLayout(null);
		panelComisionRecursos.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelComisionRecursos.setBackground(new Color(190, 209, 201));
		panelComisionRecursos.setBounds(119, 299, 578, 62);
		contentPanel.add(panelComisionRecursos);
		
		JLabel lblCantComisiones = new JLabel("Cant. Comisiones: ");
		lblCantComisiones.setBounds(6, 22, 113, 16);
		panelComisionRecursos.add(lblCantComisiones);
		txtCantidadComisiones.setBounds(119, 16, 30, 28);
		panelComisionRecursos.add(txtCantidadComisiones);
		txtCantidadComisiones.setEditable(false);
		
		txtCantidadComisiones.setEditable(false);
		txtCantidadComisiones.setColumns(10);
		
		if(miEvento == null) {
			btnGestionarCom.setEnabled(false);

		}
		btnGestionarCom.setBounds(161, 16, 91, 28);
		panelComisionRecursos.add(btnGestionarCom);
		
		JLabel lblCantRecursos = new JLabel("Cant. Recursos: ");
		lblCantRecursos.setBounds(276, 22, 103, 16);
		panelComisionRecursos.add(lblCantRecursos);
		txtCantidadRecursos.setBounds(379, 16, 29, 28);
		panelComisionRecursos.add(txtCantidadRecursos);
		
		txtCantidadRecursos.setEditable(false);
		txtCantidadRecursos.setColumns(10);
		
		btnGestionarRecursos.setBounds(420, 16, 91, 28);
		panelComisionRecursos.add(btnGestionarRecursos);
		btnGestionarRecursos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListRecursos listrecursos;
				listrecursos = new ListRecursos(evento);
				listrecursos.setModal(true);
				listrecursos.setVisible(true);
				
			}
		});
		btnGestionarCom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				evento.setNombre(txtNombre.getText());
				ListComisiones listcomisiones = new ListComisiones(evento, cbxArea.getSelectedItem().toString());
				listcomisiones.setModal(true);
				listcomisiones.setVisible(true);
				
			}
		});
		JPanel buttonPane = new JPanel();
		buttonPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		buttonPane.setBackground(new Color(190,209,201));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		lblCamposObligatorios.setFont(new Font("SansSerif", Font.ITALIC, 12));
		lblCamposObligatorios.setDisplayedMnemonic('C');
		lblCamposObligatorios.setHorizontalAlignment(SwingConstants.CENTER);
		
		buttonPane.add(lblCamposObligatorios);
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				if(txtNombre.getText().isEmpty() || cbxArea.getSelectedIndex() == 0 || cbxLugar.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "Por favor rellene los campos obligatorios", "ERROR!", JOptionPane.WARNING_MESSAGE);
				}
				if(evento.getMisComisiones().isEmpty()){
					JOptionPane.showMessageDialog(null, "Debe registrar por lo menos una comision", "Aviso", JOptionPane.WARNING_MESSAGE);
					
				}
				else if(!checkWork()){
					
						JOptionPane.showMessageDialog(null, "Debe asignar los trabajos a las comisiones creadas", "Aviso", JOptionPane.WARNING_MESSAGE);
					
				}
				else if(evento.getMisRecursos().isEmpty()) {
						JOptionPane.showMessageDialog(null, "No asigno ningun recurso", "Aviso", JOptionPane.WARNING_MESSAGE);
						
				}
				else {
					int option = JOptionPane.showConfirmDialog(null,"Esta seguro que desea efectuar la operacion?",
							"Advertencia",JOptionPane.WARNING_MESSAGE);
					
					if(option == JOptionPane.OK_OPTION) {
						if(miEvento == null) {
							ArrayList<Comision> comisiones = evento.getMisComisiones();
							ArrayList<Recurso> recursos = evento.getMisRecursos();
							evento = new Evento(txtId.getText(),txtNombre.getText(), cbxArea.getSelectedItem().toString(),cbxLugar.getSelectedItem().toString(),
									cbxCampus.getSelectedItem().toString(),(Date)spnFechaInicio.getValue(),(Date)spnFechaFin.getValue(),(Date)spnHoraIni.getValue(),
									(Date)spnHoraFin.getValue());
							evento.setMisComisiones(comisiones);
							evento.setMisRecursos(recursos);
							Principal.createLineChart();
							Principal.createPieChart();	
							PUCMM.pucmm().crearEvento(evento);
							JOptionPane.showMessageDialog(null, "Operacion Satisfactoria", "Guardado", JOptionPane.INFORMATION_MESSAGE);
							clean();
							dispose();
						}
						else {
							ArrayList<Comision> comisiones = evento.getMisComisiones();
							ArrayList<Recurso> recursos = evento.getMisRecursos();
							Evento modEvento = PUCMM.pucmm().searchEventoById(miEvento.getId());
							modEvento.setNombre(txtNombre.getText());
							modEvento.setArea( cbxArea.getSelectedItem().toString());
							modEvento.setLugar(cbxLugar.getSelectedItem().toString());
							modEvento.setCampus(cbxCampus.getSelectedItem().toString());
							modEvento.setFechaIni((Date)spnFechaInicio.getValue());
							modEvento.setFechaFin((Date)spnFechaFin.getValue());
							modEvento.setHorarioInicio((Date)spnHoraIni.getValue());
							modEvento.setHorarioFin((Date)spnHoraFin.getValue());
							modEvento.setMisComisiones(comisiones);
							modEvento.setMisRecursos(recursos);
							JOptionPane.showMessageDialog(null, "Operacion Satisfactoria", "Guardado", JOptionPane.INFORMATION_MESSAGE);

							dispose();
						}
						
					}
				}
					
			}
		});
		btnRegistrar.setActionCommand("OK");
		buttonPane.add(btnRegistrar);
		getRootPane().setDefaultButton(btnRegistrar);
		{
			JButton btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					dispose();
					
					if(evento != null) {
						for(Comision comision : evento.getMisComisiones()) {
							for(Persona persona : comision.getMisMiembros()) {
								persona.setdisponible(true);
							}
						}
						for(Recurso recurso: evento.getMisRecursos()) {
							recurso.setDisponible(true);
						}
					}
					
				}
			});
			btnCancelar.setActionCommand("Cancel");
			buttonPane.add(btnCancelar);
		}
	}
	boolean checkWork() {
		for(Comision miComision: evento.getMisComisiones()){
			if(miComision.getMisTrabajos().isEmpty()) {
				return false;
			}
			
		}
		return true;
	}
	void clean() {
		txtId.setText(Integer.toString(PUCMM.pucmm().getCantEventos() + 1));
		txtNombre.setText("");
		cbxArea.setSelectedIndex(0);
		cbxCampus.setSelectedIndex(0);
		cbxLugar.setSelectedIndex(0);
		spnFechaFin.setValue(new Date());
		spnFechaInicio.setValue(new Date());
		try {
			spnHoraFin.setValue(formatoHora.parse("8:00"));
			spnHoraIni.setValue(formatoHora.parse("8:00"));
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
	}
	public static void loadText() {
		txtCantidadComisiones.setText(String.valueOf(evento.getMisComisiones().size()));
		txtCantidadRecursos.setText(String.valueOf(evento.getMisRecursos().size()));	
		}
}
