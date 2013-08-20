package dpnm.netmsuite.util;

public class FaultInfo {
	public String time;
	public String macAddr;
	public int nodeType;
	public String type;	//Speed[0], Error[1], Memory[2], ModemDown[3]
	

	
	public FaultInfo(String time, String macAddr, int nodeType, int type){
		this.time = time;
		this.macAddr = macAddr;
		this.nodeType = nodeType;
		if(type == 0){
			this.type  = "low speed";
		}
		else if(type == 1){
			this.type  = "error";
		}
		else if(type == 2){
			this.type  = "not enough memory";
		}
		else if(type ==3){
			this.type  = "connection is lost";
		}
		else{
			this.type  = "non defined error";
		}
	}
}
