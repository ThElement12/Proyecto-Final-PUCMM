
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

import SQLConnections.EventosServices;
import org.javatuples.Pair;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import Logico.PUCMM;
import SQLConnections.Conexion;

import java.awt.Color;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.awt.Toolkit;

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
					
					System.out.println(Conexion.getConnection());
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Principal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/img/Icono_pucmm.jpg")));
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
				createBarGraph();
				createLineChart();
				createPieChart();
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
				RegPersona registrarPersona = new RegPersona(false);
				registrarPersona.setModal(true);
				registrarPersona.setVisible(true);
			}
		});
		mnPersonas.add(mntmRegistrarPersonas);
		
		JMenuItem mntmListarPersonas = new JMenuItem("Listar Personas");
		mntmListarPersonas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListPersonas listarPersonas = new ListPersonas();
				listarPersonas.setModal(true);
				listarPersonas.setVisible(true);
			}
;		});
		mnPersonas.add(mntmListarPersonas);
	
		JMenu mnRecurso = new JMenu("Recurso");
		menuBar.add(mnRecurso);
		JMenuItem mntmAgregarRecurso = new JMenuItem("Agregar Recurso");
		mntmAgregarRecurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegRecursos registrarRecurso = new RegRecursos(false);
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
		contentPane.setLayout(new BorderLayout(0, 0));
		
		pnl_Eventos = new JPanel();
		pnl_Eventos.setBorder(new LineBorder(Color.BLACK));
		contentPane.add(pnl_Eventos);
		
		pnl_AFavorit = new JPanel();
		pnl_AFavorit.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnl_AFavorit.setLayout(new BorderLayout(0, 0));
		contentPane.add(pnl_AFavorit, BorderLayout.EAST);
		
		
		pnl_EventosPorMes = new JPanel();
		pnl_EventosPorMes.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(pnl_EventosPorMes, BorderLayout.NORTH);
		createLineChart();
		createPieChart();
		createBarGraph();
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
		int [] cantActmeses = new int[12];
		getCantMonth(cantActmeses);
		DefaultCategoryDataset dataLine = new DefaultCategoryDataset();
		String[] mes = {"Ene","Feb", "Mar", "Abr","May", "Jun","Jul","Ago","Sep","Oct","Nov","Dic"};
		for(int i= 0; i < 12; i ++) {
			dataLine.addValue(cantActmeses[i], "Eventos", mes[i]);
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
	
	private static void getCantMonth(int[] mes) {
		PUCMM pucmm = PUCMM.pucmm();
		Calendar date = Calendar.getInstance();
		for(int i = 0; i < 12 ; i ++) {
			for(int j = 0; j < pucmm.getMisEventos().size(); j ++) {
				date.setTime(pucmm.getMisEventos().get(j).getFechaIni());
				if(date.get(Calendar.MONTH) == i) {
					mes[i] += 1;
				}
			}
		}
		
	}
	
	public static void createPieChart() {
		ArrayList<Pair<String, Integer>> areaCant = new ArrayList<>();
		try {
			areaCant = EventosServices.getCantidadEventoPorTipo();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DefaultPieDataset dataPie = new DefaultPieDataset();
		for (Pair<String, Integer> cant:
				areaCant) {
			dataPie.setValue(cant.getValue0(),cant.getValue1());
		}
		JFreeChart pieChart = ChartFactory.createPieChart("Cantidad de Eventos por Area", dataPie);
		ChartPanel piePanel = new ChartPanel(pieChart);
		pieChart.getPlot().setBackgroundPaint(Color.DARK_GRAY);
		pieChart.setBackgroundPaint(Color.lightGray);
		pieChart.getTitle().setPaint(Color.BLACK);
		pnl_AFavorit.add(piePanel, BorderLayout.WEST);
		piePanel.setLayout(new BorderLayout(0, 0));
	}
	
	

}
