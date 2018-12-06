
package Visual;

import Logico.Evento;

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
import java.awt.Paint;
import java.awt.PaintContext;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.border.LineBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import Logico.PUCMM;

import java.awt.Color;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;

public class Principal extends JFrame {
	private Dimension dim;
	private JPanel contentPane;
	private static JPanel pnl_AFavorit;
	private static JPanel pnl_Eventos;
	private static JPanel pnl_EventosPorMes;

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
		PUCMM.setInstance();
		setTitle("Manejador de Eventos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		dim = super.getToolkit().getScreenSize();
		super.setSize(dim.width,( dim.height - 50));
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnEvento = new JMenu("Evento");
		menuBar.add(mnEvento);
		
		JMenuItem mntmRegistrarEvento = new JMenuItem("Registrar Evento");
		mntmRegistrarEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegEvent registrarEvento = new RegEvent(null);
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
		JMenuItem mntmRegistrarPersonas = new JMenuItem("Registrar Personas");
		mntmRegistrarPersonas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegPersona registrarPersona = new RegPersona();
				registrarPersona.setModal(true);
				registrarPersona.setVisible(true);
			}
		});
		mnPersonas.add(mntmRegistrarPersonas);
	
		JMenu mnRecurso = new JMenu("Recurso");
		menuBar.add(mnRecurso);
		JMenuItem mntmAgregarRecurso = new JMenuItem("Agregar Recurso");
		mntmAgregarRecurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegRecursos registrarRecurso = new RegRecursos();
				registrarRecurso.setModal(true);
				registrarRecurso.setVisible(true);
			}
		});
		mnRecurso.add(mntmAgregarRecurso);
		
		JMenuItem mntmListarRecursos = new JMenuItem("Listar Recursos");
		mntmListarRecursos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListRecursos listarRecurso = new ListRecursos(null);
				listarRecurso.setModal(true);
				listarRecurso.setVisible(true);
			}
		});
		mnRecurso.add(mntmListarRecursos);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		pnl_Eventos = new JPanel();
		pnl_Eventos.setBorder(new LineBorder(Color.BLACK));
		pnl_Eventos.setBounds(6, 6, 759, 418);
		contentPane.add(pnl_Eventos);
		
		pnl_AFavorit = new JPanel();
		pnl_AFavorit.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnl_AFavorit.setBounds(764, 6, 824, 418);
		pnl_AFavorit.setLayout(new BorderLayout(0, 0));
		contentPane.add(pnl_AFavorit);
		
		
		pnl_EventosPorMes = new JPanel();
		pnl_EventosPorMes.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnl_EventosPorMes.setBounds(6, 422, 1578, 366);
		contentPane.add(pnl_EventosPorMes);
		Principal.createLineChart();
		Principal.createPieChart();
		Principal.createBarGraph();
	}
	public static void createBarGraph() {
		
		PUCMM pucmm = PUCMM.pucmm();
		Evento [] event = new Evento [6];
		int []part = new int [6];
		DefaultCategoryDataset dataBar = new DefaultCategoryDataset();
		if(pucmm.getMisEventos().size() < 6) {
			for(int i = pucmm.getMisEventos().size() - 1 , j = 0; i > 0 || j < pucmm.getMisEventos().size(); i -- , j ++) {
				event[j] = pucmm.getMisEventos().get(i);
			}
		
			for(int i = 0; i < pucmm.getMisEventos().size(); i++) {
				part[i] = event[i].cantParticipantes();
			}
		
			for(int i = 0; i < pucmm.getMisEventos().size(); i ++) {
				dataBar.addValue(part[i], "Participantes", event[i].getNombre());
			}
		}
		
		else {
			for(int i = pucmm.getMisEventos().size() - 1, j = 0; i > 0 && i > pucmm.getCantEventos() - 6 || j < 6; i -- , j ++) {
				event[j] = pucmm.getMisEventos().get(i);
			}
		
			for(int i = 0; i < 6; i++) {
				part[i] = event[i].cantParticipantes();
			}
		
			for(int i = 0; i < 6; i ++) {
				dataBar.addValue(part[i], "Participantes", event[i].getNombre());
			}
		}
		JFreeChart barChart = ChartFactory.createBarChart3D("Cantidad de Participantes en eventos próximos", "Evento", "Participantes", dataBar);
		barChart.getPlot().setBackgroundPaint(Color.DARK_GRAY);
		barChart.setBackgroundPaint(Color.lightGray);
		ChartPanel barPanel = new ChartPanel(barChart);
		pnl_Eventos.setLayout(new BorderLayout(0 ,0));
		pnl_Eventos.add(barPanel);
	}
	
	public static void createLineChart() {
		int [] meses = new int[12];
		getCantMonth(meses);
		DefaultCategoryDataset dataLine = new DefaultCategoryDataset();
		String[] mes = {"Ene","Feb", "Mar", "Abr","May", "Jun","Jul","Ago","Sep","Oct","Nov","Dic"};
		for(int i= 0; i < 12; i ++) {
			dataLine.addValue(meses[i], "Eventos", mes[i]);
		}
		
		JFreeChart lineChart = ChartFactory.createLineChart("Eventos por Mes", "Meses","Cantidad de Eventos",dataLine, 
				PlotOrientation.VERTICAL, true, true, false);
		pnl_EventosPorMes.setLayout(new BorderLayout(0, 0));
		ChartPanel linePanel = new ChartPanel(lineChart);
		lineChart.getPlot().setBackgroundPaint(Color.DARK_GRAY);
		lineChart.setBackgroundPaint(Color.lightGray);
		pnl_EventosPorMes.add(linePanel);
		linePanel.setLayout(new BorderLayout(0, 0));
	}
	
	public static void createPieChart() {
		String [] area = {"Ciencias/Tecnología","Medicina","Mercadeo/Administracion","Deportivo"};
		int []cantArea = new int[4];
		getCantArea(area, cantArea);
		DefaultPieDataset dataPie = new DefaultPieDataset();
		for(int i = 0; i < 4; i ++) {
			dataPie.setValue(area[i], cantArea[i]);
		}
		JFreeChart pieChart = ChartFactory.createPieChart("Cantidad de Eventos por Area", dataPie);
		ChartPanel piePanel = new ChartPanel(pieChart);
		pieChart.getPlot().setBackgroundPaint(Color.DARK_GRAY);
		pieChart.setBackgroundPaint(Color.lightGray);
		pieChart.getTitle().setPaint(Color.BLACK);
		pnl_AFavorit.add(piePanel);
		piePanel.setLayout(new BorderLayout(0, 0));
	}
	
	private static void getCantMonth(int []mes) {
		PUCMM pucmm = PUCMM.pucmm();
		for(int i = 0;i < 12; i++) {
			for(int j = 0; j < pucmm.getCantEventos() - 1; j ++) {
				if(pucmm.getMisEventos().get(j).getFechaIni().getMonth() == i) {
					mes[i] += 1;
				}
			}
		}
	}
	
	private static void getCantArea(String area[],int cantArea[]) {
		PUCMM pucmm = PUCMM.pucmm();
		for(int i = 0; i < 4; i ++) {
			for(int j = 0; j < pucmm.getCantEventos() - 1 ; j++) {
				if(area[i].equalsIgnoreCase(pucmm.getMisEventos().get(j).getArea())) {
					cantArea[i] += 1;
				}
			}
		}
	}
}
