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

import Logico.Evento;
import Logico.PUCMM;

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
	private JRadioButton rdbtnEventoDeVarios = new JRadioButton();
	private JRadioButton rdbtnEventoDeUn = new JRadioButton();
	private JPanel panelVariosDias = new JPanel();
	private JPanel panelUnDia = new JPanel();
	private JSpinner spnFechaFin = new JSpinner();
	private JSpinner spnFechaInicio = new JSpinner();
	private JSpinner spnHoraIni1 = new JSpinner();
	private JSpinner spnHoraIni = new JSpinner();
	private JSpinner spnHoraFin = new JSpinner();
	private JSpinner spnHoraFin1 = new JSpinner();
	private JSpinner spnDiaDelEvento = new JSpinner();
	private JPanel panelImagen = new JPanel();
	private JPanel panelReg = new JPanel();
	private Evento evento;
	private SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
	private final JButton btnSiguiente = new JButton("Siguiente");
	private final JLabel lblCantComisiones = new JLabel("Cant. Comisiones:");
	private final JSpinner spnCantComisiones = new JSpinner();
	private final JLabel label_2 = new JLabel("*");
	private final JLabel lblCamposObligatorios = new JLabel("* Campos Obligatorios");
	private final JPanel panelComision = new JPanel();
	private final JLabel label_3 = new JLabel("Comision 1:");
	private final JTextField textField = new JTextField();
	private final JButton button = new JButton("Crear Comision");
	private final JButton button_1 = new JButton("Asignar Trabajos");
	private final JLabel label_4 = new JLabel("Comision 2:");
	private final JTextField textField_1 = new JTextField();
	private final JButton button_2 = new JButton("Crear Comision");
	private final JButton button_3 = new JButton("Asignar Trabajos");
	private final JLabel label_5 = new JLabel("Comision 3:");
	private final JTextField textField_2 = new JTextField();
	private final JButton button_4 = new JButton("Crear Comision");
	private final JButton button_5 = new JButton("Asignar Trabajos");
	private final JLabel label_6 = new JLabel("Representante");
	private final JRadioButton radioButton = new JRadioButton("");
	private final JRadioButton radioButton_1 = new JRadioButton("");
	private final JRadioButton radioButton_2 = new JRadioButton("");
	private final JLabel label_7 = new JLabel("Comision 4:");
	private final JTextField textField_3 = new JTextField();
	private final JButton button_6 = new JButton("Crear Comision");
	private final JButton button_7 = new JButton("Asignar Trabajos");
	private final JRadioButton radioButton_3 = new JRadioButton("");
	

	
	public RegEvent() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegEvent.class.getResource("/img/Icono_pucmm.jpg")));
		setTitle("Registrar Nuevo Evento");
		setBounds(100, 100, 674, 437);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.setBackground(new Color(190,209,201));
		{
			panelReg.setBounds(119, 5, 533, 192);
			panelReg.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			panelReg.setBackground(new Color(190,209,201));
			contentPanel.add(panelReg);
			panelReg.setLayout(null);
			
			JLabel lblId = new JLabel("ID: ");
			lblId.setBounds(6, 18, 29, 16);
			panelReg.add(lblId);
			
			txtId = new JTextField();
			txtId.setEditable(false);
			txtId.setText(Integer.toString(PUCMM.pucmm().getCantEventos() + 1));
			txtId.setBounds(47, 12, 102, 28);
			panelReg.add(txtId);
			txtId.setColumns(10);
			
			JLabel lblNombre = new JLabel("Nombre: ");
			lblNombre.setBounds(6, 52, 55, 16);
			panelReg.add(lblNombre);
			
			txtNombre = new JTextField();
			txtNombre.setBounds(57, 46, 244, 28);
			panelReg.add(txtNombre);
			txtNombre.setColumns(10);
			
			JLabel lblArea = new JLabel("Area:");
			lblArea.setBounds(6, 133, 41, 16);
			panelReg.add(lblArea);
			
			cbxArea.setModel(new DefaultComboBoxModel<String>(new String[] {"<Seleccione>", "Fisica", "Quimica", "Biologia/Medicina", "Mercadeo/Administracion", "Informatica/Redes"}));
			cbxArea.setBounds(47, 128, 156, 26);
			panelReg.add(cbxArea);
			rdbtnEventoDeVarios.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(rdbtnEventoDeVarios.isSelected()) {
						rdbtnEventoDeUn.setSelected(false);
						panelVariosDias.setVisible(true);
						panelUnDia.setVisible(false);
					}
					else {
						panelVariosDias.setVisible(false);
					}
				}
			});
			
			
			rdbtnEventoDeVarios.setSelected(true);
			rdbtnEventoDeVarios.setText("Evento de Varios D\u00EDas");
			rdbtnEventoDeVarios.setBackground(new Color(190,209,201));
			rdbtnEventoDeVarios.setBounds(45, 158, 168, 18);
			panelReg.add(rdbtnEventoDeVarios);
			rdbtnEventoDeUn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(rdbtnEventoDeUn.isSelected()) {
						rdbtnEventoDeVarios.setSelected(false);
						panelUnDia.setVisible(true);
						panelVariosDias.setVisible(false);
					}
					else {
						panelUnDia.setVisible(false);
					}
					
					
				}
			});
			
			
			rdbtnEventoDeUn.setBounds(256, 158, 156, 18);
			rdbtnEventoDeUn.setText("Evento de Un Solo D\u00EDa");
			rdbtnEventoDeUn.setBackground(new Color(190,209,201));
			panelReg.add(rdbtnEventoDeUn);
		}
		
		JLabel lblLugar = new JLabel("Lugar:");
		lblLugar.setBounds(246, 133, 55, 16);
		panelReg.add(lblLugar);
		
		
		cbxLugar.setModel(new DefaultComboBoxModel<String>(new String[] {"<Seleccione>", "Multiuso", "Teatro", "Anfiteatro", "Auditorio I", "Sede Postgrado", "Sala Reuniones (PA)"}));
		cbxLugar.setBounds(300, 128, 144, 26);
		panelReg.add(cbxLugar);
		
		JLabel lblCamps = new JLabel("Camp\u00FAs:");
		lblCamps.setBounds(220, 91, 55, 16);
		panelReg.add(lblCamps);
		
		
		cbxCampus.setModel(new DefaultComboBoxModel<String>(new String[] {"CSTI", "CSTA"}));
		cbxCampus.setBounds(290, 86, 64, 26);
		panelReg.add(cbxCampus);
		lblCantComisiones.setBounds(6, 89, 118, 16);
		
		panelReg.add(lblCantComisiones);
		spnCantComisiones.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spnCantComisiones.setBounds(118, 83, 74, 28);
		
		panelReg.add(spnCantComisiones);
		
		JLabel campoObligatorio = new JLabel("*");
		campoObligatorio.setBounds(313, 52, 55, 16);
		panelReg.add(campoObligatorio);
		
		JLabel label_1 = new JLabel("*");
		label_1.setBounds(215, 133, 29, 16);
		panelReg.add(label_1);
		label_2.setBounds(456, 133, 55, 16);
		
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
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		panelVariosDias.add(spnHoraFin);
		
		panelUnDia.setVisible(false);
		panelUnDia.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panelUnDia.setBounds(119, 211, 533, 130);
		panelUnDia.setBackground(new Color(190,209,201));
		contentPanel.add(panelUnDia);
		panelUnDia.setLayout(null);
		
		
		spnHoraIni1.setModel(new SpinnerDateModel());
		JSpinner.DateEditor horaini1 = new JSpinner.DateEditor(spnHoraIni1, "HH:mm:ss");
		spnHoraIni1.setEditor(horaini1);
		spnHoraIni1.setBounds(92, 15, 148, 28);
		try {
			spnHoraIni1.setValue(formatoHora.parse("8:00"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		panelUnDia.add(spnHoraIni1);
		
		JLabel lblHoraInicio = new JLabel("Hora De Inicio:");
		lblHoraInicio.setBounds(6, 21, 84, 16);
		panelUnDia.add(lblHoraInicio);
		
		JLabel lblHoraFin = new JLabel("Hora de Finalizaci\u00F3n:");
		lblHoraFin.setBounds(252, 21, 115, 16);
		panelUnDia.add(lblHoraFin);
		
		
		spnHoraFin1.setModel(new SpinnerDateModel());
		JSpinner.DateEditor horaFin1 = new JSpinner.DateEditor(spnHoraFin1, "HH:mm:ss");
		spnHoraFin1.setEditor(horaFin1);	
		spnHoraFin1.setBounds(379, 15, 148, 28);
		try {
			spnHoraFin1.setValue(formatoHora.parse("8:00"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		panelUnDia.add(spnHoraFin1);
		
		spnDiaDelEvento.setModel(new SpinnerDateModel());
		JSpinner.DateEditor diaEvento = new JSpinner.DateEditor(spnDiaDelEvento, "dd/MM/yyy");
		spnDiaDelEvento.setEditor(diaEvento);
		spnDiaDelEvento.setBounds(94, 64, 148, 28);
		panelUnDia.add(spnDiaDelEvento);
		
		JLabel lblDiaDelEvento = new JLabel("Dia Del Evento:");
		lblDiaDelEvento.setBounds(6, 70, 91, 16);
		panelUnDia.add(lblDiaDelEvento);
		
		
		panelImagen.setBounds(6, 6, 104, 335);
		contentPanel.add(panelImagen);
		panelImagen.setLayout(new BorderLayout(0, 0));
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(RegEvent.class.getResource("/img/RegEVento.jpg")));
		panelImagen.add(label, BorderLayout.NORTH);
		panelComision.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelComision.setVisible(false);
		panelComision.setBounds(119, 5, 533, 243);
		contentPanel.add(panelComision);
		panelComision.setLayout(null);
		label_3.setBounds(10, 44, 68, 14);
		
		panelComision.add(label_3);
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(88, 41, 102, 20);
		
		panelComision.add(textField);
		button.setBounds(200, 40, 120, 23);
		
		panelComision.add(button);
		button_1.setBounds(330, 40, 120, 23);
		
		panelComision.add(button_1);
		label_4.setBounds(10, 90, 68, 14);
		
		panelComision.add(label_4);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(88, 87, 102, 20);
		
		panelComision.add(textField_1);
		button_2.setBounds(200, 86, 120, 23);
		
		panelComision.add(button_2);
		button_3.setBounds(330, 86, 120, 23);
		
		panelComision.add(button_3);
		label_5.setBounds(10, 136, 68, 14);
		
		panelComision.add(label_5);
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(88, 133, 102, 20);
		
		panelComision.add(textField_2);
		button_4.setBounds(200, 130, 120, 23);
		
		panelComision.add(button_4);
		button_5.setBounds(330, 130, 120, 23);
		
		panelComision.add(button_5);
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setBounds(449, 11, 84, 14);
		
		panelComision.add(label_6);
		radioButton.setBounds(474, 40, 59, 23);
		
		panelComision.add(radioButton);
		radioButton_1.setBounds(474, 86, 59, 23);
		
		panelComision.add(radioButton_1);
		radioButton_2.setBounds(474, 132, 59, 23);
		
		panelComision.add(radioButton_2);
		label_7.setBounds(10, 180, 68, 14);
		
		panelComision.add(label_7);
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(88, 172, 102, 20);
		
		panelComision.add(textField_3);
		button_6.setBounds(200, 169, 120, 23);
		
		panelComision.add(button_6);
		button_7.setBounds(330, 169, 120, 23);
		
		panelComision.add(button_7);
		radioButton_3.setBounds(474, 171, 59, 23);
		
		panelComision.add(radioButton_3);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			buttonPane.setBackground(new Color(190,209,201));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRegistrar = new JButton("Registrar");
				btnRegistrar.setEnabled(false);
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						int option = JOptionPane.showConfirmDialog(null,"Esta seguro que desea efectuar la operacion?",
								"Advertencia",JOptionPane.WARNING_MESSAGE);
						
						if(option == JOptionPane.OK_OPTION) {
							
							Principal.createLineChart();
							Principal.createPieChart();	
							PUCMM.pucmm().crearEvento(evento);
							JOptionPane.showMessageDialog(null, "Operacion Satisfactoria", "Guardado", JOptionPane.INFORMATION_MESSAGE);
						
							clean();
						}
					
					}
				});
				btnSiguiente.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						
						if(txtNombre.getText().isEmpty() || cbxArea.getSelectedIndex() == 0 || cbxLugar.getSelectedIndex() == 0) {
							JOptionPane.showMessageDialog(null, "Por favor rellene los campos obligatorios", "ERROR!", JOptionPane.WARNING_MESSAGE);
						}
						
						else {
							if(rdbtnEventoDeVarios.isSelected()) {
								evento = new Evento(txtId.getText(),txtNombre.getText(), cbxArea.getSelectedItem().toString(),cbxLugar.getSelectedItem().toString(),
									cbxCampus.getSelectedItem().toString(),(Date)spnFechaInicio.getValue(),(Date)spnFechaFin.getValue(),(Date)spnHoraIni.getValue(),
										(Date)spnHoraFin.getValue(), (Integer) spnCantComisiones.getValue());
								
							}
							else if(rdbtnEventoDeUn.isSelected()) {
							 evento = new Evento(txtId.getText(),txtNombre.getText(), cbxArea.getSelectedItem().toString(),cbxLugar.getSelectedItem().toString(),
									cbxCampus.getSelectedItem().toString(),(Date)spnDiaDelEvento.getValue(),(Date)spnDiaDelEvento.getValue(),(Date)spnHoraIni1.getValue(),
									(Date)spnHoraFin1.getValue(),(Integer)spnCantComisiones.getValue());
							 	}
							
							panelComision.setVisible(true);
							panelReg.setVisible(false);
							panelUnDia.setVisible(false);
							panelVariosDias.setVisible(false);
		
							/**RegComision comFrame = new RegComision(evento);
							comFrame.setModal(true);
							comFrame.setVisible(true);
							*/
						}
						
						
					}
				});
				lblCamposObligatorios.setFont(new Font("SansSerif", Font.ITALIC, 12));
				lblCamposObligatorios.setDisplayedMnemonic('C');
				lblCamposObligatorios.setHorizontalAlignment(SwingConstants.CENTER);
				
				buttonPane.add(lblCamposObligatorios);
				
				JButton btnAtrs = new JButton("Atr\u00E1s");
				btnAtrs.setEnabled(false);
				buttonPane.add(btnAtrs);
				buttonPane.add(btnSiguiente);
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
			spnHoraFin1.setValue(formatoHora.parse("8:00"));
			spnHoraIni.setValue(formatoHora.parse("8:00"));
			spnHoraIni1.setValue(formatoHora.parse("8:00"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		spnDiaDelEvento.setValue(new Date());
		
		
		
	}
}
