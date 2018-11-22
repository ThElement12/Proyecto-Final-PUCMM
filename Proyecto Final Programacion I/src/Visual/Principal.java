package Visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class Principal extends JFrame {
	private Dimension dim;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setResizable(false);
		
		setTitle("Manejador de Eventos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		dim = super.getToolkit().getScreenSize();
		super.setSize(dim.width, dim.height - 50);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnEvento = new JMenu("Evento");
		menuBar.add(mnEvento);
		
		JMenu mnPersonas = new JMenu("Personas");
		menuBar.add(mnPersonas);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pnl_Eventos = new JPanel();
		pnl_Eventos.setBorder(new LineBorder(Color.BLACK));
		pnl_Eventos.setBounds(6, 6, 759, 418);
		contentPane.add(pnl_Eventos);
		
		JPanel pnl_AFavorit = new JPanel();
		pnl_AFavorit.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnl_AFavorit.setBounds(764, 6, 824, 418);
		contentPane.add(pnl_AFavorit);
		
		JPanel pnl_EventosPorMes = new JPanel();
		pnl_EventosPorMes.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnl_EventosPorMes.setBounds(6, 422, 1582, 370);
		contentPane.add(pnl_EventosPorMes);
	}
}
