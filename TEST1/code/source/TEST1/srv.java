package TEST1;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.app.b2b.server.ServiceThread;
// --- <<IS-END-IMPORTS>> ---

public final class srv

{
	// ---( internal utility methods )---

	final static srv _instance = new srv();

	static srv _newInstance() { return new srv(); }

	static srv _cast(Object o) { return (srv)o; }

	// ---( server methods )---




	public static final void publish (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(publish)>> ---
		// @sigtype java 3.5
		// [i] recref:0:required OnboardingClient processes.PP01:OnboardingClient
		IData input = IDataUtil.getIData(pipeline.getCursor(), "OnboardingClient");
		String docType = "processes.PP01:OnboardingClient";
		
		IData document = IDataFactory.create();
		IDataUtil.put(document.getCursor(), "documentTypeName", docType);
		IDataUtil.put(document.getCursor(), "document", input);
		
		try {
		    ServiceThread out = Service.doThreadInvoke("pub.publish", "publish", document, (long) 1000);
		    
		    IDataUtil.put(pipeline.getCursor(), "result", out.getIData());
		} catch (Exception e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		    IDataUtil.put(pipeline.getCursor(), "error", e.getMessage() + e.getCause() + e.getCause());
		}
		// --- <<IS-END>> ---

                
	}
}

