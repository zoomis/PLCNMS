package dpnm.netmsuite.plc.manager.frontend.graph;

/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2005, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation; either version 2.1 of the License, or 
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public 
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, 
 * USA.  
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc. 
 * in the United States and other countries.]
 *
 * -------------------------
 * TimeSeriesChartDemo1.java
 * -------------------------
 * (C) Copyright 2003-2005, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   ;
 *
 * $Id: TimeSeriesChartDemo1.java,v 1.7 2006/06/16 17:23:57 ranivris Exp $
 *
 * Changes
 * -------
 * 09-Mar-2005 : Version 1, copied from the demo collection that ships with
 *               the JFreeChart Developer Guide (DG);
 *
 */

import java.util.*;

import java.awt.Color;
import java.awt.image.*;
import java.text.SimpleDateFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.*;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

import dpnm.netmsuite.util.*;
/**
 * An example of a time series chart.  For the most part, default settings are 
 * used, except that the renderer is modified to show filled shapes (as well as 
 * lines) at each data point.
 */
public class TimeSeriesChartDemo1 extends ApplicationFrame {

	
	public static int SECOND = -1;
	public static int MINUTE = 0;

	JFreeChart chart;
	Vector statisticInfos;
	int graphType, height, width;
	String title;
	int time;
	
    /**
     * A demonstration application showing how to create a simple time series 
     * chart.  This example uses monthly data.
     *
     * @param title  the frame title.
     * @param graphType - 1,2,3 -> inPkts/outPkts | inBytes/outBytes | inSpeed/outSpeed
     * @param statisticsInfos - Vector<StatisticInfo>
     * @param heigh/width -> graph's size
     * @param time - graph size
     */
    public TimeSeriesChartDemo1(String title, int graphType, Vector statisticInfos, int height, int width, int time) {
        super(title);
        this.title = title;
        this.graphType = graphType;
        this.statisticInfos = statisticInfos;
        this.height = height;
        this.width = width;
        this.time = time;
        
        ChartPanel chartPanel = (ChartPanel) createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(height, width));
        chartPanel.setMouseZoomable(true, false);
        setContentPane(chartPanel);
    }
	

    /**
     * Creates a chart.
     * 
     * @param dataset  a dataset.
     * 
     * @return A chart.
     */
    private JFreeChart createChart(XYDataset dataset) {

    	JFreeChart chart = null;
    	if (this.graphType == 1) {
    		chart = ChartFactory.createTimeSeriesChart(
            title,  			// title
            "Minute",             // x-axis label
            "Packets/sec",   // y-axis label
            dataset,            // data
            true,               // create legend?
            true,               // generate tooltips?
            false               // generate URLs?
    		);
    	} else if (this.graphType == 2) { 
      		chart = ChartFactory.createTimeSeriesChart(
      				title,  			// title
      				"Minute",             // x-axis label
      				"Bytes/sec",   // y-axis label
      				dataset,            // data
      				true,               // create legend?
      				true,               // generate tooltips?
      				false               // generate URLs?
      		);
    	} else {
      		chart = ChartFactory.createTimeSeriesChart(
      				title,  			// title
      				"Minute",             // x-axis label
      				"KBps", 			// y-axis label
      				dataset,            // data
      				true,               // create legend?
      				true,               // generate tooltips?
      				false               // generate URLs?
      		);
    	}
    	
        
        chart.setBackgroundPaint(Color.white);

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);
        
        XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setBaseShapesVisible(true);
            renderer.setBaseShapesFilled(true);
        }
        
        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("hh:mm"));
        
        return chart;

    }
    
    /**
     * Creates a dataset, consisting of two series of monthly data.
     * @return The dataset.
     */
    private XYDataset createDataset() {
    	
    	/*
    	 * each 5 seconds, the data updated...
    	 * 
    	 */
    	String firstGraph = "";
    	String secondGraph = "";
    	
    	if(graphType == 1){
    		firstGraph = "InPackets";
    		secondGraph = "OutPackets";
    		
            TimeSeries s1 = new TimeSeries(firstGraph, Second.class);
            for(int i=1; i<statisticInfos.size(); i++){
            	StatisticInfo pastInfo = (StatisticInfo)statisticInfos.elementAt(i-1);
            	StatisticInfo oneInfo = (StatisticInfo)statisticInfos.elementAt(i);
            	long timeStamp = oneInfo._timeStamp;
            	long gap = (oneInfo._timeStamp - pastInfo._timeStamp)/1000;
            	gap = gap == 0 ? 1 : gap;
            	Date date = new Date(timeStamp);
            	long inPkts = oneInfo._inPkts - pastInfo._inPkts; 
            	if(inPkts < 0){
            		pastInfo._inPkts = oneInfo._inPkts + 2^32 - pastInfo._inPkts;
            	}
            	inPkts = inPkts/gap;
                s1.add(new Second(date), inPkts);
            }


            TimeSeries s2 = new TimeSeries(secondGraph, Second.class);
            for(int i=1; i<statisticInfos.size(); i++){
            	StatisticInfo pastInfo = (StatisticInfo)statisticInfos.elementAt(i-1);
            	StatisticInfo oneInfo = (StatisticInfo)statisticInfos.elementAt(i);
            	long timeStamp = oneInfo._timeStamp;
            	long gap = (oneInfo._timeStamp - pastInfo._timeStamp)/1000;
            	gap = gap == 0 ? 1 : gap;
            	Date date = new Date(timeStamp);
            	long outPkts = oneInfo._outPkts - pastInfo._outPkts; 
            	if(outPkts < 0){
            		pastInfo._outPkts = oneInfo._outPkts + 2^32 - pastInfo._outPkts;
            	}
            	outPkts = outPkts/gap;
            	s2.add(new Second(date), outPkts);
            }

            TimeSeriesCollection dataset = new TimeSeriesCollection();
            dataset.addSeries(s1);
            dataset.addSeries(s2);
            
            dataset.setDomainIsPointsInTime(true);
            return dataset;
    	}
    	else if(graphType == 2){
    		firstGraph = "InBytes";
    		secondGraph = "OutBytes";
    		
            TimeSeries s1 = new TimeSeries(firstGraph, Second.class);
            for(int i=1; i<statisticInfos.size(); i++){
            	StatisticInfo pastInfo = (StatisticInfo)statisticInfos.elementAt(i-1);
            	StatisticInfo oneInfo = (StatisticInfo)statisticInfos.elementAt(i);
            	long timeStamp = oneInfo._timeStamp;
            	long gap = (oneInfo._timeStamp - pastInfo._timeStamp)/1000;
            	gap = gap == 0 ? 1 : gap;
            	Date date = new Date(timeStamp);
            	long _inBytes = oneInfo._inBytes - pastInfo._inBytes;
            	if(_inBytes < 0){
            		pastInfo._inBytes = oneInfo._inBytes + 2^32 - pastInfo._inBytes;
            	}
            	_inBytes = _inBytes/gap;
                s1.add(new Second(date), _inBytes);
            }


            TimeSeries s2 = new TimeSeries(secondGraph, Second.class);
            for(int i=1; i<statisticInfos.size(); i++){
            	StatisticInfo pastInfo = (StatisticInfo)statisticInfos.elementAt(i-1);
            	StatisticInfo oneInfo = (StatisticInfo)statisticInfos.elementAt(i);
            	long timeStamp = oneInfo._timeStamp;
            	long gap = (oneInfo._timeStamp - pastInfo._timeStamp)/1000;
            	gap = gap == 0 ? 1 : gap;
            	Date date = new Date(timeStamp);
            	long _outBytes = oneInfo._outBytes - pastInfo._outBytes;
            	if(_outBytes < 0){
            		pastInfo._outBytes = oneInfo._outBytes + 2^32 - pastInfo._outBytes;
            	}
            	_outBytes = _outBytes/gap;
            	s2.add(new Second(date), _outBytes);
            }

            TimeSeriesCollection dataset = new TimeSeriesCollection();
            dataset.addSeries(s1);
            dataset.addSeries(s2);
            
            dataset.setDomainIsPointsInTime(true);
            return dataset;
    	}
    	else if(graphType ==3){
    		firstGraph = "InSpeed";
    		secondGraph = "OutSpeed";
    		
            TimeSeries s1 = new TimeSeries(firstGraph, Second.class);
            for(int i=1; i<statisticInfos.size(); i++){
            	StatisticInfo pastInfo = (StatisticInfo)statisticInfos.elementAt(i-1);
            	StatisticInfo oneInfo = (StatisticInfo)statisticInfos.elementAt(i);
            	long timeStamp = oneInfo._timeStamp;
            	Date date = new Date(timeStamp);
            	long _inSpeed = oneInfo._inSpeed;
                s1.add(new Second(date), _inSpeed);
            }

            TimeSeries s2 = new TimeSeries(secondGraph, Second.class);
            for(int i=1; i<statisticInfos.size(); i++){
            	StatisticInfo pastInfo = (StatisticInfo)statisticInfos.elementAt(i-1);
            	StatisticInfo oneInfo = (StatisticInfo)statisticInfos.elementAt(i);
            	long timeStamp = oneInfo._timeStamp;
            	Date date = new Date(timeStamp);
            	long _outSpeed = oneInfo._outSpeed;

            	s2.add(new Second(date), _outSpeed);
            }

            TimeSeriesCollection dataset = new TimeSeriesCollection();
            dataset.addSeries(s1);
            dataset.addSeries(s2);

            dataset.setDomainIsPointsInTime(true);
            return dataset;
    	}
    	else{
    		firstGraph = "Not Defiend";
    		secondGraph = "Not Defiend";
    		return null;
    	}
    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     * 
     * @return A panel.
     */
    public JPanel createDemoPanel() {
        chart = createChart(createDataset());
        return new ChartPanel(chart);
    }
    
    public BufferedImage getBufferedImage(int width, int height){
    	return chart.createBufferedImage(width,height);
    }
    
}
