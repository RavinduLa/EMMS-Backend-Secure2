package com.emms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emms.api.DepartmentApi;
import com.emms.api.EquipmentApi;
import com.emms.api.EquipmentCategoryApi;
import com.emms.api.ModelApi;
import com.emms.api.SupplierApi;
import com.emms.inventoryModel.Brand;
import com.emms.inventoryModel.Department;
import com.emms.inventoryModel.Equipment;
import com.emms.inventoryModel.EquipmentCategories;
import com.emms.inventoryModel.Model;
import com.emms.inventoryModel.Supplier;

@RestController
@RequestMapping("/api/addInventory/")
@CrossOrigin(origins ="*",allowedHeaders = "*",exposedHeaders = "*")
public class AddInventoryController {
	
	private DepartmentApi departmentApi;
	private SupplierApi supplierApi;
	private EquipmentCategoryApi equipmentCategoryApi;
	private ModelApi modelApi;
	private EquipmentApi equipmentApi;
	
	public AddInventoryController(DepartmentApi departmentApi, SupplierApi supplierApi, EquipmentCategoryApi equipmentCategoryApi,
			ModelApi modelApi, EquipmentApi equipmentApi ) {
		this.departmentApi = departmentApi;
		this.supplierApi = supplierApi;
		this.equipmentCategoryApi = equipmentCategoryApi;
		this.modelApi = modelApi;
		this.equipmentApi = equipmentApi;
	}
	
	@GetMapping(value="getAllDepartments")
	public List<Department> getAllDepartments(){
		return departmentApi.getAllDepartments();
	}
	
	@GetMapping("getAllSuppliers")
	public List<Supplier> getAllSuppliers(){
		return supplierApi.getAllSuppliers();
	}
	
	@GetMapping("getAllCategories")
	public List<EquipmentCategories> getAllCategories(){
		return equipmentCategoryApi.getAllCategories();
	}
	
	@GetMapping("getBrandsForCategory/{category}")
	public List<Brand> getBrandsForCategory(@PathVariable String category){
		return equipmentCategoryApi.getBrandsForCategory(category);
	}
	
	@GetMapping("getModelsForBrand/{brand}")
	public List<Model> getModelsForBrand(@PathVariable String brand){
		return modelApi.getModelsForBrands(brand);
	}
	
	@PostMapping("addEquipment")
	public synchronized Equipment addEquipment (@RequestBody Equipment newEquipment) {
		return equipmentApi.addEquipment(newEquipment);
	}
	
	@GetMapping("checkIdAvailability/{id}")
	public synchronized boolean getIdAvailability(@PathVariable long id) {
		return equipmentApi.getIdAvailability(id);
	}
	
	
	
	
	

}
