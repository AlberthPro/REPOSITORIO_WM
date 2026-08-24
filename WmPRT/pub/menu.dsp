<HTML>
<HEAD>
<LINK REL="stylesheet" TYPE="text/css" HREF="/WmRoot/webMethods.css">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
<META HTTP-EQUIV="Expires" CONTENT="-1">
<script src="/WmRoot/common-menu.js"></script>
<style>
body {     border-top: 1px solid #97A6CB; }
</style>
<script type="text/javascript">
var selected = null;
var menuInit = false;

function menuSelect(object, id) {
  selected = menuext.select(object, id, selected);
}

function menuMouseOver(object, id) {
  menuext.mouseOver(object, id, selected);
}

function menuMouseOut(object, id) {
  menuext.mouseOut(object, id, selected);
}

function initMenu(firstImage) {
    menuInit = true;
    return true;
}

</script>
</HEAD>

%invoke wm.prt.dashboard:mainMenu%
<BODY CLASS="menu" onload="initMenu('%value buttonUrls/sections[0]/submenu[0]/url%');">



<TABLE class="menuTable" width="100%" cellspacing="0" cellpadding="0" border="0">

%scope buttonUrls%
%loop sections%

	<tr manualhide="false" 
    onClick="toggle(this, '%value text%_subMenu', '%value text%_twistie');" 
    onMouseOver="this.className='cursor';"/>
  	<td class="menusection menusection-expanded" id="elmt_%value text%_subMenu"/>
    <img id='%value text%_twistie' src="/WmRoot/images/expanded.gif" />&nbsp;%value text%</td>
	</tr>

%loop submenu%
<!--        
	%ifvar url -notempty%
		%ifvar value value ../text equals('Process Engine')%
			<TR name="%value ../text%_subMenu" style="display: table-row">
		%else%
			<TR name="%value ../text%_subMenu" style="display: table-row">
		%endif%
    <TD id="i%value url%"
        %ifvar url equals('nonedsp')%
          class="menuitem-unclickable"
        %else%
          class="menuitem xyz"
          onmouseover="menuMouseOver(this, '%value url%');"
          onmouseout="menuMouseOut(this, '%value url%');"
          onclick="menuSelect(this, '%value url%'); document.all['a%value url%'].click();"
          style="background-color: rgb(8, 153, 204);"
        %endif%>
        <span style="white-space: nowrap">
    <nobr>
    <img valign="middle" src="/WmRoot/images/blank.gif" width="4" height="1" border="0"><img valign="middle"
            %ifvar url%id="%value url%" name="%value url%"%endif%
            src="/WmRoot/images/blank.gif"
            height="8" width="8" border="0">
        %ifvar url equals('nonedsp')%%value text%
		%else%
				<A id="a%value url%" TARGET="main" HREF="%value url%"	style="color: rgb(255, 255, 255);" %ifvar noarrow%%else%onclick="menuMove('%value url%', 'main'); return true;"%endif%>
				</span>
				<script type="text/javascript">
		  		if( IE() )
					{
						document.write("</a>");
					}
				</script>

				<span class="menuitemspanie">%value text%%ifvar target%...%endif%</span></a>



		%endif%
    </nobr></TD></TR>
    %else%
    %endif%
-->    
<tr name="%value ../text%_subMenu" style="display: table-row">
  <td id="i%value url%" class="menuitem xyz"
      onmouseover="menuMouseOver(this, '%value url%');"
      onmouseout="menuMouseOut(this, '%value url%');"
      onclick="menuSelect(this, '%value url%'); document.all['a%value url%'].click();">
    <span style="white-space: nowrap">
      <a id="a%value url%" target="main" href="%value url%">%value text%</a>
    </span>
  </td>
</tr>    
    
%endloop%
%endif%

    %endloop%
%endscope%
</TABLE>

    <div style="height=0; width=0">
        <form name="urlsaver">
            <input type="hidden" name="helpURL" value="/WmRoot/doc/OnlineHelp/wwhelp.htm?context=is_help&topic=IS_Server_SrvrStatsScrn">
        </form>
    </div>

</BODY>
</HTML>
