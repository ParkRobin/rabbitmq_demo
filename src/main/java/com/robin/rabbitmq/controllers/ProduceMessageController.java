package com.robin.rabbitmq.controllers;

import com.robin.rabbitmq.domains.Employee;
import com.robin.rabbitmq.mq.producers.EmployeeProducer;
import com.robin.rabbitmq.mq.producers.TextProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
public class ProduceMessageController {

    @Autowired
    private TextProducer textProducer;

    @Autowired
    private EmployeeProducer employeeProducer;

    @PostMapping("/text/default")
    public Boolean sendTextMessageToDefaultExchange(@RequestBody String text, @RequestParam String queueName){
        return textProducer.sendMessageToDefaultExchange(queueName, text);
    }

    @PostMapping("/text/direct")
    public Boolean sendTextMessageToDirectExchange(@RequestBody String text, @RequestParam String routingKey){
        return textProducer.sendMessageToDirectExchange(routingKey, text);
    }

    @PostMapping("/text/fanout")
    public Boolean sendTextMessageToFanoutExchange(@RequestBody String text){
        return textProducer.sendMessageToFanoutExchange(text);
    }

    @PostMapping("/text/topic")
    public Boolean sendTextMessageToTopicExchange(@RequestBody String text, @RequestParam String routingKey){
        return textProducer.sendMessageToTopicExchange(routingKey, text);
    }

    @PostMapping("/employee/default")
    public Boolean sendEmployeeMessageToDefaultExchange(@RequestBody Employee employee, @RequestParam String queueName){
        return employeeProducer.sendMessageToDefaultExchange(queueName, employee);
    }

    @PostMapping("/employee/direct")
    public Boolean sendEmployeeMessageToDirectExchange(@RequestBody Employee employee, @RequestParam String routingKey){
        return employeeProducer.sendMessageToDirectExchange(routingKey, employee);
    }

    @PostMapping("/employee/fanout")
    public Boolean sendEmployeeMessageToFanoutExchange(@RequestBody Employee employee){
        return employeeProducer.sendMessageToFanoutExchange(employee);
    }

    @PostMapping("/employee/topic")
    public Boolean sendEmployeeMessageToTopicExchange(@RequestBody Employee employee, @RequestParam String routingKey){
        return employeeProducer.sendMessageToTopicExchange(routingKey, employee);
    }
}
