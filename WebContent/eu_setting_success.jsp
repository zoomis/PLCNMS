<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"
	import="dpnm.netmsuite.plc.manager.frontend.*, dpnm.netmsuite.util.*, java.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>EMS ���� �� ������������..</title>
</head>

<body>
<%
IFrontEndManager frontEndManager = FrontEndManager.getInstance();
frontEndManager.frontEndManagerstart();
%>
<b>EMS ���� �Ϸ�</b>
<p>
<%String ipAddr = request.getParameter("ipAddr");%>
<%
int timeout = Integer.parseInt(request.getParameter("timeout")); //string --> int
int speedthreshold = Integer.parseInt(request.getParameter("speedthreshold"));
int errorthreshold = Integer.parseInt(request.getParameter("errorthreshold"));
int memthreshold = Integer.parseInt(request.getParameter("memthreshold"));
int polling = Integer.parseInt(request.getParameter("polling"));
String writegroup = request.getParameter("writegroup");
String readgroup = request.getParameter("readgroup");
%>

timeout : <%frontEndManager.setTimeOut(timeout); %>
speedthreshold : <%frontEndManager.setSpeedThreshold(speedthreshold); %>
errorthreshold : <%frontEndManager.setErrorThreshold(errorthreshold); %>
memthreshold : <%frontEndManager.setMemThreshold(memthreshold); %>
polling : <%frontEndManager.setPollingInterval(polling); %>
writegroup : <%frontEndManager.setWriteGroup(writegroup); %>
readgroup : <%frontEndManager.setReadGroup(readgroup); %>

<!-- ���� ������ Ȯ�� �� �ʿ�� ����? ������ ������? .. return ����? -->
<!-- ���� ������ �����ִ� ������.. -->
<%
	response.sendRedirect("setting.jsp?MacAddr=" + ipAddr);
%>
<!-- test ��~~
<br>ipAddr<%=ipAddr%>
<br>timeout<%=timeout%>
<br>speedthreshold <%=speedthreshold%>
<br>errorthreshold <%=errorthreshold%>
<br>memthreshold <%=memthreshold%>
<br>polling <%=polling%>
-->
</body>
</html>