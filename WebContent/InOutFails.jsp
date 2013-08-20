<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"
	import="dpnm.netmsuite.plc.manager.frontend.*,dpnm.netmsuite.util.*,java.util.*"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Just Test</title>

</head>
<body>
<%IFrontEndManager frontEndManager = FrontEndManager.getInstance();
			frontEndManager.frontEndManagerstart();
			String MacAddr = request.getParameter("MacAddr"); //MacAddr 값 넘겨 받는부분.
			//String MacAddr = "1111138";
%>
<jsp:forward page="../ServletForGraph">
	<jsp:param name="macAddress" value="<%=MacAddr%>"/>
	<jsp:param name="graphType" value="3"/>
	<jsp:param name="height" value="250"/>
	<jsp:param name="width" value="350"/>
</jsp:forward>

</body>
</html>
