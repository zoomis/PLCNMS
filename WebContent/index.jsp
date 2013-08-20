<html>

<head>
<title>i-NetMSuite for PLC, POSTECH</title>
</head>
	<%
	String id=(String)session.getAttribute("id");
	%>
<frameset rows="75, *, 145" border=0>
    <frame src="title.jsp?id=<%=id %>"  name="title" noresize scrolling="no" marginwidth="0" marginheight="0">
    <frameset cols="200, *" border=0>
        <frame src="treemap.jsp"  name="treemap" noresize scrolling="no" marginwidth="0" marginheight="0">
<!--        <frame src="main.jsp" name="main" noresize scrolling="no" marginwidth="0" marginheight="0"> -->
        <frame src="map.jsp" name="main" noresize scrolling="no" marginwidth="0" marginheight="0">
    </frameset>
    <frame src="trap.jsp" noresize marginwidth="0" marginheight="0">
    <noframes>
    <body>

	<!-- session process.. -->
	<%
	String serverId="";
	serverId = (String)session.getAttribute("login");
	if (serverId == null)
		{
			//String login = "fail";
			//session.setAttribute("login", login);
			response.sendRedirect("index.htm");
		}
	%>
    </body>
    </noframes>
</frameset>

</html>
