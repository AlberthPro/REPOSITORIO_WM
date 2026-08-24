<HTML>
<HEAD>
<META http-equiv="Pragma" content="no-cache">
<META http-equiv='Content-Type' content='text/html; charset=UTF-8'>
<META HTTP-EQUIV="Expires" CONTENT="-1">

<TITLE>Process Engine Settings</TITLE>
<LINK REL="stylesheet" TYPE="text/css" HREF="/WmRoot/webMethods.css">
</HEAD>
<BODY>

  <TABLE width="100%">
    <TR>
      <TD class="menusection-Settings">
          Process Engine &gt;
          Save to File
      </TD>
    </TR>
    <tr>
          <td valign="top">
            <ul>
             <li><a href="/WmPRT/dashboardDiagnostic.dsp">Return to Diagnostic Page</a></li>
            </ul>
          </td>
     </tr>
  
  %ifvar InstanceId equals('')%
	<TR> <TD> Error: 'Instance ID' value is missing. </TD> </TR> 
  %else%	
  %invoke wm.prt.debug:getDiagnosticData%
    %ifvar status equals('ERROR')%
       %ifvar InstanceIteration equals('')%
	   <TR> <TD> Error: '%value InstanceId%' is invalid or does not exist. </TD> </TR> 
	  %else% 
       <TR> <TD> Error: '%value InstanceId%:%value InstanceIteration%' is invalid or does not exist. </TD> </TR> 
	  %endif% 
    %else%
	<TR> <TD> Diagnostic data package created: <A href=%value savedURL%> %value savedURL% </A> </TD> </TR> 
	<TR> <TD> The file is available in \WmPRT\pub. </TD> </TR>
    %endif%	
  %endinvoke%
  %endif%	
</TABLE>
</BODY>
</HTML>	