import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.jfree.chart.*;
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
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.*;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.data.time.Year;
public class createScatterPlotChart{
	ChartPanel chartPanel;
	boolean checker = true;
	public createScatterPlotChart(result result) {
		String analysis_name;
		int count1=0;
		int count2=0;
			HashMap<Integer,Double> data_container1 = result.get_result1().get_data_recieved();
			HashMap<Integer,Double> data_container2 = result.get_result2().get_data_recieved();
			TimeSeries series1 = new TimeSeries(result.get_result1().get_unit());
			Set<Integer> keys = result.get_result1().get_data_recieved().keySet();
			int year_start = keys.stream().findFirst().get(); //return the first key
			TreeSet<Integer> sorted = new TreeSet<Integer>(keys);//just use to find the last key,no other use
			int year_end = sorted.last(); //return the last key
			if(result.get_result2().getType()==null) {//one analysis chosen
				for(int i=year_start; i<year_end+1; i++) {
					if(data_container1.get(i) == -1) {
						count1++;
					}
					series1.add(new Year(i),data_container1.get(i));
				}//add data to the dataset1
				if(count1==year_end +1 - year_start) {
					checker = false;
				}
				analysis_name =result.get_result1().getType()+" vs " +result.get_result2().getType();
				TimeSeriesCollection dataset1 = new TimeSeriesCollection();
				dataset1.addSeries(series1);
				XYPlot plot = new XYPlot();
				XYItemRenderer itemrenderer1 = new XYLineAndShapeRenderer(false, true);
				plot.setDataset(dataset1);
				plot.setRenderer(itemrenderer1);
				DateAxis domainAxis = new DateAxis("Year");
				plot.setDomainAxis(domainAxis);
				plot.setRangeAxis(new NumberAxis(result.get_result1().get_unit()));
				plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
				JFreeChart scatterChart = new JFreeChart(analysis_name,
						new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

				ChartPanel chartPanel = new ChartPanel(scatterChart);
				chartPanel.setPreferredSize(new Dimension(400, 300));
				chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
				chartPanel.setBackground(Color.white);
			}
			
			
			
			else {
				
			
			for(int i=year_start; i<year_end+1; i++) {
				if(data_container1.get(i) == -1) {
					count1++;
				}
				series1.add(new Year(i),data_container1.get(i));
			}
			TimeSeries series2 = new TimeSeries(result.get_result2().get_unit());
			for(int i=year_start; i<year_end+1; i++) {
				if(data_container2.get(i) == -1) {
					count2++;
				}
				series2.add(new Year(i),data_container2.get(i));
			}
			if(count1==year_end +1 - year_start&& count2==year_end +1 - year_start) {
				checker = false;
			}
			TimeSeriesCollection dataset2 = new TimeSeriesCollection();
			TimeSeriesCollection dataset1 = new TimeSeriesCollection();
			dataset2.addSeries(series2);
			dataset1.addSeries(series1);
			
			XYPlot plot = new XYPlot();
			XYItemRenderer itemrenderer1 = new XYLineAndShapeRenderer(false, true);
			XYItemRenderer itemrenderer2 = new XYLineAndShapeRenderer(false, true);

			plot.setDataset(0, dataset1);
			plot.setRenderer(0, itemrenderer1);
			DateAxis domainAxis = new DateAxis("Year");
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(new NumberAxis(result.get_result1().get_unit()));

			plot.setDataset(1, dataset2);
			plot.setRenderer(1, itemrenderer2);
			plot.setRangeAxis(1, new NumberAxis(result.get_result2().get_unit()));

			plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
			plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis
			
		    analysis_name =result.get_result1().getType()+" vs " +result.get_result2().getType();
			
			JFreeChart scatterChart = new JFreeChart(analysis_name,
					new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

			ChartPanel chartPanel = new ChartPanel(scatterChart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			
	}
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
