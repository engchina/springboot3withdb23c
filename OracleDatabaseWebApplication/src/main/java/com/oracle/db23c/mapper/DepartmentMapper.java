package com.oracle.db23c.mapper;


import com.oracle.db23c.po.Department;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultType;

public interface DepartmentMapper {

    @Insert("insert into departments(department_id,department_name,manager_id,location_id) values (#{departmentId,jdbcType=INTEGER},#{departmentName,jdbcType=VARCHAR},#{managerId,jdbcType=INTEGER},#{locationId,jdbcType=INTEGER})")
    @ResultType(Integer.class)
    Integer insert(Department department);
}
