
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

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import Logico.PUCMM;

import java.awt.Color;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				PUCMM.save();
			}
		});
		setResizable(false);
		PUCMM.setInstance();
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
		
		JMenuItem mntmRegistrarEvento = new JMenuItem("Registrar Evento");
		mntmRegistrarEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegEvent registrarEvento = new RegEvent();
				registrarEvento.setModal(true);
				registrarEvento.setVisible(true);
			}
		});
		mnEvento.add(mntmRegistrarEvento);
		
		JMenuItem mntmListarEventos = new JMenuItem("Listar Eventos");
		mntmListarEventos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListEventos listarEvento = new ListEventos();
				listarEvento.setModal(true);
				listarEvento.setVisible(true);
			}
		});
		mnEvento.add(mntmListarEventos);
		
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
		int [] meses = new int[12];
		
		getCantMonth(meses);
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(meses[0], "Eventos", "Enero");
		dataset.addValue(meses[1], "Eventos", "Febrero");
		dataset.addValue(meses[2], "Eventos", "Marzo");
		dataset.addValue(meses[3], "Eventos", "Abril");
		dataset.addValue(meses[4], "Eventos", "Mayo");
		dataset.addValue(meses[5], "Eventos", "Junio");
		dataset.addValue(meses[6], "Eventos", "Julio");
		dataset.addValue(meses[7], "Eventos", "Agosto");
		dataset.addValue(meses[8], "Eventos", "Septiembre");
		dataset.addValue(meses[9], "Eventos", "Octubre");
		dataset.addValue(meses[10], "Eventos", "Noviembre");
		dataset.addValue(meses[11], "Eventos", "Diciembre");
		
		JFreeChart lineChart = ChartFactory.createLineChart("Eventos por Mes", "Meses",
				"Cantidad de Eventos",dataset, PlotOrientation.VERTICAL, true, true, false);
		ChartPanel linePanel = new ChartPanel(lineChart);
		linePanel.setBounds(5, 420, 1579, 368);
		pnl_EventosPorMes.add(linePanel);
	}
	
	private void getCantMonth(int []mes) {
		PUCMM pucmm = PUCMM.pucmm();
		for(int i = 0;i < 12; i++) {
			for(int j = 0; j < pucmm.getCantEventos(); j ++) {
				if(pucmm.getMisEventos().get(j).getFechaIni().getMonth() == i) {
					mes[i] += 1;
				}
			}
		}
	}
}
