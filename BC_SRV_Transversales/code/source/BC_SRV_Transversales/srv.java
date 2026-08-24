package BC_SRV_Transversales;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.lang.ns.NSName;
import com.wm.lang.ns.NSNode;
import com.wm.lang.ns.NSRecord;
import com.wm.lang.ns.NSService;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.Deque;
import com.wm.app.b2b.server.ns.NSNodeUtil;
import com.wm.app.b2b.server.ns.Namespace;
// --- <<IS-END-IMPORTS>> ---

public final class srv

{
	// ---( internal utility methods )---

	final static srv _instance = new srv();

	static srv _newInstance() { return new srv(); }

	static srv _cast(Object o) { return (srv)o; }

	// ---( server methods )---




	public static final void callApiMulesoft (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(callApiMulesoft)>> ---
		// @sigtype java 3.5
		// [i] field:0:required IS_SERVICE
		// [i] record:0:required REQUEST
		// [i] record:0:required HEADERS
		// [i] field:0:required URL
		// [i] field:0:required ON_ERROR {"EXIT_FLOW","KEEP_ALIVE"}
		// [o] record:0:required response
		String serviceName = IDataUtil.getString(pipeline.getCursor(), "IS_SERVICE");
		
		
		
		Namespace ns;
		NSName nsName = NSName.create(serviceName);
		NSNode node = Namespace.current().getNode(nsName);
		
		if (node == null) {
		throw new ServiceException("No existe el servicio: " + serviceName);
		}
		
		if (!(node instanceof NSService)) {
		throw new ServiceException("El path existe, pero no es un servicio: " + serviceName);
		}
		
		NSService service = (NSService) node;
		
		NSRecord inputSignature = service.getSignature().getInput();
		NSRecord outputSignature = service.getSignature().getOutput();
		
		// Aqu\u00ED puedes inspeccionar inputSignature y outputSignature
		// dependiendo de c\u00F3mo quieras imprimirlos o convertirlos a IData.
		
		
		//IDataUtil.put(pipeline.getCursor(), "signatureOut", outputSignature);
		
		String[] inputFields = new String[inputSignature.getFieldCount()];
		for ( int i=0;i<inputSignature.getFieldCount();i++ ){
		IData[] hasChilds = IDataUtil.getIDataArray(inputSignature.getField(i).getAsData().getCursor(), "rec_fields");
		if (hasChilds.length == 1){
		inputFields[i] = IDataUtil.getString(hasChilds[0].getCursor(), "field_name");
		}
		}
		
		IData inputMulesoft = IDataFactory.create();
		IDataUtil.put(inputMulesoft.getCursor(), inputFields[0], IDataUtil.getIData(pipeline.getCursor(), "HEADERS"));
		IDataUtil.put(inputMulesoft.getCursor(), inputFields[1], IDataUtil.getIData(pipeline.getCursor(), "REQUEST"));
		
		///
		//IDataUtil.put(pipeline.getCursor(), "inputMulesfot", inputMulesoft);
		///
		IData httpClient = IDataFactory.create();
		IDataUtil.put(httpClient.getCursor(), "genericRq", IDataUtil.getIData(inputMulesoft.getCursor(), inputFields[1]));
		IDataUtil.put(httpClient.getCursor(), "genericHd", IDataUtil.getIData(inputMulesoft.getCursor(), inputFields[0]));
		
		IData requestInfo = IDataFactory.create();
		IDataUtil.put(requestInfo.getCursor(), "url", IDataUtil.getString(pipeline.getCursor(), "URL"));
		String method = serviceName.substring(serviceName.lastIndexOf("_")+1, serviceName.length());
		if (method == null || method.equals("")){
		throw new RuntimeException("No method found");
		}
		IDataUtil.put(requestInfo.getCursor(), "method", method.toLowerCase());
		
		IDataUtil.put(httpClient.getCursor(), "requestInfo", requestInfo);
		
		IDataUtil.put(pipeline.getCursor(), "UT_HTTP_CLIENT", httpClient);
		inputMulesoft.getCursor().destroy();
		requestInfo.getCursor().destroy();
		
		try {
		IData output = Service.doInvoke("BC_SRV_Apolo.util", "UT_HTTP_Client", httpClient);
		String responseName = IDataUtil.getString(outputSignature.getField(0).getAsData().getCursor(), "field_name");
		boolean hasError = IDataUtil.getBoolean(output.getCursor(), "hasError");
		//IDataUtil.put(pipeline.getCursor(), "hasError", hasError);
		
		if (hasError){
		
		IData jsonToIData = IDataFactory.create();
		IDataUtil.put(jsonToIData.getCursor(), "jsonString", IDataUtil.getString(output.getCursor(), "stringResponse"));
		IData castDoc = Service.doInvoke("pub.json", "jsonStringToDocument",jsonToIData);
		
		IDataUtil.put(pipeline.getCursor(), "commonErrorRs", IDataUtil.getIData(castDoc.getCursor(), "document"));
		IDataUtil.put(pipeline.getCursor(), "strHd", IDataUtil.getString(output.getCursor(), "strHd"));
		IDataUtil.put(pipeline.getCursor(), "strRq", IDataUtil.getString(output.getCursor(), "strRq"));
		
		String onError = IDataUtil.getString(pipeline.getCursor(), "ON_ERROR");
		if (onError.equals("EXIT_FLOW")){
		IData header = IDataUtil.getIData(output.getCursor(), "header");
		throw new RuntimeException("status: "+ IDataUtil.getString(header.getCursor(), "status")+ ", statusMessage:"+ IDataUtil.getString(header.getCursor(), "statusMessage"));
		}
		}
		else{
		IDataUtil.put(pipeline.getCursor(), responseName, IDataUtil.getIData(output.getCursor(), responseName));
		IDataUtil.put(pipeline.getCursor(), "header", IDataUtil.getIData(output.getCursor(), "header"));
		}
		
		//removing fields out
		IDataUtil.remove(pipeline.getCursor(), "IS_SERVICE");
		IDataUtil.remove(pipeline.getCursor(), "HEADERS");
		IDataUtil.remove(pipeline.getCursor(), "REQUEST");
		IDataUtil.remove(pipeline.getCursor(), "UT_HTTP_CLIENT");
		
		} catch (Exception e) {
		// TODO Auto-generated catch block
		 throw new RuntimeException(e.getMessage());
		}
		// --- <<IS-END>> ---

                
	}



	public static final void getFileLogService (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getFileLogService)>> ---
		// @sigtype java 3.5
		// [i] field:0:required filePath
		// [i] field:0:required maxLines
		// [o] field:0:required jsonResponse
		IDataCursor cursor = pipeline.getCursor();
		
		    String filePath = IDataUtil.getString(cursor, "filePath");
		    String maxLinesStr = IDataUtil.getString(cursor, "maxLines");
		
		    int maxLines = 1000;
		
		    if (maxLinesStr != null && !maxLinesStr.trim().isEmpty()) {
		maxLines = Integer.parseInt(maxLinesStr);
		    }
		
		    cursor.destroy();
		
		    if (filePath == null || filePath.trim().isEmpty()) {
		throw new ServiceException("El par\u00E1metro filePath es requerido");
		    }
		
		    Deque<String> lines = new ArrayDeque<String>();
		
		    try (BufferedReader reader = new BufferedReader(
		    new InputStreamReader(
		    new FileInputStream(filePath),
		    StandardCharsets.UTF_8
		    )
		    )) {
		
		String line;
		
		while ((line = reader.readLine()) != null) {
		    if (lines.size() == maxLines) {
		lines.removeFirst();
		    }
		
		    lines.addLast(line);
		}
		
		    } catch (Exception e) {
		throw new ServiceException("Error leyendo archivo log: " + e.getMessage());
		    }
		
		    StringBuilder json = new StringBuilder();
		    json.append("{");
		    json.append("\"filePath\":\"").append(escapeJson(filePath)).append("\",");
		    json.append("\"totalLines\":").append(lines.size()).append(",");
		    json.append("\"lines\":[");
		
		    int index = 0;
		
		    for (String line : lines) {
		if (index > 0) {
		    json.append(",");
		}
		
		json.append("\"").append(escapeJson(line)).append("\"");
		index++;
		    }
		
		    json.append("]");
		    json.append("}");
		
		    IDataCursor outCursor = pipeline.getCursor();
		    IDataUtil.put(outCursor, "jsonResponse", json.toString());
		    outCursor.destroy();
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	    private static String escapeJson(String value) {
	    if (value == null) return "";
	
	    return value
	            .replace("\\", "\\\\")
	            .replace("\"", "\\\"")
	            .replace("\r", "\\r")
	            .replace("\n", "\\n")
	            .replace("\t", "\\t");
	}
	// --- <<IS-END-SHARED>> ---
}

