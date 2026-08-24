package AF_REG_TraspasoAfore;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
// --- <<IS-END-IMPORTS>> ---

public final class test

{
	// ---( internal utility methods )---

	final static test _instance = new test();

	static test _newInstance() { return new test(); }

	static test _cast(Object o) { return (test)o; }

	// ---( server methods )---




	public static final void castDate (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(castDate)>> ---
		// @sigtype java 3.5
		// [i] field:0:required strInput
		// [o] object:0:required resultado
		DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter FORMATO_FECHA_HORA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		
		    // Validaci\u00F3n de fechas con rango correcto de d\u00EDa y mes
		  Pattern PATRON_FECHA_HORA = Pattern.compile("\\b(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4} (\\d{2}):(\\d{2}):(\\d{2})\\b");
		  Pattern PATRON_FECHA = Pattern.compile("\\b(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}\\b");
		
		String entrada = IDataUtil.getString(pipeline.getCursor(), "strInput");
		  
		Matcher matcherFechaHora = PATRON_FECHA_HORA.matcher(entrada);
		if (matcherFechaHora.find()) {
		    String fechaStr = matcherFechaHora.group();
		    try {
		LocalDateTime fechaHora = LocalDateTime.parse(fechaStr, FORMATO_FECHA_HORA);
		fechaHora = fechaHora.plusHours(6);
		String result = fechaHora.format(FORMATO_FECHA_HORA);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = formatter.parse(result);
		IDataUtil.put(pipeline.getCursor(), "resultado", date);
		return;
		    } catch (DateTimeParseException ignored) {
		
		    } catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		    }}
		
		Matcher matcherFecha = PATRON_FECHA.matcher(entrada);
		while (matcherFecha.find()) {
		    String fechaStr = matcherFecha.group();
		    try {
		LocalDate fecha = LocalDate.parse(fechaStr, FORMATO_FECHA);
		// Convertir a LocalDateTime con hora 00:00 para poder restar
		LocalDateTime fechaHora = fecha.atStartOfDay().plusHours(6);
		String result = fechaHora.format(FORMATO_FECHA_HORA); 
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatter.parse(result);
		IDataUtil.put(pipeline.getCursor(), "resultado", date);
		return;
		    } catch (DateTimeParseException ignored) {} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		    }
		}
		
		throw new IllegalArgumentException("No se encontr\u00F3 una fecha v\u00E1lida en el texto de entrada.");
		// --- <<IS-END>> ---

                
	}
}

