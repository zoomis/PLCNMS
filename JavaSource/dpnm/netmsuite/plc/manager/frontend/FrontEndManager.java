package dpnm.netmsuite.plc.manager.frontend;

import java.util.*;

import dpnm.netmsuite.util.*;
import dpnm.netmsuite.plc.manager.frontend.data.*;
import dpnm.netmsuite.plc.manager.frontend.data.db.*;
import dpnm.netmsuite.plc.manager.frontend.user.*;
import dpnm.netmsuite.plc.comm.*;
import java.awt.image.*;
import java.net.MalformedURLException;
import java.rmi.*;


import dpnm.netmsuite.plc.manager.frontend.graph.*;

public class FrontEndManager implements IFrontEndManager{
	
	static FrontEndManager instance= null;
	
	DataManager dataManager;
	AuthenticationChecker userChecker;
	ICommunicationManager commMgr;
	IGraphManager graphMgr;
	
	public static FrontEndManager getInstance(){
		if(instance == null){
			instance = new FrontEndManager();
		}
		return instance;
	}
	
	private FrontEndManager(){
		try{
			dataManager = DataManagerFactory.createDataManager("DBManager", null);
			userChecker = new AuthenticationChecker();
			graphMgr = GraphManager.getInstance();
			bindCommManager();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
	static boolean check = false;
	public void frontEndManagerstart(){
		if (check == false) {
			dataManager.updateDataModelStart();
			check = true;
		}
	}
	
	public void frontEndManagerstop(){
		dataManager.updateDataModelStop();
	}
	
	public boolean isDataChanged(){
		return false;
	}
	
	public boolean isTrapOccured(){
		return false;
	}
	
	public void bindCommManager(){
		try{
			commMgr = (ICommunicationManager)Naming.lookup("//localhost/commMgr");
		}
		catch(MalformedURLException e){
			e.printStackTrace();
			commMgr= null;
		}
		catch(NotBoundException e){
			e.printStackTrace();
			commMgr = null;
		}
		catch(RemoteException e){
			e.printStackTrace();
			commMgr = null;
		}
	}
	
	public Vector getNewestFaultList( int faultListSize){
		return dataManager.getNewestFaultList( faultListSize);
	}
	
	
	public Vector getFaultList(String macAddr){
		return dataManager.getFaultList(macAddr);
	}	
	
	public String getData(String nodeID, String field){
		return dataManager.getData(nodeID, field);
	}
	
	public PLCTreeNode getTreeMap(){
		return dataManager.getTreeMap();
	}
	
	public int getNodeType(String macAddr){
		return dataManager.getNodeType(macAddr);
	}
	
	public boolean isValidateUser(String id, String passwd){
		return userChecker.isValidateUser(id, passwd);
	}
	
	public int setTimeOut(long timeout){
		if(commMgr != null){
			return commMgr.setTimeOut(timeout);
		}
		else return -1;
	}
	
	public int setSpeedThreshold(long threshold){
		if(commMgr != null){
			return commMgr.setSpeedThreshold(threshold);
		}
		else return -1;
	}
	
	public int setErrorThreshold(long threshold){
		if(commMgr != null){
			return commMgr.setErrorThreshold(threshold);
		}
		return -1;
	}
	
	public int setMemThreshold(long threshold){
		if(commMgr != null){
			return commMgr.setMemThreshold(threshold);
		}
		return -1;
	}
	
	public int setPollingInterval(long interval){
		if(commMgr != null){
			commMgr.setPollingInterval(interval);
		}
		return -1;
	}

	// EMS 관련 Setting 이긴 한데, 젤라인 MIB에는 없고, 우리가 정의한 DB에만 있음.
	public int setWriteGroup(String writegroup){
		if(commMgr != null){
			return commMgr.setWriteGroup(writegroup);
		}
		else{
			return -1;
		}
	}
	
	public int setReadGroup(String readgroup){
		if(commMgr != null){
			return commMgr.setReadGroup(readgroup);
		}
		else
		{
			return -1;
		}
	}

	// Modem 관련 Setting :: [ dpnm.netmsuite.plc.manager.backend.BackendManager.ConfigurationManager ]
	public int setSystemStatus(String target, int status){ // target은 MAC Address
		if(commMgr != null){
			return commMgr.setSystemStatus(target, status);
		}
		else{
			return -1;
		}
	}
	
	public int setRTSCTSStatus(String target, int status){
		if(commMgr != null){
			return commMgr.setRTSCTSStatus(target, status);
		}
		else{
			return -1;
		}
	}
}