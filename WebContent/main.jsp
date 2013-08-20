<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"
	import="dpnm.netmsuite.plc.manager.frontend.*, dpnm.netmsuite.util.*, java.util.*"%>
	
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>testing...</title>
</head>

<% 
IFrontEndManager frontEndManager = FrontEndManager.getInstance();
frontEndManager.frontEndManagerstart();

String macAddr = request.getParameter("MacAddr"); //MacAddr 값 넘겨 받는부분.

if (macAddr.equalsIgnoreCase("HOME")) {
	response.sendRedirect("map.jsp");
}
%>

<frameset rows="60, *" border=0>
	<frame src='menu.jsp?MacAddr=<%=macAddr%>' name='menu' noresize scrolling="no" marginwidth="0" marginheight="0">
	<%
	String type = request.getParameter("Type");
	if (type != null && type.equalsIgnoreCase("statistics")) {
	%>
		<frame src='statistics.jsp?MacAddr=<%=macAddr%>' name='content'>
	<%
	} else {
	%>
		<frame src='information.jsp?MacAddr=<%=macAddr%>' name='content'>
	<%
	}%>
</frameset>

<!-- Mac Address :
<%//out.println( request.getParameter("MacAddr") ); %>
  -->
</html>