<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"
	import="dpnm.netmsuite.plc.manager.frontend.*, dpnm.netmsuite.util.*, java.util.*"%>
	
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>testing...</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<% 
IFrontEndManager frontEndManager = FrontEndManager.getInstance();
frontEndManager.frontEndManagerstart();

String macAddr = request.getParameter("MacAddr"); //MacAddr 값 넘겨 받는부분.
int nodetype = frontEndManager.getNodeType(macAddr);

	if (nodetype == 0) //eu
		{
		//	pageContext.forward("login.jsp");
		//	session.setAttribute("login", login);
			response.sendRedirect("eu_info.jsp?IPAddr=" + macAddr);
		}
	else if (nodetype == 1) //master
		{
		response.sendRedirect("master_info.jsp?MacAddr=" + macAddr);	
		}
	else if (nodetype == 2) //slave
		{
		response.sendRedirect("slave_info.jsp?MacAddr=" + macAddr);	
		}
	else if (nodetype == 3) //repeater
		{
			response.sendRedirect("repeater_info.jsp?MacAddr=" + macAddr);	
		}	
%>


NetMask : 
<%
String data = frontEndManager.getData(macAddr, "NETMASK");
%>

Mac Address : 
<%out.println( request.getParameter("MacAddr") ); %>
<br>
nodetype :
<%=data %>
<%=nodetype %>
</body>
</html>