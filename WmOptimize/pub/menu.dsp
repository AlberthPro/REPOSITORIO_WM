<!-- Copyright (c) 2003, webMethods Inc.  All Rights Reserved. -->

<html>
	<head>
		<meta http-equiv="Pragma" content="no-cache">
		<meta http-equiv="Expires" content="-1">
		<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
		<link rel="stylesheet" type="text/css" href="/WmRoot/webMethods.css">
		<script src="/WmRoot/common-menu.js"></script>
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
	</head>
	<body class="menu" onload="initMenu('controllerConfigSettings.dsp');">
		<table width="100%" cellspacing="0" cellpadding="1" border="0">
			<tr>
				<td class="menusection-Server">
					<nobr>
						<img src="images/blank.gif" width="4" height="1" border="0">
						Optimize Package
					</nobr>
				</td>
			</tr>
			<tr>
				<td id="icontrollerConfigSettings.dsp" class="menuitem"
						onmouseover="menuMouseOver(this, 'controllerConfigSettings.dsp');"
						onmouseout="menuMouseOut(this, 'controllerConfigSettings.dsp');"
						onclick="document.all['acontrollerConfigSettings.dsp'].click();">
					<nobr>
						<img valign="middle" src="/WmRoot/images/blank.gif" width="4" height="1" border="0">
						<img valign="middle" src="/WmRoot/images/blank.gif" width="8" height="8" border="0"
								id="controllerConfigSettings.dsp" name="controllerConfigSettings.dsp"/>
						<a id="acontrollerConfigSettings.dsp" href="controllerConfigSettings.dsp" target="main"
								onclick="menuMove('controllerConfigSettings.dsp', 'main'); return true;" >
							<span class="menuitemspan">Configuration Settings</span>
						</a>
					</nobr>
				</td>
			</tr>
		</table>
	</body>
</html>

