package com.oracle.db23c.listener;

import com.oracle.db23c.po.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = "employee.topic", groupId = "mygroup")
@Slf4j
public class EmployeeListener {

//    @KafkaListener(topics = "employee.topic", groupId = "mygroup")
//    public void receiveEmployee(Employee employee) {
//        log.info("### in EmployeeListener.receiveEmployee() ###");
//        log.info(employee.toString());
//    }

    @KafkaHandler
    public void receiveEmployee(Employee employee, Message<Employee> message) {
        log.info("### in EmployeeListener.receiveEmployee() ###");
        MessageHeaders headers = message.getHeaders();
        log.info("Received from partition {} with timestamp {}",
                headers.get(KafkaHeaders.RECEIVED_PARTITION),
                headers.get(KafkaHeaders.RECEIVED_TIMESTAMP));
        log.info(employee.toString());
    }
}
