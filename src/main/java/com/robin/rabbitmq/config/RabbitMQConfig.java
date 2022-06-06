package com.robin.rabbitmq.config;

import com.robin.rabbitmq.mq.consumers.TextConsumer;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${spring.rabbitmq.exchange.direct.text}")
    private String textDirectExchangeName;

    @Value("${spring.rabbitmq.exchange.fanout.text}")
    private String textFanoutExchangeName;

    @Value("${spring.rabbitmq.exchange.topic.text}")
    private String textTopicExchangeName;

    @Value("${spring.rabbitmq.queue.text1}")
    private String textQueue1Name;

    @Value("${spring.rabbitmq.queue.text2}")
    private String textQueue2Name;

    @Value("${spring.rabbitmq.queue.text3}")
    private String textQueue3Name;

    /**
     * Exchange
     */
    @Bean
    public DirectExchange textDirectExchange(){
        return new DirectExchange(textDirectExchangeName, false, true);
    }

    @Bean
    public FanoutExchange textFanoutExchange(){
        return new FanoutExchange(textFanoutExchangeName, false, true);
    }

    @Bean
    public TopicExchange textTopicExchange(){
        return new TopicExchange(textTopicExchangeName, false, true);
    }

    /**
     * Queue
     */
    @Bean
    public Queue textQueue1(){
        return new Queue(textQueue1Name, false, false, false, null);
    }

    @Bean
    public Queue textQueue2(){
        return new Queue(textQueue2Name, false, false, false, null);
    }

    @Bean
    public Queue textQueue3(){
        return new Queue(textQueue3Name, false, false, false, null);
    }

    /**
     * Binding
     */
    @Bean
    public Binding textQueue1ToTextDirectExchange(DirectExchange textDirectExchange, Queue textQueue1){
        return BindingBuilder.bind(textQueue1).to(textDirectExchange).with("q1");
    }

    @Bean
    public Binding textQueue2ToTextDirectExchange(DirectExchange textDirectExchange, Queue textQueue2){
        return BindingBuilder.bind(textQueue2).to(textDirectExchange).with("q2");
    }

    @Bean
    public Binding textQueue3ToTextDirectExchange(DirectExchange textDirectExchange, Queue textQueue3){
        return BindingBuilder.bind(textQueue3).to(textDirectExchange).with("q3");
    }

    @Bean
    public Binding textQueue1ToTextFanoutExchange(FanoutExchange textFanoutExchange, Queue textQueue1){
        return BindingBuilder.bind(textQueue1).to(textFanoutExchange);
    }

    @Bean
    public Binding textQueue2ToTextFanoutExchange(FanoutExchange textFanoutExchange, Queue textQueue2){
        return BindingBuilder.bind(textQueue2).to(textFanoutExchange);
    }

    @Bean
    public Binding textQueue3ToTextFanoutExchange(FanoutExchange textFanoutExchange, Queue textQueue3){
        return BindingBuilder.bind(textQueue3).to(textFanoutExchange);
    }

    @Bean
    public Binding textQueue1ToTextTopicExchange(TopicExchange textTopicExchange, Queue textQueue1){
        return BindingBuilder.bind(textQueue1).to(textTopicExchange).with("white.*");
    }

    @Bean
    public Binding textQueue2ToTextTopicExchange(TopicExchange textTopicExchange, Queue textQueue2){
        return BindingBuilder.bind(textQueue2).to(textTopicExchange).with("white.dog");
    }

    @Bean
    public Binding textQueue3ToTextTopicExchange(TopicExchange textTopicExchange, Queue textQueue3){
        return BindingBuilder.bind(textQueue3).to(textTopicExchange).with("*.dog");
    }

    /**
     * Consumer
     */
    @Bean
    public TextConsumer consumer1(){
        return new TextConsumer();
    }

    @Bean
    public TextConsumer consumer2(){
        return new TextConsumer();
    }

    @Bean
    public TextConsumer consumer3(){
        return new TextConsumer();
    }
}
