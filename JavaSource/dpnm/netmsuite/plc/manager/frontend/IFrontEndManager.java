package dpnm.netmsuite.plc.manager.frontend;

import java.util.*;
import dpnm.netmsuite.util.*;
import java.awt.image.*;

public interface IFrontEndManager {
	public void frontEndManagerstart()  ;
	public void frontEndManagerstop();
	public boolean isDataChanged();
	public boolean isTrapOccured();
	public String getData(String nodeID, String field);
	public PLCTreeNode getTreeMap();
	public int getNodeType(String macAddr);
	public boolean isValidateUser(String ID, String passwd);
	
	
	public Vector getFaultList(String macAddr);
	public Vector getNewestFaultList(int faultListSize);
	
	
	int setTimeOut(long timeout);
	int setSpeedThreshold(long threshold);
	int setErrorThreshold(long threshold);
	int setMemThreshold(long threshold);
	int setPollingInterval(long interval);

	// EMS ���� Setting �̱� �ѵ�, ������ MIB���� ����, �츮�� ������ DB���� ����.
	int setWriteGroup(String writegroup);
	int setReadGroup(String readgroup);

	// Modem ���� Setting :: [ dpnm.netmsuite.plc.manager.backend.BackendManager.ConfigurationManager ]
	int setSystemStatus(String target, int status); // target�� MAC Address
	int setRTSCTSStatus(String target, int status);	
}
