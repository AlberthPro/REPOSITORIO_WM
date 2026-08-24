package CO_REG_RopaMuebles.util;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.softwareag.util.IDataMap;
import java.awt.geom.Path2D;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
// --- <<IS-END-IMPORTS>> ---

public final class common

{
	// ---( internal utility methods )---

	final static common _instance = new common();

	static common _newInstance() { return new common(); }

	static common _cast(Object o) { return (common)o; }

	// ---( server methods )---




	public static final void StringDateToDate (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(StringDateToDate)>> ---
		// @sigtype java 3.5
		// [i] field:0:required dateStr
		// [o] object:0:required date
		String strDate = IDataUtil.getString(pipeline.getCursor(), "dateStr");
		DateFormat format = null;
		String patter = "";
		
		if (strDate.contains(":")){
		    format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		    patter = "dd/MM/yyyy HH:mm:ss";
		}else{
		    format = new SimpleDateFormat("dd/MM/yyyy"); 
		    patter = "dd/MM/yyyy";
		}
		 
		try {
		    
		    IData output =  Service.doInvoke("AF_REG_TraspasoAfore.util.common", "validateDate", pipeline);
		    IDataUtil.put(pipeline.getCursor(), "dateStr", output);
		    boolean isValid = IDataUtil.getBoolean(output.getCursor(), "isValidaDate");
		    if (!isValid){
		throw new RuntimeException("");
		    }
		    
		    IDataUtil.put(pipeline.getCursor(), "dateIn", strDate);
		    IDataUtil.put(pipeline.getCursor(), "patternIn", patter);
		    
		    IData result = Service.doInvoke("AF_SRV_Transversales.util", "UT_Times", pipeline);
		    String hours = IDataUtil.getString(result.getCursor(), "hours");
		    
		    Date date = format.parse(strDate);
		    Calendar calendar = Calendar.getInstance();
		 
		    
		    if (hours!=null){
		calendar.add(Calendar.HOUR_OF_DAY,Integer.parseInt(hours) );  
		calendar.setTime(date);
		IDataUtil.put(pipeline.getCursor(), "date",calendar.getTime());
		    }else {
		IDataUtil.put(pipeline.getCursor(), "date", date);
		    }
		    
		  
		} catch (Exception e1) {
		    throw new RuntimeException(e1.getMessage());
		}
		// --- <<IS-END>> ---

                
	}



	public static final void StringDate_1 (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(StringDate_1)>> ---
		// @sigtype java 3.5
		// [i] field:0:required dateStr
		// [o] object:0:required date
		String strDate = IDataUtil.getString(pipeline.getCursor(), "dateStr");
		DateFormat format = null;
		String patter = "";
		
		if (strDate.contains(":")){
		    format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		    patter = "dd/MM/yyyy HH:mm:ss";
		}else{
		    format = new SimpleDateFormat("dd/MM/yyyy"); 
		    patter = "dd/MM/yyyy";
		}
		 
		try {
		    
		    IData output =  Service.doInvoke("Rules_Bancoppel.util", "validateDate", pipeline);
		    IDataUtil.put(pipeline.getCursor(), "dateStr", output);
		    boolean isValid = IDataUtil.getBoolean(output.getCursor(), "isValidaDate");
		    if (!isValid){
		throw new RuntimeException("");
		    }
		    
		    IDataUtil.put(pipeline.getCursor(), "dateIn", strDate);
		    IDataUtil.put(pipeline.getCursor(), "patternIn", patter);
		    
		    IData result = Service.doInvoke("AF_SRV_Transversales.util", "UT_Times", pipeline);
		    String hours = IDataUtil.getString(result.getCursor(), "hours");
		    
		    Date date = format.parse(strDate);
		    Calendar calendar = Calendar.getInstance();
		 
		    
		    if (hours!=null){
		calendar.add(Calendar.HOUR_OF_DAY,Integer.parseInt(hours) );  
		calendar.setTime(date);
		IDataUtil.put(pipeline.getCursor(), "date",calendar.getTime());
		    }else {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		 Date date1 = formatter.parse(strDate);
		IDataUtil.put(pipeline.getCursor(), "date", date1);
		    }
		    
		  
		} catch (Exception e1) {
		    throw new RuntimeException(e1.getMessage());
		}
		// --- <<IS-END>> ---

                
	}



	public static final void ValidateCoordinates (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(ValidateCoordinates)>> ---
		// @sigtype java 3.5
		// [i] object:0:required x
		// [i] object:0:required y
		// [o] object:0:required inside
		Path2D path = new Path2D.Double();
		float x = Float.parseFloat(IDataUtil.getString(pipeline.getCursor(), "x"));
		float y  = Float.parseFloat(IDataUtil.getString(pipeline.getCursor(), "y"));
		
		String[] Coordinate = new String[]{
				"32.7184, -117.1349",
				"32.7184, -114.8181",
				"31.3326, -114.8076",
				"29.3920, -109.3275",
				"27.9613, -106.5660",
				"25.6350, -103.1214",
				"24.9483, -101.8478",
				"23.6214, -99.0121",
				"23.0920, -97.1947",
				"22.2602, -97.0237",
				"21.1799, -97.0534" ,
				"19.6500, -96.0518",
				"19.2000, -95.9151",
				"18.0983, -94.4191",
				"16.5205, -88.1064",
				"14.5290, -90.2350",
				"14.5326, -94.6370",
				"15.7941, -97.6315",
				"16.0770, -102.2273",
				"17.8884, -102.8130",
				"19.1500, -104.9088",
				"19.2900, -107.8173",
				"32.7184, -117.1349"
				};
		
		
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
		
		IDataUtil.put(pipeline.getCursor(), "inside", path.contains(x, y));	
		
			
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
}

