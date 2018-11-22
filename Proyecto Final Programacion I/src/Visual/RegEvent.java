package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;

public class RegEvent extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtId;
	private JTextField txtNombre;
	private JSpinner spnFechaFin = new JSpinner();
	private JComboBox<String> cbxArea = new JComboBox<>();
	private JRadioButton rdbtnEventoDeVarios = new JRadioButton("Evento de Varios D\u00EDas");
	private JRadioButton rdbtnEventoDeUn = new JRadioButton("Evento de Un Solo D\u00EDa");
	private JPanel panelVariosDias = new JPanel();
	private JPanel panelUnDia = new JPanel();
	private JSpinner spnHoraInicio1 = new JSpinner();
	private JSpinner spnFechaInicio = new JSpinner();
	private JSpinner spnHoraIni = new JSpinner();
	private JSpinner spnHoraFin = new JSpinner();
	private JPanel panelImagen = new JPanel();
	private JPanel panelReg = new JPanel();
	private JSpinner spnHoraFinalizacion1 = new JSpinner();
	
	public RegEvent() {
		setBounds(100, 100, 674, 426);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			panelReg.setBounds(109, 5, 543, 194);
			panelReg.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
			contentPanel.add(panelReg);
			panelReg.setLayout(null);
			
			JLabel lblId = new JLabel("ID: ");
			lblId.setBounds(6, 42, 29, 16);
			panelReg.add(lblId);
			
			txtId = new JTextField();
			txtId.setEditable(false);
			txtId.setBounds(57, 36, 102, 28);
			panelReg.add(txtId);
			txtId.setColumns(10);
			
			JLabel lblNombre = new JLabel("Nombre: ");
			lblNombre.setBounds(6, 76, 55, 16);
			panelReg.add(lblNombre);
			
			txtNombre = new JTextField();
			txtNombre.setBounds(57, 70, 244, 28);
			panelReg.add(txtNombre);
			txtNombre.setColumns(10);
			
			JLabel lblArea = new JLabel("Area:");
			lblArea.setBounds(6, 117, 41, 16);
			panelReg.add(lblArea);
			
			
			cbxArea.setModel(new DefaultComboBoxModel<String>(new String[] {"<Seleccione>", "Ciencias/Tecnolog\u00EDa", "Medicina", "Mercadeo/Administracion", "Deportivo"}));
			cbxArea.setBounds(57, 112, 156, 26);
			panelReg.add(cbxArea);
			
			
			rdbtnEventoDeVarios.setSelected(true);
			rdbtnEventoDeVarios.setBounds(45, 158, 168, 18);
			panelReg.add(rdbtnEventoDeVarios);
			
			
			rdbtnEventoDeUn.setBounds(225, 158, 156, 18);
			panelReg.add(rdbtnEventoDeUn);
		}
		
		
		panelVariosDias.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panelVariosDias.setBounds(109, 211, 543, 130);
		contentPanel.add(panelVariosDias);
		panelVariosDias.setLayout(null);
		
		JLabel lblFechaInicio = new JLabel("Fecha Inicio:");
		lblFechaInicio.setBounds(6, 21, 74, 16);
		panelVariosDias.add(lblFechaInicio);
		
		
		spnFechaInicio.setModel(new SpinnerDateModel(new Date(1542859200000L), new Date(1542859200000L), null, Calendar.DAY_OF_YEAR));
		spnFechaInicio.setBounds(92, 15, 148, 28);
		panelVariosDias.add(spnFechaInicio);
		
		JLabel lblFechaFinalizacin = new JLabel("Fecha Finalizaci\u00F3n:");
		lblFechaFinalizacin.setBounds(252, 21, 115, 16);
		panelVariosDias.add(lblFechaFinalizacin);
		
		
		spnFechaFin.setModel(new SpinnerDateModel(new Date(1542859200000L), new Date(1542859200000L), null, Calendar.DAY_OF_YEAR));
		spnFechaFin.setBounds(379, 15, 148, 28);
		panelVariosDias.add(spnFechaFin);
		
		JLabel lblHoraDeInicio = new JLabel("Hora de Inicio:");
		lblHoraDeInicio.setBounds(6, 70, 84, 16);
		panelVariosDias.add(lblHoraDeInicio);
		
		
		spnHoraIni.setModel(new SpinnerDateModel(new Date(1542859200000L), new Date(1542859200000L), null, Calendar.DAY_OF_YEAR));
		spnHoraIni.setBounds(92, 64, 148, 28);
		panelVariosDias.add(spnHoraIni);
		
		JLabel lblHoraDeFinalizacion = new JLabel("Hora de Finalizacion:");
		lblHoraDeFinalizacion.setBounds(252, 70, 115, 16);
		panelVariosDias.add(lblHoraDeFinalizacion);
		
		
		spnHoraFin.setModel(new SpinnerDateModel(new Date(1542859200000L), new Date(1542859200000L), null, Calendar.DAY_OF_YEAR));
		spnHoraFin.setBounds(379, 64, 148, 28);
		panelVariosDias.add(spnHoraFin);
		
		
		panelUnDia.setVisible(false);
		panelUnDia.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panelUnDia.setBounds(109, 211, 543, 130);
		contentPanel.add(panelUnDia);
		panelUnDia.setLayout(null);
		
		
		spnHoraInicio1.setModel(new SpinnerDateModel(new Date(1542859200000L), new Date(1542859200000L), null, Calendar.DAY_OF_YEAR));
		spnHoraInicio1.setBounds(92, 39, 148, 28);
		panelUnDia.add(spnHoraInicio1);
		
		JLabel lblHoraInicio = new JLabel("Hora De Inicio:");
		lblHoraInicio.setBounds(6, 46, 84, 16);
		panelUnDia.add(lblHoraInicio);
		
		JLabel lblHoraFin = new JLabel("Hora de Finalizaci\u00F3n:");
		lblHoraFin.setBounds(252, 45, 115, 16);
		panelUnDia.add(lblHoraFin);
		
		
		spnHoraFinalizacion1.setModel(new SpinnerDateModel(new Date(1542859200000L), new Date(1542859200000L), null, Calendar.DAY_OF_YEAR));
		spnHoraFinalizacion1.setBounds(379, 39, 148, 28);
		panelUnDia.add(spnHoraFinalizacion1);
		
		
		panelImagen.setBounds(6, 6, 104, 335);
		contentPanel.add(panelImagen);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRegistrar = new JButton("Registrar");
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
}
