<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"
	import="dpnm.netmsuite.plc.manager.frontend.*, dpnm.netmsuite.util.*, java.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>forwading</title>
<%
	String str1 = request.getParameter("id");
	String str2 = request.getParameter("password");
	
	out.println("ID:" + str1 + "    PASSWD:" + str2 + "<br>");
%>
</head>

<body>
<% 
IFrontEndManager frontEndManager = FrontEndManager.getInstance();
frontEndManager.frontEndManagerstart();

boolean user = frontEndManager.isValidateUser(str1, str2);
%>
<%
if (user == true)
{
	String login = "success";
	//out.println("true---"+ user+"<br>");
	//pageContext.forward("login.jsp");
	session.setAttribute("login", login);
	session.setAttribute("id", str1);
	response.sendRedirect("index.jsp"); // nms 첫 화면..
}
else 
{
	String login = "fail";
	//out.println("false---"+ user+"<br>");
	session.setAttribute("login", login);
	response.sendRedirect("index.htm"); // login page..
}

%>

</body>
</html>