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
import javax.swing.border.MatteBorder;

import Logico.Comision;
import Logico.Evento;
import Logico.Juez;
import Logico.PUCMM;
import Logico.Persona;

import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.SpinnerNumberModel;
import java.awt.Component;
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
	private Evento evento = new Evento(null, null, null, null, null, null, null, null, null);
	private SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
	private final JButton btnSiguiente = new JButton("Siguiente");
	private final JButton btnAtrs = new JButton("Atr\u00E1s");
	private final JButton btnRegistrar = new JButton("Registrar");
	private final JLabel label_2 = new JLabel("*");
	private final JLabel lblCamposObligatorios = new JLabel("* Campos Obligatorios");
	private final JPanel panelComision = new JPanel();
	private final JLabel label_3 = new JLabel("Comision 1:");
	private final JTextField txtComision1 = new JTextField();
	private final JButton btnCrearComision1 = new JButton("Crear Comision");
	private final JButton btnAsignarTrabajo1 = new JButton("Asignar Trabajos");
	private final JLabel label_4 = new JLabel("Comision 2:");
	private final JTextField txtComision2 = new JTextField();
	private final JButton btnCrearComision2 = new JButton("Crear Comision");
	private final JButton btnAsignarTrabajo2 = new JButton("Asignar Trabajos");
	private final JLabel label_5 = new JLabel("Comision 3:");
	private final JTextField txtComision3 = new JTextField();
	private final JButton btnCrearComision3 = new JButton("Crear Comision");
	private final JButton btnAsignarTrabajo3 = new JButton("Asignar Trabajos");
	private final JLabel label_7 = new JLabel("Comision 4:");
	private final JTextField txtComision4 = new JTextField();
	private final JButton btnCrearComision4 = new JButton("Crear Comision");
	private final JButton btnAsignarTrabajo4 = new JButton("Asignar Trabajos");
	private final JButton btnAgregarRecurso = new JButton("Agregar...");
	private final JTextField txtCantRecursos = new JTextField();
	private final JLabel label_8 = new JLabel("Cant. Recursos: ");
	
	public RegEvent(Evento miEvento) {
		if(miEvento == null) {
			setTitle("Registrar Nuevo Evento");
			btnRegistrar.setEnabled(false);
		}
		
		else {
			setTitle("Modificar un Evento");
			btnSiguiente.setVisible(false);
			btnAtrs.setVisible(false);
			btnRegistrar.setEnabled(true);
		}
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegEvent.class.getResource("/img/Icono_pucmm.jpg")));
		setBounds(100, 100, 674, 437);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		contentPanel.setBackground(new Color(190,209,201));
		panelComision.setBounds(0, 0, 658, 1);
		contentPanel.add(panelComision);
		panelComision.setVisible(false);
		panelComision.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelComision.setLayout(null);
		panelComision.setBackground(new Color(190,209,201));
		btnAsignarTrabajo2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				evento.getMisComisiones().get(1).createTrabajo();
				RegTrabajo traFrame = new RegTrabajo(evento.getMisComisiones().get(1), evento);
				traFrame.setModal(true);
				traFrame.setVisible(true);
				
			}
		});
		btnAsignarTrabajo2.setEnabled(false);
		btnAsignarTrabajo2.setBounds(330, 86, 120, 23);
		
		panelComision.add(btnAsignarTrabajo2);
		btnAsignarTrabajo1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				evento.getMisComisiones().get(0).createTrabajo();
				RegTrabajo traFrame = new RegTrabajo(evento.getMisComisiones().get(0), evento);
				traFrame.setModal(true);
				traFrame.setVisible(true);
			}
		});
		btnAsignarTrabajo1.setEnabled(false);
		btnAsignarTrabajo1.setBounds(330, 40, 120, 23);
		
		panelComision.add(btnAsignarTrabajo1);
		btnAsignarTrabajo3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				evento.getMisComisiones().get(2).createTrabajo();
				RegTrabajo traFrame = new RegTrabajo(evento.getMisComisiones().get(2), evento);
				traFrame.setModal(true);
				traFrame.setVisible(true);
			}
		});
		btnAsignarTrabajo3.setEnabled(false);
		btnAsignarTrabajo3.setBounds(330, 130, 120, 23);
		
		panelComision.add(btnAsignarTrabajo3);
		btnAsignarTrabajo4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				evento.getMisComisiones().get(3).createTrabajo();
				RegTrabajo traFrame = new RegTrabajo(evento.getMisComisiones().get(3), evento);
				traFrame.setModal(true);
				traFrame.setVisible(true);
			}
		});
		btnAsignarTrabajo4.setEnabled(false);
		btnAsignarTrabajo4.setBounds(330, 169, 120, 23);
		
		panelComision.add(btnAsignarTrabajo4);
		label_3.setBounds(10, 44, 68, 14);
		
		panelComision.add(label_3);
		txtComision1.setEditable(false);
		txtComision1.setColumns(10);
		txtComision1.setBounds(88, 41, 102, 20);
		
		panelComision.add(txtComision1);
		btnCrearComision1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegComision comFrame = new RegComision(evento);
				comFrame.setModal(true);
				comFrame.setVisible(true);
				if(evento.getMisComisiones().size() > 0) {
					txtComision1.setText(evento.getMisComisiones().get(0).getTema());
					if(!txtComision1.getText().isEmpty()) {
						btnAsignarTrabajo1.setEnabled(true);
					}
					
				}
				
			}
		});
		btnCrearComision1.setBounds(200, 40, 120, 23);
		
		panelComision.add(btnCrearComision1);
		label_4.setBounds(10, 90, 68, 14);
		
		panelComision.add(label_4);
		txtComision2.setEditable(false);
		txtComision2.setColumns(10);
		txtComision2.setBounds(88, 87, 102, 20);
		
		panelComision.add(txtComision2);
		btnCrearComision2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegComision comFrame = new RegComision(evento);
				comFrame.setModal(true);
				comFrame.setVisible(true);
				if(!evento.getMisComisiones().get(1).equals(null)) {
					txtComision2.setText(evento.getMisComisiones().get(1).getTema());
					if(!txtComision2.getText().isEmpty()){
						btnAsignarTrabajo2.setEnabled(true);
					}
					
				}
				
			}
		});
		btnCrearComision2.setBounds(200, 86, 120, 23);
		
		panelComision.add(btnCrearComision2);
		label_5.setBounds(10, 136, 68, 14);
		
		panelComision.add(label_5);
		txtComision3.setEditable(false);
		txtComision3.setColumns(10);
		txtComision3.setBounds(88, 133, 102, 20);
		
		panelComision.add(txtComision3);
		btnCrearComision3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegComision comFrame = new RegComision(evento);
				comFrame.setModal(true);
				comFrame.setVisible(true);
				if(!evento.getMisComisiones().get(2).equals(null)) {
					txtComision3.setText(evento.getMisComisiones().get(2).getTema());
					if(!txtComision3.getText().isEmpty()){
						btnAsignarTrabajo3.setEnabled(true);
					}
					
				}
			
			}
		});
		btnCrearComision3.setBounds(200, 130, 120, 23);
		
		panelComision.add(btnCrearComision3);
		label_7.setBounds(10, 180, 68, 14);
		
		panelComision.add(label_7);
		txtComision4.setEditable(false);
		txtComision4.setColumns(10);
		txtComision4.setBounds(88, 172, 102, 20);
		
		panelComision.add(txtComision4);
		btnCrearComision4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegComision comFrame = new RegComision(evento);
				comFrame.setModal(true);
				comFrame.setVisible(true);
				if(!evento.getMisComisiones().get(3).equals(null)) {
					txtComision4.setText(evento.getMisComisiones().get(3).getTema());
					if(!txtComision4.getText().isEmpty()){
						btnAsignarTrabajo4.setEnabled(true);
					}
				}
			}
		});
		btnCrearComision4.setBounds(200, 169, 120, 23);
		
		panelComision.add(btnCrearComision4);
		btnAgregarRecurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListRecursos frameRecurso = new ListRecursos(evento);
				frameRecurso.setModal(true);
				frameRecurso.setVisible(true);
				txtCantRecursos.setText(String.valueOf(evento.getMisRecursos().size()));
			}
		});
		btnAgregarRecurso.setHorizontalAlignment(SwingConstants.LEFT);
		btnAgregarRecurso.setBounds(200, 202, 89, 28);
		
		panelComision.add(btnAgregarRecurso);
		txtCantRecursos.setText("0");
		txtCantRecursos.setEditable(false);
		txtCantRecursos.setColumns(10);
		txtCantRecursos.setBounds(110, 204, 55, 23);
		
		panelComision.add(txtCantRecursos);
		label_8.setBounds(10, 208, 99, 16);
		
		panelComision.add(label_8);
		{
			panelReg.setBounds(119, 6, 533, 192);
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
				txtId.setText(miEvento.getId());
			}
			txtId.setBounds(47, 12, 102, 28);
			panelReg.add(txtId);
			txtId.setColumns(10);
			
			JLabel lblNombre = new JLabel("Nombre: ");
			lblNombre.setBounds(6, 52, 55, 16);
			panelReg.add(lblNombre);
			txtNombre = new JTextField();
			txtNombre.setBounds(57, 46, 244, 28);
			if(miEvento != null) {
				txtNombre.setText(miEvento.getNombre());
			}
			panelReg.add(txtNombre);
			txtNombre.setColumns(10);
			
			JLabel lblArea = new JLabel("Area:");
			lblArea.setBounds(6, 96, 41, 16);
			panelReg.add(lblArea);
			
			cbxArea.setModel(new DefaultComboBoxModel<String>(new String[] {"<Seleccione>", "Fisica", "Quimica", "Biologia/Medicina", "Mercadeo/Administracion", "Informatica/Redes"}));
			cbxArea.setBounds(47, 91, 156, 26);
			if(miEvento != null) {
				cbxArea.setSelectedItem(miEvento.getArea());
			}
			panelReg.add(cbxArea);
		}
		
		JLabel lblLugar = new JLabel("Lugar:");
		lblLugar.setBounds(6, 135, 55, 16);
		panelReg.add(lblLugar);
		
		
		cbxLugar.setModel(new DefaultComboBoxModel<String>(new String[] {"<Seleccione>", "Multiuso", "Teatro", "Anfiteatro", "Auditorio I", "Sede Postgrado", "Sala Reuniones (PA)"}));
		cbxLugar.setBounds(60, 130, 144, 26);
		if(miEvento != null) {
			cbxLugar.setSelectedItem(miEvento.getLugar());
		}
		panelReg.add(cbxLugar);
		
		JLabel lblCamps = new JLabel("Camp\u00FAs:");
		lblCamps.setBounds(238, 96, 55, 16);
		panelReg.add(lblCamps);
		
		
		cbxCampus.setModel(new DefaultComboBoxModel<String>(new String[] {"CSTI", "CSTA"}));
		cbxCampus.setBounds(304, 91, 64, 26);
		if(miEvento != null) {
			cbxCampus.setSelectedItem(miEvento.getCampus());
		}
		panelReg.add(cbxCampus);
		
		JLabel campoObligatorio = new JLabel("*");
		campoObligatorio.setBounds(313, 52, 55, 16);
		panelReg.add(campoObligatorio);
		
		JLabel label_1 = new JLabel("*");
		label_1.setBounds(215, 96, 29, 16);
		panelReg.add(label_1);
		label_2.setBounds(216, 135, 55, 16);
		
		panelReg.add(label_2);
		
		panelVariosDias.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelVariosDias.setBackground(new Color(190,209,201));
		panelVariosDias.setBounds(119, 211, 533, 130);
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
		lblFechaFinalizacin.setBounds(252, 21, 115, 16);
		panelVariosDias.add(lblFechaFinalizacin);
		
		spnFechaFin.setModel(new SpinnerDateModel());
		JSpinner.DateEditor te = new JSpinner.DateEditor(spnFechaFin, "dd/MM/yyy");
		spnFechaFin.setEditor(te);
		spnFechaFin.setBounds(379, 15, 148, 28);
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
		lblHoraDeFinalizacion.setBounds(252, 70, 115, 16);
		panelVariosDias.add(lblHoraDeFinalizacion);
		
		
		spnHoraFin.setModel(new SpinnerDateModel());
		JSpinner.DateEditor horaFin = new JSpinner.DateEditor(spnHoraFin, "HH:mm:ss");
		spnHoraFin.setEditor(horaFin);
		spnHoraFin.setBounds(379, 64, 148, 28);
		try {
			spnHoraFin.setValue(formatoHora.parse("8:00"));
		} catch (ParseException e2) {
			
			e2.printStackTrace();
		}
		panelVariosDias.add(spnHoraFin);
		panelImagen.setBounds(6, 6, 104, 335);
		contentPanel.add(panelImagen);
		panelImagen.setLayout(new BorderLayout(0, 0));
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(RegEvent.class.getResource("/img/RegEVento.jpg")));
		panelImagen.add(label, BorderLayout.NORTH);
		{
			{
				
				if(miEvento == null) {
					btnRegistrar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							if(panelComision.isVisible()) {
								if(txtComision1.getText().isEmpty() && txtComision2.getText().isEmpty() &&	 txtComision3.getText().isEmpty() && 
										txtComision4.getText().isEmpty()){
									JOptionPane.showMessageDialog(null, "Debe registrar por lo menos una comision", "Aviso", JOptionPane.WARNING_MESSAGE);
									
									
								}
								else if((!txtComision1.getText().isEmpty() && evento.getMisComisiones().get(0).getMisTrabajos().isEmpty()) || (!txtComision2.getText().isEmpty() && evento.getMisComisiones().get(1).getMisTrabajos().isEmpty()) 
										|| (!txtComision3.getText().isEmpty() && evento.getMisComisiones().get(2).getMisTrabajos().isEmpty()) || (!txtComision4.getText().isEmpty() && evento.getMisComisiones().get(3).getMisTrabajos().isEmpty())){
									
										JOptionPane.showMessageDialog(null, "Debe asignar los trabajos a las comisiones creadas", "Aviso", JOptionPane.WARNING_MESSAGE);
									
								}
								else if(evento.getMisRecursos().isEmpty()) {
										JOptionPane.showMessageDialog(null, "No asigno ningun recurso", "Aviso", JOptionPane.WARNING_MESSAGE);
										
								}
								
								else {
									int option = JOptionPane.showConfirmDialog(null,"Esta seguro que desea efectuar la operacion?",
											"Advertencia",JOptionPane.WARNING_MESSAGE);
									
									if(option == JOptionPane.OK_OPTION) {
										Principal.createLineChart();
										Principal.createPieChart();	
										PUCMM.pucmm().crearEvento(evento);
										JOptionPane.showMessageDialog(null, "Operacion Satisfactoria", "Guardado", JOptionPane.INFORMATION_MESSAGE);
										clean();
										dispose();
									}
								}
								
							}
						}
					});
				}
				if(miEvento != null) {
					btnRegistrar.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							Evento modEvento = PUCMM.pucmm().searchEventoById(miEvento.getId());
							modEvento.setNombre(txtNombre.getText());
							modEvento.setArea( cbxArea.getSelectedItem().toString());
							modEvento.setLugar(cbxLugar.getSelectedItem().toString());
							modEvento.setCampus(cbxCampus.getSelectedItem().toString());
							modEvento.setFechaIni((Date)spnFechaInicio.getValue());
							modEvento.setFechaFin((Date)spnFechaFin.getValue());
							modEvento.setHorarioInicio((Date)spnHoraIni.getValue());
							modEvento.setHorarioFin((Date)spnHoraFin.getValue());
							dispose();
						}
					});
				}
			}
		}
		JPanel buttonPane = new JPanel();
		buttonPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		buttonPane.setBackground(new Color(190,209,201));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtNombre.getText().isEmpty() || cbxArea.getSelectedIndex() == 0 || cbxLugar.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "Por favor rellene los campos obligatorios", "ERROR!", JOptionPane.WARNING_MESSAGE);
					}

				else{
					evento = new Evento(txtId.getText(),txtNombre.getText(), cbxArea.getSelectedItem().toString(),cbxLugar.getSelectedItem().toString(),
					cbxCampus.getSelectedItem().toString(),(Date)spnFechaInicio.getValue(),(Date)spnFechaFin.getValue(),(Date)spnHoraIni.getValue(),
					(Date)spnHoraFin.getValue());
					panelComision.setVisible(true);
					panelReg.setVisible(false);
					panelVariosDias.setVisible(false);
					btnAtrs.setEnabled(true);
					btnSiguiente.setEnabled(false);
					btnRegistrar.setEnabled(true);
					}
			}
		});
		lblCamposObligatorios.setFont(new Font("SansSerif", Font.ITALIC, 12));
		lblCamposObligatorios.setDisplayedMnemonic('C');
		lblCamposObligatorios.setHorizontalAlignment(SwingConstants.CENTER);
		
		buttonPane.add(lblCamposObligatorios);
		btnAtrs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelComision.setVisible(false);
				panelReg.setVisible(true);
				panelVariosDias.setVisible(true);
				btnAtrs.setEnabled(false);
				btnSiguiente.setEnabled(true);
			}
		});
		btnAtrs.setEnabled(false);
		buttonPane.add(btnAtrs);
		buttonPane.add(btnSiguiente);
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
					}
					
				}
			});
			btnCancelar.setActionCommand("Cancel");
			buttonPane.add(btnCancelar);
		}
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
}
