package com.oracle.db23c.service;

import com.oracle.db23c.po.Employee;
import jakarta.jms.JMSException;

public interface EmployeeMessagingService {
    void sendMessage(Employee employee);
    Employee receiveMessage() throws JMSException;
}
