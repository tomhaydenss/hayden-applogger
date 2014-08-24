package hayden.apploger;

import hayden.applogger.annotation.TransactionField;
import hayden.applogger.type.TransactionFieldType;

public class PDVRequet {

	@TransactionField(customType = "pdv", required = true)
	private String pdvMsisdn;

	@TransactionField(customType = "iccid", required = true)
	private String pdvIccid;

	private String pdvPassword;

	@TransactionField(required = false)
	private String pdvName;

	@TransactionField(type = TransactionFieldType.AMOUNT, required = true)
	private Double amount;

	@TransactionField(type = TransactionFieldType.RECEIVER, required = true)
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
