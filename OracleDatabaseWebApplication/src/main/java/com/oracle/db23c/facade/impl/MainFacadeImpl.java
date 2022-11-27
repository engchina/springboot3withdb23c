package com.oracle.db23c.facade.impl;

import com.oracle.db23c.facade.MainFacade;
import com.oracle.db23c.po.Department;
import com.oracle.db23c.service.DepartmentService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@Slf4j
public class MainFacadeImpl implements MainFacade {

    @Resource
    DepartmentService departmentService;

    @Override
    public Integer insertDepartment(Department department) {
        log.info(department.toString());
        return departmentService.insertDepartment(department);
    }
}
