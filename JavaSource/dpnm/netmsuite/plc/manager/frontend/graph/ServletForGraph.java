package dpnm.netmsuite.plc.manager.frontend.graph;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.image.codec.jpeg.*;


/**
 * Servlet implementation class for Servlet: ServletForGraph
 *
 */
public class ServletForGraph extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	IGraphManager graphMgr = null;
	
	public ServletForGraph() {
		super();
		graphMgr = GraphManager.getInstance();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}  	
	
	/**
	 * 
	 * @param macAddr : target MacAddress
	 * @param graphType : 1 - inPkts, outPkts . 2 - inAcks, outAcks, 3 - inFails, outFails
	 * @param graphTime : 1 - second, 2 - minute, 3 - hour
	 * @param height : returned size of graph's height
	 * @param width  : returned size of graph's width
	 * @return : BufferedImage of instaniated graph 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String macAddr = (String)request.getParameter("macAddress");
		String graphType = (String)request.getParameter("graphType");
		String height = (String)request.getParameter("height");
		String width = (String)request.getParameter("width");
		
		ServletOutputStream sos = response.getOutputStream();
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(sos);
		encoder.encode(graphMgr.getGraph(macAddr, Integer.parseInt(graphType), Integer.parseInt(width), Integer.parseInt(height)));
		sos.close();
	}   	  	    
}