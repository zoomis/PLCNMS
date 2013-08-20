<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"
	import="dpnm.netmsuite.plc.manager.frontend.*, dpnm.netmsuite.util.*, java.util.*"%>


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
            String type = request.getParameter("Type");
            int t = 1;
            if (type.equalsIgnoreCase("Pkts")) {
            	t = 1;
            } else if (type.equalsIgnoreCase("Bytes")) {
            	t = 2;
            } else if (type.equalsIgnoreCase("Speed")) {
            	t = 3;
            }
            String height = request.getParameter("Height");
            String width = request.getParameter("Width");
%>
<a href="map.jsp" target=_blank>
<jsp:forward page="../ServletForGraph">
	<jsp:param name="macAddress" value="<%=MacAddr%>"/>
	<jsp:param name="graphType" value="<%=t %>"/>
	<jsp:param name="height" value="<%=height %>"/>
	<jsp:param name="width" value="<%=width %>"/>
</jsp:forward>
</a>
</body>
</html>
