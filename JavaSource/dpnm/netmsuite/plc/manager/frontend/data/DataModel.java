package dpnm.netmsuite.plc.manager.frontend.data;

import java.util.*;
import dpnm.netmsuite.util.*;

public class DataModel implements PLCInfoDefinition {
	
	Vector plcNodes = new Vector();
	
//	public void removePLCNode();
	
	public void addPLCNode(PLCNodeDataModel plcNode){
		plcNodes.add(plcNode);
	}
	
	public void removeAllPLCData(){
		plcNodes.removeAllElements();
	}
	
	/**
	 * @TODO implementation
	 * @return
	 */
	public synchronized PLCTreeNode getTreeMap(){
		
		PLCTreeNode.initCounter();
//		return buildDummyTreeNode();
		
		if(plcNodes == null || plcNodes.size() == 0){
			return new PLCTreeNode(NODETYPE_EMPTY, "--:--:--:--:--");
		}
		
		Vector tempVector = new Vector();
		
		PLCNodeDataModel nodeDataModel = findEUNode();
		if( nodeDataModel == null){
			return new PLCTreeNode(NODETYPE_EMPTY, "--:--:--:--:--");
		}
		
		PLCTreeNode rootNode = new PLCTreeNode(NODETYPE_EU, nodeDataModel._IPAddress);
		
		tempVector.addElement(rootNode);
		
		/**
		 @TODO must check unlimited loops
		 *	step 1 : 각각의 PLC Node들을 검사하여 현재 해당 차례에 있는 PLCTreeNode를 부모로 갖는 PLCNode를 Vector에 넣는다.
		 *  	step 1.1 : PLCTreeNode의 MACAddress를 각각의 PLCNodes의 자식 원소와 비교해 본다.
		 *  step 2 : 모든 PLCNode들을 검색한 후 다음 Vector에 있는 PLCTreeNode를 부모로 갖는 PLCNode를 또 Vector에 넣는다. 이를 반복한다.
		 */
		int back = 0; //because of EU
		for(int i=0; tempVector.size() < plcNodes.size(); i++){
			
			/** 
			 * 문제가 있다거나 DB 데이터의 오류로 아직 추가 되지 않았으나 추가될 가능성이 없는 Node가 존재.
			 * 예) 특정 SlaveModem의 ParentMACAddress를 지니고 있는 Repeater나 MasterModem이 없는 경우!
			 */
			if( back == tempVector.size()){
				break;
			}
			else{
				back = tempVector.size();
			}
			
			PLCTreeNode oneTreeNode = (PLCTreeNode)tempVector.elementAt(i);
			
			for(int j=0; j< plcNodes.size(); j++){
				PLCNodeDataModel oneNodeDataModel = (PLCNodeDataModel)plcNodes.elementAt(j);
				
				//check if null (may EU or error DATA)
				if(oneNodeDataModel._ParentMACAddress == null){
					continue;
				}
				
				String oneTreeNode_MacAddr = oneTreeNode.getMacAddr().trim();
				String oneNodeDataModel_ParentMac = oneNodeDataModel._ParentMACAddress.trim();
				
				if(oneTreeNode_MacAddr.equals(oneNodeDataModel_ParentMac)){
					
					PLCTreeNode oneChildTreeNode = new PLCTreeNode(oneNodeDataModel._NodeType, oneNodeDataModel._MACAddressName.trim());
					oneTreeNode.addChild(oneChildTreeNode);
					tempVector.add(oneChildTreeNode);
				}
			}
		}
		
		return rootNode;
	}
	
	public PLCTreeNode buildDummyTreeNode(){
		PLCTreeNode eu = new PLCTreeNode(NODETYPE_EU, "00:00:00:00:00");
		
		PLCTreeNode oneNode = new PLCTreeNode(NODETYPE_MASTER_MODEM,"00:00:00:00:01");
		eu.addChild(oneNode);
		
		PLCTreeNode otherNode = new PLCTreeNode(NODETYPE_REPATER,"00:00:00:00:03");
		oneNode.addChild(otherNode);
		
		PLCTreeNode anotherNode = new PLCTreeNode(NODETYPE_SLAVE_MODEM,"00:00:00:00:13");
		otherNode.addChild(anotherNode);
		
		anotherNode = new PLCTreeNode(NODETYPE_SLAVE_MODEM,"00:30:00:00:03");
		oneNode.addChild(anotherNode);
		
		return eu;
	}
	
	
	public PLCNodeDataModel findEUNode(){
		for(int i=0; i< plcNodes.size(); i++){
			PLCNodeDataModel dataModel = (PLCNodeDataModel) plcNodes.elementAt(i);
			if(dataModel._NodeType == NODETYPE_EU){
				return dataModel;
			}
		}
		
		return null;
	}
	
	public String getData(String id, String field){
		String value = null;
		for(int i=0; i<plcNodes.size(); i++){
			PLCNodeDataModel oneNodeDataModel = (PLCNodeDataModel)plcNodes.elementAt(i);
			if(oneNodeDataModel._NodeType == PLCInfoDefinition.NODETYPE_EU){
				if(oneNodeDataModel._IPAddress.equals(id)){
					return oneNodeDataModel.getData(field);
				}
			}
			else{
				if(oneNodeDataModel._MACAddressName.equals(id)){
					return oneNodeDataModel.getData(field);
				}
			}
		}
		
		return "0";
	}
	
	/**
	 * 
	 * @param macAddr
	 * @return if there is no macAddr thie method returns -1 otherwise it returns 
	 * appropriate nodeType
	 */
	public int getNodeType(String macAddr){
		
		for(int i=0; i<plcNodes.size(); i++){
			PLCNodeDataModel oneNodeDataModel = (PLCNodeDataModel)plcNodes.elementAt(i);
			
			if( oneNodeDataModel._NodeType == PLCInfoDefinition.NODETYPE_EU){
				String oneMacAddr  = oneNodeDataModel._IPAddress.trim();
				if(oneMacAddr.equals(macAddr)){
					return oneNodeDataModel._NodeType;
				}
			}
			else{
				String oneMacAddr  = oneNodeDataModel._MACAddressName.trim();
				if(oneMacAddr.equals(macAddr)){
					return oneNodeDataModel._NodeType;
				}
			}

		}
		
		return PLCInfoDefinition.NODETYPE_EMPTY;
	}
//	public Vector copyPLCNodes(){
//		Vector newVector = new Vector();
//		
//		for(int i=0; i< plcNodes.size(); i++){
//			PLCNodeDataModel nodeDataModel = (PLCNodeDataModel)((PLCNodeDataModel)plcNodes.elementAt(i)).clone();
//			newVector.add(nodeDataModel);
//		}
//		
//		return newVector;
//	}
}
