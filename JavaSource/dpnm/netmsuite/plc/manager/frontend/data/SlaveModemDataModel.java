package dpnm.netmsuite.plc.manager.frontend.data;

import dpnm.netmsuite.util.PLCInfoDefinition;
import sun.nio.cs.ext.MacArabic;

public class SlaveModemDataModel extends PLCNodeDataModel{
	
	/* Table Column
	 MACADDR
	 STATUS
	 INPKTS, 	 OUTPKTS
	 INACKS, 	 OUTACKS
	 INFAILS, 	 OUTFAILS
	 INAGCGAIN, 	 OUTAGCGAIN
	 INPHYBPC, 	 OUTPHYBPC
	 INSPEED, 	 OUTSPEED
	 INERROR, 	 OUTERROR
	 PARENTMAC
	 
	 DEFAULTSID, DEFAULTGID
	 CURRENTGID, FIRMWAREVER
	 REGTYPE, REGSTATUS  
	 INBYTES, OUTBYTES
	 INPUNC, OUTPUNC
	 CFCS ,	 DFCS
	 
	 */

	
	//1
	public String _InAcks, 	  _OutAcks,
	_InFails, 	  _OutFails,
	_InAGCGain,   _OutAGCGain,
	_InPhyBPC, 	  _OutPhyBPC,
	_InSpeed, 	  _OutSpeed,
	_InError, 	  _OutError,  _RTSCTSStatus;
	
	//2
	public String _DefaultID, _DefaultGID, _CurrentGID, _FirmWareVer,
	_RegType, _RegStatus, _InBytes, _OutBytes, _InPunc, _OutPunc, _CFCS, _DFCS;
	
	public SlaveModemDataModel(String macAddr, String inPkts, String outPkts,
			String inAcks, String outAcks, String inFails, String outFails,
			String inAGCGain, String outAGCGain, String inPhyBPC, String outPhyBPC,
			String inSpeed, String outSpeed, String inError, String outError,
			String parentMAC, String RTSCTSStatus,
			String DefaultID, String DefaultGID, String CurrentGID,
			String FirmWareVer,
			String RegType, String RegStatus,
			String InBytes, String OutBytes,
			String InPunc, String OutPunc,
			String CFCS, String DFCS
	)
	{
		super(null, macAddr, PLCInfoDefinition.NODETYPE_SLAVE_MODEM, inPkts, outPkts, parentMAC);
		_InAcks = inAcks;		_OutAcks = outAcks;
		_InFails = inFails; 		_OutFails = outFails;
		_InAGCGain = inAGCGain;		_OutAGCGain = outAGCGain;
		_InPhyBPC = inPhyBPC;		_OutPhyBPC = outPhyBPC;
		_InSpeed = inSpeed;		_OutSpeed = outSpeed;
		_InError = inError;		_OutError = outError;
		_RTSCTSStatus = RTSCTSStatus;
		_CurrentGID = CurrentGID;
		_DefaultID = DefaultID; _DefaultGID = DefaultGID;
		_FirmWareVer = FirmWareVer;
		_RegType = RegType; _RegStatus = RegStatus;
		_InBytes = InBytes; _OutBytes = OutBytes;
		_CFCS = CFCS; _DFCS = DFCS;
	}
	
	/* Table Column
	 MACADDR
	 STATUS
	 INPKTS, 	 OUTPKTS
	 INACKS, 	 OUTACKS
	 INFAILS, 	 OUTFAILS
	 INAGCGAIN, 	 OUTAGCGAIN
	 INPHYBPC, 	 OUTPHYBPC
	 INSPEED, 	 OUTSPEED
	 INERROR, 	 OUTERROR
	 PARENTMAC
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
		else if(fieldID.compareToIgnoreCase("INAGCGAIN") == 0){
			return this._InAGCGain;
		}
		else if(fieldID.compareToIgnoreCase("OUTAGCGAIN") == 0){
			return this._OutAGCGain;
		}
		else if(fieldID.compareToIgnoreCase("INPHYBPC") == 0){
			return this._InPhyBPC;
		}
		else if(fieldID.compareToIgnoreCase("OUTPHYBPC") == 0){
			return this._OutAGCGain;
		}
		else if(fieldID.compareToIgnoreCase("INSPEED") == 0){
			return this._InSpeed;
		}
		else if(fieldID.compareToIgnoreCase("OUTSPEED") == 0){
			return this._OutSpeed;
		}
		else if(fieldID.compareToIgnoreCase("INERROR") == 0){
			return this._InError;
		}
		else if(fieldID.compareToIgnoreCase("OUTERROR") == 0){
			return this._OutError;
		}
		else if(fieldID.compareToIgnoreCase("PARENTMAC") == 0){
			return this._ParentMACAddress;
		}
		else if(fieldID.compareToIgnoreCase("RTSCTSSTATUS") ==0){
			return this._RTSCTSStatus;
		}
		/*
		 extra~ 
		 
		 DEFAULTSID, DEFAULTGID
		 CURRENTGID, FIRMWAREVER
		 REGTYPE, REGSTATUS  
		 INBYTES, OUTBYTES
		 INPUNC, OUTPUNC
		 CFCS ,	 DFCS
		 * 
		 */
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
	
	public String toString(){
		return "Slave Modem " + _MACAddressName;
	}
	
//	public Object clone(){
//	SlaveModemDataModel copiedData = new SlaveModemDataModel();
//	
//	copyPLCBasicData(copiedData);
//	
//	copiedData._InAcks = new String(this._InAcks);
//	copiedData._InFails = new String(this._InFails);
//	copiedData._OutFails = new String(this._OutFails);
//	copiedData._OutAcks = new String(this._OutAcks);
//	copiedData._InAGCGain = new String(this._InAGCGain);
//	copiedData._OutAGCGain = new String(this._OutAGCGain);
//	copiedData._InPhyBPC = new String(this._InPhyBPC);
//	copiedData._OutPhyBPC = new String(this._OutPhyBPC);
//	copiedData._InSpeed = new String(this._InSpeed);
//	copiedData._OutSpeed = new String(this._OutSpeed);
//	copiedData._InError = new String(this._InError);
//	copiedData._OutError = new String(this._OutError);
//	
//	return copiedData;
//	}
}
