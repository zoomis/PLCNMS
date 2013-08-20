package dpnm.netmsuite.plc.comm;

import java.rmi.*;
import java.util.*;

public interface ICommunicationManager extends Remote{
	
	// EMS ���� Setting [ dpnm.netmsuite.plc.manager.backend.BackendManager ]
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
