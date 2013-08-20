package dpnm.netmsuite.plc.manager.frontend.graph;

import java.awt.image.*;

public interface IGraphManager {
	/**
	 * 
	 * @param macAddr : target MacAddress
	 * @param graphType : 1 - inPkts, outPkts . 2 - inAcks, outAcks, 3 - inFails, outFails
	 * @param height : returned size of graph's height
	 * @param width  : returned size of graph's width
	 * @return : BufferedImage of instaniated graph 
	 */
	public BufferedImage getGraph(String macAddr, int graphType, int height, int width);
}
