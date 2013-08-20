package dpnm.netmsuite.plc.manager.frontend.data.db;

import java.sql.*;
import java.util.*;

import dpnm.netmsuite.plc.manager.frontend.data.*;

public class DBDataUpdater extends DataUpdater{
	
	Connection con;
	
	public DBDataUpdater(DataModel model, Connection con){
		super(model);
		this.con = con;
		updateModel();
	}
	

	
	/**
	 * @TODO : 	 * synch�� �ɾ�� �ϳ� ���ƾ� �ϳ�??  * ���
	 * 
	 * step1. ������ DB�� ������ ������.
	 * step2. �������� �м��Ͽ� dataModel�� ������Ʈ �Ѵ�.
	 */
	public void updateModel(){
		
		PreparedStatement pstmtForEMS = null;
		PreparedStatement pstmtForMM = null;
		PreparedStatement pstmtForSM = null;
		PreparedStatement pstmtForR = null;
		
		if(con == null){
			
		}
		
		try{
			//step 3.
			//���� �� ����.
			//�̸� statement�� �Է��� ���� ���� statement���� ���� ������ ��Ź�ϴ� �κ��Դϴ�.
			//���� ���Ǵ� ������ ��� PreparedStatement�� ����ϸ� ���� ���� ȿ������ ����� �� �ִ�ϴ�.
			//set �� �κп� '?'�� �־� �ְ� ���� ���� set�� ���ָ� �Ǵ� ������
			//�ڼ��� ������ API ���� �ϼ���.
			
			String sqlForEMS = "select * from EMS";
	        pstmtForEMS = con.prepareStatement(sqlForEMS);
	        
	        String sqlForMM = "select * from MASTER";
	        pstmtForMM = con.prepareStatement(sqlForMM);
	        
	        String sqlForSM = "select * from SLAVE";
	        pstmtForSM = con.prepareStatement(sqlForSM);
	        
	        String sqlForR = "select * from REPEATER";
	        pstmtForR = con.prepareStatement(sqlForR);
	        
//	        Vector newEMSVector = new Vector();
//	        Vector newMMVector = new Vector();
//	        Vector newSMVector = new Vector();
//	        Vector newRVector = new Vector();
	        
	        pstmtForEMS.executeUpdate();
	        pstmtForMM.executeUpdate();
	        pstmtForSM.executeUpdate();
	        pstmtForR.executeUpdate();
	        
	        synchronized (model){
	        	model.removeAllPLCData();
	        	
	        	ResultSet rSet = pstmtForEMS.getResultSet();
	        	while(rSet.next()){
	        		EMSDataModel ems = makeEMSDataModel(rSet);
//	        		newEMSVector.add(ems);
	        		model.addPLCNode(ems);
	        	}
	        	
	        	
	        	rSet = pstmtForMM.getResultSet();
	        	while(rSet.next()){
	        		MasterModemDataModel mm = makeMasterModemDataModel(rSet);
//	        		newMMVector.add(mm);
	        		model.addPLCNode(mm);
	        	}
	        	
	        	rSet = pstmtForSM.getResultSet();
	        	while(rSet.next()){
	        		SlaveModemDataModel sm = makeSlaveModemDataModel(rSet);
//	        		newSMVector.add(sm);
	        		model.addPLCNode(sm);
	        	}
	        	
	        	rSet = pstmtForR.getResultSet();
	        	while(rSet.next()){
	        		RepeaterDataModel r = makeRepeaterDataModel(rSet);
//	        		newRVector.add(r);
	        		model.addPLCNode(r);
	        	}
	        }

			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//step 4.
	        //statement ����.
			try{
				if(pstmtForEMS != null){
					pstmtForEMS.close();
				}
				if(pstmtForMM != null){
					pstmtForMM.close();
				}
				
				if(pstmtForSM != null){
					pstmtForSM.close();
				}
				
				if(pstmtForR != null){
					pstmtForR.close();
				}
			}
			catch(Exception e){
				
			}
		}
	}
	
	
	public void disconnectionWithDB(){
		//step 5.
        //connection ����
		try{
			con.close();
		}
		catch(SQLException e){
			
		}
//		System.out.println("�����ͺ��̽��� ������ ����");
	}
	
	private EMSDataModel makeEMSDataModel(ResultSet rSet){
		
		/*
		 INDEXNUMBER (will be skipped!)
		 IPADDR
		 NETMASK, GATEWAY, DNSADDR
		 REGISTERNUM, UNREGISTERNUM
		 POLLINGINTERVAL, TIMEOUT
		 WRITEGROUP, READGROUP
		 INPKT, OUTPKT
		 SPEEDTHRESHOLD, ERRORTHRESHOLD
		 MEMORYSIZE, FREEMEMSIZE, MEMTHRESHOLD
		 */
		EMSDataModel newDataModel = null;
		try{
			newDataModel =  new EMSDataModel(
					//IPAddr
					rSet.getString("IPADDR"),
					//NetMask
					rSet.getString("NETMASK"),
					//GateWay
					rSet.getString("GATEWAY"),
					//DNSAddr
					rSet.getString("DNSADDR"),
					//RegisterNUM			
					rSet.getString("REGISTERNUM"),
					//UnResiterNUM
					rSet.getString("UNREGISTERNUM"),
					//PollingInterval
					rSet.getString("POLLINGINTERVAL"),
					//TimeOut
					rSet.getString("TIMEOUT"),
					//WriteGroup
					rSet.getString("WRITEGROUP"),
					//ReadGroup
					rSet.getString("READGROUP"),
					//Inpkts
					rSet.getString("INPKT"),
					//outPkte	
					rSet.getString("OUTPKT"),
					//SpeedThreshold
					rSet.getString("SPEEDTHRESHOLD"),
					//ErrorThreshold
					rSet.getString("ERRORTHRESHOLD"),
					//MemorySize
					rSet.getString("MEMORYSIZE"),
					//FreeMemorySize
					rSet.getString("FREEMEMSIZE"),
					//MemoryThreshold
					rSet.getString("MEMTHRESHOLD"),
					rSet.getString("MACADDR"),
					rSet.getString("FIRMWAREVER")
			);
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		
		return newDataModel;
	}
	
	private MasterModemDataModel makeMasterModemDataModel(ResultSet rSet){
		MasterModemDataModel newDataModel = null;
		
		/* Table column
		MACADDR
		STATUS 
		INPKTS, 		OUTPKTS
		INACKS, 		OUTACKS
		INFAILS, 		OUTFAILS
		LINKEDNODENUM, 
		EMSIP
		*/
		
		try{
			newDataModel =  new MasterModemDataModel(
					//MacAddr
					rSet.getString(2),
					//Inpkts
					rSet.getString(4),
					//outPkte	
					rSet.getString(5),
					//InAcks
					rSet.getString(6),
					//OutAcks
					rSet.getString(7),
					//InFails
					rSet.getString(8),
					//OutFails
					rSet.getString(9),
					//LinkedNodeNum
					rSet.getString(10),
					//EMSIP
					rSet.getString(11),
					//RTSCTSStatus
					rSet.getString(12),
					/*
	 DEFAULTSID, DEFAULTGID
	 CURRENTGID, FIRMWAREVER
	 REGTYPE, REGSTATUS  
	 INBYTES, OUTBYTES
	 INPUNC, OUTPUNC
	 CFCS ,	 DFCS
					 */
					rSet.getString("DEFAULTSID"),
					rSet.getString("DEFAULTGID"),
					rSet.getString("CURRENTGID"),
					rSet.getString("FIRMWAREVER"),
					rSet.getString("REGTYPE"),
					rSet.getString("REGSTATUS"),
					rSet.getString("INBYTES"),
					rSet.getString("OUTBYTES"),
					rSet.getString("INPUNC"),
					rSet.getString("OUTPUNC"),
					rSet.getString("CFCS"),
					rSet.getString("DFCS")
			);
			
			newDataModel.set_Status(rSet.getInt(3));
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		
		return newDataModel;
	}
	
	private SlaveModemDataModel makeSlaveModemDataModel(ResultSet rSet){

	/* Table Column
	 MACADDR
	 STATUS
	 INPKTS, 	 OUTPKTS
	 INACKS, 	 OUTACKS
	 INFAILS, 	 OUTFAILS
	 INAGCGAIN, OUTAGCGAIN
	 INPHYBPC, 	 OUTPHYBPC
	 INSPEED, 	 OUTSPEED
	 INERROR, 	 OUTERROR
	 PARENTMAC
	 */
		SlaveModemDataModel newDataModel = null;
		
		try{
			newDataModel =  new SlaveModemDataModel(
					//MacAddr
					rSet.getString(2),
					//Inpkts
					rSet.getString(4),
					//outPkte	
					rSet.getString(5),
					//InAcks
					rSet.getString(6),
					//OutAcks
					rSet.getString(7),
					//InFails
					rSet.getString(8),
					//OutFails
					rSet.getString(9),
					//INAGCGAIN
					rSet.getString(10),
					//OUTAGCGAIN
					rSet.getString(11),
					//INPHYBPC
					rSet.getString(12),
					//OUTPHYBPC
					rSet.getString(13),
					//INSPEED
					rSet.getString(14),
					//OUTSPEED
					rSet.getString(15),
					//INERROR
					rSet.getString(16),
					//OUTERROR
					rSet.getString(17),
					//Parent_IP
					rSet.getString(18),
					//RTSCTSStatus
					rSet.getString(19),
					rSet.getString("DEFAULTSID"),
					rSet.getString("DEFAULTGID"),
					rSet.getString("CURRENTGID"),
					rSet.getString("FIRMWAREVER"),
					rSet.getString("REGTYPE"),
					rSet.getString("REGSTATUS"),
					rSet.getString("INBYTES"),
					rSet.getString("OUTBYTES"),
					rSet.getString("INPUNC"),
					rSet.getString("OUTPUNC"),
					rSet.getString("CFCS"),
					rSet.getString("DFCS")
			);
			
			newDataModel.set_Status(rSet.getInt(3));
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		
		return newDataModel;
	}
	
	private RepeaterDataModel makeRepeaterDataModel(ResultSet rSet){
		/*
		 INDEXNUMBER
		 MACADDR
		 STATUS
		 INPKTS,	 OUTPKTS
		 INACKS, 	 OUTACKS
		 INFAILS, 	 OUTFAILS
		 INAGCGAIN,  OUTAGCGAIN
		 INPHYBPC,	 OUTPHYBPC
		 INSPEED,	 OUTSPEED
		 INERROR,	 OUTERROR
		 PARENTMAC
		 LINKEDNODENUM
		 */
		
		RepeaterDataModel newDataModel = null;
		
		try{
			newDataModel =  new RepeaterDataModel(
					//MacAddr
					rSet.getString(2),
					//Inpkts
					rSet.getString(4),
					//outPkte	
					rSet.getString(5),
					//InAcks
					rSet.getString(6),
					//OutAcks
					rSet.getString(7),
					//InFails
					rSet.getString(8),
					//OutFails
					rSet.getString(9),
					//INAGCGAIN
					rSet.getString(10),
					//OUTAGCGAIN
					rSet.getString(11),
					//INPHYBPC
					rSet.getString(12),
					//OUTPHYBPC
					rSet.getString(13),
					//INSPEED
					rSet.getString(14),
					//OUTSPEED
					rSet.getString(15),
					//INERROR
					rSet.getString(16),
					//OUTERROR
					rSet.getString(17),
					//Parent_MAC
					rSet.getString(18),
					//Linked)NodeNum
					rSet.getString(19),
					//RTSCTSStatus
					rSet.getString(19),
					rSet.getString("DEFAULTSID"),
					rSet.getString("DEFAULTGID"),
					rSet.getString("CURRENTGID"),
					rSet.getString("FIRMWAREVER"),
					rSet.getString("REGTYPE"),
					rSet.getString("REGSTATUS"),
					rSet.getString("INBYTES"),
					rSet.getString("OUTBYTES"),
					rSet.getString("INPUNC"),
					rSet.getString("OUTPUNC"),
					rSet.getString("CFCS"),
					rSet.getString("DFCS")
			);
			
			newDataModel.set_Status(rSet.getInt(3));
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		
		return newDataModel;
	}
	
}
