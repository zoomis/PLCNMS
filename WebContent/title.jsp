<html>

<head>
<title>title</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>

<body bgcolor="#FA8800" text="black" link="blue" vlink="purple" alink="red">
	<!-- session process.. -->
	<%
	String serverId="";
	serverId = (String)session.getAttribute("id");
	if (serverId == null)
		{
			//String login = "fail";
			//session.setAttribute("login", login);
			response.sendRedirect("index.htm");
		}
	%>
<table width="1010" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="300"><img src="images/logo_new_1.gif"></td>
    <td width="710" align="right" valign="bottom">
    	<div align=right id=divTime></div>
	<script language="JavaScript">
	function getNum(num) {
		if (num < 10) {	num = '0'+num;}	return num;
	}
	
	function getFullToday(){
	var yIdx='SunMonTueWedThuFriSat';
	var today = new Date();
	var buf = "";

	yy=today.getYear();
	mm=getNum(today.getMonth() + 1);
	dd=getNum(today.getDate());
	h=today.getHours(); if(h>12){h-=12;ap='PM';}else{ap='AM';}
	h = getNum(h);
	m=getNum(today.getMinutes());
	s=getNum(today.getSeconds());

	y = today.getDay()*3;
	yo=yIdx.charAt(y)+yIdx.charAt(y+1)+yIdx.charAt(y+2);

	buf='<b>'+yy+'/'+mm+'/'+dd+'</b> ['+yo+'] '+ap+' '+h+':'+m+':'+s;

	return buf;
	}

	function putsTime()
	{
	if ( typeof(document.all.divTime)=="object" ) {
	document.all.divTime.innerHTML = "" + getFullToday() + " ";
	}
	setTimeout("putsTime()", 1000);
	}

	putsTime();

	</script>
	<a href="map.jsp" target="main">Home</a> <strong>|</strong> 
	<a href="help.html" target="_blank">Help</a> 
<strong>|</strong> 
    <a href="logout.jsp" target="_top">Logout</a> 
    [<font color="navy"><%=serverId %></font> ] 
    </strong></div></td>
  </tr>
</table>

</body>

</html>
