package dpnm.netmsuite.plc.manager.frontend.data;

public abstract class PLCNodeDataModel {
	
	//EMS인 경우에는 _IPAddress 아닌 경우는 _MACAddressName이 쓰인다.
	//이후 MasterModem이나 Slave Modem 이 IP를 가질수도 있을것이라 추측하여 상위
	//데이터 모델에 ㅂ 두었다.
	public String _IPAddress;
	public String _MACAddressName;
	public int _NodeType;
	public String _InPkts;
	public String _OutPkts;
	
	//부모가 있는 경우 이를 표현한다.
	//EMS 는 ParentMACAddress가 null이다.
	public String _ParentMACAddress = null;
	
	//상태를 나타낸다.
	public int _Status;
	
	public PLCNodeDataModel(String ipAddress, String macAddress, int nodeType, String inPkts, String outPkts, String parentMACAddr){
		_IPAddress = ipAddress;
		_MACAddressName = macAddress;
		_NodeType = nodeType;
		_InPkts = inPkts;
		_OutPkts = outPkts;
		_ParentMACAddress = parentMACAddr;
	}

	public void set_Status(int _Status) {
		this._Status = _Status;
	}

	public int get_Status() {
		return _Status;
	}
	
	public abstract String getData(String field);
	
	public abstract String toString();
	
	// clone 기능이 필요 있을지 없을지 몰라서 내비 둠.
	
//	public abstract Object clone();
	
	
//	public void copyPLCBasicData(PLCNodeDataModel copy){
//		copy._Address =new String(this._Address);
//		copy._NodeType = this._NodeType;
//		copy._InPkts = new String(this._InPkts);
//		copy._OutPkts = new String(this._OutPkts);
//		copy._Status = this._Status;
//		copy._ParentMACAddress = new String(this._ParentMACAddress);
//	}
	

}
