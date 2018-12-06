package Visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Logico.PUCMM;
import Logico.Recurso;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.border.BevelBorder;
import javax.swing.SpinnerNumberModel;
import java.awt.Toolkit;

public class RegRecursos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtID;
	private JTextField txtModelo;
	private JComboBox<String> cbxTipo = new JComboBox<String>();

	public RegRecursos() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegRecursos.class.getResource("/img/Icono_pucmm.jpg")));
		setTitle("Registrar Recurso");
		setBounds(100, 100, 477, 243);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		contentPanel.setBackground(new Color(190,209,201));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setBackground(new Color(190,209,201));
			panel.setLayout(null);
			{
				JLabel lblId = new JLabel("ID: ");
				lblId.setBounds(6, 19, 55, 16);
				panel.add(lblId);
			}
			
			txtID = new JTextField();
			txtID.setEditable(false);
			txtID.setText(Integer.toString(PUCMM.pucmm().getMisRecursos().size()+1));
			txtID.setBounds(34, 13, 122, 28);
			panel.add(txtID);
			txtID.setColumns(10);
			
			JLabel lblModelo = new JLabel("Modelo:");
			lblModelo.setBounds(6, 57, 55, 16);
			panel.add(lblModelo);
			
			txtModelo = new JTextField();
			txtModelo.setBounds(62, 51, 214, 28);
			panel.add(txtModelo);
			txtModelo.setColumns(10);
			
			JLabel lblTipo = new JLabel("Tipo:");
			lblTipo.setBounds(6, 96, 55, 16);
			panel.add(lblTipo);
			
			
			cbxTipo.setModel(new DefaultComboBoxModel<String>(new String[] {"<Seleccione>", "Audio", "Visual", "Computadora", "Luces", "Pirotecnia"}));
			cbxTipo.setBounds(48, 91, 122, 26);
			panel.add(cbxTipo);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(190,209,201));
			buttonPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int option = JOptionPane.showConfirmDialog(null,"Esta seguro que desea efectuar la operacion?",
								"Advertencia",JOptionPane.WARNING_MESSAGE);
						if(option == JOptionPane.OK_OPTION) {
							Recurso miRecurso = new Recurso(PUCMM.pucmm().getMisRecursos().size()+1,txtModelo.getText(), cbxTipo.getSelectedItem().toString());
							PUCMM.pucmm().getMisRecursos().add(miRecurso);
							clean();
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
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
	private void clean(){
		txtID.setText(Integer.toString(PUCMM.pucmm().getMisRecursos().size()+1));
		txtModelo.setText("");
		cbxTipo.setSelectedIndex(0);
		
		
	}
}
