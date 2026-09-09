package TEST1;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.app.b2b.server.ServiceThread;
import com.wm.app.prt.FragmentIdentifier;
import com.wm.app.prt.IntegrationServer;
import com.wm.app.prt.ModelIndexer;
import com.wm.app.prt.ModelManager;
import com.wm.app.prt.model.ModelIdentifier;
import com.wm.app.prt.model.PRTFragmentDef;
import com.wm.app.prt.test.TestModels;
import com.wm.app.prt.test.TestStorage;
// --- <<IS-END-IMPORTS>> ---

public final class srv

{
	// ---( internal utility methods )---

	final static srv _instance = new srv();

	static srv _newInstance() { return new srv(); }

	static srv _cast(Object o) { return (srv)o; }

	// ---( server methods )---




	public static final void handleProcess (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(handleProcess)>> ---
		// @sigtype java 3.5
		// [o] object:0:required document
		IData document = IDataFactory.create();
		
		IDataUtil.put(document.getCursor(), "ProcessInstanceID", "ff694f37-260e-461b-9f32-0138237258e5");
		IDataUtil.put(document.getCursor(), "ProcessModelID", "FP_ApoloTDC");
		IDataUtil.put(document.getCursor(), "ProcessModelVersion", "2");
		//IDataUtil.put(document.getCursor(), "DocName", "BC_SRV_Apolo.docs:dt_sic");
		//IDataUtil.put(document.getCursor(), "DocType", "IData");
		/*IData DocData = IDataFactory.create();
		IDataCursor cursor = DocData.getCursor();
		IDataUtil.put(cursor, "ProcessInstanceID", "addee73c-11ef-4da5-b585-f67830ac1bad");
		IDataUtil.put(cursor, "ProcessModelID", "BC_BPM_Apolo/SP_ValidacionSIC");
		IDataUtil.put(cursor, "ProcessModelVersion", "1");
		IDataUtil.put(cursor, "Status", 2);*/
		
		//IDataUtil.put(document.getCursor(), "DocData", DocData);
		IDataUtil.put(document.getCursor(), "TargetStepID", "S153");
		IDataUtil.put(document.getCursor(), "Index", "-1");
		IDataUtil.put(document.getCursor(), "Status", "2");
		IDataUtil.put(document.getCursor(), "ErrorData", IDataFactory.create());
		
		IDataUtil.put(pipeline.getCursor(), "document", document);
		
		
		PRTFragmentDef[] fragment = TestModels.getISReceive();	
		
		
		ModelIndexer.add(fragment[0]);
		ModelIdentifier[] list = ModelIndexer.listAllModelIDs();
		
		
		PRTFragmentDef[] fragment2 = TestModels.getSubprocessTest();
		IDataUtil.put(pipeline.getCursor(), "fragment2", fragment2);
		
		PRTFragmentDef out = new PRTFragmentDef();
		  
		
		IDataUtil.put(pipeline.getCursor(), "data", out);
		
		/*ModelIdentifier model = new ModelIdentifier("BC_BPM_Apolo/FP_ApoloTDC", "1");
		
		ModelIndexer.setEndpointReference(model, "some link", DocData);
		ModelIndexer.getEndpointReference(model, "some link", "Administrator");
		ModelIndexer.getFragmentForStep(model, "S153");
		
		ModelIdentifier[] list = ModelIndexer.listAllModelIDs();
		
		String trg = "BC_BPM_Apolo.SP_ValidacionSIC.Default:subscriptionTrigger";
		String[] ids =
		    ModelIndexer.fragIdsForModel(model);
		
		IDataUtil.put(pipeline.getCursor(), "list", list);
		//IDataUtil.put(pipeline.getCursor(), "fragmet", ids);*/
		// --- <<IS-END>> ---

                
	}



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

