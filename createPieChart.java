import java.awt.*;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.BorderFactory;

import org.jfree.chart.*;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.*;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.general.Series;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.Rotation;
import org.jfree.util.TableOrder;
import org.jfree.ui.ApplicationFrame;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.JPanel;

public class createPieChart {
	ChartPanel chartPanel;
	boolean checker = true;
	public createPieChart(result result) {
		int count = 0;
		//XYSeries series1 = new XYSeries(result.get_result1().get_unit());
		DefaultPieDataset dataset = new DefaultPieDataset();
		HashMap<Integer, Double> data_container = result.get_result1().get_data_recieved();
		Set<Integer> keys = result.get_result1().get_data_recieved().keySet();
		int year_start = keys.stream().findFirst().get();
		TreeSet<Integer> sorted = new TreeSet <Integer> (keys);
		int year_end = sorted.last();
		for(int i = year_start; i <year_end +1; i++) {
			if(data_container.get(i) == -1) {
				count++;
			}
			String a =String.valueOf(i);
			dataset.setValue(a, data_container.get(i));
		}
		if(count==year_end +1 - year_start ) {
			checker = false;
		}
		String analysis_name =result.get_result1().getType();
		JFreeChart chart = ChartFactory.createPieChart(analysis_name, 
				dataset, true, true, false);
		Plot cp = chart.getPlot();
		cp.setBackgroundPaint(ChartColor.WHITE); 
		chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(500, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
	}
	public ChartPanel get_panel() {
		if(checker == false) {
			return null;
		}
		else {
			return this.chartPanel;
		}
		
	}
	}

	
