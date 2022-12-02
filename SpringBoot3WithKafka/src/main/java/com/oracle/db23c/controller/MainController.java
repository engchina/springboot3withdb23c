package com.oracle.db23c.controller;

import com.oracle.db23c.po.Employee;
import com.oracle.db23c.service.EmployeeMessagingService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
@Slf4j
public class MainController {

    @Resource
    EmployeeMessagingService employeeMessagingService;

    @GetMapping("/send")
    public String sendMessage() {
        log.info("### in MainController.sendMessage() ###");
        Employee employee = Employee.builder().employeeId(1).employeeName("oracle").build();
        employeeMessagingService.sendMessage(employee);
        return "Hello Kafka";
    }
}
