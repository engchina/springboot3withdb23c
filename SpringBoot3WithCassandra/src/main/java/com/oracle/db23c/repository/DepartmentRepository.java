package com.oracle.db23c.repository;

import com.oracle.db23c.po.Department;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "department", path = "department")
public interface DepartmentRepository extends CassandraRepository<Department, String> {
}
