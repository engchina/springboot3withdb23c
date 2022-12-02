package com.oracle.db23c.listener;

import com.oracle.db23c.po.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmployeeListener {

    @JmsListener(destination = "employee.queue", containerFactory = "myFactory")
    public void receiveEmployee(Employee employee) {
        log.info("### in EmployeeListener.receiveEmployee() ###");
        log.info(employee.toString());
    }
}
