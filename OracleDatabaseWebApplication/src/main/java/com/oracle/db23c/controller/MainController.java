package com.oracle.db23c.controller;

import com.oracle.db23c.converter.DepartmentByIdConverter;
import com.oracle.db23c.facade.MainFacade;
import com.oracle.db23c.po.Department;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * @SessionAttributes indicates that the departments object that is put into the model a little later in the class should be maintained in session
 */
@Controller
@RequestMapping("/main")
@Slf4j
@SessionAttributes("departments")
public class MainController {

    @Resource
    MainFacade mainFacade;

    @Resource
    DepartmentByIdConverter departmentByIdConverter;

    @ModelAttribute(name = "departments")
    public List<Department> departments() {
        return new ArrayList<>();
    }

    @ModelAttribute(name = "department")
    public Department department() {
        return new Department();
    }

    @GetMapping("/departments")
    public String showDepartmentForm(@Nullable @SessionAttribute("departments") List<Department> departments) {
        if (departments != null) {
            log.info(departments.toString());
        }
        return "department";
    }

    @PostMapping(value = "/departments")
    public String insertDepartment(@Valid Department department, Errors errors, @Nullable @SessionAttribute("departments") List<Department> departments, SessionStatus sessionStatus, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute(department);
            return "department";
        }
        department = departmentByIdConverter.convert(department);
        log.info(department != null ? department.toString() : "");
        int insertResult = mainFacade.insertDepartment(department);
        log.info(String.valueOf(insertResult));
        if (departments == null) {
            departments = new ArrayList<>();
        }
        departments.add(department);
        log.info(departments.toString());
//        sessionStatus.setComplete();
        return "redirect:/main/departments";
    }
}
