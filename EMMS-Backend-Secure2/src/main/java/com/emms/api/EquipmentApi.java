package com.emms.api;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emms.dal.adapter.EquipmentCondemnDataAdapter;
import com.emms.dal.adapter.EquipmentDataAdapter;
import com.emms.inventoryModel.CondemnedEquipment;
import com.emms.inventoryModel.Equipment;
import com.emms.inventoryModel.PendingCondemnEquipment;
import com.emms.inventoryRequestModels.CondemnRequest;

@Service
public class EquipmentApi {
	
	private EquipmentDataAdapter equipmentDataAdapter;
	private EquipmentCondemnDataAdapter equipmentCondemnDataAdapter;
	private SupplierApi supplierApi;
	private DepartmentApi departmentApi;
	
	@Autowired
	public EquipmentApi(EquipmentDataAdapter equipmentDataAdapter, EquipmentCondemnDataAdapter equipmentCondemnDataAdapter,
			SupplierApi supplierApi, DepartmentApi departmentApi) {
		this.equipmentDataAdapter = equipmentDataAdapter;
		this.equipmentCondemnDataAdapter = equipmentCondemnDataAdapter;
		this.supplierApi = supplierApi;
		this.departmentApi = departmentApi;
	}
	
	
	//returns a list of all equipment
	public List<Equipment> getAllEquipment (){
		return equipmentDataAdapter.getAll();
	}
	
	//returns supplier name based on the given date
	public String getSupplierNameForDate (int id, Date purchasedate) {
		return equipmentDataAdapter.getSupplierNameForDate(id, purchasedate);
	}
	
	//returns department name based on the given date
	public String getDepartmentNameForDate(int id, Date purchasedate) {
		return equipmentDataAdapter.getDepartmentNameForDate(id, purchasedate);
	}
	
	//get equipment on the given id
	public Optional<Equipment> getEquipmentById (long id){
		return equipmentDataAdapter.getEquipmentById(id);
	}
	
	//gets the availability of the asset id
	public boolean getIdAvailability (long id) {
		//return equipmentDataAdapter.getIdAvailability(id); -- this was available earlier
		
		System.out.println("Checking id : " + id);
		
		//equipmentDataAdapter returns true if the equipment is available
		//therefore it has to be flipped when assigning to the isEquipmentinActiveList variable
		boolean isEquipmentinActiveList = !equipmentDataAdapter.getIdAvailability(id);//checks whether the equipment is currently in service
		boolean isEquipmentInCondemnList = this.isEquipmentcondemned(id);//checks whether the equipment is in condemned list
		
		if(isEquipmentinActiveList || isEquipmentInCondemnList) {
			System.out.println("isEquipmentinActiveList : " + isEquipmentinActiveList);
			System.out.println("isEquipmentInCondemnList : " + isEquipmentInCondemnList);
			
			System.out.println("Id is not avilable");
			//if either of the lists have the asset id, it is not available. Return false.
			return false;
		}
		else {
			//if the asset id is not in both the lists asset id is available. Return true.
			return true;
		}
		
		
	}
	
	//saves an equipment on the database
	public Equipment addEquipment (Equipment equipment) {
		return equipmentDataAdapter.saveEquipment(equipment);
	}
	
	//update the equipment
	public Equipment updateEquipment (Equipment equipment) {
		return equipmentDataAdapter.updateEquipment(equipment);
	}
	
	//deletes an equipment based on id
	public long deleteById (long id) {
		return equipmentDataAdapter.deleteById(id);
	}
	
	//get asset count
	public long getAssetCount() {
		return equipmentDataAdapter.getAssetCount();
	}
	
	//returns number of assets for department id
	public int getDepartmentAssetCount(int did) {
		return equipmentDataAdapter.getDepartmentAssetCount(did);
	}
	
	//returns asset count for a given type and given department id
	public int getTypeDeptAssetCount (int did, String type) {
		return equipmentDataAdapter.getTypeDeptAssetCount(did, type);
	}
	
	//returns asset count for a given location
	public int getLocationAssetCount(String location) {
		return equipmentDataAdapter.getLocationAssetCount(location);
	}
	
	//returns the number of assets for a given supplier id.
	public int getSupplierAssetCount (int id) {
		return equipmentDataAdapter.getSupplierAssetCount(id);
	}
	
	//returns a count of assets under warranty
	public int getUnderWarrantyAssetCount() {
		return equipmentDataAdapter.getUnderWarrantyAssetCount();
	}
	
	//returns a count of assets without warranty
	public int getWarrantyVoidAssetCount() {
		return equipmentDataAdapter.getWarrantyVoidAssetCount();
	}
	
	//returns a list of equipment for a supplier id
	public List<Equipment> getEquipmentBySupplier (int id){
		return equipmentDataAdapter.getEquipmentBySupplier(id);
	}
	
	//returns a list of equipment for a location
	public List<Equipment> getEquipmentForLocation (String location){
		return equipmentDataAdapter.getEquipmentForLocation(location);
	}
	
	//returns a list of equipment for a department
	public List<Equipment> getEquipmentForDepartment (int dept){
		return equipmentDataAdapter.getEquipmentForDepartment(dept);
	}
	
	//gets equipment count for a department
	public int equipmentDepartmentCount(int dept) {
		return equipmentDataAdapter.equipmentDepartmentCount(dept);
	}
	
	//gets equipment count for a location
	public int equipmentLocationCount(String location) {
		return equipmentDataAdapter.equipmentLocationCount(location);
	}
	
	//returns equipment list based on the passed location and the department.
	//both department and equipment must match for an equipment to be returned.
	public List<Equipment> getEquipmentForLocationAndDepartment (String location, int department){
		return equipmentDataAdapter.getEquipmentForLocationAndDepartment(location, department);
	}
	
	//returns an equipment for a given id
	public Equipment getEquipmentForAssetId(long assetId) {
		return equipmentDataAdapter.getEquipmentForAssetId(assetId);
	}
	
	//returns an equipment for a given serial number
	public Equipment getEquipmentForSerialNumber (String serialNumber) {
		return equipmentDataAdapter.getEquipmentForSerialNumber(serialNumber);
	}
	
	//returns an equipment within a time period
	public List<Equipment> getEquipmentForTimePeriod(String s, String en){
		return equipmentDataAdapter.getEquipmentForTimePeriod(s, en);
	}
	
	//returns a list of items that are under warranty
	public List<Equipment> getUnderWarrantyEquipment(){
		Date currentDate = new Date();
		List<Equipment> allEquipment = getAllEquipment();
		List<Equipment> underWarrantyList = new ArrayList<Equipment>();
		
		for(Equipment e : allEquipment) {
			int warrantyMonths = e.getWarrantyMonths();
			Date purchaseDate = e.getPurchaseDate();
			
			Date warrantyEndDate = calculateWarrantyEndDate(purchaseDate, warrantyMonths);
			if(currentDate.before(warrantyEndDate)) {
				underWarrantyList.add(e);
			}
		}
		
		for(Equipment e: underWarrantyList) {
			int supplierId  = e.getSupplier();
			int departmentId = e.getDepartment();
			Date purchaseDate = e.getPurchaseDate();
			
			String supplierName = getSupplierNameForDate(supplierId, purchaseDate);
			String departmentName = getDepartmentNameForDate(departmentId, purchaseDate);
			
			e.setSupplierName(supplierName);
			e.setDepartmentName(departmentName);
		}
		return underWarrantyList;
	}
	
	//returns a list of items that are not under warranty
	public List<Equipment> getNoWarrantyEquipment(){
		Date currentDate = new Date();
		List<Equipment> allEquipment = getAllEquipment();
		List<Equipment> noWarrantyList = new ArrayList<Equipment>();
		
		for(Equipment e: allEquipment) {
			int warrantyMonths = e.getWarrantyMonths();
			Date purchaseDate = e.getPurchaseDate();
			
			Date warrantyEndDate = calculateWarrantyEndDate(purchaseDate, warrantyMonths);
			if(currentDate.after(warrantyEndDate)) {
				noWarrantyList.add(e);
			}
		}
		
		for(Equipment e: noWarrantyList) {
			int supplierId  = e.getSupplier();
			int departmentId = e.getDepartment();
			Date purchaseDate = e.getPurchaseDate();
			String supplierName = getSupplierNameForDate(supplierId, purchaseDate);
			String departmentName = getDepartmentNameForDate(departmentId, purchaseDate);
			e.setSupplierName(supplierName);
			e.setDepartmentName(departmentName);
		}
		return noWarrantyList;
	}
	
	
	//convert java util date to calendar type
	public static Calendar toCalendar(Date date){ 
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(date);
		  return cal;
	}
	
	//convert calendar type to java util date type
	public static Date toDate(Calendar calender) {
		Date date = new Date();
		date = calender.getTime();
		return date;
	}
	
	//calculate the warranty ending date based on purchased date and number of warranty months.
	public static Date calculateWarrantyEndDate (Date purchaseDate, int warrantyMonths) {
		Calendar calenderPurchaseDate, calenderWarrantyEndDate ;
		calenderPurchaseDate = toCalendar(purchaseDate);
		System.out.println("Calender type date: " + calenderPurchaseDate.toString());
		calenderWarrantyEndDate  = calenderPurchaseDate;
		calenderWarrantyEndDate.add(Calendar.MONTH, warrantyMonths);
		Date warrantyEndDate = toDate(calenderWarrantyEndDate);
		
		System.out.println("Warranty end date: " + warrantyEndDate);
		return warrantyEndDate;
				
	}
	
	//add the equipment to pending condemn list on request
	public PendingCondemnEquipment requestEquipmentCondemn(CondemnRequest condemnRequest) {
		
		System.out.println("Request recieved for condemn asset id : " +condemnRequest.getAssetId());
		
		//initialize pending condemn equipment
		PendingCondemnEquipment pendingCondemnEquipment = new PendingCondemnEquipment();
		
		//get equipment for asset id
		Equipment equipment = this.getEquipmentForAssetId(condemnRequest.getAssetId());
		
		System.out.println("Equipment recieved for asset id : " + equipment.toString());
		
		pendingCondemnEquipment.setAssetId(equipment.getAssetId());
		pendingCondemnEquipment.setCondemnReason(condemnRequest.getReason());
		pendingCondemnEquipment.setSerialNumber(equipment.getSerialNumber());
		pendingCondemnEquipment.setType(equipment.getType());
		pendingCondemnEquipment.setBrand(equipment.getBrand());
		pendingCondemnEquipment.setModel(equipment.getModel());
		pendingCondemnEquipment.setPurchaseDate(equipment.getPurchaseDate());
		pendingCondemnEquipment.setWarrantyMonths(equipment.getWarrantyMonths());
		pendingCondemnEquipment.setPurchaseOrderNumber(equipment.getPurchaseOrderNumber());
		
		System.out.println("PendingCondemnEquipment object set");
		
		return equipmentCondemnDataAdapter.savePendingCondemnEquipment(pendingCondemnEquipment);
		
	}
	
	//returns a list of pending condemn equipment
	public List<PendingCondemnEquipment> getAllPendingCondemnEquipment(){
		return equipmentCondemnDataAdapter.getAllPendingCondemnEquipment();
	}
	
	//returns a list of condemned equipment
	public List<CondemnedEquipment> getAllCondemnedEquipment(){
		return equipmentCondemnDataAdapter.getAllCondemnedEquipment();
	}
	
	//cancel the condemn request
	//delete the pending condemn request
	public long cancelCondemnRequest(long assetId) {
		return equipmentCondemnDataAdapter.deletePendingCondemnEquipment(assetId);
	}
	
	public long performCondemn(long assetId) {
		
		//instantiate the equipment based on asset id
		Equipment equipment = this.getEquipmentForAssetId(assetId);
		
		//instantiate condemned equipment
		CondemnedEquipment condemnedEquipment = new CondemnedEquipment();
		
		//set the attributes of the condemned equipment
		condemnedEquipment.setAssetId(equipment.getAssetId());
		condemnedEquipment.setSerialNumber(equipment.getSerialNumber());
		condemnedEquipment.setType(equipment.getType());
		condemnedEquipment.setLocation(equipment.getLocation());
		condemnedEquipment.setDepartment(equipment.getDepartment());
		condemnedEquipment.setBrand(equipment.getBrand());
		condemnedEquipment.setModel(equipment.getModel());
		condemnedEquipment.setPurchaseDate(equipment.getPurchaseDate());
		condemnedEquipment.setWarrantyMonths(equipment.getWarrantyMonths());
		condemnedEquipment.setPurchaseOrderNumber(equipment.getPurchaseOrderNumber());
		condemnedEquipment.setSupplier(equipment.getSupplier());
		condemnedEquipment.setIpAddress(equipment.getIpAddress());
		condemnedEquipment.setWorkStationId(equipment.getWorkStationId());
		
		condemnedEquipment.setSupplierName(supplierApi.getSupplierNameForId(equipment.getSupplier()));
		condemnedEquipment.setDepartmentName(departmentApi.getDepartmentNameById(equipment.getDepartment()));
		
		System.out.println("condemned equipment object set with id : " + condemnedEquipment.getAssetId());
		
		equipmentCondemnDataAdapter.saveCondemnedEquipment(condemnedEquipment);
		System.out.println("Saved condemned equipment with id: " + condemnedEquipment.getAssetId());
		
		//delete the entry from the pending list
		equipmentCondemnDataAdapter.deletePendingCondemnEquipment(assetId);
		
		//delete the original entry from the equipment list
		long deletedEquipmentId = equipmentDataAdapter.deleteById(assetId);
		System.out.println("Deleted equipment with id : " + deletedEquipmentId);
		
		return deletedEquipmentId;
	}
	
	//checks whether an equipment is condemned
	//return true if the equipment is condemned
	public boolean isEquipmentcondemned (long assetId) {
		
		//get the condemned equipment list
		List<CondemnedEquipment> condemnedEquipmentList = this.getAllCondemnedEquipment();
		
		//iterate through the list
		for(CondemnedEquipment equipment: condemnedEquipmentList) {
			long condemnedId = equipment.getAssetId();
			
			//if the condemned id equals given asset id the equipment is conedemned.
			//return true
			if(assetId == condemnedId) {
				return true;
			}
		}
		
		//equipment was not in the condemned list
		//return false
		return false;
	}
	
	//checks whether the equipment is marked for condemn
	//return true if equipment is marked for condemn
	public boolean isEquipmentMarkedForCondemn(long assetId){
		
		//get the condemn pending equipment list
		List<PendingCondemnEquipment> pendingCondemnEquipmentList = this.getAllPendingCondemnEquipment();
		
		//iterate through the list
		for(PendingCondemnEquipment pendingEquipment : pendingCondemnEquipmentList) {
			long pendingId = pendingEquipment.getAssetId();
			//if the pending id equals to asset id given, equipment is marked for condemn
			//return true
			if(assetId == pendingId) {
				return true;
			}
		}
		
		//equipment is not in the pendingCondemnEquipmentList
		//return false
		return false;
	}
	
	
	
	

}
