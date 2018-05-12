package com.suman.springmvc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suman.springmvc.dao.EmployeeDao;
import com.suman.springmvc.entity.EmployeeEntity;
import com.suman.springmvc.model.Employee;

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
 
    @Autowired
    private EmployeeDao dao;
     
    public Employee findById(int id) {
        return getModel(dao.findById(id));
    }
 
    public void saveEmployee(Employee employee) {
    	
        dao.saveEmployee(getEntity(employee));
    }
 
    /*
     * Since the method is running with Transaction, No need to call hibernate update explicitly.
     * Just fetch the entity from db and update it with proper values within transaction.
     * It will be updated in db once transaction ends. 
     */
    public void updateEmployee(Employee employee) {
        Employee entity = getModel(dao.findById(employee.getId()));
        if(entity!=null){
            entity.setName(employee.getName());
            entity.setJoiningDate(employee.getJoiningDate());
            entity.setSalary(employee.getSalary());
            entity.setSsn(employee.getSsn());
        }
    }
 
    public void deleteEmployeeBySsn(String ssn) {
        dao.deleteEmployeeBySsn(ssn);
    }
     
    public List<Employee> findAllEmployees() {
    	
    	List<Employee> emps=new ArrayList<Employee>();
    	List<EmployeeEntity> entities=dao.findAllEmployees();
    	for (EmployeeEntity employeeEntity : entities) {
    		emps.add(getModel(employeeEntity))	;
		}
    	
        return emps;
    }
 
    public Employee findEmployeeBySsn(String ssn) {
        return getModel(dao.findEmployeeBySsn(ssn));
    }
 
    public boolean isEmployeeSsnUnique(Integer id, String ssn) {
        Employee employee = findEmployeeBySsn(ssn);
        return ( employee == null || ((id != null) && (employee.getId() == id)));
    }
    
    private EmployeeEntity getEntity(Employee emp){
    	EmployeeEntity entity=null;
    	
    	if(emp!=null){
    	entity=new EmployeeEntity();
    	entity.setName(emp.getName());
    	entity.setJoiningDate(emp.getJoiningDate());
    	entity.setSalary(emp.getSalary());
    	entity.setSsn(emp.getSsn());
    	}
    	
    return entity;	
    }
    private Employee getModel(EmployeeEntity emp){
    	Employee model=null;
    	if(emp!=null){
    	model=new Employee();
    	model.setId(emp.getId());
    	model.setName(emp.getName());
    	model.setJoiningDate(emp.getJoiningDate());
    	model.setSalary(emp.getSalary());
    	model.setSsn(emp.getSsn());
    	}
    	
    return model;	
    }

}
