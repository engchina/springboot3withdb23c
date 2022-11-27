package com.oracle.db23c.converter;


import com.oracle.db23c.po.Department;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DepartmentByIdConverter implements Converter<Department, Department> {

    @Override
    public Department convert(Department source) {
        source.setManagerId(200);
        source.setLocationId(1700);
        return source;
    }
}
