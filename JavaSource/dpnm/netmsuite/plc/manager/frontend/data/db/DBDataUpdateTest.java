package dpnm.netmsuite.plc.manager.frontend.data.db;

import dpnm.netmsuite.plc.manager.frontend.data.*;
import dpnm.netmsuite.util.*;
import dpnm.netmsuite.plc.manager.frontend.*;

public class DBDataUpdateTest {

	
	
	public static void main(String argc[]){
		
		IFrontEndManager frontEndMgr = FrontEndManager.getInstance();
		frontEndMgr.frontEndManagerstart();
		frontEndMgr.getNewestFaultList(4);
		frontEndMgr.frontEndManagerstop();
	}
	
}
