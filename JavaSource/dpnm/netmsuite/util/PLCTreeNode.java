package dpnm.netmsuite.util;

import java.util.*;

public class PLCTreeNode implements PLCInfoDefinition{
	
	private int nodeType;
	//EMS�� ��� ... IPAddress�� ����ȴ�.
	//Tree�� ��� �� ���̰� ���� ������ ���� �ʵ带 �����Ѵٰų� ���� ����!
	private String macAddr;
	private Vector childs = new Vector();
	private int nodeID;
	
	private static int nodeCounter = 1; //root��  0���̰�  ���� 1������ ���� ��������!!
	
	public static void initCounter(){
		nodeCounter = 1;
	}
	
	public PLCTreeNode(int nodeType, String macAddr){
		this.nodeType = nodeType;
		this.macAddr = macAddr;
		this.nodeID = nodeCounter++; 
	}
	
	public void addChild(PLCTreeNode childNode){
		if(childs == null){
			childs = new Vector();
		}
		childs.add(childNode);
	}
	
	public void removeChild(PLCTreeNode childNode){
		childs.remove(childNode);
	}

	public void setMacAddr(String macAddr) {
		this.macAddr = macAddr;
	}

	public String getMacAddr() {
		return macAddr;
	}

	public void setNodeType(int nodeType) {
		this.nodeType = nodeType;
	}

	public int getNodeType() {
		return nodeType;
	}

	public void setChilds(Vector childs) {
		this.childs = childs;
	}

	public Vector getChilds() {
		return childs;
	}
	public int getNodeNumber(){
		return nodeID;
	}
	
//	public void setNodeNumber(int nodeNumber){
//		this.nodeID = nodeNumber;
//	}

}
