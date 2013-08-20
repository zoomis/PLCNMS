ns4 = (document.layers)? true:false
ie4 = (document.all)? true:false
if (ie4) {
	if (navigator.userAgent.indexOf('MSIE 6')>0) {
		ie5 = true;
	} else {
		ie5 = false; }
} else { ie5 = false; }

var width = 240;
var border = "0";
var offsetx = 4;
var offsety = 4;
/*var fcolor = "#000000";*/
/*var fcolor = "#003399";*/
var fcolor = "#FF6600";

/*var backcolor = "#FFCC50"; */
var textcolor = "#FFFFFF";
/* var capcolor = "#986800" */;
/*
var capcolor = "#6666ff";
var backcolor = "#ddddff"; */
var capcolor = "#222222";
var backcolor = "#ffdddd"; 

var fontstyle = "font-size:9pt;";

var x = 0;
var y = 0;
var snow = 0;
var	tmpX;
var newWin = null;
var	doc_width=800, doc_height=600

if ( (ns4) || (ie4) ) {
	if (ns4) over = document.overDiv
	if (ie4) over = overDiv.style
	document.onmousemove = mouseMove
	if (ns4) document.captureEvents(Event.MOUSEMOVE)
}

function newWindow(message, wid, het, picName) {
	if (newWin!=null)
	{
		newWin.close()
	}
	newWin=window.open('', 'photo_window', 'toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,copyhistory=no,width='+(wid+20)+',height='+(het+20));
	if (newWin!=null) {
		newWin.document.write('<html><head><title> </title></head><body >\n');
		newWin.document.write('<script language="JavaScript">\n<!--\nself.moveTo(10,10);\nfunction click() {if (event.button==1) self.close(); }\ndocument.onmousedown=click;\n--></script>\n');		newWin.document.write("<img alt='?????????? ???? ????????' src='"+picName+"' width="+wid+" height="+het+">\n");
		newWin.document.write('</body></html>');
	}
	else
		alert("Error : in function newWindow");
	return;
}
function nd() {
	if ( (ns4) || (ie4) ) {
		snow = 0;
		hideObject(over);
	}
}
function drc(title, text) {
	if(ns4) doc_width=self.innerWidth;
	else if(ie4) doc_width=document.body.clientWidth;
	txt = "<TABLE id=popup WIDTH="+width+" STYLE=\"filter:alpha(opacity=80); border:1 #101010 solid\" CELLPADDING="+border+" CELLSPACING=0><TR><TD BGCOLOR=\""+backcolor+"\"><TABLE WIDTH=100% BORDER=0 CELLPADDING=0 CELLSPACING=0><TR><TD align=right><SPAN ID=\"PTT\">&nbsp;<FONT style="+fontstyle+" COLOR=\""+capcolor+"\"><B>"+title+"</B> &nbsp;</FONT></SPAN></TD></TR></TABLE><TABLE WIDTH=100% BORDER=0 CELLPADDING=2 CELLSPACING=0 BGCOLOR=\""+fcolor+"\"><TR><TD align=left><SPAN ID=\"PST\"><FONT COLOR=\""+textcolor+"\" style="+fontstyle+">"+text+"</FONT><SPAN></TD></TR><tr><td bgcolor=#dddddd><font face=tahoma size=1 color=black><b>(c) PLC-NMS 2006 [<font face=Times color=red>i-</font><font color=blue>NetM</font><font color=green>Suite</font> for <font color=purple>PLC</font>]</b></td></tr></TABLE></TD></TR></TABLE>"

	layerWrite(txt);
	disp();
}
function drcv(title, text, votes) {
	if(ns4) doc_width=self.innerWidth;
	else if(ie4) doc_width=document.body.clientWidth;
	txt = "<TABLE WIDTH="+width+" STYLE=\"border:1 black solid\" CELLPADDING="+border+" CELLSPACING=0><TR><TD BGCOLOR=\""+backcolor+"\"><TABLE WIDTH=100% BORDER=0 CELLPADDING=0 CELLSPACING=0><TR><TD align=right><SPAN ID=\"PTT\">&nbsp;<FONT COLOR=\""+capcolor+"\"><B>"+title+"</B></FONT></SPAN></TD></TR></TABLE><TABLE WIDTH=100% BORDER=0 CELLPADDING=2 CELLSPACING=0 BGCOLOR=\""+fcolor+"\"><TR><TD align=left><SPAN ID=\"PST\"><FONT COLOR=\""+textcolor+"\">"+text+"</FONT> <br><table width=100%><tr><td bgcolor=red width="+((width/2) * (votes/a_total) + 1) +" ></td><td align=right><font size=-2>"+ votes +"/" + a_total +"?? ????</font></td></table> <SPAN></TD></TR></TABLE></TD></TR></TABLE>"
	layerWrite(txt);
	disp();
}
function disp() {
	if ( (ns4) || (ie4) ) {
		if (snow == 0) 	{
			moveTo(over,tmpX,y+offsety);
			showObject(over);
			snow = 1;
		}
	}
}
function mouseMove(e) {
	if (ns4) {x=e.pageX; y=e.pageY}
	if (ie4) {x=event.x; y=event.y}
	if (ie5) {x=event.x+document.body.scrollLeft; y=event.y+document.body.scrollTop;}
	tmpX=(doc_width-x-offsetx-width < 0)? (doc_width-width):(x+offsetx)
	if (snow) {
		moveTo(over,tmpX,y+offsety);
	}
}
function layerWrite(txt) {
	if (ns4) {
		var lyr = document.overDiv.document
		lyr.write(txt)
		lyr.close()
	}
	else if (ie4) document.all["overDiv"].innerHTML = txt
}
function showObject(obj) {
	if (ns4) obj.visibility = "show"
	else if (ie4) obj.visibility = "visible"
}
function hideObject(obj) {
	if (ns4) obj.visibility = "hide"
	else if (ie4) obj.visibility = "hidden"
}
function moveTo(obj,xL,yL) {
	obj.left = xL;
	obj.top = yL;
}

var popupWin;

function openWindow(URL,name) {
        popupWin = window.open(URL, name, 'resizable=yes,scrollbars=yes,width=500,height=300');
}       

function openWindowSized(URL,name,w,h) {
    popupWin = window.open(URL,name,'resizable=yes,scrollbars=yes,width='+w+',height='+h);
}

function openWindowFlagged(URL,name,w,h,res,scr,newwin) {

	if (newwin == 'no' && popupWin && popupWin.open)
		popupWin.close(); 
	popupWin = window.open(URL,name,'resizable='+res+',scrollbars='+scr+',width='+w+',height='+h);
}

function bgmWindow(URL,w,h) {
	bgmWin = window.open(URL,'BGM','resizable=no,scrollbars=no,width='+w+',height='+h);
}


function toHex(dec) {
	// create list of hex characters
  	var hexCharacters = "0123456789ABCDEF"
  	// if number is out of range return limit
    if (dec < 0)
    	return "00"
    if (dec > 255)
        return "FF"
    // decimal equivalent of first hex character in converted number
    var i = Math.floor(dec / 16)
    // decimal equivalent of second hex character in converted number
    var j = dec % 16
    // return hexadecimal equivalent
    return hexCharacters.charAt(i) + hexCharacters.charAt(j)
}
	
// set background color to specified descriptors
function setbgColor(red, green, blue) {
   	document.bgColor = "#" + toHex(red) + toHex(green) + toHex(blue)
}
	
// fade from start to end descriptors (increase step to increase transition speed)
function fade(sred, sgreen, sblue, ered, egreen, eblue, step) {
	// loop to create fade effect
    for(var i = 0; i <= step; ++i) {
    // set current red descriptor
    var red = Math.floor(sred * ((step - i) / step) + ered * (i / step))
    // set current green descriptor
    var green = Math.floor(sgreen * ((step - i) / step) + egreen * (i / step))
    // set current green descriptor
    var blue = Math.floor(sblue * ((step - i) / step) + eblue * (i / step))
    // set background color according to descriptors
    setbgColor(red, green, blue)
    }
}


function high(which2)
{ 
	if (window.lowlighting) clearInterval(lowlighting);
	if (window.highlighting) clearInterval(highlighting);

	theobject=which2;
	highlighting=setInterval("highlightit(theobject )",50);
} 

function low(which2)
{ 
	if (window.lowlighting) clearInterval(lowlighting);
	if (window.highlighting) clearInterval(highlighting);

	theobject=which2;
	lowlighting=setInterval("lowlightit(theobject )",50)
} 
 
function highlightit(cur2)
{ 
	if (cur2.filters.alpha.opacity<75) cur2.filters.alpha.opacity+=25;
} 

function lowlightit(cur2)
{ 
	try
	{
		if (cur2.filters.alpha.opacity>=40) cur2.filters.alpha.opacity-=40;

		if (cur2.filters.alpha.opacity<45)
		{
			if (lowlighting) clearInterval(highlighting);
			if (highlighting) clearInterval(highlighting);
			nd();
		}
	} catch (e) {
		nd();
	}
}
