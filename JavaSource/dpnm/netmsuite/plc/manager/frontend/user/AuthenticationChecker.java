package dpnm.netmsuite.plc.manager.frontend.user;

import dpnm.netmsuite.plc.manager.frontend.data.db.OracleDBManager;

public class AuthenticationChecker {
	
	public boolean isValidateUser(String id, String passwd){
		
		OracleDBManager dbMgr = OracleDBManager.getInstance();
		return dbMgr.isValidateUser(id, passwd);
	}
	
	public static void main(String argc[]){
		AuthenticationChecker checker = new AuthenticationChecker();
		System.out.println(checker.isValidateUser("root", "public"));
	}
}
