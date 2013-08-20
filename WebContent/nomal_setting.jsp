<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"
	import="dpnm.netmsuite.plc.manager.frontend.*, dpnm.netmsuite.util.*, java.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>master, repeater, slave 수정정보</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>

<body>
<%
IFrontEndManager frontEndManager = FrontEndManager.getInstance();
frontEndManager.frontEndManagerstart();
%>
<%String nodetype = request.getParameter("nodetype");%>

<%String macAddr = request.getParameter("MacAddr");%>
<%int status = Integer.parseInt(frontEndManager.getData(macAddr, "STATUS"));%>
<%int rtsctsstatus = Integer.parseInt(frontEndManager.getData(macAddr, "RTSCTSSTATUS"));%>
<%
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



<form method='post' action='normal_setting_success.jsp'>
<table border='0'>
<tr><td>
<table width='305' border="1" align="left" cellpadding="2" cellspacing="0" class="homeTableDarkBorder_2" > 
	<tr class="homeHeader">
        <td colspan='2' class="boldWhiteText"><%=nodetype%> Configuration</td>
	</tr>
	<tr valign="middle">
        <td class="tableOddRow">STATUS</td>
		<td>
			<input type='radio' name='status' value='0' <%=check_0%>> Active <br>
			<input type='radio' name='status' value='1'<%=check_1%>> Suspend <br>
			<input type='radio' name='status' value='2'<%=check_2%>> Factory Mode <br>
			<input type='radio' name='status' value='3'<%=check_3%>> Reset 
		</td>
	</tr>
	<tr>
		<td class="tableOddRow">RTS/CTS STATUS</td>
		<td>
			
			 <input type='radio' name='rtsctsstatus' value='0' <%=check_4%>>Enable <br>
			 <input type='radio' name='rtsctsstatus' value='1'<%=check_5%>> Disable
			
		</td>
	</tr>
</table>
</td></tr>

<tr><td align='center'>
<table>
	<tr>
		<td colspan='2'>
			<p align='center'>
				<input type='submit' value="Modify">&nbsp;&nbsp;&nbsp;
				<input type='reset' value="Reset">	
		</td>
	</tr>
	<tr>
		<td>
			<input type='hidden' name='MacAddr' value='<%=macAddr%>'>
			<input type='hidden' name='nodetype' value='<%=nodetype%>'>
		</td>
	</tr>
</table>
</td></tr>

</table>
</form>
</body>
</html>