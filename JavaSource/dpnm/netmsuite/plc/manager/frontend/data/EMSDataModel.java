package dpnm.netmsuite.plc.manager.frontend.data;

import dpnm.netmsuite.util.PLCInfoDefinition;

public class EMSDataModel extends PLCNodeDataModel{
	
	/*
IPADDR
NETMASK, GATEWAY, DNSADDR
REGISTERNUM, UNREGISTERNUM
POLLINGINTERVAL, TIMEOUT
WRITEGROUP, READGROUP
INPKT, OUTPKT
SPEEDTHRESHOLD, ERRORTHRESHOLD
MEMORYSIZE, FREEMEMSIZE, MEMTHRESHOLD
MACADDR,FIRMWAREVER

	*/
	//public String IPAddr;
	public String _NetMask, _GateWay,_DNSAddr, _RegisterNUM;
	public String _UnRegisterNUM, _PollingInterval,_TimeOut, _WriteGroup;
	public String _ReadGroup, _SpeedThreshold, _ErrorThreshold, _MemorySize;
	public String _FreeMemorySize, _MemoryThreshold,_MacAddr, _FirmWareVer;
	
	public EMSDataModel(String ipAddr, String netMask, String gateWay,
			String dnsAddr, String registerNUM, String unRegisterNUM,
			String pollingInterval, String timeOut, String writeGroup,
			String readGroup, String inPkts, String outPkts,
			String speedThreshold, String errorThreshold,
			String memorySize, String freeMemorySize, String memoryThreshold,
			String macAddr, String firmWareVer
	)
	{
		super(ipAddr, macAddr, PLCInfoDefinition.NODETYPE_EU, inPkts, outPkts, null);
		_NetMask = netMask;
		_GateWay = gateWay;
		_DNSAddr = dnsAddr;
		_RegisterNUM = registerNUM;
		_UnRegisterNUM = unRegisterNUM;
		_PollingInterval = pollingInterval;
		_TimeOut = timeOut;
		_WriteGroup = writeGroup;
		_ReadGroup = readGroup;
		_SpeedThreshold = speedThreshold;
		_ErrorThreshold = errorThreshold;
		_MemorySize = memorySize;
		_FreeMemorySize = freeMemorySize;
		_MemoryThreshold = memoryThreshold;
		_FirmWareVer = firmWareVer;
	}
	
	
	/*
	IPADDR
	NETMASK, GATEWAY, DNSADDR
	REGISTERNUM, UNREGISTERNUM
	POLLINGINTERVAL, TIMEOUT
	WRITEGROUP, READGROUP
	INPKT, OUTPKT
	SPEEDTHRESHOLD, ERRORTHRESHOLD
	MEMORYSIZE, FREEMEMSIZE, MEMTHRESHOLD
		*/
	public String getData(String fieldID){
		if(fieldID.compareToIgnoreCase("IPADDR") == 0){
			return this._IPAddress;
		}
		else if(fieldID.compareToIgnoreCase("NETMASK") == 0){
			return this._NetMask;
		}
		else if(fieldID.compareToIgnoreCase("GATEWAY") == 0){
			return this._GateWay;
		}
		else if(fieldID.compareToIgnoreCase("DNSADDR") == 0){
			return this._DNSAddr;
		}
		else if(fieldID.compareToIgnoreCase("REGISTERNUM") == 0){
			return this._RegisterNUM;
		}
		else if(fieldID.compareToIgnoreCase("UNREGISTERNUM") == 0){
			return this._UnRegisterNUM;
		}
		else if(fieldID.compareToIgnoreCase("POLLINGINTERVAL") == 0){
			return this._PollingInterval;
		}
		else if(fieldID.compareToIgnoreCase("TIMEOUT") == 0){
			return this._TimeOut;
		}
		else if(fieldID.compareToIgnoreCase("WRITEGROUP") == 0){
			return this._WriteGroup;
		}
		else if(fieldID.compareToIgnoreCase("READGROUP") == 0){
			return this._ReadGroup;
		}
		else if(fieldID.compareToIgnoreCase("INPKT") == 0){
			return this._InPkts;
		}
		else if(fieldID.compareToIgnoreCase("OUTPKT") == 0){
			return this._OutPkts;
		}
		else if(fieldID.compareToIgnoreCase("SPEEDTHRESHOLD") == 0){
			return this._SpeedThreshold;
		}
		else if(fieldID.compareToIgnoreCase("ERRORTHRESHOLD") == 0){
			return this._ErrorThreshold;
		}
		else if(fieldID.compareToIgnoreCase("MEMORYSIZE") == 0){
			return this._MemorySize;
		}
		else if(fieldID.compareToIgnoreCase("FREEMEMSIZE") == 0){
			return this._FreeMemorySize;
		}
		else if(fieldID.compareToIgnoreCase("MEMTHRESHOLD") == 0){
			return this._MemoryThreshold;
		}
		else if(fieldID.compareToIgnoreCase("FIRMWAREVER") == 0){
			return this._FirmWareVer;
		}
	
		else{
			return "No Data : Fild error code #109";
		}
	}
	
	public String toString(){
		return "EMS " + this._IPAddress;
	}
//	public Object clone(){
//		EMSDataModel copiedData = new EMSDataModel();
//		
//		copyPLCBasicData(copiedData);
//		
//		copiedData._ReadGroup = new String(this._ReadGroup);
//		copiedData._WriteGroup = new String(this._WriteGroup);
//		copiedData._SpeedThreshold = new String(this._SpeedThreshold);
//		copiedData._ErrorThreshold = new String(this._ErrorThreshold);
//		copiedData._MemorySize = new String(this._MemorySize);
//		copiedData._FreeMemorySize = new String(this._FreeMemorySize);
//		copiedData._MemoryThreshold = new String(this._MemoryThreshold);
//		
//		return copiedData;
//	}
}
