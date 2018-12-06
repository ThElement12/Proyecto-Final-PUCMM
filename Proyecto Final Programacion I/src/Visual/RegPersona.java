package Visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import Logico.Juez;
import Logico.PUCMM;
import Logico.Participante;
import Logico.Persona;

import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;

public class RegPersona extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JLabel lblImagenPerfil = new JLabel("");
	private JTextField txtCedula;
	private ImageIcon imagen = new ImageIcon();
	private JRadioButton rdbtnParticipante;
	private JRadioButton rdbtnJuez;
	private JComboBox<String> cbxArea = new JComboBox<>();
	private boolean imagenSubida = false;

	
	public RegPersona() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegPersona.class.getResource("/img/Icono_pucmm.jpg")));
		setTitle("Registrar Persona ");
		setBounds(100, 100, 650, 373);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.setBackground(new Color(190,209,201));
		
		JPanel panel_principal = new JPanel();
		panel_principal.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_principal.setBounds(6, 6, 434, 264);
		contentPanel.add(panel_principal);
		panel_principal.setLayout(null);
		panel_principal.setBackground(new Color(190,209,201));
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(6, 25, 35, 16);
		panel_principal.add(lblId);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(41, 19, 122, 28);
		txtId.setText(Integer.toString(PUCMM.pucmm().getMisPersonas().size() + 1));
		panel_principal.add(txtId);
		txtId.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(6, 96, 55, 16);
		panel_principal.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(57, 90, 301, 28);
		panel_principal.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono:");
		lblTelfono.setBounds(6, 130, 55, 16);
		panel_principal.add(lblTelfono);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(63, 126, 320, 28);
		panel_principal.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		rdbtnJuez = new JRadioButton("Juez");
		rdbtnJuez.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnParticipante.isSelected()) {
					rdbtnJuez.setSelected(true);
					rdbtnParticipante.setSelected(false);
				}
				else if(rdbtnJuez.isSelected()) {
					rdbtnJuez.setSelected(true);
				}
			}
		});
		rdbtnJuez.setBounds(6, 202, 115, 18);
		panel_principal.add(rdbtnJuez);
		rdbtnJuez.setBackground(new Color(190,209,201));
		
		rdbtnParticipante = new JRadioButton("Participante");
		rdbtnParticipante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnJuez.isSelected()) {
					rdbtnParticipante.setSelected(true);
					rdbtnJuez.setSelected(false);
				}
				else if(rdbtnParticipante.isSelected()) {
					rdbtnParticipante.setSelected(true);
				}
			}
		});
		rdbtnParticipante.setSelected(true);
		rdbtnParticipante.setBounds(145, 202, 115, 18);
		rdbtnParticipante.setBackground(new Color(190,209,201));
		panel_principal.add(rdbtnParticipante);
		
		JLabel lblAreaEspecializado = new JLabel("Area Especializado:");
		lblAreaEspecializado.setBounds(6, 174, 108, 16);
		panel_principal.add(lblAreaEspecializado);
		
		
		cbxArea.setModel(new DefaultComboBoxModel<String>(new String[] {"<Seleccione>", "Fisica", "Quimica", "Biologia/Medicina", "Mercadeo/Administracion", "Informatica/Redes"}));
		cbxArea.setBounds(126, 169, 134, 26);
		panel_principal.add(cbxArea);
		
		JLabel lblCedula = new JLabel("C\u00E9dula: ");
		lblCedula.setBounds(6, 59, 55, 16);
		panel_principal.add(lblCedula);
		
		txtCedula = new JTextField();
		txtCedula.setBounds(51, 53, 248, 28);
		panel_principal.add(txtCedula);
		txtCedula.setColumns(10);
		
		JLabel lbll = new JLabel("*");
		lbll.setBounds(311, 59, 55, 16);
		panel_principal.add(lbll);
		
		JLabel label = new JLabel("*");
		label.setBounds(370, 96, 55, 16);
		panel_principal.add(label);
		
		JLabel label_1 = new JLabel("*");
		label_1.setBounds(395, 130, 33, 16);
		panel_principal.add(label_1);
		
		JLabel label_2 = new JLabel("*");
		label_2.setBounds(272, 174, 55, 16);
		panel_principal.add(label_2);
		
		JPanel panel_fotoPerfil = new JPanel();
		panel_fotoPerfil.setBounds(452, 6, 176, 264);
		contentPanel.add(panel_fotoPerfil);
		panel_fotoPerfil.setLayout(null);
		panel_fotoPerfil.setBackground(new Color(190,209,201));
		
		JButton btnSubirFoto = new JButton("Subir Foto...");
		btnSubirFoto.setHorizontalAlignment(SwingConstants.LEFT);
		btnSubirFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fotoPerfil = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
				fotoPerfil.setFileFilter(filter);
				int returnVal = fotoPerfil.showOpenDialog(btnSubirFoto);
				if(returnVal ==JFileChooser.APPROVE_OPTION) {
					imagen = new ImageIcon(fotoPerfil.getSelectedFile().getPath());
					lblImagenPerfil.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(96, 128, Image.SCALE_SMOOTH)));
					imagenSubida = true;
				}
			
			}
		});
		btnSubirFoto.setBounds(36, 164, 117, 28);
		panel_fotoPerfil.add(btnSubirFoto);
		
		
		lblImagenPerfil.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagenPerfil.setIcon(new ImageIcon(RegPersona.class.getResource("/img/iconfinder_user_118589(1).png")));
		lblImagenPerfil.setBounds(16, 6, 144, 144);
		panel_fotoPerfil.add(lblImagenPerfil);
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
						
						if(txtCedula.getText().isEmpty() || txtNombre.getText().isEmpty() || txtTelefono.getText().isEmpty() || cbxArea.getSelectedIndex() == 0) {
							JOptionPane.showMessageDialog(null, "Por favor rellene los campos obligatorios", "ERROR!", JOptionPane.ERROR_MESSAGE);
							
						}
						else if(!imagenSubida) {
							JOptionPane.showMessageDialog(null, "Por favor agregue una foto", "ERROR!", JOptionPane.ERROR_MESSAGE);
						}
						else {

							int option = JOptionPane.showConfirmDialog(null,"Esta seguro que desea efectuar la operacion?",
									"Advertencia",JOptionPane.WARNING_MESSAGE);
							if(option == JOptionPane.OK_OPTION) {
								if(rdbtnJuez.isSelected()) {
									Juez miJuez = new Juez(PUCMM.pucmm().getMisPersonas().size() + 1,txtCedula.getText(), txtNombre.getText(), txtTelefono.getText(),
									cbxArea.getSelectedItem().toString(),imagen.getImage());
									PUCMM.pucmm().getMisPersonas().add(miJuez);
								}
								else if(rdbtnParticipante.isSelected()) {
									Participante miParticipante = new Participante(PUCMM.pucmm().getMisPersonas().size() + 1,txtCedula.getText(), txtNombre.getText(), txtTelefono.getText()
									,cbxArea.getSelectedItem().toString(), imagen.getImage());
									
									PUCMM.pucmm().getMisPersonas().add(miParticipante);
								}
								clean();
						}
						
						
						}
						
					}
				});
				
				JLabel lblCamposObligatorios = new JLabel("* Campos Obligatorios");
				lblCamposObligatorios.setFont(new Font("SansSerif", Font.ITALIC, 12));
				lblCamposObligatorios.setDisplayedMnemonic('C');
				buttonPane.add(lblCamposObligatorios);
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
	private void clean() {
		txtId.setText(Integer.toString(PUCMM.pucmm().getMisPersonas().size() + 1));
		txtCedula.setText("");
		txtNombre.setText("");
		txtTelefono.setText("");
		cbxArea.setSelectedIndex(0);
		lblImagenPerfil.setIcon(new ImageIcon(RegPersona.class.getResource("/img/iconfinder_user_118589(1).png")));
		
		
	}
}
