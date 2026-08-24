<HTML>
<HEAD>
<LINK REL="stylesheet" TYPE="text/css" HREF="/WmRoot/webMethods.css">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
<META HTTP-EQUIV="Expires" CONTENT="-1">
<script src="/WmRoot/common-menu.js"></script>
<script src="processEngine.js"></script>
<style>
body {     border-top: 1px solid #97A6CB; }
</style>
</HEAD>

%invoke wm.prt.dashboard:healthCheck%
<BODY CLASS="main">
		<TABLE width="100%" class="tableForm">
			<TBODY>
				<TR>
					<TD class="breadcrumb">Process Engine &gt; Dashboard
				</TR>
			</TBODY>
		</TABLE>
				
		<TABLE class="tableForm">
				<TBODY>
					<TR>
						<TD colspan="3" class="heading">Dashboard</TD>
					</TR>
					<TR class="subheading2">
						<TD width="25%" class="oddcol-l">Subsystem Name</TD>
						<TD width="10%" class="oddcol">Status</TD>
						<TD class="oddcol">Message</TD>
					</TR>
<SCRIPT>resetRows();</SCRIPT>
%loop health%
	<TR>
		<SCRIPT> writeTD('row-l\" width=\"25%'); </SCRIPT>
		&nbsp;%value name%</TD>
      		<SCRIPT> writeTD('rowdata\" width=\"10%');</SCRIPT>
		%switch status%
			%case '1'%
				<img src="images/green-ball.gif" border="0">
			%case '2'%
				<img src="images/yellow-ball.gif" border="0">
			%case '3'%
				<img src="images/red-ball.gif" border="0">
		%end%
		</TD>
		<SCRIPT> writeTD('row-l');</SCRIPT>
			%value message%
		</TD>
		<SCRIPT>swapRows();</SCRIPT>
		
	</TR>
	
%endloop%
</TBODY>
</TABLE>
</BODY>
</HTML>

