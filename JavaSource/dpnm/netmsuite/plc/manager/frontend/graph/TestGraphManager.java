package dpnm.netmsuite.plc.manager.frontend.graph;

import dpnm.netmsuite.plc.manager.frontend.*;

public class TestGraphManager {
	
	public static void main(String argc[]){
		IFrontEndManager frontMgr = FrontEndManager.getInstance();
		frontMgr.frontEndManagerstart();
		
		IGraphManager graphMgr = GraphManager.getInstance();
		
		graphMgr.getGraph("1111138", 1, 600, 400);
		
		frontMgr.frontEndManagerstop();
	}
}
