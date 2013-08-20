<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"
	import="dpnm.netmsuite.plc.manager.frontend.*, dpnm.netmsuite.util.*, java.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>master, repeater, slave 수정 후 수정페이지로..</title>
</head>

<body>
<%
IFrontEndManager frontEndManager = FrontEndManager.getInstance();
frontEndManager.frontEndManagerstart();
%>
<%String nodetype = request.getParameter("nodetype");%>
<b><%=nodetype%> 수정 완료</b>

<p>
<%String macAddr = request.getParameter("MacAddr");%>
<%
int status = Integer.parseInt(request.getParameter("status")); //string --> int
int rtsctsstatus = Integer.parseInt(request.getParameter("rtsctsstatus"));
%>

status : <%frontEndManager.setSystemStatus(macAddr, status); %>
rtsctsstatus : <%frontEndManager.setRTSCTSStatus(macAddr, rtsctsstatus); %>


<!-- 변경 됐음을 확인 할 필요는 없나? 무조건 가나요? .. return 값은? -->
<!-- 세팅 정보를 보여주는 곳으로.. -->
<%
	response.sendRedirect("setting.jsp?MacAddr=" + macAddr);
%>
<br>status<%=macAddr%>
<!-- test 용~~
<br>status<%=status%>
<br>rtsctsstatus<%=rtsctsstatus%>
-->
</body>
</html>