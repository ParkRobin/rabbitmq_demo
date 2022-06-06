package com.robin.rabbitmq.mq.consumers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Slf4j
public class TextConsumer {

    @RabbitListener(queues = "${spring.rabbitmq.queue.text1}")
    public void getMessage1(String message){
        log.info("q1 Receive text message: " + message);
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue.text2}")
    public void getMessage2(String message){
        log.info("q2 Receive text message: " + message);
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue.text3}")
    public void getMessage3(String message){
        log.info("q3 Receive text message: " + message);
    }
}
