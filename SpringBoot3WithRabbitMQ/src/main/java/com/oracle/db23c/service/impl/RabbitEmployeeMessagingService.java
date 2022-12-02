package com.oracle.db23c.service.impl;

import com.oracle.db23c.po.Employee;
import com.oracle.db23c.service.EmployeeMessagingService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitEmployeeMessagingService implements EmployeeMessagingService {

    @Resource
    RabbitTemplate rabbitTemplate;

    @Override
    public void sendMessage(Employee employee) {
        log.info("### in RabbitEmployeeMessagingService.sendMessage() ###");
//        MessageConverter messageConverter = rabbitTemplate.getMessageConverter();
//        MessageProperties messageProperties = new MessageProperties();
//        Message message = messageConverter.toMessage(employee, messageProperties);
        rabbitTemplate.convertAndSend(employee);
    }

    @Override
    public Employee receiveMessage() {
        log.info("### in RabbitEmployeeMessagingService.receiveMessage() ###");
        Employee employee = (Employee) rabbitTemplate.receiveAndConvert();
        log.info("### employee is " + employee + " ###");
        return employee;
    }
}
