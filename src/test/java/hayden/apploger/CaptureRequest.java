package hayden.apploger;

import hayden.applogger.annotation.TransactionField;

public class CaptureRequest {

	@TransactionField(required = true)
	private final String serviceID;
	
	@TransactionField(required = true)
	private final String externalID;

	public CaptureRequest(String serviceID, String externalID) {
		super();
		this.serviceID = serviceID;
		this.externalID = externalID;
	}

}
