package BC_SRV_Apolo.mock;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.util.Random;
// --- <<IS-END-IMPORTS>> ---

public final class srv

{
	// ---( internal utility methods )---

	final static srv _instance = new srv();

	static srv _newInstance() { return new srv(); }

	static srv _cast(Object o) { return (srv)o; }

	// ---( server methods )---




	public static final void generateVoterKey (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(generateVoterKey)>> ---
		// @sigtype java 3.5
		String regex = "[BCDFGHJKLMNPQRSTVWXYZ]{6}\\d{2}[0-1]\\d[0-3]\\d[0-3]\\d[HM]\\d{3}";
		String consonantes = "BCDFGHJKLMNPQRSTVWXYZ";
		Random rand = new Random();
		
		String voterKey;
		
		do {
		StringBuilder sb = new StringBuilder();
		
		// 6 consonantes
		for (int i = 0; i < 6; i++) {
		sb.append(consonantes.charAt(rand.nextInt(consonantes.length())));
		}
		
		// 2 d\u00EDgitos (a\u00F1o)
		sb.append(rand.nextInt(10));
		sb.append(rand.nextInt(10));
		
		// mes (00\u201319)
		sb.append(String.format("%02d", rand.nextInt(20)));
		
		// d\u00EDa (00\u201339)
		sb.append(String.format("%02d", rand.nextInt(40)));
		
		// d\u00EDa extra (00\u201339)
		sb.append(String.format("%02d", rand.nextInt(40)));
		
		// g\u00E9nero
		sb.append(rand.nextBoolean() ? "H" : "M");
		
		// 3 d\u00EDgitos finales
		sb.append(rand.nextInt(10));
		sb.append(rand.nextInt(10));
		sb.append(rand.nextInt(10));
		
		voterKey = sb.toString();
		
		} while (!voterKey.matches(regex)); // valida contra el regex
		
		IDataUtil.put(pipeline.getCursor(), "voterKey", voterKey);
		// --- <<IS-END>> ---

                
	}
}

