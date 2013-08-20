<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"
	import="dpnm.netmsuite.plc.manager.frontend.*, dpnm.netmsuite.util.*, java.util.*"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<meta http-equiv="refresh" content='60'>
<title>Treemap</title>

<style>
	A:link    { color:#000000; text-decoration:none; }
	A:visited { color:#000000; text-decoration:none; }
	A:active  { color:#000000; text-decoration:none; }
	A:hover   { color:red;  TEXT-DECORATION: none; }
	body {
	font-size:10pt;
	font-family:Verdana;
} 
</style>

<script src="tree/tree.js"></script>
</head>
<body>
<br>
<TABLE id="eu" border=0 cellpadding=0 cellspacing=0 ondragstart="return false" onselectstart="return false" oncontextmenu="return false">
</TABLE>
<br>



<%!
public String msg = "";
public String treeMap(PLCTreeNode node) {
	Vector childsList = node.getChilds();
	
	if(childsList.size() > 0)
	{	
		for(int i=0; i<childsList.size(); i++)
		{
			PLCTreeNode oneTreeNode = (PLCTreeNode)childsList.elementAt(i);
			int nodeNum = oneTreeNode.getNodeNumber(); //tree 번호
			int parentNum = node.getNodeNumber();
			String MacAddr = oneTreeNode.getMacAddr();
			msg += "var "+ "node" + nodeNum +  " = new TreeNode(\"" + MacAddr + "\"); " 
			+ "node" + parentNum + ".add(node" + nodeNum + "); ";
			treeMap(oneTreeNode);
		}		
	}
	else
	{
	} 
return  "<script> var node1 = new TreeNode(\"" + node.getMacAddr()+ "\"); " + msg + "</script>";
			
}
%>


<% 
IFrontEndManager frontEndManager = FrontEndManager.getInstance();
frontEndManager.frontEndManagerstart();

PLCTreeNode rootTreeNode = frontEndManager.getTreeMap();
%>

<%
if (msg == "")
{ 
	out.print(treeMap(rootTreeNode)); 
}
else
{
	msg="";
	out.print(treeMap(rootTreeNode));
}

%>
<script>
	var tree = new Tree("eu","HOME");
	tree.iconDir="";
	tree.selectionMode = tree.DISCONTIGUOUS_TREE_SELECTION;
	tree.treeSelectionListener = function(node, event){
          this.selectNode(node, event);
         // document.all("msg").innerText = tree.selectedNode[0].get("link")+" selected";
      }
 
      
      tree.root.add(node1); //직접입력함..수정할것..
      tree.expandAll();
      tree.repaint();
   
 </script>  


</BR>
</br>
</body>
</html>
