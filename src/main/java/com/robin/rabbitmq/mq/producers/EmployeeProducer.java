package com.robin.rabbitmq.mq.producers;

import com.robin.rabbitmq.domains.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmployeeProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Exchange employeeDirectExchange;

    @Autowired
    private Exchange employeeFanoutExchange;

    @Autowired
    private Exchange employeeTopicExchange;

    @Autowired
    private Exchange employeeHeadersExchange;

    public boolean sendMessageToDefaultExchange(String queueName, Employee message){
        try{
            rabbitTemplate.convertAndSend("", queueName, message);
            return true;
        }catch (Exception e){
            log.error("Send Message To Default Exchange Error, queueName: {}, message: {}", queueName, message, e);
            return false;
        }
    }

    public boolean sendMessageToDirectExchange(String routingKey, Employee message){
        try{
            rabbitTemplate.convertAndSend(employeeDirectExchange.getName(), routingKey, message);
            return true;
        }catch (Exception e){
            log.error("Send Message To Direct Exchange Error, routingKey: {}, message: {}", routingKey, message, e);
            return false;
        }
    }

    public boolean sendMessageToFanoutExchange(Employee message){
        try{
            rabbitTemplate.convertAndSend(employeeFanoutExchange.getName(), "", message);
            return true;
        }catch (Exception e){
            log.error("Send Message To Fanout Exchange Error, message: {}", message, e);
            return false;
        }
    }

    public boolean sendMessageToTopicExchange(String routingKey, Employee message){
        try{
            rabbitTemplate.convertAndSend(employeeTopicExchange.getName(), routingKey, message);
            return true;
        }catch (Exception e){
            log.error("Send Message To Topic Exchange Error, routingKey: {}, message: {}", routingKey, message, e);
            return false;
        }
    }

    public boolean sendMessageToHeadersExchange(Employee message, int headerValue){
        try{
            rabbitTemplate.convertAndSend(employeeHeadersExchange.getName(), "", message, m -> {
                m.getMessageProperties().getHeaders().put("header-test", headerValue);
                return m;
            });
            return true;
        }catch (Exception e){
            log.error("Send Message To Headers Exchange Error, headerValue: {}, message: {}", headerValue, message, e);
            return false;
        }
    }
}
