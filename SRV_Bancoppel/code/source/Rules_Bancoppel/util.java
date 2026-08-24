package Rules_Bancoppel;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.app.b2b.server.streamprocessing.operations.InvokeService;
import com.softwareag.util.IDataMap;
import java.awt.geom.Path2D;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
// --- <<IS-END-IMPORTS>> ---

public final class util

{
	// ---( internal utility methods )---

	final static util _instance = new util();

	static util _newInstance() { return new util(); }

	static util _cast(Object o) { return (util)o; }

	// ---( server methods )---




	public static final void PointInPath (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(PointInPath)>> ---
		// @sigtype java 3.5
		// [i] field:0:required PolygonStr
		// [i] object:0:required x
		// [i] object:0:required y
		// [o] object:0:required inside
		String Polygon = IDataUtil.getString(pipeline.getCursor(), "PolygonStr");
		float x = Float.parseFloat(IDataUtil.getString(pipeline.getCursor(), "x"));
		float y  = Float.parseFloat(IDataUtil.getString(pipeline.getCursor(), "y"));
		
		Path2D path = new Path2D.Double();
		
		if(!Polygon.isEmpty()){
			
			String[] Coordinate= Polygon.split("],");
			for(int i=0;i< Coordinate.length;i++){
		        Coordinate[i]= Coordinate[i].trim().replace("[","");
		        Coordinate[i]= Coordinate[i].trim().replace("]","");
		        Coordinate[i]=Coordinate[i].trim();
			}
			
			
			for(int i=0;i< Coordinate.length;i++){
		        String [] point0= Coordinate[i].split(",");
		        float yp0=Float.parseFloat(point0[1]);
		        float xp0=Float.parseFloat(point0[0]);
		        
		        if(i==0){
		            path.moveTo(xp0,yp0);
		        }else{
		            path.lineTo(xp0,yp0);
		        }
			}
			 
			path.closePath();
		}
		
		IDataUtil.put(pipeline.getCursor(), "inside", path.contains(x, y));	
		// --- <<IS-END>> ---

                
	}



	public static final void StringDateToDate (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(StringDateToDate)>> ---
		// @sigtype java 3.5
		// [i] field:0:required dateStr
		// [o] object:0:required date
		String strDate = IDataUtil.getString(pipeline.getCursor(), "dateStr");
		validarFecha(strDate);
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		try {
		Date date = format.parse(strDate);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, 6);
		
		IDataUtil.put(pipeline.getCursor(), "date", calendar.getTime());
		} catch (ParseException e) {
		try {
		DateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");
		Date date2 = format2.parse(strDate);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date2);
		calendar.add(Calendar.HOUR_OF_DAY, 6);
		
		
		IDataUtil.put(pipeline.getCursor(), "date", calendar.getTime());
		} catch (Exception a) {
		IDataUtil.put(pipeline.getCursor(), "error", "El formato de la fecha es incorrecta"+a.getMessage());
		/*try {
		DateFormat format3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date3 = format3.parse(strDate);
		IDataUtil.put(pipeline.getCursor(), "date", date3);
		}catch (Exception t){
		
		}*/
		}
		}
		// --- <<IS-END>> ---

                
	}



	public static final void compareDates (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(compareDates)>> ---
		// @sigtype java 3.5
		// [i] object:0:required obt1
		// [i] object:0:required obtA
		// [i] object:0:required obt2
		// [i] object:0:required obtB
		// [o] object:0:required result
		Date date1 = (Date) IDataUtil.get(pipeline.getCursor(), "obt1");
		Date dateA = (Date) IDataUtil.get(pipeline.getCursor(), "obtA");
		
		Date date2 = (Date) IDataUtil.get(pipeline.getCursor(), "obt2");
		Date dateB = (Date) IDataUtil.get(pipeline.getCursor(), "obtB");
		
		if (dateA.after(date1) && dateB.before(date2)){
		IDataUtil.put(pipeline.getCursor(), "result", true);
		}else{
		IDataUtil.put(pipeline.getCursor(), "result", false);
		}
		// --- <<IS-END>> ---

                
	}



	public static final void validateDate (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(validateDate)>> ---
		// @sigtype java 3.5
		// [i] field:0:required dateStr
		String strDate = IDataUtil.getString(pipeline.getCursor(), "dateStr");
		boolean invalid = false;
		
		
		String[] items = strDate.split("/");
		
		if ( items[2].contains(":") ){
		items = strDate.split(" ");
		items = items[0].split("/");  	
		}
		
		if (items.length>2){
		    for ( String item : items ){
		boolean isNumber = item.trim().matches("\\d*");
		if (!isNumber || item.length()<2){
		    invalid = true;
		    throw new RuntimeException("La fecha "+strDate+" es invalida");
		}
		    }
		    if (Integer.parseInt(items[0])>31)
		invalid = true;
		    if (Integer.parseInt(items[1])>12)
		invalid = true;
		    
		    
		    if (items[2].length() < 4 || items[2].length() > 4 ){
		invalid = true;
		    }
		    
		 
		   
		}else
		    invalid = true;
		
		if (invalid){
		    IDataUtil.put(pipeline.getCursor(), "isValidaDate", false);
		    throw new RuntimeException("La fecha "+strDate+" es invalida !");
		}else
		    IDataUtil.put(pipeline.getCursor(), "isValidaDate", true);
		// --- <<IS-END>> ---

                
	}



	public static final void validateInput (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(validateInput)>> ---
		// @sigtype java 3.5
		// [i] field:0:required input
		// [o] field:0:required output
		IData input = IDataUtil.getIData(pipeline.getCursor(), "input");
		
		if (input == null)
		IDataUtil.put(pipeline.getCursor(), "output", " ");
		else IDataUtil.put(pipeline.getCursor(), "output", " ");
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	public static void validarFecha(String fecha) {
	    if (fecha == null || fecha.trim().isEmpty()) {
	        throw new IllegalArgumentException("la fecha es incorrecta.");
	    }
	
	    try {
	        // Formato: dd/MM/yyyy
	        String[] partes = fecha.split("/");
	
	        if (partes.length != 3) {
	            throw new IllegalArgumentException("la fecha es incorrecta.");
	        }
	
	        int mes = Integer.parseInt(partes[1]);
	        String anioStr = partes[2];
	        
	        if (mes < 1 || mes > 12) {
	            throw new IllegalArgumentException("la fecha ingresada es incorrecta: "+fecha);
	        }
	        
	        // Validaci\u00F3n del a\u00F1o (m\u00E1ximo 4 d\u00EDgitos y num\u00E9rico)
	        if (anioStr.length() != 4) {
	            throw new IllegalArgumentException("la fecha es incorrecta.");
	        }
	
	    } catch (NumberFormatException e) {
	        throw new IllegalArgumentException("la fecha ingresada es incorrecta: "+fecha);
	    }
	}
	// --- <<IS-END-SHARED>> ---
}

