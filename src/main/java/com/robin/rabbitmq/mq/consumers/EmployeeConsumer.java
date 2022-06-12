package com.robin.rabbitmq.mq.consumers;

import com.robin.rabbitmq.domains.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Slf4j
public class EmployeeConsumer {

    @RabbitListener(queues = "${spring.rabbitmq.queue.employee1}")
    public void getMessageFromQueue1(Employee message){
        log.info("q1 Receive employee message: " + message);
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue.employee2}")
    public void getMessageFromQueue2(Employee message){
        log.info("q2 Receive employee message: " + message);
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue.employee3}")
    public void getMessageFromQueue3(Employee message){
        log.info("q3 Receive employee message: " + message);
    }
}
