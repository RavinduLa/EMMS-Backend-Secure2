package com.emms.inventoryModel;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="condemned_equipment")
public class CondemnedEquipment {
	
	@Id
	@Column(name="asset_id")
	private long assetId;
	
	@Column(name="serial_number")
	private String serialNumber;
	
	@Column(name="type")
	private String type;
	
	@Column(name="location")
	private String location;
	
	@Column(name="department")
	private int department;
	
	@Column(name="department_name")
	private String departmentName;
	
	@Column(name="brand")
	private String brand;
	
	@Column(name="model")
	private String model;
	
	// java sql date data type is used. May need to change to java util Date
	@Column(name="purchase_date")
	private Date purchaseDate;
	
	@Column(name="warranty_months")
	private int warrantyMonths;
	
	@Column(name="purchase_order_number")
	private int purchaseOrderNumber;
	
	@Column(name="supplier")
	private int supplier;
	
	@Column(name="supplier_name")
	private String supplierName; //must be set in the backend using the supplier id
	
	@Column(name = "ip_address")
	private String ipAddress;
	
	@Column(name = "work_station_id")
	private String workStationId;

	public CondemnedEquipment(long assetId, String serialNumber, String type, String location, int department,
			String departmentName, String brand, String model, Date purchaseDate, int warrantyMonths,
			int purchaseOrderNumber, int supplier, String supplierName, String ipAddress, String workStationId) {
		super();
		this.assetId = assetId;
		this.serialNumber = serialNumber;
		this.type = type;
		this.location = location;
		this.department = department;
		this.departmentName = departmentName;
		this.brand = brand;
		this.model = model;
		this.purchaseDate = purchaseDate;
		this.warrantyMonths = warrantyMonths;
		this.purchaseOrderNumber = purchaseOrderNumber;
		this.supplier = supplier;
		this.supplierName = supplierName;
		this.ipAddress = ipAddress;
		this.workStationId = workStationId;
	}

	public CondemnedEquipment() {
		
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getDepartment() {
		return department;
	}

	public void setDepartment(int department) {
		this.department = department;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
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

	public int getSupplier() {
		return supplier;
	}

	public void setSupplier(int supplier) {
		this.supplier = supplier;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getWorkStationId() {
		return workStationId;
	}

	public void setWorkStationId(String workStationId) {
		this.workStationId = workStationId;
	}
	
	

}
