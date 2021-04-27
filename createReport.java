import java.awt.Color;


import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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
public class createReport {
	public JScrollPane createReport(result result) {
	
		JTextArea report = new JTextArea();
		report.setEditable(false);
		report.setPreferredSize(new Dimension(400, 300));
		report.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
		report.setBackground(Color.white);
		HashMap<Integer,Double> data_container1 = result.get_result1().get_data_recieved();
		HashMap<Integer,Double> data_container2 = result.get_result2().get_data_recieved();
		String reportMessage;
		String analysis_name =result.get_result1().getType()+" vs " +result.get_result2().getType();
		String message1 ="";
		Set<Integer> keys = result.get_result1().get_data_recieved().keySet();
		int year_start = keys.stream().findFirst().get(); //return the first key
		TreeSet<Integer> sorted = new TreeSet<Integer>(keys);//just use to find the last key,no other use
		int year_end = sorted.last(); //return the last key
		if(result.get_result2().getType()==null) {//one analysis chosen
			for(int i=year_start; i<year_end+1; i++) {
				message1 += "Year "+i+":\n";
				if(data_container1.get(i)!=-1) {
				   message1 += "\t"+result.get_result1().get_unit()+"="+data_container1.get(i);
				}
				else {
				   message1 += "\t"+"no data for "+result.get_result1().getType();
				}
			}
			reportMessage = analysis_name+"\n==============================\n"+message1;
			report.setText(reportMessage);
			JScrollPane outputScrollPane = new JScrollPane(report);
			return outputScrollPane;
		}
		
		
		else {
			
		for(int i=year_start; i<year_end+1; i++) {
			message1 += "Year "+i+":\n";
			if(data_container1.get(i)!=-1&&data_container2.get(i)!=-1) {
				message1 += "\t"+result.get_result1().get_unit()+"="+data_container1.get(i)+"\n"+"\t"+result.get_result2().get_unit()+"="+data_container2.get(i)+"\n";
			}
			else if(data_container2.get(i)!=-1&&data_container1.get(i)==-1) {
				message1 += "\t"+"no data for "+result.get_result1().getType()+"\n"+"\t"+result.get_result2().get_unit()+"="+data_container2.get(i)+"\n";
			}
			else if(data_container2.get(i)==-1&&data_container1.get(i)!=-1) {
				message1 += "\t"+result.get_result1().get_unit()+"="+data_container1.get(i)+"\n"+"\t"+"no data for "+result.get_result2().getType()+"\n";
			}
			else {
				message1 += "\t"+"no data for "+result.get_result1().getType()+"\n"+"\t"+"no data for "+result.get_result2().getType()+"\n";
			}
		} 
		reportMessage = analysis_name+"\n==============================\n"+message1;
		report.setText(reportMessage);
		JScrollPane outputScrollPane = new JScrollPane(report);
		return outputScrollPane;
		
	}
	}
	

}
