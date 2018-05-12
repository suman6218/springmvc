package com.suman.springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.suman.springmvc.entity.EmployeeEntity;

@Repository("employeeDao")
public class EmployeeDaoImpl extends AbstractDao<Integer, EmployeeEntity> implements EmployeeDao {
 
    public EmployeeEntity findById(int id) {
        return getByKey(id);
    }
 
    public void saveEmployee(EmployeeEntity employee) {
        persist(employee);
    }
 
    public void deleteEmployeeBySsn(String ssn) {
        Query query = getSession().createSQLQuery("delete from Employee where ssn = :ssn");
        query.setString("ssn", ssn);
        query.executeUpdate();
    }
 
    @SuppressWarnings("unchecked")
    public List<EmployeeEntity> findAllEmployees() {
        Criteria criteria = createEntityCriteria();
        return (List<EmployeeEntity>) criteria.list();
    }
 
    public EmployeeEntity findEmployeeBySsn(String ssn) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("ssn", ssn));
        return (EmployeeEntity) criteria.uniqueResult();
    }
}
