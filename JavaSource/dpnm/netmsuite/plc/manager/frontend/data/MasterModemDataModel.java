package dpnm.netmsuite.plc.manager.frontend.data;

import dpnm.netmsuite.util.PLCInfoDefinition;

public class MasterModemDataModel extends PLCNodeDataModel {
	
	/* Table column
	 MACADDR ,	STATUS
	 INPKTS	OUTPKTS
	 INACKS	OUTACKS
	 INFAILS	OUTFAILS
	 LINKEDNODENUM
	 EMSIP
	 RTSCTSStatus
	 */
	
	/* extra
	 _DefaultID, _DefaultGID, _CurrentGID,
	  _FirmWareVer,
	 _RegType, _RegStatus, 
	 _InBytes, _OutBytes, 
	 _InPunc, _OutPunc,
	  _CFCS, _DFCS;
	 */
	
	public String _InAcks;
	public String _OutAcks;
	public String _InFails;
	public String _OutFails;
	public String _LinkedNodeNum;
	public String _RTSCTSStatus;
	
	 public String _DefaultID, _DefaultGID, _CurrentGID, _FirmWareVer,
	  _RegType, _RegStatus, _InBytes, _OutBytes, _InPunc, _OutPunc, _CFCS, _DFCS;
	
	public MasterModemDataModel(String macAddr,
			String inPkts, String outPkts,
			String inAcks, String outAcks,
			String inFails, String outFails,
			String linkedNodeNum, String emsIP, String RTSCTSStuats,
			String DefaultID, String DefaultGID, String CurrentGID,
			String FirmWareVer,
			String RegType, String RegStatus,
			String InBytes, String OutBytes,
			String InPunc, String OutPunc,
			String CFCS, String DFCS
	)
	{
		super(null, macAddr, PLCInfoDefinition.NODETYPE_MASTER_MODEM, inPkts, outPkts, emsIP);
		
		_InAcks = inAcks;
		_OutAcks = outAcks;
		_InFails = inFails;
		_OutFails = outFails;
		_LinkedNodeNum = linkedNodeNum;
		_RTSCTSStatus = RTSCTSStuats;
		
		_DefaultID = DefaultID; _DefaultGID = DefaultGID;
		_CurrentGID = CurrentGID;
		_FirmWareVer = FirmWareVer;
		_RegType = RegType; _RegStatus = RegStatus;
		_InBytes = InBytes; _OutBytes = OutBytes;
		_CFCS = CFCS; _DFCS = DFCS;
		
	}	
	
	/* Table column
	 MACADDR
	 STATUS
	 INPKTS, 	OUTPKTS
	 INACKS, 	OUTACKS
	 INFAILS, 	OUTFAILS
	 LINKEDNODENUM
	 EMSIP
	 
	 _DefaultID, _DefaultGID, _CurrentGID,
	  _FirmWareVer,
	 _RegType, _RegStatus, 
	 _InBytes, _OutBytes, 
	 _InPunc, _OutPunc,
	  _CFCS, _DFCS;
	 */
	public String getData(String fieldID){
		if(fieldID.compareToIgnoreCase("MACADDR") == 0){
			return this._MACAddressName;
		}
		else if(fieldID.compareToIgnoreCase("STATUS") == 0){
			return "" + this._Status;
		}
		else if(fieldID.compareToIgnoreCase("INPKTS") == 0){
			return this._InPkts;
		}
		else if(fieldID.compareToIgnoreCase("OUTPKTS") == 0){
			return this._OutPkts;
		}
		else if(fieldID.compareToIgnoreCase("INACKS") == 0){
			return this._InAcks;
		}
		else if(fieldID.compareToIgnoreCase("OUTACKS") == 0){
			return this._OutAcks;
		}
		else if(fieldID.compareToIgnoreCase("INFAILS") == 0){
			return this._InFails;
		}
		else if(fieldID.compareToIgnoreCase("OUTFAILS") == 0){
			return this._OutFails;
		}
		else if(fieldID.compareToIgnoreCase("LINKEDNODENUM") == 0){
			return this._LinkedNodeNum;
		}
		else if(fieldID.compareToIgnoreCase("EMSIP") == 0){
			return this._ParentMACAddress;
		}
		else if(fieldID.compareToIgnoreCase("RTSCTSSTATUS") ==0){
			return this._RTSCTSStatus;
		}
		else if(fieldID.compareToIgnoreCase("DEFAULTSID") ==0){
			return this._DefaultID;
		}
		else if(fieldID.compareToIgnoreCase("DEFAULTGID") ==0){
			return this._DefaultGID;
		}
		else if(fieldID.compareToIgnoreCase("CURRENTGID") ==0){
			return this._CurrentGID;
		}
		else if(fieldID.compareToIgnoreCase("FIRMWAREVER") ==0){
			return this._FirmWareVer;
		}
		else if(fieldID.compareToIgnoreCase("REGTYPE") ==0){
			return this._RegType;
		}
		else if(fieldID.compareToIgnoreCase("REGSTATUS") ==0){
			return this._RegStatus;
		}
		else if(fieldID.compareToIgnoreCase("INBYTES") ==0){
			return this._InBytes;
		}
		else if(fieldID.compareToIgnoreCase("OUTBYTES") ==0){
			return this._OutBytes;
		}
		else if(fieldID.compareToIgnoreCase("INPUNC") ==0){
			return this._InPunc;
		}
		else if(fieldID.compareToIgnoreCase("OUTPUNC") ==0){
			return this._OutPunc;
		}
		else if(fieldID.compareToIgnoreCase("CFCS") ==0){
			return this._CFCS;
		}
		else if(fieldID.compareToIgnoreCase("DFCS") ==0){
			return this._DFCS;
		}
		else{
			return "No Data : Fild error code #109";
		}
	}
	
//	public Object clone(){
//	MasterModemDataModel copiedData = new MasterModemDataModel();
//	
//	copyPLCBasicData(copiedData);
//	
//	copiedData._InAcks = new String(this._InAcks);
//	copiedData._InFails = new String(this._InFails);
//	copiedData._OutFails = new String(this._OutFails);
//	copiedData._OutAcks = new String(this._OutAcks);
//	copiedData._LinkedNodeNum = new String(this._LinkedNodeNum);
//	
//	return copiedData;
//	}
	
	public String toString(){
		return "Master Modem " + _MACAddressName;
	}
	
}
