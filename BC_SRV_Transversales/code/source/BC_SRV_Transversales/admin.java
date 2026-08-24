package BC_SRV_Transversales;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
// --- <<IS-END-IMPORTS>> ---

public final class admin

{
	// ---( internal utility methods )---

	final static admin _instance = new admin();

	static admin _newInstance() { return new admin(); }

	static admin _cast(Object o) { return (admin)o; }

	// ---( server methods )---




	public static final void UT_CastDateToNanoTime (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(UT_CastDateToNanoTime)>> ---
		// @sigtype java 3.5
		// [i] field:0:required fecha
		// [o] field:0:required nanoTime
		String fechaString = IDataUtil.getString(pipeline.getCursor(), "fecha");
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS");
		
		LocalDateTime fecha = LocalDateTime.parse(fechaString, formatter);
		
		long epochMillis = fecha
		.atZone(ZoneId.systemDefault())
		.toInstant()
		.toEpochMilli();
		
		String epochString = String.valueOf(epochMillis);
		IDataUtil.put(pipeline.getCursor(), "nanoTime", epochString);
		// --- <<IS-END>> ---

                
	}



	public static final void ut_debug (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(ut_debug)>> ---
		// @sigtype java 3.5
		IData input = IDataFactory.create();
		IDataUtil.put(input.getCursor(), "todate", "2025-08-28");
		IDataUtil.put(input.getCursor(), "archiveAction", "ARCHIVE");
		//IDataUtil.put(input.getCursor(), "status", "COMPLETED");
		IDataUtil.put(input.getCursor(), "batchSize", "10");
		IDataUtil.put(input.getCursor(), "modelId", new String[]{"BC_BPM_Apolo/FP_ApoloN4"});
		
		try {
		IData output = Service.doInvoke(
		"pub.monitor.archive",
		"processArchive",
		input
		);
		IDataUtil.put(pipeline.getCursor(), "output", output);
		//throw new RuntimeException();
		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		//IDataUtil.put(pipeline.getCursor(), "erno", e.getLocalizedMessage());
		IDataUtil.put(pipeline.getCursor(), "erno", e.getStackTrace()[4]);
		}
		// --- <<IS-END>> ---

                
	}
}

