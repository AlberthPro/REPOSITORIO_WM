package AF_REG_TraspasoAfore.util;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.lang.ns.NSService;
import com.wm.app.b2b.server.InvokeState;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;
// --- <<IS-END-IMPORTS>> ---

public final class java

{
	// ---( internal utility methods )---

	final static java _instance = new java();

	static java _newInstance() { return new java(); }

	static java _cast(Object o) { return (java)o; }

	// ---( server methods )---




	public static final void getFlowName (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getFlowName)>> ---
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



	public static final void writeFileLogger (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(writeFileLogger)>> ---
		// @sigtype java 3.5
		// [i] field:0:required path
		// [i] field:0:required data
		// [o] field:0:required error
		String path = IDataUtil.getString(pipeline.getCursor(), "path");
		File log = new File(path);
		
		try{
		if(!log.exists()){
		System.out.println("to create a file");
		log.createNewFile();
		}
		
		FileWriter fileWriter = new FileWriter(log, true);
		
		String data = IDataUtil.getString(pipeline.getCursor(), "data");
		
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		bufferedWriter.write(data+",");
		
		bufferedWriter.close();
		
		System.out.println("Done");
		} catch(IOException e) {
		   IDataUtil.put(pipeline.getCursor(), "error", e);
		}
		// --- <<IS-END>> ---

                
	}
}

