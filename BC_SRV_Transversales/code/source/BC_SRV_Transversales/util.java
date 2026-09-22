package BC_SRV_Transversales;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.util.coder.IDataXMLCoder;
import com.wm.util.coder.InvalidDatatypeException;
import com.wm.util.Files;
import com.wm.lang.ns.NSService;
import com.wm.app.b2b.server.InvokeState;
import jdk.jshell.ErroneousSnippet;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.ibm.wsdl.util.StringUtils;
import com.softwareag.util.IDataMap;
// --- <<IS-END-IMPORTS>> ---

public final class util

{
	// ---( internal utility methods )---

	final static util _instance = new util();

	static util _newInstance() { return new util(); }

	static util _cast(Object o) { return (util)o; }

	// ---( server methods )---




	public static final void UT_AddDocument (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(UT_AddDocument)>> ---
		// @sigtype java 3.5
		// [i] record:0:required headers
		// [i] record:0:required taskDataInput
		// [i] record:0:required respaldo
		IData taskData = IDataUtil.getIData(pipeline.getCursor(), "taskDataInput");
		IDataMap datos = new IDataMap(taskData);	
		IData headers = IDataUtil.getIData(pipeline.getCursor(), "headers");
		
		for (Entry<String, Object> entry : datos.entrySet()){
		    IData child = (IData) entry.getValue();
		    IDataMap hijos = new IDataMap(child);
		    for (Entry<String, Object> entry2 : hijos.entrySet() ){
		if (entry2.getKey().toLowerCase().equals("dt_authenticate")) {
		IData dt_auth = headers;
		
		//IData factory = IDataFactory.create();
		
		//IDataUtil.put(factory.getCursor(), "dt_authenticate", dt_auth);
		IDataUtil.put(((IData) entry.getValue()).getCursor(), entry2.getKey(), dt_auth);
		//IDataUtil.put(((IData) entry2.getValue()).getCursor(), entry2.getKey(), taskData);
		IDataUtil.remove(pipeline.getCursor(), "headers");
		IDataUtil.remove(pipeline.getCursor(), "dt_authenticate");
		}
		    }
		
		}
		// --- <<IS-END>> ---

                
	}



	public static final void UT_BooleanToString (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(UT_BooleanToString)>> ---
		// @sigtype java 3.5
		// [i] object:0:required input
		// [o] field:0:required output
		Boolean input = IDataUtil.getBoolean(pipeline.getCursor(), "input");
		if (input == null)
		    IDataUtil.put(pipeline.getCursor(), "ouput", false);
		IDataUtil.put(pipeline.getCursor(), "output", ""+input);
		// --- <<IS-END>> ---

                
	}



	public static final void UT_BuildFromToken (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(UT_BuildFromToken)>> ---
		// @sigtype java 3.5
		// [i] field:1:required valueList
		// [o] record:0:required params
		String[] lista = IDataUtil.getStringArray(pipeline.getCursor(), "valueList");
		IData output = IDataFactory.create();
		
		for (String item : lista){
		    String [] values = item.split("=");
		    IDataUtil.put(output.getCursor(), values[0], values[1]);
		}
		IDataUtil.put(pipeline.getCursor(), "params", output);
		output.getCursor().destroy();
		// --- <<IS-END>> ---

                
	}



	public static final void UT_CalcularDuracion (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(UT_CalcularDuracion)>> ---
		// @sigtype java 3.5
		// [i] field:0:required startDate
		// [i] field:0:required endDate
		// [o] field:0:required durationFormatted
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		String startDateStr = IDataUtil.getString(cursor, "startDate");
		String endDateStr = IDataUtil.getString(cursor, "endDate");
		
		if (startDateStr == null || startDateStr.trim().isEmpty()) {
		//throw new ServiceException("El campo startDate es requerido");
		}
		
		if (endDateStr == null || endDateStr.trim().isEmpty()) {
		//throw new ServiceException("El campo endDate es requerido");
		}
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS");
		
		LocalDateTime startDate = LocalDateTime.parse(startDateStr, formatter);
		LocalDateTime endDate = LocalDateTime.parse(endDateStr, formatter);
		
		long durationMs = ChronoUnit.MILLIS.between(startDate, endDate);
		
		double durationSeconds = durationMs / 1000.0;
		
		DecimalFormat df = new DecimalFormat("0.###");
		String durationFormatted = df.format(durationSeconds) + "s";
		
		IDataUtil.put(cursor, "durationMs", String.valueOf(durationMs));
		IDataUtil.put(cursor, "durationSeconds", String.valueOf(durationSeconds));
		IDataUtil.put(cursor, "durationFormatted", durationFormatted);
		
		} catch (Exception e) {
		//throw new ServiceException("Error calculando duraci\u00F3n: " + e.getMessage());
		} finally {
		cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void UT_CleanEmptyFields (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(UT_CleanEmptyFields)>> ---
		// @sigtype java 3.5
		// [i] record:0:required documento
		// [o] record:0:required documentoLimpio
		IDataCursor pipelineCursor = pipeline.getCursor();
		
		    IData documento = IDataUtil.getIData(
		pipelineCursor,
		"documento"
		    );
		
		    pipelineCursor.destroy();
		
		    // Obtener la estructura limpia
		    IData documentoLimpio = limpiarStringsVacios(documento);
		
		    // Si deseas que la salida del servicio sea el documento limpio:
		    IDataUtil.put(
		pipeline.getCursor(),
		"documentoLimpio",
		documentoLimpio
		    );
		// --- <<IS-END>> ---

                
	}



	public static final void UT_CompleteTask (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(UT_CompleteTask)>> ---
		// @sigtype java 3.5
		// [i] record:0:required request
		// [i] record:0:required taskData
		// [o] record:0:required TaskData
		IData canonicoInput = IDataUtil.getIData(pipeline.getCursor(), "request");
		IData taskDataSource = IDataUtil.getIData(pipeline.getCursor(), "taskData");
		 Map<String, Object> input = Map.of("inputDoc", IDataUtil.get(canonicoInput.getCursor(), "taskData"));
		Map<String, Object> taskData = Map.of("taskDoc", taskDataSource);
		//mergeIData(getFirstChildIData(new IDataMap(taskData)), getFirstChildIData(new IDataMap(canonicoInput)));
		IData merged = mergeToSingleIData(input, taskData);
		IDataUtil.put(pipeline.getCursor(), "TaskData", merged);
		// --- <<IS-END>> ---

                
	}



	public static final void UT_EqualsIgnoreCase (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(UT_EqualsIgnoreCase)>> ---
		// @sigtype java 3.5
		// [i] field:0:required str1
		// [i] field:0:required str2
		// [o] object:0:required isEquals
		String a = IDataUtil.getString(pipeline.getCursor(), "str1");
		String b = IDataUtil.getString(pipeline.getCursor(), "str2");
		
		if (a.equalsIgnoreCase(b))
		    IDataUtil.put(pipeline.getCursor(), "isEquals", true);
		else
		    IDataUtil.put(pipeline.getCursor(), "isEquals", false);
		// --- <<IS-END>> ---

                
	}



	public static final void UT_FileLogJson (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(UT_FileLogJson)>> ---
		// @sigtype java 3.5
		// [i] field:0:required line
		// [i] field:0:required type
		// [i] field:0:required color
		// [i] field:0:required header
		// [i] field:0:required code
		// [i] field:0:required time
		String type = IDataUtil.getString(pipeline.getCursor(), "type");
		String line = IDataUtil.getString(pipeline.getCursor(), "line");
		String header = IDataUtil.getString(pipeline.getCursor(), "header");
		String code = IDataUtil.getString(pipeline.getCursor(), "code");
		
		   try {
		    String logDir = "/opt/softwareag/IntegrationServer/instances/bancoppel/monitoring/BPM_Apolo";
		
		    DateTimeFormatter fileDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		
		    String fileName = "monitoring-" + LocalDate.now().format(fileDateFormat) + ".log";
		    Path logFile = Paths.get(logDir, fileName);
		
		    // Color seg\u00FAn tipo
		    String color;
		    if (type == null) {
		color = "#E5E7EB";
		    } else {
		switch (type.toLowerCase()) {
		    case "http request": color = "#60A5FA"; break;
		    case "http response": color = "#86EFAC"; break;
		    case "info": color = "#C084FC"; break;
		    case "error": color = "#FCA5A5"; break;
		    case "webhook": color = "#FACC15"; break;
		    default: color = "#E5E7EB";
		}
		    }
		
		    String time = LocalDateTime.now().format(timeFormat);
		
		    // Escape JSON
		    String safeLine = (line == null ? "" : line)
		    .replace("\\", "\\\\")
		    .replace("\"", "\\\"")
		    .replace("\n", "\\n")
		    .replace("\r", "\\r")
		    .replace("\t", "\\t");
		
		    String safeType = (type == null ? "" : type).replace("\"", "\\\"");
		    String safeHeader = (header == null ? "" : header).replace("\"", "\\\"");
		    String safeCode = (code == null ? "" : code).replace("\"", "\\\"");
		
		    String json = "{"
		    + "\"line\":\"" + safeLine + "\","
		    + "\"type\":\"" + safeType + "\","
		    + "\"color\":\"" + color + "\","
		    + "\"header\":\"" + safeHeader + "\","
		    + "\"code\":\"" + safeCode + "\","
		    + "\"time\":\"" + time + "\""
		    + "}";
		
		    //  CLAVE: APPEND + CREATE
		   
		    BufferedWriter writer = new BufferedWriter(
		    new FileWriter(logDir, true)
		    );
		
		    writer.write(json);
		    writer.newLine();
		
		    writer.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void UT_GetFlowName (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(UT_GetFlowName)>> ---
		// @sigtype java 3.5
		// [i] field:0:required index
		// [o] field:0:required flowName
		String serviceName = null; 
		String index = IDataUtil.getString(pipeline.getCursor(), "index");
		Stack callStack = InvokeState.getCurrentState().getCallStack();  
		int size = callStack.size();  
		 
		if (size >= 2) {  
		 NSService myService = (NSService) callStack.elementAt (size - (index == null ? 3 : Integer.parseInt(index)));  
		 serviceName = myService.getNSName().getFullName();  
		}  
		 
		// pipeline out 
		IDataCursor pipelineCursor = pipeline.getCursor(); 
		IDataUtil.put(pipelineCursor, "flowName", serviceName); 
		pipelineCursor.destroy();
		// --- <<IS-END>> ---

                
	}



	public static final void UT_IDataXMLCoder (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(UT_IDataXMLCoder)>> ---
		// @sigtype java 3.5
		// [i] field:0:required xml
		// [o] record:0:required document
		IDataCursor pipelineCursor = pipeline.getCursor();
		
		    String xml = IDataUtil.getString(pipelineCursor, "xml");
		    pipelineCursor.destroy();
		
		    IDataXMLCoder coder = new IDataXMLCoder();
		
		    IData data = null;
		    try {
		data = coder.decode(
		    new ByteArrayInputStream(
		xml.getBytes(StandardCharsets.UTF_8)
		    )
		);
		    } catch (InvalidDatatypeException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		    }
		
		    pipelineCursor = pipeline.getCursor();
		    IDataUtil.put(pipelineCursor, "document", data);
		    pipelineCursor.destroy();
		// --- <<IS-END>> ---

                
	}



	public static final void UT_MaxIteration (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(UT_MaxIteration)>> ---
		// @sigtype java 3.5
		// [i] field:0:required currentMax
		// [i] record:0:required instanceStep
		// [o] field:0:required maxIteration
		IData[] instanceStep = IDataUtil.getIDataArray(pipeline.getCursor(), "instanceStep");
		
		int maxIteration = (int) IDataUtil.get(instanceStep[0].getCursor(), "STEPITERATION");
		for (IData obt : instanceStep){
		int iterationN = (int) IDataUtil.get(obt.getCursor(), "STEPITERATION");
		
		if (iterationN > maxIteration){
		maxIteration = iterationN;
		}
		}
		IDataUtil.put(pipeline.getCursor(), "maxIteration", maxIteration);
		// --- <<IS-END>> ---

                
	}



	public static final void UT_MilisecondsTimeToDate (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(UT_MilisecondsTimeToDate)>> ---
		// @sigtype java 3.5
		// [i] field:0:required miliseconds
		// [i] field:0:required pattern {"yyyy-MM-dd","dd-MM-yyyy"}
		// [o] field:0:required dateConverted
		long timestamp = Long.parseLong(IDataUtil.getString(pipeline.getCursor(), "miliseconds"));
		String patter = IDataUtil.getString(pipeline.getCursor(), "pattern");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patter);
		
		String fecha = Instant.ofEpochMilli(timestamp)
		.atZone(ZoneId.systemDefault())
		.format(formatter);
		
		IDataUtil.put(pipeline.getCursor(), "dateConverted", fecha);
		// --- <<IS-END>> ---

                
	}



	public static final void UT_RefreshField (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(UT_RefreshField)>> ---
		// @sigtype java 3.5
		// [i] record:0:required ParentDocument
		// [i] field:0:required keyParent
		// [i] field:0:required keyValue
		// [i] field:0:required value
		// [i] object:0:required returnMergedItem
		// [o] record:0:required RefreshDocument
		// [o] object:0:required itemRefreshed
		String[] paths = IDataUtil.getStringArray(pipeline.getCursor(), "path");
		IDataCursor cursor = IDataUtil.getIData(pipeline.getCursor(), "ParentDocument").getCursor();
		//cursor = IDataUtil.getIData(cursor, "TaskData").getCursor();
		
		while (cursor.next()){
		   String key = cursor.getKey();
		   Object value = cursor.getValue();
		   
		   if (value instanceof IData) {
		IData child = (IData) value;
		
		// second child - BB
		
		IDataCursor cursorChild  = child.getCursor();
		
		while (cursorChild.next()){
		String keyChild = cursorChild.getKey();
		Object valueChild = cursorChild.getValue();
		
		if (valueChild instanceof IData){
		//IDataUtil.put(pipeline.getCursor(), keyChild, valueChild);
		
		 // find keyParent
		String keyParent = IDataUtil.getString(pipeline.getCursor(), "keyParent");
		if ( keyChild.equalsIgnoreCase(keyParent)){
		
		
		IData childData = (IData) valueChild;
		String toReplace = IDataUtil.getString(pipeline.getCursor(), "value");
		IDataUtil.remove(childData.getCursor(), "token");
		IDataUtil.put(childData.getCursor(), IDataUtil.getString(pipeline.getCursor(), "keyValue"), toReplace);
		
		boolean preserve = IDataUtil.getBoolean(pipeline.getCursor(), "returnMergedItem");
		if (preserve)
		IDataUtil.put(pipeline.getCursor(), "itemRefreshed", childData);
		}
		
		}
		
		}
		   }
		}
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



	public static final void UT_RetrieveDocumentFromBB (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(UT_RetrieveDocumentFromBB)>> ---
		// @sigtype java 3.5
		// [i] field:0:required path
		// [i] record:0:required document
		// [o] object:0:required documentFounded
		IDataCursor pipelineCursor = pipeline.getCursor();
		
		try {
		IData taskData = (IData) IDataUtil.get(pipelineCursor, "TaskData");
		
		String path = IDataUtil.getString(pipelineCursor, "path");
		
		if (taskData == null) {
		throw new ServiceException("TaskData es null");
		}
		
		if (path == null || path.trim().isEmpty()) {
		throw new ServiceException("path es null o vac\u00EDo");
		}
		
		String[] paths = path.trim().split(";");
		
		IData finalResult = null;
		
		for (String currentPath : paths) {
		
		currentPath = currentPath.trim();
		
		if (currentPath.isEmpty()) {
		continue;
		}
		
		String[] parts = currentPath.split("/");
		
		IData result = findAndBuild(
		    taskData,
		    parts,
		    0
		);
		
		if (result != null) {
		
		if (finalResult == null) {
		    finalResult = result;
		} else {
		    mergeIDataSteps(finalResult, result);
		}
		}
		}
		
		if (finalResult == null) {
		throw new ServiceException(
		"No se encontro ningun path: " + path
		);
		}
		
		IDataUtil.put(
		pipelineCursor,
		"documentFounded",
		finalResult
		);
		
		} finally {
		pipelineCursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void UT_StringToBoolean (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(UT_StringToBoolean)>> ---
		// @sigtype java 3.5
		// [i] field:0:required object
		// [o] object:0:required boolean
		try {
		String str = IDataUtil.getString(pipeline.getCursor(), "object");
		if (str!=null){
		boolean result = Boolean.parseBoolean(str);
		IDataUtil.put(pipeline.getCursor(), "boolean", result);
		}
		}catch(Exception e){
		IDataUtil.put(pipeline.getCursor(), "error", e.getMessage());
		}
		// --- <<IS-END>> ---

                
	}



	public static final void UT_ThrowException (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(UT_ThrowException)>> ---
		// @sigtype java 3.5
		// [i] field:0:required message
		String mesage = IDataUtil.getString(pipeline.getCursor(), "message");
		throw new RuntimeException(mesage);
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

	// --- <<IS-START-SHARED>> ---
	    public static IData limpiarStringsVacios(IData data) {
	
	    if (data == null) {
	        return null;
	    }
	
	    IDataCursor cursor = data.getCursor();
	
	    while (cursor.next()) {
	
	        String key = cursor.getKey();
	        Object value = cursor.getValue();
	
	        if (value instanceof String) {
	
	            String texto = (String) value;
	
	            // Eliminar campo si est\u00E1 vac\u00EDo o contiene solo espacios
	            if (texto.trim().isEmpty()) {
	                cursor.delete();
	            }
	
	        } else if (value instanceof IData) {
	
	            // Limpiar documento IData anidado
	            IData limpio = limpiarStringsVacios((IData) value);
	
	            // Mantener el documento limpio en la misma clave
	            cursor.setValue(limpio);
	
	        } else if (value instanceof IData[]) {
	
	            // Limpiar arreglos de documentos IData
	            IData[] documentos = (IData[]) value;
	
	            for (int i = 0; i < documentos.length; i++) {
	                documentos[i] = limpiarStringsVacios(documentos[i]);
	            }
	
	            cursor.setValue(documentos);
	        }
	    }
	
	    cursor.destroy();
	
	    return data;
	}
	    
	    private static void mergeIDataSteps(
	        IData target,
	        IData source) {
	
	    if (target == null || source == null) {
	        return;
	    }
	
	    IDataCursor sourceCursor = source.getCursor();
	
	    try {
	
	        while (sourceCursor.next()) {
	
	            String key = sourceCursor.getKey();
	            Object value = sourceCursor.getValue();
	
	            if (key == null) {
	                continue;
	            }
	
	            IDataCursor targetCursor = target.getCursor();
	
	            try {
	
	                boolean found = false;
	
	                while (targetCursor.next()) {
	
	                    if (key.equals(targetCursor.getKey())) {
	
	                        Object existingValue =
	                                targetCursor.getValue();
	
	                        /*
	                         * Si ambos son IData,
	                         * combinamos sus hijos.
	                         */
	                        if (existingValue instanceof IData &&
	                            value instanceof IData) {
	
	                            mergeIData(
	                                    (IData) existingValue,
	                                    (IData) value
	                            );
	
	                        } else {
	
	                            targetCursor.setValue(value);
	                        }
	
	                        found = true;
	                        break;
	                    }
	                }
	
	                /*
	                 * Si el elemento no existe en el target,
	                 * lo agregamos.
	                 */
	                if (!found) {
	
	                    IDataCursor insertCursor = target.getCursor();
	
	                    try {
	                        IDataUtil.put(
	                                insertCursor,
	                                key,
	                                value
	                        );
	                    } finally {
	                        insertCursor.destroy();
	                    }
	                }
	
	            } finally {
	                targetCursor.destroy();
	            }
	        }
	
	    } finally {
	        sourceCursor.destroy();
	    }
	}
	
	private static IData findAndBuild(
	            IData data,
	            String[] path,
	            int index) {
	
	        if (data == null || index >= path.length) {
	            return null;
	        }
	
	        IDataCursor cursor = data.getCursor();
	
	        try {
	
	            while (cursor.next()) {
	
	                String key = cursor.getKey();
	                Object value = cursor.getValue();
	
	                if (key == null) {
	                    continue;
	                }
	
	                /*
	                 * Encontramos el elemento actual
	                 * del path.
	                 */
	                if (key.equals(path[index])) {
	
	                    /*
	                     * \u00DAltimo elemento del path.
	                     *
	                     * Ejemplo:
	                     *
	                     * dt_expedienteDigital/documentation
	                     *                                
	                     */
	                    if (index == path.length - 1) {
	
	                        IData result =
	                                IDataFactory.create();
	
	                        IDataCursor resultCursor =
	                                result.getCursor();
	
	                        try {
	                            IDataUtil.put(
	                                    resultCursor,
	                                    key,
	                                    value
	                            );
	                        } finally {
	                            resultCursor.destroy();
	                        }
	
	                        return result;
	                    }
	
	                    /*
	                     * Todav\u00EDa faltan elementos.
	                     *
	                     * Bajamos al siguiente nivel.
	                     */
	                    IData childResult =
	                            findChild(
	                                    value,
	                                    path,
	                                    index + 1
	                            );
	
	                    if (childResult != null) {
	
	                        /*
	                         * Reconstruimos la estructura.
	                         *
	                         * childResult:
	                         *
	                         * documentation
	                         *
	                         * se convierte en:
	                         *
	                         * dt_expedienteDigital
	                         *    documentation
	                         */
	                        IData result =
	                                IDataFactory.create();
	
	                        IDataCursor resultCursor =
	                                result.getCursor();
	
	                        try {
	                            IDataUtil.put(
	                                    resultCursor,
	                                    key,
	                                    childResult
	                            );
	                        } finally {
	                            resultCursor.destroy();
	                        }
	
	                        return result;
	                    }
	                }
	
	                /*
	                 * No encontramos el elemento actual
	                 * en este nivel.
	                 *
	                 * Buscamos recursivamente dentro
	                 * de cualquier IData.
	                 */
	                if (value instanceof IData) {
	
	                    IData result =
	                            findAndBuild(
	                                    (IData) value,
	                                    path,
	                                    index
	                            );
	
	                    if (result != null) {
	                        return result;
	                    }
	                }
	
	                /*
	                 * Soporte para IData[]
	                 */
	                if (value instanceof IData[]) {
	
	                    IData[] array = (IData[]) value;
	
	                    for (IData item : array) {
	
	                        IData result =
	                                findAndBuild(
	                                    item,
	                                    path,
	                                    index
	                                );
	
	                        if (result != null) {
	                            return result;
	                        }
	                    }
	                }
	            }
	
	        } finally {
	            cursor.destroy();
	        }
	
	        return null;
	    }
	
	
	    /**
	     * Contin\u00FAa la b\u00FAsqueda dentro del elemento
	     * que ya coincidi\u00F3 con una parte del path.
	     */
	    private static IData findChild(
	            Object value,
	            String[] path,
	            int index) {
	
	        if (value instanceof IData) {
	
	            return findAndBuild(
	                    (IData) value,
	                    path,
	                    index
	            );
	        }
	
	        if (value instanceof IData[]) {
	
	            IData[] array = (IData[]) value;
	
	            for (IData item : array) {
	
	                IData result =
	                        findAndBuild(
	                            item,
	                            path,
	                            index
	                        );
	
	                if (result != null) {
	                    return result;
	                }
	            }
	        }
	
	        return null;
	    }
	
	
		
		private static String escapeJson(String value) {
		    if (value == null) return "";
	
	    return value
	            .replace("\\", "\\\\")
	            .replace("\"", "\\\"")
	            .replace("\r", "\\r")
	            .replace("\n", "\\n")
	            .replace("\t", "\\t");
	}
	    
	    public static void mergeIData(IData target, IData source) {
	        if (target == null || source == null) return;
	
	        IDataCursor sourceCursor = source.getCursor();
	        IDataCursor targetCursor = target.getCursor();
	
	        try {
	            while (sourceCursor.next()) {
	                String key = sourceCursor.getKey();
	                Object sourceValue = sourceCursor.getValue();
	
	                if (targetCursor.first(key)) {
	                    Object existingValue = targetCursor.getValue();
	                    if (existingValue instanceof IData && sourceValue instanceof IData) {
	                        mergeIData((IData) existingValue, (IData) sourceValue);
	                    } else {
	                        targetCursor.setValue(sourceValue);
	                    }
	                } else {
	                    targetCursor.insertAfter(key, sourceValue);
	                }
	            }
	        } finally {
	            sourceCursor.destroy();
	            targetCursor.destroy();
	        }
	    }
	 
	 public static Map<String, Object> iDataToMap(IData idata) {
		    Map<String, Object> map = new HashMap<>();
		    if (idata == null) return map;
	
		    IDataCursor cursor = idata.getCursor();
		    try {
		        while (cursor.next()) {
		            String key = cursor.getKey();
		            Object value = cursor.getValue();
	
		            // Si el valor es otro IData (documento anidado), lo convertimos recursivamente
		            if (value instanceof IData) {
		                map.put(key, iDataToMap((IData) value));
		            } 
		            // Si es un arreglo de IData (lista de documentos)
		            else if (value instanceof IData[]) {
		                IData[] array = (IData[]) value;
		                Map<String, Object>[] subMaps = new Map[array.length];
		                for (int i = 0; i < array.length; i++) {
		                    subMaps[i] = iDataToMap(array[i]);
		                }
		                map.put(key, subMaps);
		            } 
		            // Si es un valor normal (String, Integer, etc.)
		            else {
		                map.put(key, value);
		            }
		        }
		    } finally {
		        cursor.destroy();
		    }
		    return map;
		}
	   private static void copyIData(IData source, IData target) {
	        IDataCursor src = source.getCursor();
	        IDataCursor tgt = target.getCursor();
	        try {
	            while (src.next()) {
	                String key = src.getKey();
	                Object value = src.getValue();
	
	                // Clonar recursivamente si es IData
	                if (value instanceof IData) {
	                    IData clone = IDataFactory.create();
	                    copyIData((IData) value, clone);
	                    tgt.insertAfter(key, clone);
	                } else {
	                    tgt.insertAfter(key, value);
	                }
	            }
	        } finally {
	            src.destroy();
	            tgt.destroy();
	        }
	    }
	   
	   
	   public static IData mergeToSingleIData(Map<String, Object> input, Map<String, Object> taskData) {
	        // Obtener los hijos IData
	        IData inputChild = getFirstChildIData(input);
	        IData taskChild = getFirstChildIData(taskData);
	
	        // Si alguno es nulo, retornamos el otro
	        if (inputChild == null && taskChild == null) return IDataFactory.create();
	        if (inputChild == null)
				try {
					return IDataUtil.deepClone(taskChild);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        if (taskChild == null)
				try {
					return IDataUtil.deepClone(inputChild);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
	        // Crear documento resultante
	        IData merged = IDataFactory.create();
	
	        // Copiar taskData en merged
	        copyIData(taskChild, merged);
	
	        // Fusionar input sobre merged
	        mergeIData(merged, inputChild);
	
	        return merged;
	    }
	   
	private static IData getFirstChildIData(Map<String, Object> map) {
	    if (map == null || map.isEmpty()) return null;
	    for (Object value : map.values()) {
	        if (value instanceof IData) {
	            return (IData) value;
	        }
	    }
	    return null;
	}
	private static boolean containsKey(IDataCursor cursor, String key) {
	    if (cursor.first(key)) {
	        cursor.first(); // Reiniciar cursor
	        return true;
	    }
	    cursor.first();
	    return false;
	}
	
	private static Object getValue(IDataCursor cursor, String key) {
	    if (cursor.first(key)) {
	        return cursor.getValue();
	    }
	    return null;
	}
	// --- <<IS-END-SHARED>> ---
}

