package com.oracle.db23c.service.impl;

import com.oracle.db23c.po.Employee;
import com.oracle.db23c.service.EmployeeMessagingService;
import jakarta.annotation.Resource;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

@Service
public class JmsEmployeeMessagingService implements EmployeeMessagingService {

    @Resource
    JmsTemplate jmsTemplate;

    @Resource
    MessageConverter messageConverter;

    @Override
    public void sendMessage(Employee employee) {
        jmsTemplate.convertAndSend("employee.queue", employee, this::addEmployeeSource);
    }

    @Override
    public Employee receiveMessage() throws JMSException {
        Message message = jmsTemplate.receive("employee.queue");
        assert message != null;
        return (Employee)messageConverter.fromMessage(message);
    }

    private Message addEmployeeSource(Message message) throws JMSException {
        message.setStringProperty("key1", "value1");
        return message;
    }
}
