package hayden.apploger;

import hayden.applogger.TransactionParam;
import hayden.applogger.TransactionParamType;

public class PDVRequet {
	
	@TransactionParam(customType = "pdv", required = true)
	private String pdvMsisdn;
	
	@TransactionParam(customType = "iccid", required = true)
	private String pdvIccid;
	
	private String pdvPassword;
	
	@TransactionParam(required = false)
	private String pdvName;
	
	@TransactionParam(type = TransactionParamType.AMOUNT, required = true)
	private Double amount;
	
	@TransactionParam(type = TransactionParamType.RECEIVER, required = true)
	private String prepaid;
	
	public PDVRequet(String pdvMsisdn, String pdvIccid, String pdvPassword, String pdvName, Double amount, String prepaid) {
		super();
		this.pdvMsisdn = pdvMsisdn;
		this.pdvIccid = pdvIccid;
		this.pdvPassword = pdvPassword;
		this.pdvName = pdvName;
		this.amount = amount;
		this.prepaid = prepaid;
	}
}
