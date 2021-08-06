package com.emms.inventoryRequestModels;

public class CondemnRequest {
	
	private long assetId;
	private String reason;
	
	
	public CondemnRequest(long assetId, String reason) {
		super();
		this.assetId = assetId;
		this.reason = reason;
	}


	public long getAssetId() {
		return assetId;
	}


	public void setAssetId(long assetId) {
		this.assetId = assetId;
	}


	public String getReason() {
		return reason;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
	
	

}
