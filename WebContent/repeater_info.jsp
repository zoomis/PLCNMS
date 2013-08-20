<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"
	import="dpnm.netmsuite.plc.manager.frontend.*, dpnm.netmsuite.util.*, java.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>EMS Information</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>



<body>
<%
IFrontEndManager frontEndManager = FrontEndManager.getInstance();
frontEndManager.frontEndManagerstart();
%>
<b>Repeater Information</b>
<p>
<%String macAddr = request.getParameter("MacAddr");%>

<table border='1'>
<table width="400" border="0" align="left" cellpadding="2" cellspacing="0" class="homeTableDarkBorder" > 
         <tr class="homeHeader">
         <TD class="boldWhiteText">Field</TD>
         <TD class="boldWhiteText">Value</TD>
	</tr>
    <TR valign="bottom" class="reportsEvenRow">
		<td>INDEXNUMBER</td>
		<td><%=frontEndManager.getData(macAddr, "INDEXNUMBER")%></td>
	</tr>
    <TR valign="bottom" class="tableOddRow">
		<td>MAC Address</td>
		<td><%=macAddr%></td>
	</tr>
    <TR valign="bottom" class="reportsEvenRow">
		<td>STATUS</td>
		<td><%=frontEndManager.getData(macAddr, "STATUS")%></td>
	</tr>
    <TR valign="bottom" class="tableOddRow">
		<td>INPKTS</td>
		<td><%=frontEndManager.getData(macAddr, "INPKTS")%></td>
	</tr>
    <TR valign="bottom" class="reportsEvenRow">
		<td>OUTPKTS</td>
		<td><%=frontEndManager.getData(macAddr, "OUTPKTS")%></td>
	</tr>
    <TR valign="bottom" class="tableOddRow">
		<td>INACKS</td>
		<td><%=frontEndManager.getData(macAddr, "INACKS")%></td>
	</tr>
    <TR valign="bottom" class="reportsEvenRow">
		<td>OUTACKS</td>
		<td><%=frontEndManager.getData(macAddr, "OUTACKS")%></td>
	</tr>
    <TR valign="bottom" class="tableOddRow">
		<td>INFAILS</td>
		<td><%=frontEndManager.getData(macAddr, "INFAILS")%></td>
	</tr>
	<tr>
    <TR valign="bottom" class="reportsEvenRow">
		<td>OUTFAILS</td>
		<td><%=frontEndManager.getData(macAddr, "OUTFAILS")%></td>
	</tr>
    <TR valign="bottom" class="tableOddRow">
		<td>INAGCGAIN</td>
		<td><%=frontEndManager.getData(macAddr, "INAGCGAIN")%></td>
	</tr>
    <TR valign="bottom" class="reportsEvenRow">
		<td>OUTAGCGAIN</td>
		<td><%=frontEndManager.getData(macAddr, "OUTAGCGAIN")%></td>
	</tr>
    <TR valign="bottom" class="tableOddRow">
		<td>INPHYBPC</td>
		<td><%=frontEndManager.getData(macAddr, "INPHYBPC")%></td>
	</tr>
    <TR valign="bottom" class="reportsEvenRow">
		<td>OUTPHYBPC</td>
		<td><%=frontEndManager.getData(macAddr, "OUTPHYBPC")%></td>
	</tr>
    <TR valign="bottom" class="tableOddRow">
		<td>INSPEED</td>
		<td><%=frontEndManager.getData(macAddr, "INSPEED")%></td>
	</tr>
    <TR valign="bottom" class="reportsEvenRow">
		<td>OUTSPEED</td>
		<td><%=frontEndManager.getData(macAddr, "OUTSPEED")%></td>
	</tr>
    <TR valign="bottom" class="tableOddRow">
		<td>INERROR</td>
		<td><%=frontEndManager.getData(macAddr, "INERROR")%></td>
	</tr>
    <TR valign="bottom" class="reportsEvenRow">
		<td>OUTERROR</td>
		<td><%=frontEndManager.getData(macAddr, "OUTERROR")%></td>
	</tr>
    <TR valign="bottom" class="tableOddRow">
		<td>PARENTMAC</td>
		<td><%=frontEndManager.getData(macAddr, "PARENTMAC")%></td>
	</tr>
    <TR valign="bottom" class="reportsEvenRow">
		<td>LINKEDNODENUM</td>
		<td><%=frontEndManager.getData(macAddr, "LINKEDNODENUM")%></td>
	</tr>
    <TR valign="bottom" class="tableOddRow">
		<td>RTSCTSSTATUS</td>
		<td><%=frontEndManager.getData(macAddr, "RTSCTSSTATUS")%></td>
	</tr>
</table>

</body>
</html>
