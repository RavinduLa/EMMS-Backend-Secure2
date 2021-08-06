package com.emms.inventoryModel;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pending_condemn_equipment")
public class PendingCondemnEquipment {
	
	@Id
	@Column(name="asset_id")
	private long assetId;
	
	@Column(name="serial_number")
	private String serialNumber;
	
	@Column(name="condemn_reason")
	private String condemnReason;


	@Column(name="type")
	private String type;
	
	@Column(name="brand")
	private String brand;
	
	@Column(name="model")
	private String model;
	
	@Column(name="purchase_date")
	private Date purchaseDate;
	
	@Column(name="warranty_months")
	private int warrantyMonths;
	
	@Column(name="purchase_order_number")
	private int purchaseOrderNumber;

	
	
	public PendingCondemnEquipment(long assetId, String serialNumber, String condemnReason, String type, String brand,
			String model, Date purchaseDate, int warrantyMonths, int purchaseOrderNumber) {
		super();
		this.assetId = assetId;
		this.serialNumber = serialNumber;
		this.condemnReason = condemnReason;
		this.type = type;
		this.brand = brand;
		this.model = model;
		this.purchaseDate = purchaseDate;
		this.warrantyMonths = warrantyMonths;
		this.purchaseOrderNumber = purchaseOrderNumber;
	}

	public PendingCondemnEquipment() {
		
	}

	public long getAssetId() {
		return assetId;
	}

	public void setAssetId(long assetId) {
		this.assetId = assetId;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	

	public String getCondemnReason() {
		return condemnReason;
	}

	public void setCondemnReason(String condemnReason) {
		this.condemnReason = condemnReason;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public int getWarrantyMonths() {
		return warrantyMonths;
	}

	public void setWarrantyMonths(int warrantyMonths) {
		this.warrantyMonths = warrantyMonths;
	}

	public int getPurchaseOrderNumber() {
		return purchaseOrderNumber;
	}

	public void setPurchaseOrderNumber(int purchaseOrderNumber) {
		this.purchaseOrderNumber = purchaseOrderNumber;
	}
	
	

}
