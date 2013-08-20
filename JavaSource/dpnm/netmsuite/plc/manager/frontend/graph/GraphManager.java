package dpnm.netmsuite.plc.manager.frontend.graph;


import dpnm.netmsuite.plc.manager.frontend.data.db.*;
import dpnm.netmsuite.plc.manager.frontend.data.*;

import java.awt.image.BufferedImage;
import java.util.*;

public class  GraphManager implements IGraphManager{
	
	private static GraphManager graphMgr= new GraphManager();
	
	private int graphID;
	
	public static GraphManager getInstance(){
		return graphMgr;
	}
	
	private GraphManager(){
		
	}
	
	/**
	 * 
	 * @param macAddr : target MacAddress
	 * @param graphType : 1 - inPkts, outPkts . 2 - inAcks, outAcks, 3 - inFails, outFails
	 * @param height : returned size of graph's height
	 * @param width  : returned size of graph's width
	 * @return : BufferedImage of instaniated graph 
	 */
	public BufferedImage getGraph(String macAddr, int graphType, int height,int width){
		DataManager dataManager = OracleDBManager.getInstance();
		Vector getStatics = dataManager.getStatics(macAddr);
		
		return makeGraph(macAddr, getStatics, graphType, height, width);
	}
	
	public BufferedImage makeGraph(String macAddr, Vector getStatics, int graphType, int height,int width){
		BufferedImage image;
		String title = "no title";
		if(graphType == 1){
			title = macAddr + ": InPackets/OutPackets";
		}
		else if(graphType ==2){
			title = macAddr + ": InBytes/OutBytes";
		}
		else if(graphType ==3){
			title = macAddr + ": InSpeed/OutSpeed";
		}
		else{
			return null;
		}
		
		TimeSeriesChartDemo1 oneDemo = new TimeSeriesChartDemo1(title, graphType, getStatics, height, width, TimeSeriesChartDemo1.SECOND);
		image =  oneDemo.getBufferedImage(height,width);
		return image;
	}


	public void setGraphID(int graphID) {
		this.graphID = graphID;
	}

	public int getGraphID() {
		return graphID;
	}
}
