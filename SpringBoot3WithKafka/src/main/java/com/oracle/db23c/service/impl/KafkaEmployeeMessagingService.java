package com.oracle.db23c.service.impl;

import com.oracle.db23c.po.Employee;
import com.oracle.db23c.service.EmployeeMessagingService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaEmployeeMessagingService implements EmployeeMessagingService {

    @Resource
    KafkaTemplate<String, Employee> kafkaTemplate;

    @Override
    public void sendMessage(Employee employee) {
        log.info("### in KafkaEmployeeMessagingService.sendMessage() ###");
        kafkaTemplate.sendDefault(employee);
    }
}
