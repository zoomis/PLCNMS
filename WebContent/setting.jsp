<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"
	import="dpnm.netmsuite.plc.manager.frontend.*, dpnm.netmsuite.util.*, java.util.*"%>
	
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>setting...</title>
</head>
<body background="images/bodybg.gif">
<% 
IFrontEndManager frontEndManager = FrontEndManager.getInstance();
frontEndManager.frontEndManagerstart();

String macAddr = request.getParameter("MacAddr"); //MacAddr 값 넘겨 받는부분.
int nodetype = frontEndManager.getNodeType(macAddr);

	if (nodetype == 0) //eu
		{
		//	pageContext.forward("login.jsp");
		//	session.setAttribute("login", login);
			response.sendRedirect("eu_setting.jsp?IPAddr=" + macAddr);
		}
	else if (nodetype == 1) //master
		{
		response.sendRedirect("nomal_setting.jsp?MacAddr=" + macAddr + "&nodetype=Master");	
		}
	else if (nodetype == 2) //slave
		{
		response.sendRedirect("nomal_setting.jsp?MacAddr=" + macAddr + "&nodetype=Slave");	
		}
	else if (nodetype == 3) //repeater
		{
			response.sendRedirect("nomal_setting.jsp?MacAddr=" + macAddr + "&nodetype=Repeater");	
		}	
%>


Mac Address : 
<%out.println( request.getParameter("MacAddr") ); %>
<br>
nodetype :
<%=nodetype %>
</body>
</html>