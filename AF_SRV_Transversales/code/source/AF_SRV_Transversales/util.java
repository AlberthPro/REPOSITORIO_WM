package AF_SRV_Transversales;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.app.b2b.server.ServiceThread;
import com.wm.lang.ns.NSService;
import com.wm.net.HttpHeader;
import com.wm.app.b2b.server.InvokeState;
import jdk.jshell.ErroneousSnippet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.softwareag.g11n.util.iContext;
import com.softwareag.util.IDataMap;
// --- <<IS-END-IMPORTS>> ---

public final class util

{
	// ---( internal utility methods )---

	final static util _instance = new util();

	static util _newInstance() { return new util(); }

	static util _cast(Object o) { return (util)o; }

	// ---( server methods )---




	public static final void UT_BuildContextBussinesRULE (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(UT_BuildContextBussinesRULE)>> ---
		// @sigtype java 3.5
		// [i] field:0:required initTime
		// [i] field:0:required ruleName
		// [i] field:0:required UUID
		// [o] field:0:required customID
		String ruleName = IDataUtil.getString(pipeline.getCursor(), "ruleName");
		String preRuleName = ruleName.substring(ruleName.lastIndexOf("_")-4, ruleName.lastIndexOf("_"));
		String shortUUID = IDataUtil.getString(pipeline.getCursor(), "UUID");
		shortUUID = shortUUID.substring(0, shortUUID.lastIndexOf("-"));
		String requestDate = IDataUtil.getString(pipeline.getCursor(), "initTime");
		String timestr = requestDate.replaceAll("[\\/.: ]", "");
		
		String customID = preRuleName+"-"+shortUUID+"-"+timestr;
		IDataUtil.put(pipeline.getCursor(), "customID", customID);
		// --- <<IS-END>> ---

                
	}



	public static final void UT_CastDate (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(UT_CastDate)>> ---
		// @sigtype java 3.5
		// [i] field:0:required dateStr
		// [i] field:0:required cast {"date","string"}
		// [i] object:0:required date2cast
		// [o] object:0:required outputDate
		// [o] field:0:required outputDateStr
		String cast = IDataUtil.getString(pipeline.getCursor(), "cast");
		if (cast.equalsIgnoreCase("date")){
		String dateStr = IDataUtil.getString(pipeline.getCursor(), "dateStr");
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ROOT);
		 try {
		    Date date = format.parse(dateStr);
		    IDataUtil.put(pipeline.getCursor(), "outputDate", date);
		} catch (ParseException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		}else if(cast.equalsIgnoreCase("string")) {
		    String date = IDataUtil.getString(pipeline.getCursor(), "dateStr");
		    String pattern = "dd/MM/yyyy HH:mm:ss";
		    SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		    String formattedDate = dateFormat.format(date);
		    IDataUtil.put(pipeline.getCursor(), "outputDateStr", formattedDate);
		}
		// --- <<IS-END>> ---

                
	}



	public static final void UT_Date2Int (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(UT_Date2Int)>> ---
		// @sigtype java 3.5
		// [i] field:0:required date
		// [o] object:0:required intValue
		String dateStr = IDataUtil.getString(pipeline.getCursor(), "date");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
		Date fecha = null;
		try {
		fecha = sdf.parse(dateStr);
		} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		long epochMillis = fecha.getTime();
		IDataUtil.put(pipeline.getCursor(), "intValue", epochMillis);
		// --- <<IS-END>> ---

                
	}



	public static final void UT_FindVarieble (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(UT_FindVarieble)>> ---
		// @sigtype java 3.5
		// [i] record:0:required OUTPUT_RULE
		// [o] field:0:required codigo
		IDataMap mapa = new IDataMap(pipeline);
		String codigo= "";
		 if (mapa.containsKey("return")) {
		Object valor = mapa.get("return");
		
		if (valor instanceof Boolean) {
		boolean resultado = (Boolean) valor;
		codigo = "200";
		} else {
		codigo = "505";
		}
		} else {
		codigo = "404";
		}
		// --- <<IS-END>> ---

                
	}



	public static final void UT_GenerateMetaData (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(UT_GenerateMetaData)>> ---
		// @sigtype java 3.5
		// [i] record:0:required documento
		// [i] record:0:required rangos
		// [o] record:1:required metaData
		IDataMap map = new IDataMap(IDataUtil.getIData(pipeline.getCursor(), "documento"));
		IData rangos = IDataUtil.getIData(pipeline.getCursor(), "rangos");
		
		List<IData> mapa = new ArrayList<IData>();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
		IData item = IDataFactory.create();
		IDataUtil.put(item.getCursor(), "key", entry.getKey());
		String value = (String) entry.getValue();
		if (value == null || value == "")
		throw new RuntimeException("El campo "+entry.getKey()+" no puede ser nulo");
		else
		IDataUtil.put(item.getCursor(), "value", value);
		
		String rangoValue = IDataUtil.getString(rangos.getCursor(), entry.getKey().toLowerCase()); 
		IDataUtil.put(item.getCursor(), "rango", rangoValue);
		IDataUtil.put(item.getCursor(), "operador", "toDo");
		
		mapa.add(item);
		}
		IData[] metadata = new IData[map.size()];
		metadata = mapa.toArray(metadata);
		
		if (metadata.length==0)
		IDataUtil.put(pipeline.getCursor(), "metaData", null);
		else
		IDataUtil.put(pipeline.getCursor(), "metaData", metadata);
		// --- <<IS-END>> ---

                
	}



	public static final void UT_GetFlowName (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(UT_GetFlowName)>> ---
		// @sigtype java 3.5
		// [o] field:0:required flowName
		String serviceName = null; 
		Stack callStack = InvokeState.getCurrentState().getCallStack();  
		int size = callStack.size();  
		 
		if (size >= 2) {  
		 NSService myService = (NSService) callStack.elementAt (size - 2);  
		 serviceName = myService.getNSName().getFullName();  
		}  
		 
		// pipeline out 
		IDataCursor pipelineCursor = pipeline.getCursor(); 
		IDataUtil.put(pipelineCursor, "flowName", serviceName); 
		pipelineCursor.destroy();
		// --- <<IS-END>> ---

                
	}



	public static final void UT_PipelineToDocument (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(UT_PipelineToDocument)>> ---
		// @sigtype java 3.5
		// [o] record:0:required pipelineData
		IData pipelineData = IDataFactory.create();
		IDataUtil.put(pipelineData.getCursor(), "pipeline", pipeline);
		IDataUtil.put(pipeline.getCursor(), "pipelineData", pipelineData);
		// --- <<IS-END>> ---

                
	}



	public static final void UT_PipelineToString (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(UT_PipelineToString)>> ---
		// @sigtype java 3.5
		// [o] field:0:required strPipeline
		IDataMap pipe = new IDataMap(pipeline);
		IDataUtil.put(pipeline.getCursor(), "strPipeline", pipe.toString());
		// --- <<IS-END>> ---

                
	}



	public static final void UT_ReadTable (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(UT_ReadTable)>> ---
		// @sigtype java 3.5
		// --- <<IS-END>> ---

                
	}



	public static final void UT_Regex (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(UT_Regex)>> ---
		// @sigtype java 3.5
		// [i] field:0:required message
		String message = IDataUtil.getString(pipeline.getCursor(), "message");
		if (message == null){
		IDataUtil.put(pipeline.getCursor(), "isValid", false);
		return;
		}
		String validPattern = "\\W*((?i)Pipeline reenviado en el paso: S(?-i))\\W*";
		Pattern pattern = Pattern.compile(validPattern);
		Matcher matcher = pattern.matcher(message);
		
		IDataUtil.put(pipeline.getCursor(), "isValid", matcher.find());
		// --- <<IS-END>> ---

                
	}



	public static final void UT_Replace (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(UT_Replace)>> ---
		// @sigtype java 3.5
		// [i] field:0:required inString
		// [i] field:0:required toReplace
		// [i] field:0:required keyString
		// [o] field:0:required outString
		String source = IDataUtil.getString(pipeline.getCursor(), "inString");
		String key = IDataUtil.getString(pipeline.getCursor(), "keyString");
		String toReplace = IDataUtil.getString(pipeline.getCursor(), "toReplace");
		
		String res = source.replaceAll(key, toReplace.trim());
		IDataUtil.put(pipeline.getCursor(), "outString", res);
		// --- <<IS-END>> ---

                
	}



	public static final void UT_ServiceContext (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(UT_ServiceContext)>> ---
		// @sigtype java 3.5
		// [o] record:0:required code
		HttpHeader httpStatus = Service.getHttpResponseHeader();
		
		  long javaThreadId = Thread.currentThread().getId();
		String javaThreadName = Thread.currentThread().getName();
		
		final long startNs = System.nanoTime();
		final long startMs = System.currentTimeMillis();
		final Instant startInstant = Instant.ofEpochMilli(startMs);
		// 6) Timing at exit
		final long endNs = System.nanoTime();
		final long endMs = System.currentTimeMillis();
		final Instant endInstant = Instant.ofEpochMilli(endMs);
		final long durationMs = Math.max(0L, (endNs - startNs) / 1_000_000L);
		
		// 7) ISO-8601 timestamps (UTC) for easier log correlation
		DateTimeFormatter ISO_UTC = DateTimeFormatter.ISO_INSTANT.withZone(ZoneOffset.UTC);
		String startTimeISO = ISO_UTC.format(startInstant);
		String endTimeISO   = ISO_UTC.format(endInstant);
		
		IData javaInfo = IDataFactory.create();
		IDataCursor cursor = javaInfo.getCursor();
		
		IDataUtil.put(cursor, "javaThreadId", String.valueOf(javaThreadId));
		IDataUtil.put(cursor, "javaThreadName", javaThreadName);
		
		IDataUtil.put(cursor, "startTimeEpochMs", String.valueOf(startMs));
		IDataUtil.put(cursor, "endTimeEpochMs", String.valueOf(endMs));
		IDataUtil.put(cursor, "startTimeISO", startTimeISO);
		IDataUtil.put(cursor, "endTimeISO", endTimeISO);
		IDataUtil.put(cursor, "durationMs", String.valueOf(durationMs));
		
		
		  int codehttp = httpStatus.getResponseCode();
		  
		  IDataUtil.put(javaInfo.getCursor(), "code", httpStatus);
		  IDataUtil.put(pipeline.getCursor(), "javaInfo", javaInfo);
		// --- <<IS-END>> ---

                
	}



	public static final void UT_ValidateInput (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(UT_ValidateInput)>> ---
		// @sigtype java 3.5
		// [i] field:1:required required
		// [i] record:0:required input
		// [i] - field:0:optional processInstanceID
		// [i] - field:0:required taskID
		// [i] - field:0:required customID
		// [i] - record:0:required genericDocument
		// [i] - object:0:required stopInstance
		// [i] - object:0:required completeTask
		// [o] field:1:required errors
		IDataMap request = new IDataMap(IDataUtil.getIData(pipeline.getCursor(), "input"));
		String[] required = IDataUtil.getStringArray(pipeline.getCursor(), "required");
		List<String> errors = new ArrayList<String>();
		
		if (required.length == 1 && required[0].equals("NOTcustomID") ){
		    errors.add("el customID no funciono, envie el processInstanceID");
		    IDataUtil.put(pipeline.getCursor(), "errors", errors.toArray());
		    throw new RuntimeException("el customID: "+request.getAsString("customID")+" es invalido");
		}
		    
		    
		
		boolean found = false;
		try {
		    for (String obt : required) {
		for (Entry<String, Object> entry : request.entrySet()) {
		    String key = entry.getKey();
		    if (key.equalsIgnoreCase(obt)) {
		IDataUtil.put(pipeline.getCursor(), "temp", key);
		found = !found;
		break;
		    }
		}
		if (!found) {
		    errors.add("el campo " + obt + " es requerido");
		    found = false;
		}
		    }
		    
		 if (!errors.isEmpty()){
		     throw new RuntimeException("Algunos campos no fueron enviados");
		 }
		} catch (Exception e) {
		    IDataUtil.put(pipeline.getCursor(), "errors", errors.toArray());
		    throw new RuntimeException("Algunos campos no fueron enviados");
		}
		
		
		IDataUtil.put(pipeline.getCursor(), "errors", errors.toArray());
		// --- <<IS-END>> ---

                
	}
}

