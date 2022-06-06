package com.robin.rabbitmq.mq.producers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Message;
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

    public boolean sendMessageToDirectExchange(String routingKey, String message){
        try{
            rabbitTemplate.convertAndSend(textDirectExchange.getName(), routingKey, message);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    public boolean sendMessageToFanoutExchange(String message){
        try{
            rabbitTemplate.convertAndSend(textFanoutExchange.getName(), "", message);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    public boolean sendMessageToTopicExchange(String routingKey, String message){
        try{
            rabbitTemplate.convertAndSend(textTopicExchange.getName(), routingKey, message);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
}
