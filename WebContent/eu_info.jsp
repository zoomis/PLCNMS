<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"
	import="dpnm.netmsuite.plc.manager.frontend.*, dpnm.netmsuite.util.*, java.util.*"%>
<%!
public String getRate(double value) {
    value = value * 100;
	StringBuffer sb = new StringBuffer();
    int v = (int)value;
    sb.append(v);
    sb.append(".");
    value = value - v;
    v = (int)((value+0.005) * 100);
    sb.append(v);
    return sb.toString();

}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>EMS Information</title>
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

td.bar { border:1px black solid};
-->
</style>
</head>

<body background="images/bodybg.gif">
<%
IFrontEndManager frontEndManager = FrontEndManager.getInstance();
frontEndManager.frontEndManagerstart();

%>
<b>EMS Information</b>
<br>
<%String ipAddr = request.getParameter("IPAddr");%>

<table border='0' cellspacing='0' width='810' height='340'>
<tr><td>The PLC EMS Unit for remote registration, firmware upgrade, management and monitoring of the PLC networks.</td></tr>
<tr>
<td align='center' valign='bottom'>

<table border='1'>
<td width='265' align='center' valign='top' rowspan="2">
<table width='100%' border="0" align="left" cellpadding="2" cellspacing="0" class="homeTableDarkBorder" > 
    <tr class="homeHeader">
         <td colspan='2' class="boldWhiteText">General Information</td>
	</tr>
    <tr valign="bottom" class="reportsEvenRow">
		<td>Agent IP</td>
		<td><%=ipAddr%></td>
	</tr>
    <tr valign="bottom" class="tableOddRow">
		<td>Firmware Version</td>
		<td><%=frontEndManager.getData(ipAddr, "FIRMWAREVER")%></td>
	</tr>
	<tr valign="bottom" class="reportsEvenRow">
		<td>MAC Address</td>
		<td>00 d0 cb da 52 35<!-- <%=frontEndManager.getData(ipAddr, "MACADDR")%>--></td>
	</tr>
	<tr valign="bottom" class="tableOddRow">
		<td>No. of Registered Stations</td>
		<td><%=frontEndManager.getData(ipAddr, "REGISTERNUM")%></td>
	</tr>
    <tr valign="bottom" class="reportsEvenRow">
		<td>No. of Unregistered Stations</td>
		<td><%=frontEndManager.getData(ipAddr, "UNREGISTERNUM")%></td>
	</tr>
	<tr valign="bottom" class="tableOddRow">
		<td>Polling Interval</td>
		<td><%=frontEndManager.getData(ipAddr, "POLLINGINTERVAL")%> Min</td>
	</tr>
</table>	
</td>

<td width='265' align='center' valign='top'>
<table width='100%' border="0" align="left" cellpadding="2" cellspacing="0" class="homeTableDarkBorder" > 
    <tr class="homeHeader">
         <td colspan='2' class="boldWhiteText">SNMP / Interface Information</TD>
	</tr>
    <tr valign="bottom" class="reportsEvenRow">
		<td>Network Mask</td>
		<td><%=frontEndManager.getData(ipAddr, "NETMASK")%></td>
	</tr>
    <tr valign="bottom" class="tableOddRow">
		<td>Default Gateway</td>
		<td><%=frontEndManager.getData(ipAddr, "GATEWAY")%></td>
	</tr>
    <tr valign="bottom" class="reportsEvenRow">
		<td>DNS Address</td>
		<td><%=frontEndManager.getData(ipAddr, "DNSADDR")%></td>
	</tr>

    <tr valign="bottom" class="tableOddRow">
		<td>TIMEOUT</td>
		<td><%=frontEndManager.getData(ipAddr, "TIMEOUT")%></td>
	</tr>
    <tr valign="bottom" class="reportsEvenRow">
		<td>WRITEGROUP</td>
		<td><%=frontEndManager.getData(ipAddr, "WRITEGROUP")%></td>
	</tr>
    <tr valign="bottom" class="tableOddRow">
		<td>READGROUP</td>
		<td><%=frontEndManager.getData(ipAddr, "READGROUP")%></td>
	</tr>
    <tr valign="bottom" class="reportsEvenRow">
		<td>INPKT</td>
		<td><%=frontEndManager.getData(ipAddr, "INPKT")%></td>
	</tr>
    <tr valign="bottom" class="tableOddRow">
		<td>OUTPKT</td>
		<td><%=frontEndManager.getData(ipAddr, "OUTPKT")%></td>
	</tr>
</table>
</td>


<td width='265' align='center' valign='top' rowspan="2">
<table width='100%' border="0" align="left" cellpadding="2" cellspacing="0" class="homeTableDarkBorder" > 
    <tr class="homeHeader">
         <td colspan='2' class="boldWhiteText">Memory Information</TD>
	</tr>
    <tr valign="bottom" class="tableOddRow">
		<td>MEMORYSIZE</td>
		<td><%=frontEndManager.getData(ipAddr, "MEMORYSIZE")%> KBytes</td>
	</tr>
	<tr valign="bottom" class="reportsEvenRow">
		<td>FREEMEMSIZE</td>
		<td><%=frontEndManager.getData(ipAddr, "FREEMEMSIZE")%> KBytes</td>
	</tr>
		<tr valign="bottom" class="reportsEvenRow">
		<td colspan=2>
		Total Memory:
		</td>

	</tr>
	<tr valign="bottom" class="reportsEvenRow">
		<td colspan=2>
		<table cellpadding=0 cellspacing=0 width=202>
		<td style="border:1px black solid" cellpadding=0 cellspacing=0 margin=0><%for (int i = 0; i < 100; i++) {
			%><img src="images/blue_2px.png"><%	}%></td>
		</table>
		</td>

			<%
			int memory = Integer.parseInt(frontEndManager.getData(ipAddr, "MEMORYSIZE"));
			int free = Integer.parseInt(frontEndManager.getData(ipAddr, "FREEMEMSIZE"));
			
			double freeRate = (double)free/memory;
			int freePx = (int)(freeRate*100 + 0.5);
			%>
	</tr>
		<tr valign="bottom" class="reportsEvenRow">
		<td colspan=2>
		<table cellpadding=0 cellspacing=0 width=202>
		<td style="border:1px black solid" cellpadding=0 cellspacing=0 margin=0><%for (int i = 0; i < freePx; i++) {
			%><img src="images/red_2px.png"><%	}%></td>
		</table>
		</td>
	</tr>
		<tr valign="bottom" class="reportsEvenRow">
		<td colspan=2>
			Free Memory : <%=getRate(freeRate)%> % Free
		</td>

	</tr>
</table>
</td>


</tr>
<tr>

<td width='265' align='center' valign='top'>
<table width='100%' border="0" align="left" cellpadding="2" cellspacing="0" class="homeTableDarkBorder" > 
    <tr class="homeHeader">
         <td colspan='2' class="boldWhiteText">Parameter Setting Information</TD>
	</tr>
    <tr valign="bottom" class="tableOddRow">
		<td>SPEEDTHRESHOLD</td>
		<td><%=frontEndManager.getData(ipAddr, "SPEEDTHRESHOLD")%></td>
	</tr>
	<tr valign="bottom" class="reportsEvenRow">
		<td>ERRORTHRESHOLD</td>
		<td><%=frontEndManager.getData(ipAddr, "ERRORTHRESHOLD")%></td>
	</tr>
    <tr valign="bottom" class="tableOddRow">
		<td>MEMTHRESHOLD</td>
		<td><%=frontEndManager.getData(ipAddr, "MEMTHRESHOLD")%></td>
	</tr>
</table>
</td>


</tr>
</table>


</td></tr></table>
</body>
</html>
