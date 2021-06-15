package com.neokred.EmployeeData.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.neokred.EmployeeData.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String> {

	@Query("select count(*) from Employee emp where LOWER(emp.firstName)=:firstName AND LOWER(emp.lastName)=:lastName")
	Integer getExisitingEmployeeCountWithSameFullName(String firstName, String lastName);
}