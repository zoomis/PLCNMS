<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"
	import="dpnm.netmsuite.plc.manager.frontend.*,dpnm.netmsuite.util.*,java.util.*"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<meta http-equiv="refresh" content='60'>
	<title>Statistic list</title>
	<link href="css/style.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
A:link    {text-decoration: none; color:blue;}
A:active  {text-decoration: none; color:blue;}
A:visited {text-decoration: none; color:blue;}
A:hover   {text-decoration: underline; color: red;}
-->
</style>

<body background="images/bodybg.gif">
<%!
public String time = "";
public String type = "";
%>
<%
%>			
<!-- <table width="400" border="1" align="left" cellpadding="0" cellspacing="0" bordercolordark="white" bordercolorlight="black" >  -->
<table border='0' cellspacing="0"><tr><td><!--//큰 테이블 -->
<table width="800" border="1" align="left" cellpadding="2" cellspacing="0" bordercolordark="white" bordercolorlight="black" class="homeTableDarkBorderFault" > 
         <tr class="homeHeader">
         <td class="boldWhiteText" align='center'>Index</td>
         <td class="boldWhiteText" align='center'>MACADDR</td>
         <td class="boldWhiteText" align='center'>TIME</td>
         <td class="boldWhiteText" align='center'>TYPE</td>
	</tr>
	
	<%IFrontEndManager frontEndManager = FrontEndManager.getInstance();
		frontEndManager.frontEndManagerstart();
		String MacAddr = request.getParameter("MacAddr"); //MacAddr 값 넘겨 받는부분.
			int nodetype = frontEndManager.getNodeType(MacAddr); //nodeType 넘겨 받는 부분.

			//Vector faultList = frontEndManager.getFaultList("111111");  // test
			Vector faultList = frontEndManager.getFaultList(MacAddr);
			
			if (faultList.size() == 0) {
				out.println("<tr valign='bottom' class='reportsEvenRow'><td colspan='4' align='center'>no error.</td></tr>");
				out.println("<tr valign='bottom' class='tableOddRow'><td colspan='4'>&nbsp</td></tr>");
			} else {

				for (int i = 0; i < faultList.size(); i++) {
					FaultInfo oneFault = (FaultInfo) faultList.elementAt(i);

					String macAddr = oneFault.macAddr;
					time = oneFault.time;
					type = oneFault.type;

					if (i % 2 == 0) {
						out.println("<tr valign='bottom' class='reportsEvenRow'><td>index</td><td>"
										+ macAddr
										+ "</td><td>"
										+ time
										+ "</td><td>" + type + "</td></tr>");
					} else if (i % 2 == 1) {
						out.println("<tr valign='bottom' class='tableOddRow'><td>index</td><td>"
										+ macAddr
										+ "</td><td>"
										+ time
										+ "</td><td>" + type + "</td></tr>");
					}
					//out.println(oneFault.macAddr + " " + oneFault.time + " " + oneFault.type + "<br>");
				}//for

			}//else

			%>
		
</table>

<br>
<%//ems는 그래프가 없어요..
			if (nodetype == 0) {
				//out.println("그래프 없음..");
			} else if (nodetype == 1) {
//	master modem에는 다음과 같은 그래프가 그려져야 함
//	1. Pkts graph
//	2. Bytes graph
//	3. 각 slave별로 speed 그래프
			%>
	</td></tr><tr><td>
	<table border=0 width='350'>
		<tr>
			<td><iframe src='graph.jsp?MacAddr=<%=MacAddr%>&Type=Pkts&Height=250&Width=350'
				frameborder='1' bordercolor='' marginwidth='' marginheight='' width='400' height='350' aligh=''></iframe></td>
			<td><iframe src='graph.jsp?MacAddr=<%=MacAddr%>&Type=Bytes&Height=250&Width=350' frameborder='1' bordercolor='' marginwidth='' marginheight='' width='400' height='350' aligh=''></iframe>
			</td>
		</tr>
	</table>
	<table border=0 width='350'>
	<tr>
<%
PLCTreeNode rootTreeNode = frontEndManager.getTreeMap();
Vector nodes = rootTreeNode.getChilds();
PLCTreeNode masterNode = (PLCTreeNode)nodes.elementAt(0);
nodes = masterNode.getChilds();
for (int i = 0; i < nodes.size(); i++) {
	PLCTreeNode slaveNode = (PLCTreeNode)nodes.elementAt(i);
%>
			<td>SLAVE - <%=i+1 %><iframe src='graph.jsp?MacAddr=<%=slaveNode.getMacAddr()%>&Type=Speed&Height=250&Width=350'
				frameborder='1' bordercolor='' marginwidth='' marginheight='' width='400' height='350' aligh=''></iframe></td>
<%
	if (i % 2 == 1) { out.println("</tr><tr>");}		
}
%>
</tr>
</table>
	<!--<jsp:include page="graph_view.jsp"></jsp:include>-->
			
			<%
			}
			else {%>
	</td></tr><tr><td>
	<table border=0 width='350'>
		<tr>
			<td><iframe src='graph.jsp?MacAddr=<%=MacAddr%>&Type=Pkts&Height=250&Width=790'
				frameborder='1' bordercolor='' marginwidth='' marginheight='' width='800' height='350' aligh='' scrolling=no></iframe></td>
			</tr>
			<tr>
			<td><iframe src='graph.jsp?MacAddr=<%=MacAddr%>&Type=Speed&Height=250&Width=790' frameborder='1' bordercolor='' marginwidth='' marginheight='' width='800' height='350' aligh='' scrolling=no></iframe>
			</td>
		</tr>
	</table>
	<!--<jsp:include page="graph_view.jsp"></jsp:include>-->
	<%}%>
</td></tr></table><!--//큰 테이블 end-->
</body>
</html>
