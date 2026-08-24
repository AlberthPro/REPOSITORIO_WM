package PD_Pruebas;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.lang.ns.NSService;
import java.util.Stack;
import com.wm.app.b2b.server.InvokeState;
// --- <<IS-END-IMPORTS>> ---

public final class util

{
	// ---( internal utility methods )---

	final static util _instance = new util();

	static util _newInstance() { return new util(); }

	static util _cast(Object o) { return (util)o; }

	// ---( server methods )---




	public static final void UT_GetFlowName (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(UT_GetFlowName)>> ---
		// @sigtype java 3.5
		String serviceName = null; 
		Stack callStack = InvokeState.getCurrentState().getCallStack();  
		int size = callStack.size();  
		 
		if (size >= 2) {  
		 NSService myService = (NSService) callStack.elementAt (size - 2);  
		 serviceName = myService.getNSName().getFullName();  
		}
		 
		// pipeline out 
		IDataCursor pipelineCursor = pipeline.getCursor(); 
		IDataUtil.put(pipelineCursor, "callStack", callStack.get(0)); 
		IDataUtil.put(pipelineCursor, "serviceName", serviceName); 
		//pipelineCursor.destroy();
		// --- <<IS-END>> ---

                
	}
}

