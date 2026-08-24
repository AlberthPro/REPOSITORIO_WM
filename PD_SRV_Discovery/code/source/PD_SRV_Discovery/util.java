package PD_SRV_Discovery;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import com.softwareag.util.IDataMap;
// --- <<IS-END-IMPORTS>> ---

public final class util

{
	// ---( internal utility methods )---

	final static util _instance = new util();

	static util _newInstance() { return new util(); }

	static util _cast(Object o) { return (util)o; }

	// ---( server methods )---




	public static final void UT_CastDateToNanoTime (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(UT_CastDateToNanoTime)>> ---
		// @sigtype java 3.5
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



	public static final void isInstanceOf (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(isInstanceOf)>> ---
		// @sigtype java 3.5
		// [i] object:0:required obt
		// [i] field:0:required type
		// [o] object:0:required return
		Object obt = IDataUtil.get(pipeline.getCursor(), "obt");
		String className = IDataUtil.getString(pipeline.getCursor(), "type");
		if ( obt.getClass().getName().toLowerCase() ==  className ){
		    IDataUtil.put(pipeline.getCursor(), "return", true);
		}else 
		    IDataUtil.put(pipeline.getCursor(), "return", false);
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
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

