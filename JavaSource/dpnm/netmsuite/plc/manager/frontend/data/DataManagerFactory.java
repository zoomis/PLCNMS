package dpnm.netmsuite.plc.manager.frontend.data;

import java.util.*;
import dpnm.netmsuite.plc.manager.frontend.data.db.*;

public class DataManagerFactory {
	
	public static DataManager createDataManager(String className, Vector parameters) throws ClassNotFoundException{
		if(className.equals("DBManager")){
			
			return OracleDBManager.getInstance();
		}
		else{
			throw new ClassNotFoundException(); 
		}
		
	}
	
}
