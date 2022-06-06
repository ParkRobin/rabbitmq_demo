package com.robin.rabbitmq.controllers;

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

    @PostMapping("/text/direct")
    public Boolean sendTextQueueMessage1(@RequestBody String text, @RequestParam String routingKey){
        return textProducer.sendMessageToDirectExchange(routingKey, text);
    }

    @PostMapping("/text/fanout")
    public Boolean sendTextQueueMessage2(@RequestBody String text){
        return textProducer.sendMessageToFanoutExchange(text);
    }

    @PostMapping("/text/topic")
    public Boolean sendTextQueueMessage3(@RequestBody String text, @RequestParam String routingKey){
        return textProducer.sendMessageToTopicExchange(routingKey, text);
    }
}
