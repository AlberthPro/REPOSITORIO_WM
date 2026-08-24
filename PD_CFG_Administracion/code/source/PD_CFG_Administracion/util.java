package PD_CFG_Administracion;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
// --- <<IS-END-IMPORTS>> ---

public final class util

{
	// ---( internal utility methods )---

	final static util _instance = new util();

	static util _newInstance() { return new util(); }

	static util _cast(Object o) { return (util)o; }

	// ---( server methods )---




	public static final void generateDate (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(generateDate)>> ---
		// @sigtype java 3.5
		// [o] field:0:required fechaInicio
		// [o] field:0:required fechaFin
		// [o] field:0:required nombreArchivo
		// Formato requerido
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		// Fecha actual (se asume lunes)
		LocalDate hoy = LocalDate.now();
		
		// Obtener el viernes de la semana pasada
		LocalDate viernesPasado = hoy
		.with(DayOfWeek.MONDAY)   // asegurar semana actual
		.minusWeeks(1)
		.with(DayOfWeek.FRIDAY);
		
		// Obtener el viernes de hace dos semanas
		LocalDate viernesDosSemanas = hoy
		.with(DayOfWeek.MONDAY)
		.minusWeeks(2)
		.with(DayOfWeek.FRIDAY);
		
		// Agregar hora fija 15:00:00
		LocalDateTime fechaInicio = LocalDateTime.of(viernesDosSemanas, LocalTime.of(15, 0, 0));
		LocalDateTime fechaFin = LocalDateTime.of(viernesPasado, LocalTime.of(15, 0, 0));
		
		// Formatear salida
		String fecha_inicio = fechaInicio.format(formatter);
		String fecha_fin = fechaFin.format(formatter);
		
		DateTimeFormatter nombreFormatter = DateTimeFormatter.ofPattern("dd-MMM", new Locale("es", "ES"));
		
		 String parteInicio = viernesDosSemanas.format(nombreFormatter);
		 String parteFin = viernesPasado.format(nombreFormatter);
		
		 String nombreArchivo = parteInicio + "-" + parteFin+"_";
		
		IDataUtil.put(pipeline.getCursor(), "fechaInicio", fecha_inicio);
		IDataUtil.put(pipeline.getCursor(), "fechaFin", fecha_fin);
		IDataUtil.put(pipeline.getCursor(), "nombreArchivo", nombreArchivo);
		// --- <<IS-END>> ---

                
	}
}

