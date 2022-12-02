package com.oracle.db23c.listener;

import com.oracle.db23c.po.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmployeeListener {

    @RabbitListener(queues = "employee.queue")
    public void receiveEmployee(Employee employee) {
        log.info("### in EmployeeListener.receiveEmployee() ###");
        log.info(employee.toString());
    }
}
