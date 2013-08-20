<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"
	import="dpnm.netmsuite.plc.manager.frontend.*, dpnm.netmsuite.util.*, java.util.*"%>
	
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>testing...</title>
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
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body background="images/bodybg.gif" bgcolor="white" text="black" link="blue" vlink="purple" alink="red">
<% 
IFrontEndManager frontEndManager = FrontEndManager.getInstance();
frontEndManager.frontEndManagerstart();

String macAddr = request.getParameter("MacAddr"); //MacAddr 값 넘겨 받는부분.
%>

<!-- test : Mac addr print 
<%out.println( request.getParameter("MacAddr") ); %>
-->

<table border='0' width='810'>
	<tr>
	<td bgcolor="#FCEDD4" width=450>
	<b>DEVICE</b> [ <%=macAddr %> ]
	</td>
	</tr>
</table>
<table border="0" cellpadding="8" cellspacing="2" width="810">
	<tr>
	<td width=270 align=center>
	<a class=link href='information.jsp?MacAddr=<%=macAddr%>' target='content'>Information</a> 
	</td>
	<td width=270 align=center>
	<a class=link href='setting.jsp?MacAddr=<%=macAddr%>' target='content'>Settings</a>
	</td>
	<td width=270 align=center><a class=link href='statistics.jsp?MacAddr=<%=macAddr%>' target='content' target='content'>Statistics</a>
	</td>
	</tr>
</table>
</body>

</html>
