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

	//TODO config File에서 로드 하듯이 고쳐야 할 것~!
	
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
			 * 이 쿼리문은 timestamp를 비교하여 가장 최신 데이터를 20개 가지고 오는 query문이다.
			 * (역순으로.) 
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
			//해당 DB 드라이버를 로드 합니다.
			//이 부분은 정확하게 모르겠지만 하나의 어플리케이션이 여러개의 DB와 통신 할 수 있을지는 테스트가 필요하군요.
			//우리는 그럴 필요가 없음으로 패스~
			Class.forName(OracleDBManager.DB_DRIVER);
			
			//step 2.
			//Connection을 생성합니다.
			//만약 connection이 여러개라면 Connection_Pool 이라도 만들어야 할 겁니다만
			//여기서는 하나의 유저로 접속하여 하나의 스키마만 이용할 것이라고 생각되니
			//한 개만 만들고 사용해도 될듯?
			//만약 여러개의 스키마를 이용할 것이라면 connection을 여러개 미리 만들어 넣어야 할 것임.
			
			con = DriverManager.getConnection(OracleDBManager.DB_URL, DB_USER, DB_PASSWD);
			System.out.println("Connection클래스의 객체 con 생성");
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
