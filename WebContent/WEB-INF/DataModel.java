package dpnm.netmsuite.plc.manager.frontend.data;

import java.util.*;
import dpnm.netmsuite.util.*;

public class DataModel implements PLCInfoDefinition {
	
	Vector plcNodes;
	
//	public void removePLCNode();
	
	public void addPLCNode(PLCNodeDataModel plcNode){
		plcNodes.add(plcNode);
	}
	
	/**
	 * @TODO implementation
	 * @return
	 */
	public PLCTreeNode getTreeMap(){
		
		return buildDummyTreeNode();
		
//		if(plcNodes == null || plcNodes.size() == 0){
//			return new PLCTreeNode(NODETYPE_EMPTY, "--:--:--:--:--");
//		}
//		
//		Vector tempVector = new Vector();
//		
//		PLCNodeDataModel nodeDataModel = findEUNode();
//		if( nodeDataModel == null){
//			return new PLCTreeNode(NODETYPE_EMPTY, "--:--:--:--:--");
//		}
//		
//		PLCTreeNode rootNode = new PLCTreeNode(NODETYPE_EU, nodeDataModel._MACAddressName);
//		
//		tempVector.addElement(rootNode);
//		
//		/**
//		 @TODO must check unlimited loops
//		 *	step 1 : 각각의 PLC Node들을 검사하여 현재 해당 차례에 있는 PLCTreeNode를 부모로 갖는 PLCNode를 Vector에 넣는다.
//		 *  	step 1.1 : PLCTreeNode의 MACAddress를 각각의 PLCNodes의 자식 원소와 비교해 본다.
//		 *  step 2 : 모든 PLCNode들을 검색한 후 다음 Vector에 있는 PLCTreeNode를 부모로 갖는 PLCNode를 또 Vector에 넣는다. 이를 반복한다.
//		 */
//		int back = 0; //because of EU
//		for(int i=0; tempVector.size() < plcNodes.size(); i++){
//			
//			/** 
//			 * 문제가 있다거나 DB 데이터의 오류로 아직 추가 되지 않았으나 추가될 가능성이 없는 Node가 존재.
//			 * 예) 특정 SlaveModem의 ParentMACAddress를 지니고 있는 Repeater나 MasterModem이 없는 경우!
//			 */
//			if( back == tempVector.size()){
//				break;
//			}
//			else{
//				back = tempVector.size();
//			}
//			
//			PLCTreeNode oneTreeNode = (PLCTreeNode)tempVector.elementAt(i);
//			
//			for(int j=0; j< plcNodes.size(); j++){
//				PLCNodeDataModel oneNodeDataModel = (PLCNodeDataModel)plcNodes.elementAt(j);
//				
//				//check if null (may EU or error DATA)
//				if(oneNodeDataModel._ParentMACAddress == null){
//					continue;
//				}
//				
//				if(oneTreeNode.getMacAddr().equals(oneNodeDataModel._ParentMACAddress)){
//					PLCTreeNode oneChildTreeNode = new PLCTreeNode(oneNodeDataModel._NodeType, oneNodeDataModel._MACAddressName);
//					oneTreeNode.addChild(oneChildTreeNode);
//					tempVector.add(oneChildTreeNode);
//				}
//			}
//		}
//		
//		return rootNode;
	}
	
	public PLCTreeNode buildDummyTreeNode(){
		PLCTreeNode eu = new PLCTreeNode(NODETYPE_EU, "00:00:00:00:00", 1);
		
		PLCTreeNode oneNode = new PLCTreeNode(NODETYPE_MASTER_MODEM,"00:00:00:00:01", 2);
		eu.addChild(oneNode);//-1
		
		PLCTreeNode twoNode = new PLCTreeNode(NODETYPE_SLAVE_MODEM,"00:00:00:00:02", 9);
		eu.addChild(twoNode);//-2
		
		PLCTreeNode otherNode_2 = new PLCTreeNode(NODETYPE_REPATER,"00:00:00:02:01", 10);
		twoNode.addChild(otherNode_2);//-2.1
		
		PLCTreeNode otherNode = new PLCTreeNode(NODETYPE_REPATER,"00:00:00:01:01", 3);
		oneNode.addChild(otherNode);//-1.1
		
		PLCTreeNode otherNode_1 = new PLCTreeNode(NODETYPE_REPATER,"00:00:00:01:02", 6);
		oneNode.addChild(otherNode_1);//-1.2
		
	
		PLCTreeNode anotherNode = new PLCTreeNode(NODETYPE_SLAVE_MODEM,"00:00:00:01:11", 4);
		otherNode.addChild(anotherNode);//-1.1.1
		PLCTreeNode anotherNode_3 = new PLCTreeNode(NODETYPE_SLAVE_MODEM,"00:00:00:01:12", 5);
		otherNode.addChild(anotherNode_3);//-1.1.2
		
		
		PLCTreeNode anotherNode_1 = new PLCTreeNode(NODETYPE_SLAVE_MODEM,"00:30:00:01:03", 7);
		oneNode.addChild(anotherNode_1);//-1.3
		
		PLCTreeNode anotherNode_2 = new PLCTreeNode(NODETYPE_SLAVE_MODEM,"00:30:00:01:04", 8);
		oneNode.addChild(anotherNode_2);//-1.4
		
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

	
//	public String treeDraw(){
//		for(int i)
//		
//	}
//	
	
	
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
