<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"
	import="dpnm.netmsuite.plc.manager.frontend.*,dpnm.netmsuite.util.*,java.util.*"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Trap Information</title>
	<link href="css/style.css" rel="stylesheet" type="text/css">
</head>

<body background="images/bodybg.gif">
<%!
public String time = "";
public String type = "";
public int k = 0;
%>
	
<!-- <table width="400" border="1" align="left" cellpadding="0" cellspacing="0" bordercolordark="white" bordercolorlight="black" >  -->
<b>&nbsp Trap Information<b>
<table color="pink" width="1001" border="0" align="left" cellpadding="1" cellspacing="0" class="homeTableBorder" >
         <tr class="homeHeader">
         <td class="boldWhiteText" align='center'>Index</td>
         <td class="boldWhiteText" align='center'>MACADDR</td>
         <td class="boldWhiteText" align='center'>TIME</td>
         <td class="boldWhiteText" align='center'>TYPE</td>
	</tr>
	
	<%IFrontEndManager frontEndManager = FrontEndManager.getInstance();
		frontEndManager.frontEndManagerstart();
		
		Vector newestFaultList = frontEndManager.getNewestFaultList(6);	
	
		
	if ( k != 0 ) 
	{ k = 0;}
			if (newestFaultList.size() == 0) {
				out.println("<tr valign='bottom' class='reportsEvenRow'><td colspan='4' align='center'>&nbsp</td></tr>");
				out.println("<tr valign='bottom' class='tableOddRow'><td colspan='4'>&nbsp</td></tr>");
			    out.println("<tr align='center' valign='bottom' class='reportsEvenRow'><td>There is no trap information</td></tr>");
			    out.println("<tr valign='bottom' class='tableOddRow'><td colspan='4'>&nbsp</td></tr>");
			    out.println("<tr valign='bottom' class='reportsEvenRow'><td colspan='4' align='center'>&nbsp</td></tr>");
			} else {

				for (int i = 0; i < newestFaultList.size(); i++) {
					FaultInfo oneFault = (FaultInfo) newestFaultList.elementAt(i);
					
					k++; //index를 위한 
					String macAddr = oneFault.macAddr;
					time = oneFault.time;
					type = oneFault.type;

					if (i % 2 == 0) {
						out.println("<tr valign='bottom' class='reportsEvenRow'><td align='center'>" + k + "</td><td>"
										+ macAddr
										+ "</td><td>"
										+ time
										+ "</td><td>" + type + "</td></tr>");
					} else if (i % 2 == 1) {
						out.println("<tr valign='bottom' class='tableOddRow'><td align='center'>" + k + "</td><td>"
										+ macAddr
										+ "</td><td>"
										+ time
										+ "</td><td>" + type + "</td></tr>");
					}
					
				}//for

			}//if
	
			%>
		
</table>


</body>
</html>

