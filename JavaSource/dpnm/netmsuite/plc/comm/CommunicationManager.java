package dpnm.netmsuite.plc.comm;

import java.io.*;

public class CommunicationManager implements ICommunicationManager{

	PrintStream out = System.out;
	
	// EMS ���� Setting [ dpnm.netmsuite.plc.manager.backend.BackendManager ]
	public int setTimeOut(long timeout){
		out.println("EMS " + timeout);
		return 0;
	}
	
	public int setSpeedThreshold(long threshold){
		out.println("EMS " + threshold);
		return 0;
	}
	
	public int setErrorThreshold(long threshold){
		out.println("EMS " + threshold);
		return 0;
	}
	
	public int setMemThreshold(long threshold){
		out.println("EMS " + threshold);
		return 0;
	}
	
	public int setPollingInterval(long interval){
		out.println("EMS " + interval);
		return 0;
	}

	// EMS ���� Setting �̱� �ѵ�, ������ MIB���� ����, �츮�� ������ DB���� ����.
	public int setWriteGroup(String writegroup){
		out.println("EMS " + writegroup);
		return 0;
	}
	
	public int setReadGroup(String readgroup){
		out.println("EMS " + readgroup);
		return 0;
	}

	// Modem ���� Setting :: [ dpnm.netmsuite.plc.manager.backend.BackendManager.ConfigurationManager ]
	public int setSystemStatus(String target, int status){ // target�� MAC Address
		out.println(target  + " " + status);
		return 0;
	}
	
	public int setRTSCTSStatus(String target, int status){
		out.println(target  + " " + status);
		return 0;
	}
}
