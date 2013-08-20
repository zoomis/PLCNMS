package dpnm.netmsuite.plc.manager.frontend.data.db;

import java.sql.*;

import com.sun.corba.se.spi.ior.MakeImmutable;

import dpnm.netmsuite.plc.manager.frontend.data.*;
import dpnm.netmsuite.util.PLCTreeNode;
import dpnm.netmsuite.util.*;

import java.util.*;
public class OracleDBManager implements DataManager {
	
	Connection con;
	
	public static OracleDBManager dbManager = null;
	
	DataModel model;
	DataUpdater updater;

	//TODO config File���� �ε� �ϵ��� ���ľ� �� ��~!
	
	protected static final String DB_URL = "jdbc:oracle:thin:@141.223.82.238:1521:XE";
	protected static final String DB_DRIVER ="oracle.jdbc.driver.OracleDriver";  
	protected static final String DB_USER = "plcnms";
	protected static final String DB_PASSWD = "plcnms";

	
	
	public static OracleDBManager getInstance(){
		if(dbManager == null){
			dbManager = new OracleDBManager();
			return dbManager;
		}
		else{
			return dbManager;
		}
	}
	
	private OracleDBManager(){
		makeConnection();
		model = new DataModel();
	}
	
	public PLCTreeNode getTreeMap(){
		return model.getTreeMap();
	}
	
	/**
	 * 
	 * @param nodeID
	 * @param field
	 * @return it must be found in dataModel;
	 */
	public String getData(String nodeID, String field){
		return model.getData(nodeID, field);
	}
	
	public void updateDataModelStart(){
		updater = new DBDataUpdater(model, con);
		updater.updateStart();
	}
	
	public void updateDataModelStop(){
		if(updater != null){
			updater.updateStop();
		}
	}
	
	public int getNodeType(String macAddr){
		return model.getNodeType(macAddr);
	}
	
	public Vector getStatics(String macAddr){
		Vector tempInfo = new Vector();
		Vector staticsInfos = new Vector();
		
		try{
			/**
			 * �� �������� timestamp�� ���Ͽ� ���� �ֽ� �����͸� 20�� ������ ���� query���̴�.
			 * (��������.) 
			 */
			String sqlForEMS = "select * from  (select * from STATISTICS where MACADDR='"  + macAddr + "' order by TIME_STAMP desc) where rownum <21" ;
			PreparedStatement pstmtForEMS = con.prepareStatement(sqlForEMS);
			
			ResultSet rSet;
			
			pstmtForEMS.executeUpdate();
			
			rSet = pstmtForEMS.getResultSet();
				
			
			while(rSet.next()){
				long _timeStamp = Long.parseLong(rSet.getString("TIME_STAMP"));
				int _inPkts = rSet.getInt("INPKTS");
				int _outPkts = rSet.getInt("OUTPKTS");
				int _inBytes= rSet.getInt("INBYTES");
				int _outBytes = rSet.getInt("OUTBYTES");
				int _inSpeed = rSet.getInt("INSPEED");
				int _outSpeed = rSet.getInt("OUTSPEED");
				
				StatisticInfo oneStaticInfo = new StatisticInfo(
						_timeStamp,_inPkts, _outPkts, _inBytes, _outBytes);
				oneStaticInfo._inSpeed = _inSpeed;
				oneStaticInfo._outSpeed = _outSpeed;
				tempInfo.add(oneStaticInfo);
			}
			
			pstmtForEMS.close();
			staticsInfos = reverseVector(tempInfo);
			tempInfo.removeAllElements();
			tempInfo = null;
		}
		catch(Exception e){
			e.printStackTrace();
			return staticsInfos;
		}
		
		
		return staticsInfos;
	}
	
	
	public boolean isValidateUser(String id, String passwd){
		
		try{
			String sqlForEMS = "select ID from USER_INFO where ID='"  + id + "' and PASSWD='" + passwd +"'" ;
			PreparedStatement pstmtForEMS = con.prepareStatement(sqlForEMS);
			
			ResultSet rSet;
			
			pstmtForEMS.executeUpdate();
			
			rSet = pstmtForEMS.getResultSet();
			
			
			while(rSet.next()){
				String _ID = rSet.getString("ID");
				return true;
			}
			
			pstmtForEMS.close();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	/**
	 * make db connection.
	 * if there is connection it doesn't any work.
	 */
	public void makeConnection(){
		
		if(con != null) return;
		
		try{
			//step 1.
			//�ش� DB ����̹��� �ε� �մϴ�.
			//�� �κ��� ��Ȯ�ϰ� �𸣰����� �ϳ��� ���ø����̼��� �������� DB�� ��� �� �� �������� �׽�Ʈ�� �ʿ��ϱ���.
			//�츮�� �׷� �ʿ䰡 �������� �н�~
			Class.forName(OracleDBManager.DB_DRIVER);
			
			//step 2.
			//Connection�� �����մϴ�.
			//���� connection�� ��������� Connection_Pool �̶� ������ �� �̴ϴٸ�
			//���⼭�� �ϳ��� ������ �����Ͽ� �ϳ��� ��Ű���� �̿��� ���̶�� �����Ǵ�
			//�� ���� ����� ����ص� �ɵ�?
			//���� �������� ��Ű���� �̿��� ���̶�� connection�� ������ �̸� ����� �־�� �� ����.
			
			con = DriverManager.getConnection(OracleDBManager.DB_URL, DB_USER, DB_PASSWD);
			System.out.println("ConnectionŬ������ ��ü con ����");
		}
		catch(ClassNotFoundException e1){
			e1.printStackTrace();
		}
		catch(SQLException e2){
			e2.printStackTrace();
		}
	}
	
	public Vector getFaultList(String macAddr){
		Vector faultList = new Vector();
		
		try{
			String sqlForEMS = "select * from FAULT where MACADDR='" + macAddr+"'" ;
			PreparedStatement pstmtForEMS = con.prepareStatement(sqlForEMS);
			
			ResultSet rSet;
			
			pstmtForEMS.executeUpdate();
			
			rSet = pstmtForEMS.getResultSet();
			
			while(rSet.next()){
				String _macAddr = macAddr;
				String _timeStamp = rSet.getString("TIME_STAMP");
				java.util.Date date = new java.util.Date(Long.parseLong(_timeStamp));
				String _type = rSet.getString("TYPE");
				FaultInfo falutInfo = new FaultInfo(date.toString(), _macAddr, model.getNodeType(_macAddr),Integer.parseInt(_type) );
				faultList.addElement(falutInfo);
			}
			
			pstmtForEMS.close();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return faultList;
	}
	
	public Vector getNewestFaultList( int faultListSize){
		Vector faultList = new Vector();
		
		try{
			String sqlForNewFaultLis = "select * from FAULT where rownum< " + faultListSize+ " order by TIME_STAMP desc";
			PreparedStatement pstmtForNewestFaults = con.prepareStatement(sqlForNewFaultLis);
			
			ResultSet rSet;
			
			pstmtForNewestFaults.executeUpdate();
			
			rSet = pstmtForNewestFaults.getResultSet();
			
			while(rSet.next()){
				String _macAddr = rSet.getString("MACADDR");
				String _timeStamp = rSet.getString("TIME_STAMP");
				java.util.Date date = new java.util.Date(Long.parseLong(_timeStamp));
				String _type = rSet.getString("TYPE");
				FaultInfo falutInfo = new FaultInfo(date.toString(),  _macAddr, model.getNodeType(_macAddr),Integer.parseInt(_type) );
				faultList.addElement(falutInfo);
			}
			
			pstmtForNewestFaults.close();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return faultList;
	}
	
	
	private static Vector reverseVector(Vector target){
		Vector newVector = new Vector();
		int targetVectorSize = target.size();
		
		for(int i=0; i<target.size();i++){
			newVector.add(target.elementAt(targetVectorSize-i-1));
		}
		
		return newVector;
	}
}
