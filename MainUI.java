
import java.awt.BasicStroke;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.util.TableOrder;
import org.jfree.data.Value;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
public class MainUI extends JFrame {
	private static MainUI instance;
	private static JPanel west;
	private static String country,analysisType,view;
	private static int year1,year2;
	JComboBox<String> fromList,toList,countriesList,methodsList,viewsList;
	result res;
	
	readCSV csv;
	HashMap<String, countryList> countryMap;
	JScrollPane report;
	ChartPanel lChart,bChart,pChart,sChart;
	boolean scatter=false,pie=false,line=false,bar=false;
	
	public static MainUI getInstance() {
		if(instance == null) {
			instance = new MainUI();
			
		}
		return instance;
	}
	
	public MainUI() {
		super("Environment & Health analyzer");
		
		csv = new readCSV();
		countryMap = csv.getHashMap();
		countryList list = new countryList();
		
		JLabel chooseCountryLabel = new JLabel("choose a country: ");
		Vector<String> countriesNames = new Vector<String>();
		Vector<String> years = new Vector<String>();
		
		//read all value and out all countries full name in to combobox
		for(countryList value: countryMap.values()) {
			countriesNames.add(value.getFullName());
		}
		countriesNames.sort(null);
		countriesList = new JComboBox<String>(countriesNames);
		countriesList.addActionListener(new ActionListener() {		//add selected country to country
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//System.out.println(Integer.parseInt(fromList.getSelectedItem().toString()));	//for test
				country = fromList.getSelectedItem().toString();
			}
			
		});
		
		
		JLabel from = new JLabel("From");
		JLabel to = new JLabel("To");

		
		for (int i = 2021; i >= 2010; i--) {
			years.add("" + i);
		}
		year1 = 2010;
		year2 = 2021;
		fromList = new JComboBox<String>(years);
		toList = new JComboBox<String>(years);
		fromList.addActionListener(new ActionListener() {		//add selected year to year1
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//System.out.println(Integer.parseInt(fromList.getSelectedItem().toString()));	//for test
				year1 = Integer.parseInt(fromList.getSelectedItem().toString());
				
			}
			
		});
		toList.addActionListener(new ActionListener() {		//add selected year to year2
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//System.out.println(Integer.parseInt(fromList.getSelectedItem().toString()));	//for test
				year2 = Integer.parseInt(toList.getSelectedItem().toString());
			}
			
		});
		
		JPanel north = new JPanel();
		north.add(chooseCountryLabel);
		north.add(countriesList);
		north.add(from);
		north.add(fromList);
		north.add(to);
		north.add(toList);
		
		JButton recalculate = new JButton("Recalculate");

		JLabel viewsLabel = new JLabel("Available Views: ");
		view = "Pie Chart";
		Vector<String> viewsNames = new Vector<String>();
		viewsNames.add("Pie Chart");
		viewsNames.add("Line Chart");
		viewsNames.add("Bar Chart");
		viewsNames.add("Scatter Chart");
		viewsNames.add("Report");
		viewsList = new JComboBox<String>(viewsNames);
		viewsList.addActionListener(new ActionListener() {		//add selected view to view
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//System.out.println(Integer.parseInt(fromList.getSelectedItem().toString()));	//for test
				view = viewsList.getSelectedItem().toString();
				System.out.println(view);

			}
			
		});
		JButton addView = new JButton("+");
		JButton removeView = new JButton("-");

		JLabel methodLabel = new JLabel("        Choose analysis method: ");
		analysisType = "Mortality";
		Vector<String> methodsNames = new Vector<String>();
		methodsNames.add("Mortality");
		methodsNames.add("Mortality vs Expenses");
		methodsNames.add("Mortality vs Expenses & Hospital Beds");
		methodsNames.add("Mortality vs GDP");
		methodsNames.add("Unemployment vs GDP");
		methodsNames.add("Unemployment");

		methodsList = new JComboBox<String>(methodsNames);
		methodsList.addActionListener(new ActionListener() {		//add selected method to analysisType
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//System.out.println(Integer.parseInt(fromList.getSelectedItem().toString()));	//for test
				analysisType = methodsList.getSelectedItem().toString();
				System.out.println(analysisType);
			}
			
		});

		JPanel south = new JPanel();
		south.add(viewsLabel);
		south.add(viewsList);
		south.add(addView);
		south.add(removeView);

		south.add(methodLabel);
		south.add(methodsList);
		south.add(recalculate);
		
		JPanel east = new JPanel();

		// Set charts region
		west = new JPanel();
		west.setLayout(new GridLayout(2, 0));
		
		HashMap<Integer,Double> pop,forest,Co,Pm,en,gdp;
		getPop p = new getPop();
		pop = p.getPop("can", 2000, 2010);
		getPM25 pm = new getPM25();
		Pm = pm.getPM25("can", 2000, 2010);
		getGDP gd = new getGDP();
		gdp = gd.getGDP("can", 2000, 2010);
		getCO2 co = new getCO2();
		Co = co.getCO2("can", 2000, 2010);
		getEnergy En = new getEnergy();
		en = En.getEnergy("can", 2000, 2010);
		getForest F = new getForest();
		forest = F.getForest("can", 2000, 2010);
		System.out.println("test");
		
		Data co2 = new Data();
		co2.setType("Co2");
		co2.set_unit("Co2");
		co2.set_data_recieved(Co);
		Data fore = new Data();
		fore.set_data_recieved(forest);
		fore.setType("forest");
		fore.set_unit("forest");
		Data G = new Data();
		G.set_data_recieved(gdp);
		G.set_unit("GDP");
		G.setType("GDP");
		Data Pop = new Data();
		Pop.setType("population");
		Pop.set_unit("population");
		Pop.set_data_recieved(pop); 
		Data Pm25 = new Data();
		Pm25.setType("pm2.5");
		Pm25.set_unit("pm2.5");
		Pm25.set_data_recieved(Pm); 
		
		result res2 = new result();
		res2.set_result1(Pop);
		res2.set_result2(Pm25);
		result res3 = new result();
		res3.set_result1(co2);
		res3.set_result2(G);
			
		createScatterPlotChart  sca= new createScatterPlotChart(res2);
		ChartPanel sch = sca.get_panel();
		west.add(sch);
		
		
		
		
		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(east, BorderLayout.EAST);
		getContentPane().add(south, BorderLayout.SOUTH);
		getContentPane().add(west, BorderLayout.WEST);
		recalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(countryMap.get(country).getIso3().equals("N/A"))
				getContentPane().add(west, BorderLayout.WEST);		//the next three line is to update west to JFrame
				getContentPane().validate();
				getContentPane().repaint();

			}
		});
		addView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HashMap<Integer,Double> pop,forest,Co,Pm,en,gdp;
				getPop p = new getPop();
				pop = p.getPop("can", 2000, 2010);
				getPM25 pm = new getPM25();
				Pm = pm.getPM25("can", 2000, 2010);
				getGDP gd = new getGDP();
				gdp = gd.getGDP("can", 2000, 2010);
				getCO2 co = new getCO2();
				Co = co.getCO2("can", 2000, 2010);
				getEnergy En = new getEnergy();
				en = En.getEnergy("can", 2000, 2010);
				getForest F = new getForest();
				forest = F.getForest("can", 2000, 2010);
				System.out.println("test");
				
				Data co2 = new Data();
				co2.setType("Co2");
				co2.set_unit("Co2");
				co2.set_data_recieved(Co);
				Data fore = new Data();
				fore.set_data_recieved(forest);
				fore.setType("forest");
				fore.set_unit("forest");
				Data G = new Data();
				G.set_data_recieved(gdp);
				G.set_unit("GDP");
				G.setType("GDP");
				Data Pop = new Data();
				Pop.setType("population");
				Pop.set_unit("population");
				Pop.set_data_recieved(pop); 
				Data Pm25 = new Data();
				Pm25.setType("pm2.5");
				Pm25.set_unit("pm2.5");
				Pm25.set_data_recieved(Pm); 
				
				result res2 = new result();
				res2.set_result1(Pop);
				res2.set_result2(Pm25);
				result res3 = new result();
				res3.set_result1(co2);
				res3.set_result2(G);
				if(view.equals("Report")) {
					boolean exist = false;
					Component[] components = west.getComponents();
					Component component = null;
					
					for(int i =0;i<components.length;i++) {
						component = components[i];
						if(component instanceof  JScrollPane) {
							exist = true;
							break;
						}
					}
					if(exist==false) {
						createReport rep = new createReport();	
						report = rep.createReport(res2);
						west.add(report);
						getContentPane().add(west, BorderLayout.WEST);		//the next three line is to update west to JFrame
						getContentPane().validate();
						getContentPane().repaint();
						
					}
				}
				else if(view.equals("Scatter Chart")) {
					
					if(scatter==false) {
						scatter = true;
						createScatterPlotChart sca = new createScatterPlotChart(res2);
						sChart = sca.get_panel();
						west.add(sChart);
						getContentPane().add(west, BorderLayout.WEST);		//the next three line is to update west to JFrame
						getContentPane().validate();
						getContentPane().repaint();
					}
				}
				else if(view.equals("Bar Chart")) {
					
					if(bar==false) {
						bar = true;
						creatBarChart ba = new creatBarChart(res2);
						bChart = ba.get_panel();
						west.add(bChart);
						getContentPane().add(west, BorderLayout.WEST);		//the next three line is to update west to JFrame
						getContentPane().validate();
						getContentPane().repaint();
					}
				}
				else if(view.equals("Line Chart")) {	
					
					if(line==false) {
						line = true;
						createLineChart li = new createLineChart(res2);
						lChart = li.get_panel();
						west.add(lChart);
						getContentPane().add(west, BorderLayout.WEST);		//the next three line is to update west to JFrame
						getContentPane().validate();
						getContentPane().repaint();
					}
				}
				else {	
					
					if(analysisType.equals("Forest Area")==false) {
						JOptionPane.showMessageDialog(null, "chart not avilable");
					}
					
					else if(pie==false&&(analysisType.equals("Forest Area")==true)) {
						pie = true;
						createPieChart pi = new createPieChart(res2);
						pChart = pi.get_panel();
						west.add(pChart);
						getContentPane().add(west, BorderLayout.WEST);		//the next three line is to update west to JFrame
						getContentPane().validate();
						getContentPane().repaint();
					}
					
				}
				

			}
		});//end of addView
		
		removeView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(view.equals("Report")) {
					boolean exist = false;
					Component[] components = west.getComponents();
					Component component = null;
					
					for(int i =0;i<components.length;i++) {
						component = components[i];
						if(component instanceof  JScrollPane) {
							exist = true;
							west.remove(component);
							getContentPane().add(west, BorderLayout.WEST);		//the next three line is to update west to JFrame
							getContentPane().validate();
							getContentPane().repaint();
							
							break;
						}
					}
					if(exist==false) {
						JOptionPane.showMessageDialog(null, "no such chart exists");
						
					}
				}
				else if(view.equals("Scatter Chart")) {
					
					if(scatter==true) {
						scatter = false;
						west.remove(sChart);
						getContentPane().add(west, BorderLayout.WEST);		//the next three line is to update west to JFrame
						getContentPane().validate();
						getContentPane().repaint();
						
					}
					else {
						JOptionPane.showMessageDialog(null, "no such chart exists");
					}
				}
				else if(view.equals("Bar Chart")) {
					
					if(bar==true) {
						bar = false;
						west.remove(bChart);
						getContentPane().add(west, BorderLayout.WEST);		//the next three line is to update west to JFrame
						getContentPane().validate();
						getContentPane().repaint();
						
					}
					else {
						JOptionPane.showMessageDialog(null, "no such chart exists");
					}
				}
				else if(view.equals("Line Chart")) {	
					
					if(line==true) {
						line = false;
						west.remove(lChart);
						getContentPane().add(west, BorderLayout.WEST);		//the next three line is to update west to JFrame
						getContentPane().validate();
						getContentPane().repaint();
					}
					else {
						JOptionPane.showMessageDialog(null, "no such chart exists");
					}
				}
				else {	
					
					if(pie==true) {
						pie = false;
						west.remove(pChart);
						getContentPane().add(west, BorderLayout.WEST);		//the next three line is to update west to JFrame
						getContentPane().validate();
						getContentPane().repaint();
					}
					else {
						JOptionPane.showMessageDialog(null, "no such chart exists");
					}
					
				}
				

			}
			
		});
		
		
		
	}

}