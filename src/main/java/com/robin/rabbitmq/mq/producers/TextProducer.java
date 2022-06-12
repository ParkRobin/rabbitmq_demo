package com.robin.rabbitmq.mq.producers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TextProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Exchange textDirectExchange;

    @Autowired
    private Exchange textFanoutExchange;

    @Autowired
    private Exchange textTopicExchange;

    public boolean sendMessageToDefaultExchange(String queueName, String message){
        try{
            rabbitTemplate.convertAndSend("", queueName, message);
            return true;
        }catch (Exception e){
            log.error("Send Message To Default Exchange Error, queueName: {}, message: {}", queueName, message, e);
            return false;
        }
    }

    public boolean sendMessageToDirectExchange(String routingKey, String message){
        try{
            rabbitTemplate.convertAndSend(textDirectExchange.getName(), routingKey, message);
            return true;
        }catch (Exception e){
            log.error("Send Message To Direct Exchange Error, routingKey: {}, message: {}", routingKey, message, e);
            return false;
        }
    }

    public boolean sendMessageToFanoutExchange(String message){
        try{
            rabbitTemplate.convertAndSend(textFanoutExchange.getName(), "", message);
            return true;
        }catch (Exception e){
            log.error("Send Message To Fanout Exchange Error, message: {}", message, e);
            return false;
        }
    }

    public boolean sendMessageToTopicExchange(String routingKey, String message){
        try{
            rabbitTemplate.convertAndSend(textTopicExchange.getName(), routingKey, message);
            return true;
        }catch (Exception e){
            log.error("Send Message To Topic Exchange Error, routingKey: {}, message: {}", routingKey, message, e);
            return false;
        }
    }
}
