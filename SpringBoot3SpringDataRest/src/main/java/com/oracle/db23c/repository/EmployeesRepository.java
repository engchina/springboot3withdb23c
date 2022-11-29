package com.oracle.db23c.repository;

import com.oracle.db23c.po.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "employee", path = "employee")
public interface EmployeesRepository extends CrudRepository<Employee, Integer> {
}
