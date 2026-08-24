package CO_REG_RopaMuebles.util;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.math.BigDecimal;
import java.math.RoundingMode;
// --- <<IS-END-IMPORTS>> ---

public final class rules

{
	// ---( internal utility methods )---

	final static rules _instance = new rules();

	static rules _newInstance() { return new rules(); }

	static rules _cast(Object o) { return (rules)o; }

	// ---( server methods )---




	public static final void calculateMarginCredit (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(calculateMarginCredit)>> ---
		// @sigtype java 3.5
		// [i] field:0:required minimumSalaryStr
		// [i] field:0:required numberMinimumSalaryStr
		// [i] field:0:required topLineFactorStr
		// [i] field:0:required creditLineStr
		// [i] field:0:required balanceStr
		// [o] object:0:required marginCredit
		try {
			
		
		Double realLineOfCredit=0d;
		Double minimumSalar =Double.valueOf(IDataUtil.getString(pipeline.getCursor(), "minimumSalaryStr")); 
		Double numberMinimumSalary = Double.valueOf(IDataUtil.getString(pipeline.getCursor(), "numberMinimumSalaryStr"));
		Double topLineFactor = Double.valueOf(IDataUtil.getString(pipeline.getCursor(), "topLineFactorStr"));
		Double creditLine = Double.valueOf(IDataUtil.getString(pipeline.getCursor(), "creditLineStr"));
		Double balance= Double.valueOf(IDataUtil.getString(pipeline.getCursor(), "balanceStr"));
		
		if(topLineFactor.compareTo(0d)!=0){
			realLineOfCredit=(creditLine/topLineFactor)*minimumSalar;
		}
		
		if(realLineOfCredit.compareTo(0d)==0){
			realLineOfCredit=minimumSalar*numberMinimumSalary;
		}
		
		Double marginCredit=(realLineOfCredit-balance);
		
		//Calculo con dos decimales
		BigDecimal bd = BigDecimal.valueOf(marginCredit);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		marginCredit= bd.doubleValue();
		
		
		IDataUtil.put(pipeline.getCursor(), "marginCredit", marginCredit);
			
		}catch (Exception e1) {
		    throw new RuntimeException(e1.getMessage());
		}
		// --- <<IS-END>> ---

                
	}



	public static final void calculateMaximumSaturation (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(calculateMaximumSaturation)>> ---
		// @sigtype java 3.5
		// [i] object:0:required totalBalance
		// [i] object:0:required creditLine
		// [o] object:0:required maximumSaturation
		try {
			Double creditLine = Double.valueOf(IDataUtil.getString(pipeline.getCursor(), "creditLine"));
			Double totalBalance= Double.valueOf(IDataUtil.getString(pipeline.getCursor(), "totalBalance"));
			
			Double maximumSaturation=totalBalance/creditLine;
			
			//Calculo con dos decimales
			BigDecimal bd = BigDecimal.valueOf(maximumSaturation);
			bd = bd.setScale(2, RoundingMode.HALF_UP);
			totalBalance= bd.doubleValue();
			
			
			IDataUtil.put(pipeline.getCursor(), "maximumSaturation", maximumSaturation);
				
			}catch (Exception e1) {
			    throw new RuntimeException(e1.getMessage());
			}
		// --- <<IS-END>> ---

                
	}



	public static final void calculateVSMC (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(calculateVSMC)>> ---
		// @sigtype java 3.5
		// [i] object:0:required smc
		// [i] object:0:required totalBalance
		// [o] object:0:required vsmc
		try {
		Double smc = Double.valueOf(IDataUtil.getString(pipeline.getCursor(), "smc"));
		Double totalBalance= Double.valueOf(IDataUtil.getString(pipeline.getCursor(), "totalBalance"));
		
		Double vsmc=totalBalance/smc;
		
		//Calculo con dos decimales
		BigDecimal bd = BigDecimal.valueOf(vsmc);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		vsmc= bd.doubleValue();
		
		
		IDataUtil.put(pipeline.getCursor(), "vsmc", vsmc);
			
		}catch (Exception e1) {
		    throw new RuntimeException(e1.getMessage());
		}
		// --- <<IS-END>> ---

                
	}
}

