function reloadNavigationFrame()
{
	parent.menu.location.replace("menu.dsp");
}

function writeTDnowrap (c)
{
	document.write("<TD CLASS=\"");
	document.write(row);
	document.write(c);
	document.write("\" NOWRAP>");
	return true;
}

function writeTDWidth (c, width)
{
	document.write("<TD CLASS=\"");
	document.write(row);
	document.write(c);
	document.write("\" WIDTH=");
	document.write(width);
	document.write(">");
}

function swapColor(objName, label) {

       if (navigator.appName == 'Netscape') {}
       else {
		 var theObj = eval(objName);
		 if(theObj.childNodes == null) return;
		 for(var i = 1; i < theObj.childNodes.length; i=i+4){
		   for(var j = 0; j < theObj.childNodes[1].childNodes.length; j++){
			 theObj.childNodes[i].childNodes[j].style.background='#f0f0e0';
		   }
		   if (label==true)
			 theObj.childNodes[i].firstChild.style.background='#f0f0e0';
		 }
       }   
}

function isInteger(input)
{
      if ( isNaN(parseInt(input)) || (input.indexOf(".") >= 0) ||
         (parseInt(input) != input))
      {
		return false;
      }
      return true;
}

function isIntegerGreaterThan(input, comp)
{
      if (! isInteger(input))
      {
      	return false;
      }
      if (parseInt(input) <= parseInt(comp)) 
      {
		return false;
      }
      return true;
}

function expiredOrSeconds(expires)
{
	if (expires < 1)
	{
		document.write("expired");
	}
	else
	{
		document.write(expires);
		document.write(" sec");
	}
}

function writeMessage(msg)
{
	document.write("<TR><TD class=\"message\" colspan=2>&nbsp;&nbsp;&nbsp;&nbsp;"+msg+"</TD></TR>");
}