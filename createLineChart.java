import org.jfree.ui.ApplicationFrame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.PeriodAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.*;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
public class createLineChart { 
	ChartPanel chartPanel;
	boolean checker = true;
	public createLineChart(result result) {
		
		
		if(result.get_result2()==null) {//one analysis chosen
			String analysis_name;
			int count1=0;
			XYSeries series1 = new XYSeries(result.get_result1().getType()+result.get_result1().get_unit());
			XYSeriesCollection dataset1 = new XYSeriesCollection();
			HashMap<Integer,Double> data_container1 = result.get_result1().get_data_recieved();
			Set<Integer> keys = result.get_result1().get_data_recieved().keySet();
			//return a set obj for what key the hashmap have, example: {2000,2001,2002}
			int year_start = keys.stream().findFirst().get(); //return the first key
			TreeSet<Integer> sorted = new TreeSet<Integer>(keys);//just use to find the last key,no other use
			int year_end = sorted.last(); //return the last key
			for(int i=year_start; i<year_end+1; i++) {
				if(data_container1.get(i) == -1) {
					count1++;
				}
				else {
					series1.add(i,data_container1.get(i));
				}
				
			}//add data to the dataset1
			if(count1==year_end +1 - year_start) {
				checker = false;
			}
			analysis_name =result.get_result1().getType();
			JFreeChart chart = ChartFactory.createXYLineChart(analysis_name, "Year", "", null,PlotOrientation.VERTICAL, true, true, false);
			XYPlot plot = chart.getXYPlot();
			XYLineAndShapeRenderer renderer1 = new XYLineAndShapeRenderer();
			plot.setDataset(dataset1);
			plot.setRenderer(renderer1);
			plot.setRangeAxis(new NumberAxis(""));
			plot.setRangeAxis(new NumberAxis(result.get_result1().get_unit()));
			plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
			plot.setBackgroundPaint(Color.white);

			plot.setRangeGridlinesVisible(true);
			plot.setRangeGridlinePaint(Color.BLACK);

			plot.setDomainGridlinesVisible(true);
			plot.setDomainGridlinePaint(Color.BLACK);

			chart.getLegend().setFrame(BlockBorder.NONE);

			chart.setTitle(
					new TextTitle(analysis_name, new Font("Serif", java.awt.Font.BOLD, 18)));

		    chartPanel = new ChartPanel(chart);
			chartPanel.setPreferredSize(new Dimension(500, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
		}
			
		
		else{//two data set
			String analysis_name;
			int count1=0;
			int count2=0;
			XYSeries series1 = new XYSeries(result.get_result1().getType()+result.get_result1().get_unit());
			XYSeries series2 = new XYSeries(result.get_result2().getType()+result.get_result2().get_unit());
			XYSeriesCollection dataset1 = new XYSeriesCollection();
			XYSeriesCollection dataset2 = new XYSeriesCollection();
			HashMap<Integer,Double> data_container1 = result.get_result1().get_data_recieved();
			HashMap<Integer,Double> data_container2 = result.get_result2().get_data_recieved();
			Set<Integer> keys = result.get_result1().get_data_recieved().keySet();
			//return a set obj for what key the hashmap have, example: {2000,2001,2002}
			int year_start = keys.stream().findFirst().get(); //return the first key
			TreeSet<Integer> sorted = new TreeSet<Integer>(keys);//just use to find the last key,no other use
			int year_end = sorted.last(); //return the last key
			
			for(int i=year_start; i<year_end+1; i++) {
			
			if(data_container1.get(i) == -1) {
				count1++;
			}
			else {
				series1.add(i,data_container1.get(i));
			}
			
		}//add data to the dataset1
		for(int i=year_start; i<year_end+1; i++) {
			if(data_container2.get(i) == -1) {
				count2++;
			}
			else {
				series2.add(i,data_container2.get(i));
			}
			
		}//add data to the dataset2
		if(count1==year_end +1 - year_start&& count2==year_end +1 - year_start) {
			checker = false;
		}
		dataset1.addSeries(series1);
		dataset2.addSeries(series2);
		analysis_name =result.get_result1().getType()+" vs " +result.get_result2().getType();
		// this string means we combine the type together, ex: co2 vs forest
		JFreeChart chart = ChartFactory.createXYLineChart(analysis_name, "Year", "", null,PlotOrientation.VERTICAL, true, true, false);
		XYPlot plot = chart.getXYPlot();
		XYLineAndShapeRenderer renderer1 = new XYLineAndShapeRenderer();
		XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer();
		
		  plot.setDataset(0,dataset1);
		  plot.setRenderer(0,renderer1);
		  plot.setDataset(1, dataset2);
		  plot.setRenderer(1, renderer2);
		  plot.setRangeAxis(new NumberAxis(""));
		  plot.setRangeAxis(0, new NumberAxis(result.get_result1().get_unit()));
		  plot.setRangeAxis(1, new NumberAxis(result.get_result2().get_unit()));
		  plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
		  plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

		
		plot.setBackgroundPaint(Color.white);

		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);

		plot.setDomainGridlinesVisible(true);
		plot.setDomainGridlinePaint(Color.BLACK);

		chart.getLegend().setFrame(BlockBorder.NONE);

		chart.setTitle(
				new TextTitle(analysis_name, new Font("Serif", java.awt.Font.BOLD, 18)));

	    chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(500, 300));
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
	



