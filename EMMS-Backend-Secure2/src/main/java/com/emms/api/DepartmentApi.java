package com.emms.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.emms.dal.adapter.DepartmentDataAdapter;
import com.emms.inventoryModel.Department;

@Service
public class DepartmentApi {
	
	private DepartmentDataAdapter departmentDataAdapter;
	
	@Autowired
	public DepartmentApi(DepartmentDataAdapter departmentDataAdapter) {
		this.departmentDataAdapter = departmentDataAdapter;
	}
	
	public List<Department> getAllDepartments (){
		List<Department> allDepartments = departmentDataAdapter.getAll();
		List<Department> activeDepartments = new ArrayList<>();
		
		for(Department d : allDepartments) {
			String status = d.getStatus();
			if(status.equals("active")) {
				activeDepartments.add(d);
			}
		}
		
		System.out.println("Returning all active departments");
		System.out.println(activeDepartments);
		return activeDepartments;
	}
	
	public Department addDepartment (Department department) {
		System.out.println("Api department id: " + department.getDid());
		System.out.println("Api department name : " + department.getDepartmentName());
		return departmentDataAdapter.save(department);
	}
	
	public boolean getAvailability(int id) {
		Optional<Department> dept = departmentDataAdapter.findbyId(id);
		
		if(dept.isEmpty()) {
			System.out.println("Id is available");
			return true;
		}
		else {
			System.out.println("Id is not available");
			return false;
		}
	}
	
	public int deleteDepartment (int id) {
		return departmentDataAdapter.removeDepartment(id);
	}
	
	public String getDepartmentNameById(int id) {
		return departmentDataAdapter.getNameByid(id);
	}

}
