<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"
	import="dpnm.netmsuite.plc.manager.frontend.*, dpnm.netmsuite.util.*, java.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>MASTER Information</title>
	<link href="css/style.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
A:link    {text-decoration: none; color:blue;}
A:active  {text-decoration: none; color:blue;}
A:visited {text-decoration: none; color:blue;}
A:hover   {text-decoration: underline; color: red;}
-->
</style>
</head>

<body background="images/bodybg.gif">
<%
IFrontEndManager frontEndManager = FrontEndManager.getInstance();
	frontEndManager.frontEndManagerstart();
	String macAddr = request.getParameter("MacAddr");

	int status = Integer.parseInt(frontEndManager.getData(macAddr, "STATUS"));
	int rtsctsstatus = Integer.parseInt(frontEndManager.getData(macAddr, "RTSCTSSTATUS"));

	String check_0 ="";
	String check_1 ="";
	String check_2 ="";
	String check_3 ="";

	if (status == 0) 				//active
	{	check_0 = "checked";	}
	else if (status == 1) 			//Suspend
	{	check_1 = "checked";	}
	else if (status == 2) 			//Factory Mode
	{	check_2 = "checked";	}
	else if (status == 3)			//Reset
	{	check_3 = "checked";	}
	else
	{ 	out.println("status value : error");	}
%>
<%
	String check_4 ="";
	String check_5 ="";


	if (rtsctsstatus == 0) 				//Enable
	{	check_4 = "checked";	}
	else if (rtsctsstatus == 1) 			//Disable
	{	check_5 = "checked";	}

	else
	{ 	out.println("rts/cst status value : error");	}
%>


<b>Master Modem Information</b>
<br>

<table border='0' cellspacing='0' width='810' height='340'>
<tr><td>The PLC Master Unit that controls all Slave and Repeater Units in a logical network. 
It is the access point to the backbone network.</td></tr>
<tr>
<td align='center' valign='bottom'>

<table border='1'>

<tr>
<td>
	<table width='100%' border="0" align="left" cellpadding="2" cellspacing="0" class="homeTableDarkBorder" > 
    	<tr class="homeHeader">
         	<td colspan='2' class="boldWhiteText">Station ID</TD>
		</tr>
    	<tr valign="bottom" class="reportsEvenRow">
			<td>Default SID</td>
			<td><%=frontEndManager.getData(macAddr, "DEFAULTSID")%></td>
		</tr>
		<tr valign="bottom" class="tableOddRow">
			<td>Current SID</td>
			<td><%=macAddr %></td>
		</tr>
	</table>
</td>

<td>
	<table width='100%' border="0" align="left" cellpadding="2" cellspacing="0" class="homeTableDarkBorder" > 
    	<tr class="homeHeader">
         	<td colspan='2' class="boldWhiteText">Group ID</TD>
		</tr>
    	<tr valign="bottom" class="reportsEvenRow">
			<td>Default GID</td>
			<td><%=frontEndManager.getData(macAddr, "DEFAULTGID")%></td>
		</tr>
		<tr valign="bottom" class="tableOddRow">
			<td>Current GID</td>
			<td><%=frontEndManager.getData(macAddr, "CURRENTGID")%></td>
		</tr>
	</table>
</td>

<td>

	<table width='100%' border="0" align="left" cellpadding="2" cellspacing="0" class="homeTableDarkBorder" > 
    	<tr class="homeHeader">
       		<td colspan='2' class="boldWhiteText">??</td>
		</tr>
		<tr valign="bottom" class="reportsEvenRow">
			<td>EMSIP</td>
			<td><%=frontEndManager.getData(macAddr, "EMSIP")%></td>
		</tr>
		<tr valign="bottom" class="tableOddRow">
			<td>RTSCTSSTATUS</td>
			<td>
				<input type='radio' name='rtsctsstatus' value='0' <%=check_4%> disabled>Enable  &nbsp;&nbsp;
				<input type='radio' name='rtsctsstatus' value='1'<%=check_5%> disabled>Disable 
			</td>
		</tr>
		</table>
</td>
</tr>

<tr>
<td width='265' align='center' valign='top' rowspan='2'>
<table width='100%' border="0" align="left" cellpadding="2" cellspacing="0" class="homeTableDarkBorder" > 
    <tr class="homeHeader">
         <td colspan='2' class="boldWhiteText">UP Stream</TD>
	</tr>
    <tr valign="bottom" class="reportsEvenRow">
		<td>No. of ACK</td>
		<td><%=frontEndManager.getData(macAddr, "OUTACKS")%></td>
	</tr>
    <tr valign="bottom" class="tableOddRow">
		<td>No. of Fail</td>
		<td><%=frontEndManager.getData(macAddr, "OUTFAILS")%></td>
	</tr>
	<tr valign="bottom" class="reportsEvenRow">
		<td>No. of Bytes</td>
		<td><%=frontEndManager.getData(macAddr, "OUTBYTES")%></td>
	</tr>
    <tr valign="bottom" class="tableOddRow">
		<td>No. of Packtes</td>
		<td><%=frontEndManager.getData(macAddr, "OUTPKTS")%></td>
	</tr>
	<tr valign="bottom" class="reportsEvenRow">
		<td>Registration Type</td>
		<td><%=frontEndManager.getData(macAddr, "REGTYPE")%></td>
	</tr>
</table>	
</td>

<td width='265' align='center' valign='top' rowspan='2'>
<table width='100%' border="0" align="left" cellpadding="2" cellspacing="0" class="homeTableDarkBorder" > 
    <tr class="homeHeader">
         <td colspan='2' class="boldWhiteText">Down Stream</TD>
	</tr>
	<tr valign="bottom" class="reportsEvenRow">
		<td>No. of ACK</td>
		<td><%=frontEndManager.getData(macAddr, "INACKS")%></td>
	</tr>
	<tr valign="bottom" class="tableOddRow">
		<td>No. of Fail</td>
		<td><%=frontEndManager.getData(macAddr, "INFAILS")%></td>
	</tr>
	<tr valign="bottom" class="reportsEvenRow">
		<td>No. of Bytes</td>
		<td><%=frontEndManager.getData(macAddr, "INBYTES")%></td> 
	</tr>
    <tr valign="bottom" class="tableOddRow">
		<td>No. of Packtes</td>
		<td><%=frontEndManager.getData(macAddr, "INPKTS")%></td>
	</tr>
</table>
</td>


<td width='265' align='center' valign='top'>
<table width='100%' border="0" align="left" cellpadding="2" cellspacing="0" class="homeTableDarkBorder" > 
    <tr class="homeHeader">
         <td colspan='2' class="boldWhiteText">Memory Information</TD>
	</tr>
    <tr valign="bottom" class="tableOddRow">
		<td>CFCS Packets</td>
		<td><%=frontEndManager.getData(macAddr, "CFCS")%></td>
	</tr>
	<tr valign="bottom" class="reportsEvenRow">
		<td>DFCS Packets</td>
		<td><%=frontEndManager.getData(macAddr, "DFCS")%></td>
	</tr>
	<tr valign="bottom" class="reportsEvenRow">
		<td>No. of Links</td>
		<td><%=frontEndManager.getData(macAddr, "LINKEDNODENUM")%></td>
	</tr>
</table>
</td>
</tr>

<tr>
<td>
<table width='100%' border="0" align="left" cellpadding="2" cellspacing="0" class="homeTableDarkBorder" > 
    <tr class="homeHeader">
         <td colspan='2' class="boldWhiteText">Master Status</TD>
	</tr>
    <tr valign="middle">
		<td class="tableOddRow" align="center">
		<img src="images/status_<%=status %>.png" border="0" width=50>
		</td>
		
		<td class="reportsEvenRow">
			<input type='radio' name='status' value='0'<%=check_0%> disabled> Active <br>
			<input type='radio' name='status' value='1'<%=check_1%> disabled> Suspend <br>
			<input type='radio' name='status' value='2'<%=check_2%> disabled> Factory Mode <br>
			<input type='radio' name='status' value='3'<%=check_3%> disabled> Reset 
		</td>
</tr>
		
		
	</tr>
</table>

</td>
</tr>
</table>


</td></tr></table>


</body>
</html>
