<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"
	import="dpnm.netmsuite.plc.manager.frontend.*, dpnm.netmsuite.util.*, java.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>master, repeater, slave ���� �� ������������..</title>
</head>

<body>
<%
IFrontEndManager frontEndManager = FrontEndManager.getInstance();
frontEndManager.frontEndManagerstart();
%>
<%String nodetype = request.getParameter("nodetype");%>
<b><%=nodetype%> ���� �Ϸ�</b>

<p>
<%String macAddr = request.getParameter("MacAddr");%>
<%
int status = Integer.parseInt(request.getParameter("status")); //string --> int
int rtsctsstatus = Integer.parseInt(request.getParameter("rtsctsstatus"));
%>

status : <%frontEndManager.setSystemStatus(macAddr, status); %>
rtsctsstatus : <%frontEndManager.setRTSCTSStatus(macAddr, rtsctsstatus); %>


<!-- ���� ������ Ȯ�� �� �ʿ�� ����? ������ ������? .. return ����? -->
<!-- ���� ������ �����ִ� ������.. -->
<%
	response.sendRedirect("setting.jsp?MacAddr=" + macAddr);
%>
<br>status<%=macAddr%>
<!-- test ��~~
<br>status<%=status%>
<br>rtsctsstatus<%=rtsctsstatus%>
-->
</body>
</html>