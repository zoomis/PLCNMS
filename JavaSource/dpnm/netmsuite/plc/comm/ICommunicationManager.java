package dpnm.netmsuite.plc.comm;

import java.rmi.*;
import java.util.*;

public interface ICommunicationManager extends Remote{
	
	// EMS 관련 Setting [ dpnm.netmsuite.plc.manager.backend.BackendManager ]
	int setTimeOut(long timeout);
	int setSpeedThreshold(long threshold);
	int setErrorThreshold(long threshold);
	int setMemThreshold(long threshold);
	int setPollingInterval(long interval);

	// EMS 관련 Setting 이긴 한데, 젤라인 MIB에는 없고, 우리가 정의한 DB에만 있음.
	int setWriteGroup(String writegroup);
	int setReadGroup(String readgroup);

	// Modem 관련 Setting :: [ dpnm.netmsuite.plc.manager.backend.BackendManager.ConfigurationManager ]
	int setSystemStatus(String target, int status); // target은 MAC Address
	int setRTSCTSStatus(String target, int status);	
}
