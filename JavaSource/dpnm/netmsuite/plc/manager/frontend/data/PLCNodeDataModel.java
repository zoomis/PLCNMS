package dpnm.netmsuite.plc.manager.frontend.data;

public abstract class PLCNodeDataModel {
	
	//EMS�� ��쿡�� _IPAddress �ƴ� ���� _MACAddressName�� ���δ�.
	//���� MasterModem�̳� Slave Modem �� IP�� �������� �������̶� �����Ͽ� ����
	//������ �𵨿� �� �ξ���.
	public String _IPAddress;
	public String _MACAddressName;
	public int _NodeType;
	public String _InPkts;
	public String _OutPkts;
	
	//�θ� �ִ� ��� �̸� ǥ���Ѵ�.
	//EMS �� ParentMACAddress�� null�̴�.
	public String _ParentMACAddress = null;
	
	//���¸� ��Ÿ����.
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
	
	// clone ����� �ʿ� ������ ������ ���� ���� ��.
	
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
