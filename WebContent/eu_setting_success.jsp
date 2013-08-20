<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"
	import="dpnm.netmsuite.plc.manager.frontend.*, dpnm.netmsuite.util.*, java.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>EMS 수정 후 수정페이지로..</title>
</head>

<body>
<%
IFrontEndManager frontEndManager = FrontEndManager.getInstance();
frontEndManager.frontEndManagerstart();
%>
<b>EMS 수정 완료</b>
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

<!-- 변경 됐음을 확인 할 필요는 없나? 무조건 가나요? .. return 값은? -->
<!-- 세팅 정보를 보여주는 곳으로.. -->
<%
	response.sendRedirect("setting.jsp?MacAddr=" + ipAddr);
%>
<!-- test 용~~
<br>ipAddr<%=ipAddr%>
<br>timeout<%=timeout%>
<br>speedthreshold <%=speedthreshold%>
<br>errorthreshold <%=errorthreshold%>
<br>memthreshold <%=memthreshold%>
<br>polling <%=polling%>
-->
</body>
</html>