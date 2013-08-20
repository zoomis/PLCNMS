<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"
	import="dpnm.netmsuite.plc.manager.frontend.*, dpnm.netmsuite.util.*, java.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>EMS 수정 화면</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>

<body background="images/bodybg.gif">
<%
IFrontEndManager frontEndManager = FrontEndManager.getInstance();
	frontEndManager.frontEndManagerstart();
	String ipAddr = request.getParameter("IPAddr");
%>

<p>

<form method='post' action='eu_setting_success.jsp'>
<table border='0'>
<tr><td>
<table width='305' border="0" align="left" cellpadding="2" cellspacing="0" class="homeTableDarkBorder_2" > 
	<tr class="homeHeader">
        <td colspan='2' class="boldWhiteText">EMS Configuration</td>
	</tr>
	<tr valign="bottom">
        <td class="tableOddRow">TIMEOUT</td>
		<td>
			<input type='text' name='timeout' value='<%=frontEndManager.getData(ipAddr, "TIMEOUT")%>'>
		</td>
	</tr>
	<tr valign="bottom">
		<td class="reportsEvenRow">SPEED THRESHOLD</td>
		<td>
			<input type='text' name='speedthreshold' value='<%=frontEndManager.getData(ipAddr, "SPEEDTHRESHOLD")%>'></td>
	</tr>
	<tr valign="bottom">
		<td class="tableOddRow">ERROR THRESHOLD</td>
		<td>
			<input type='text' name='errorthreshold' value='<%=frontEndManager.getData(ipAddr, "ERRORTHRESHOLD")%>'></td>
	</tr>
	<tr>
		<td class="reportsEvenRow">MEMORY THRESHOLD</td>
		<td><input type='text' name='memthreshold' value='<%=frontEndManager.getData(ipAddr, "MEMTHRESHOLD")%>'></td>
	</tr>
	<tr>
		<td class="tableOddRow">POLLING INTREVAL</td>
		<td><input type='text' name='polling' value='300'></td>
	</tr>
	<tr>
		<td class="reportsEvenRow">WRITE GROUP</td>
		<td><input type='text' name='writegroup' value='<%=frontEndManager.getData(ipAddr, "WRITEGROUP")%>'></td>
	</tr>
	<tr>
		<td class="tableOddRow">READ GROUP</td>
		<td><input type='text' name='readgroup' value='<%=frontEndManager.getData(ipAddr, "READGROUP")%>'></td>
	</tr>
	<input type='hidden' name='ipAddr' value=<%=ipAddr%>>
	</table>

</tr>
<tr>
	<td align='center'	>
		<table>
			<tr>
				<td colspan='2'>
					<p align='center'>
					<input type='submit' value="Modify">&nbsp;&nbsp;&nbsp;
					<input type='reset' value="Reset">	
				</td>
			</tr>
		</table>
	</td>
</tr>
</table> 
</form>
</body>
</html>