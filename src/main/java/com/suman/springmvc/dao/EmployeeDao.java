package com.suman.springmvc.dao;

import java.util.List;

import com.suman.springmvc.entity.EmployeeEntity;

public interface EmployeeDao {
	
	EmployeeEntity findById(int id);
	 
    void saveEmployee(EmployeeEntity employee);
     
    void deleteEmployeeBySsn(String ssn);
     
    List<EmployeeEntity> findAllEmployees();
 
    EmployeeEntity findEmployeeBySsn(String ssn);
 

}
