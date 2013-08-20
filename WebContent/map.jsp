<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"
	import="dpnm.netmsuite.plc.manager.frontend.*, dpnm.netmsuite.util.*, java.util.*"
	%>
<%!
/*  POSITION OF NODES */
int EMS[] = {50,50};
int EMS_MASTER_LINK[] = {80,153};
int MASTER[] = {30,230};
int MASTER_SLAVE_LINK[] = {140,250};
int SLAVE[][] = {
    {200,330}, {400,330}, {600,330}, {250,130}, {500,130}};
int SLAVE_LINK[][] = {
    {230,255}, {430,255}, {630,255}, {280,178}, {530,178}};
int MAX_SPEED = 24000;

String linkColor[] = {
	"00EBEB", "00A3ED", "0000F7", "00FF00","00C709",
	"008F00","969600",	"FFFF00", "FF8F00","FF0000"
};

int range[] = {
		3,6,9,12,15,18,30,50,70,100
};

public String getStatus(String status) {
	try {
		switch(Integer.parseInt(status)) {
		case 0:
			return "<font color=green><b>ACTIVE</b></font>";
		case 1:
			return "<font color=darkgreen><b>SUSPEND</b></font>";
		case 2:
			return "<font color=red><b>FACTORY DEFAULT</b></font>";
		case 3:
			return "<font color=black><b>FAULT</b></font>";
		default:
			return "<font color=black><b>UNKNOWN</b></font>";
		}
	} catch (Exception ex) {
		return "<font color=black><b>UNKNOWN</b></font>";
	}
}

public String getLinkColor(int util) {
	for (int i = 0; i < range.length; i++) {
		if (util < range[i]) {
			return linkColor[i];
		}
	}
	return linkColor[0];
}
%>

<html>

<head>
<title>Node Map</title>

<meta http-equiv="refresh" content="60; url=map.jsp">
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
    background-image: url('images/background.png');
    background-repeat: no-repeat;
    bakcground-color: #ECEAEA;
}
table.colortable {
	border:1px black solid;
}
-->
</style>

    </head>
    <body>
<% 
IFrontEndManager frontEndManager = FrontEndManager.getInstance();
frontEndManager.frontEndManagerstart();

PLCTreeNode rootTreeNode = frontEndManager.getTreeMap();
/*
out.println(rootTreeNode.getMacAddr());
Vector nodes = rootTreeNode.getChilds();
PLCTreeNode masterNode = (PLCTreeNode)nodes.elementAt(0);
out.println(masterNode.getMacAddr());
nodes = masterNode.getChilds();
for (int i = 0; i < nodes.size(); i++) {
	PLCTreeNode slaveNode = (PLCTreeNode)nodes.elementAt(i);
	out.println("Slave : " + slaveNode.getMacAddr());

}
*/
%>

<div id="colorbar" style="position:absolute; width:20px; height:150px;
      z-index:50; left: 5px; top: 5px; visibility: visible">
<table class=colortable width=40 cellspacing=0 cellpadding=0 border=0>
<%
	for (int i = 0; i < 10; i++) {
		%>
		<tr>
		<td style="font-size:7pt;font-weight:bolder" align=center bgcolor=<%=linkColor[i] %> height=5>&lt;<%=range[i] %> %</td>
		</tr>
		<%
	}
%>
</table>
</div>
<div id="overDiv" style="position:absolute; left:50px; top:50px; width:200px; height:200px; z-index:50;
 visibility:hidden; border-width:1px; border-style:none;"></div>
<script language="JavaScript" src="myLib.js"></script>

  <div id="eu" style="position:absolute; width:175px; height:103px;
      z-index:5; left: <%=EMS[0]%>px; top: <%=EMS[1]%>px; visibility: visible">
      <table width=220 height=103>
          <tr>
              <td> 
<%
	String ip = rootTreeNode.getMacAddr();
	String msg = "<b>- Polling Interval :</b> " + frontEndManager.getData(ip, "POLLINGINTERVAL")+"<br>";
	msg = msg+ "<br><b>- Incoming Packets :</b> " + frontEndManager.getData(ip, "INPKT")+"<br>";
	msg = msg+ "<b>- Outgoing Packets :</b> " + frontEndManager.getData(ip, "OUTPKT")+"<br>";
	msg = msg+ "<br><b>- Memory :</b> " + frontEndManager.getData(ip, "FREEMEMSIZE")+
	"/"+frontEndManager.getData(ip, "MEMORYSIZE")+"<br>";
%>
                  <img 
onMouseOver="this.style.cursor='pointer'; drc('EMS [<b><%=ip %></b>]','<%=msg %>');high(popup);"
                onMouseOut="low(popup);" src="images/eu.gif"
                onClick="window.open('main.jsp?MacAddr=<%=ip %>','_self')">
                [<b><%=ip %></b>]
            </td>
          </tr>
      </table>
    </div>

  <div id="eu_master_link" style="position:absolute; width:20px; height:150px;
      z-index:6; left: <%=EMS_MASTER_LINK[0]%>px; top: <%=EMS_MASTER_LINK[1]%> px; visibility: visible">
      <table width=10 height=80>
          <tr>
              <td class=link bgcolor=blue>&nbsp;</td>
<!--
              <td class=link bgcolor=blue 
                  onMouseOver="this.style.cursor='pointer'; drc('EMS-Master Link','In Pkts:000000<br>Out Pkts:000000<br>Total Pkts:00000');high(popup);"
                  onMouseOut="low(popup);">
                  &nbsp;
            </td>
-->
          </tr>
      </table>
    </div>
<%
Vector nodes = rootTreeNode.getChilds();
PLCTreeNode masterNode = (PLCTreeNode)nodes.elementAt(0);

String mac = masterNode.getMacAddr();
msg = "<b>- Status : </b>" + getStatus(frontEndManager.getData(mac, "STATUS"))+"<br>";
//msg = msg+"<img src=images/status_"+frontEndManager.getData(mac, "STATUS")+".gif><br>";
msg = msg+"<b>- Incoming Packets :</b> " + frontEndManager.getData(mac, "INPKTS")+"<br>";
msg = msg+"<b>- Outgoing Packets :</b> " + frontEndManager.getData(mac, "OUTPKTS")+"<br>";
msg = msg+"<b>- Incoming ACKs :</b> " + frontEndManager.getData(mac, "INACKS")+"<br>";
msg = msg+"<b>- Outgoing ACKs :</b> " + frontEndManager.getData(mac, "OUTACKS")+"<br>";
msg = msg+"<b>- Incoming Fails :</b> " + frontEndManager.getData(mac, "INFAILS")+"<br>";
msg = msg+"<b>- Outgoing Fails :</b> " + frontEndManager.getData(mac, "OUTFAILS")+"<br>";
msg = msg+"<b>- # of Linked Node :</b> " + frontEndManager.getData(mac, "LINKEDNODENUM")+"<br>";
msg = msg+"<b>- RTSCTSSTATUS :</b> " + frontEndManager.getData(mac, "RTSCTSSTATUS")+"<br>";

%>
  <div id="master" style="position:absolute; width:220px; height:67px;
      z-index:7; left: <%=MASTER[0]%>px; top: <%=MASTER[1]%>px; visibility: visible">
      <table width=220 height=67>
          <tr>
              <td>
                  <img onMouseOver="this.style.cursor='pointer'; drc('Master Modem[<%=mac %>]','<%=msg %>');high(popup);"
                onMouseOut="low(popup);" src="images/master.gif"
                onClick="window.open('main.jsp?MacAddr=<%=mac %>','_self')">
<br>[<b><%=mac %></b>]
            </td>

          </tr>
      </table>
  </div>
  <div id="master_status" style="position:absolute; width:220px; height:67px;
      z-index:10; left: <%=MASTER[0]+110%>px; top: <%=MASTER[1]+15%>px; visibility: visible">
      <table width=220 height=67>
          <tr>
              <td>
                <img  src="images/statusm_<%=frontEndManager.getData(mac, "STATUS") %>.gif">
            </td>

          </tr>
      </table>
  </div>

  <div id="master_slave_link" style="position:absolute; width:400px; height:10px;
      z-index:40; left: <%=MASTER_SLAVE_LINK[0]%>px; top: <%=MASTER_SLAVE_LINK[1]%>px; visibility: visible">
      <table width=515 height=15>
          <tr>
              <td class=link bgcolor=blue>
            </td>
          </tr>
      </table>
    </div>

<%
/*  SLAVE MODEM */
nodes = masterNode.getChilds();
for (int i = 0; i < nodes.size(); i++) {
	PLCTreeNode slaveNode = (PLCTreeNode)nodes.elementAt(i);

    mac = slaveNode.getMacAddr();
    msg = "<b>- Status : </b>" + getStatus(frontEndManager.getData(mac, "STATUS"))+"<br>";
    msg = msg+"<b>- MAX SPEED :</b> " + MAX_SPEED +" Kbps<br>";
    msg = msg+"<b>- Incoming Packets :</b> " + frontEndManager.getData(mac, "INPKTS")+"<br>";
    msg = msg+"<b>- Outgoing Packets :</b> " + frontEndManager.getData(mac, "OUTPKTS")+"<br>";
    msg = msg+"<b>- Incoming ACKs :</b> " + frontEndManager.getData(mac, "INACKS")+"<br>";
    msg = msg+"<b>- Outgoing ACKs :</b> " + frontEndManager.getData(mac, "OUTACKS")+"<br>";
    msg = msg+"<b>- Incoming Fails :</b> " + frontEndManager.getData(mac, "INFAILS")+"<br>";
    msg = msg+"<b>- Outgoing Fails :</b> " + frontEndManager.getData(mac, "OUTFAILS")+"<br>";
    msg = msg+"<b>- Incoming AGC Gain :</b> " + frontEndManager.getData(mac, "INAGCGAIN")+"<br>";
    msg = msg+"<b>- Outgoing AGC Gain :</b> " + frontEndManager.getData(mac, "OUTAGCGAIN")+"<br>";
    msg = msg+"<b>- Incoming PHY BPC :</b> " + frontEndManager.getData(mac, "INPHYBPC")+"<br>";
    msg = msg+"<b>- Outgoing PHY BPC :</b> " + frontEndManager.getData(mac, "OUTPHYBPC")+"<br>";
    msg = msg+"<b>- RTSCTSSTATUS :</b> " + frontEndManager.getData(mac, "RTSCTSSTATUS")+"<br>";


    int inSpeed = Integer.parseInt(frontEndManager.getData(mac, "INSPEED"));
    int outSpeed = Integer.parseInt(frontEndManager.getData(mac, "OUTSPEED"));
	String msg1 = "<b>- Incoming Speed :</b> " + inSpeed+" Kbps<br>";
    String msg2 = "<b>- Outgoing Speed :</b> " + outSpeed+" Kbps<br>";
	int inUtil = (int)(((double)inSpeed/MAX_SPEED)*100);
	int outUtil = (int)(((double)outSpeed/MAX_SPEED)*100);

	msg1 = msg1+"<b>- Incoming Utilization :</b> " + inUtil +" %<br>";
    msg2 = msg2+"<b>- Outgoing Utilization :</b> " + outUtil +" %";

	String index = Integer.toString(i+1);

%>
  <div id="slave<%=index%>" style="position:absolute; width:280px; height:44px;
      z-index:4; left: <%=SLAVE[i][0]%>px; top: <%=SLAVE[i][1]%>px; visibility: visible">
      <table width=280 height=44>
          <tr>
              <td>
                  <img onMouseOver="this.style.cursor='pointer'; drc('Slave Modem[<%=mac%>]','<%=msg%>');high(popup);"
                onMouseOut="low(popup);" src="images/slave.gif"
                onClick="window.open('main.jsp?MacAddr=<%=mac %>','_self')">
<%
    if (i < 3) {
        out.println("<br>");
    }
%>
[<b><%=mac%></b>]
            </td>

          </tr>
      </table>
  </div>
  <div id="slave_status<%=index %>" style="position:absolute; width:220px; height:67px;
      z-index:10; left: <%=SLAVE[i][0]+80%>px; top: <%=SLAVE[i][1]+10%>px; visibility: visible">
      <table width=40 height=40>
          <tr>
              <td>
                <img src="images/statusm_<%=frontEndManager.getData(mac, "STATUS") %>.gif">
            </td>

          </tr>
      </table>
  </div>
  <div id="master_slave_link<%=index%>" style="position:absolute; width:400px; height:10px;
      z-index:15; left: <%=SLAVE_LINK[i][0]%>px; top: <%=SLAVE_LINK[i][1]%>px; visibility: visible">
      <table width=15 height=77 cellspacing=0 cellpadding=0 border=0>
          <tr>
              <td style="font-size:8pt;font-weight:bolder"
              class=link bgcolor=#<%=getLinkColor(outUtil) %> 
                  onMouseOver="this.style.cursor='pointer'; drc('Master &lt;= Slave Link [<%=index%>]','<%=msg1%>');high(popup);"
                  onMouseOut="low(popup);" 
                  onClick="window.open('main.jsp?MacAddr=<%=mac %>&Type=statistics','_self')" 
                  valign=middle>¡â
            </td>
              <td style="font-size:8pt;font-weight:bolder"
              class=link bgcolor=#<%=getLinkColor(inUtil) %> 
                  onMouseOver="this.style.cursor='pointer'; drc('Master =&gt; Slave Link [<%=index%>]','<%=msg2%>');high(popup);"
                  onMouseOut="low(popup);" 
                  onClick="window.open('main.jsp?MacAddr=<%=mac %>&Type=statistics','_self')" 
                  valign=middle>¡ä
            </td>
          </tr>
      </table>
    </div>
<%
}
%>
    </body>
</html>
