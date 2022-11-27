package com.oracle.db23c.service.impl;

import com.oracle.db23c.mapper.DepartmentMapper;
import com.oracle.db23c.po.Department;
import com.oracle.db23c.service.DepartmentService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Resource
    DepartmentMapper departmentMapper;

    @Override
    public Integer insertDepartment(Department department) {
        return departmentMapper.insert(department);
    }
}
