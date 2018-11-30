package Visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class RegPersona extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtTelefono;

	public RegPersona() {
		setBounds(100, 100, 650, 430);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel_principal = new JPanel();
		panel_principal.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_principal.setBounds(6, 6, 415, 264);
		contentPanel.add(panel_principal);
		panel_principal.setLayout(null);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(6, 25, 35, 16);
		panel_principal.add(lblId);
		
		txtId = new JTextField();
		txtId.setBounds(41, 19, 122, 28);
		panel_principal.add(txtId);
		txtId.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(6, 59, 55, 16);
		panel_principal.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(57, 53, 163, 28);
		panel_principal.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono:");
		lblTelfono.setBounds(6, 93, 55, 16);
		panel_principal.add(lblTelfono);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(63, 89, 163, 28);
		panel_principal.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		JRadioButton rdbtnJuez = new JRadioButton("Juez");
		rdbtnJuez.setBounds(6, 156, 115, 18);
		panel_principal.add(rdbtnJuez);
		
		JRadioButton rdbtnParticipante = new JRadioButton("Participante");
		rdbtnParticipante.setSelected(true);
		rdbtnParticipante.setBounds(145, 156, 115, 18);
		panel_principal.add(rdbtnParticipante);
		
		JLabel lblAreaEspecializado = new JLabel("Area Especializado:");
		lblAreaEspecializado.setBounds(6, 128, 108, 16);
		panel_principal.add(lblAreaEspecializado);
		
		JComboBox cbxArea = new JComboBox();
		cbxArea.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Medicina", "Mercadeo", "Informatica", "Ingenieria General", "Arquitectura"}));
		cbxArea.setBounds(126, 123, 134, 26);
		panel_principal.add(cbxArea);
		
		JPanel panel_fotoPerfil = new JPanel();
		panel_fotoPerfil.setBounds(433, 6, 195, 264);
		contentPanel.add(panel_fotoPerfil);
		panel_fotoPerfil.setLayout(null);
		
		JButton btnSubirFoto = new JButton("Subir Foto...");
		btnSubirFoto.setHorizontalAlignment(SwingConstants.LEFT);
		btnSubirFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fotoPerfil = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
				fotoPerfil.setFileFilter(filter);
				int returnVal = fotoPerfil.showOpenDialog(btnSubirFoto);
				if(returnVal ==JFileChooser.APPROVE_OPTION) {
					
				}
			
			}
		});
		btnSubirFoto.setBounds(6, 166, 117, 28);
		panel_fotoPerfil.add(btnSubirFoto);
		
		JLabel lblImagenPerfil = new JLabel("");
		lblImagenPerfil.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagenPerfil.setIcon(new ImageIcon(RegPersona.class.getResource("/img/iconfinder_user_118589.png")));
		lblImagenPerfil.setBounds(6, 6, 144, 144);
		panel_fotoPerfil.add(lblImagenPerfil);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
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
}