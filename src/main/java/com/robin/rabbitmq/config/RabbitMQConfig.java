package com.robin.rabbitmq.config;

import com.robin.rabbitmq.mq.consumers.EmployeeConsumer;
import com.robin.rabbitmq.mq.consumers.TextConsumer;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
public class RabbitMQConfig {

    @Value("${spring.rabbitmq.exchange.direct.text}")
    private String textDirectExchangeName;

    @Value("${spring.rabbitmq.exchange.fanout.text}")
    private String textFanoutExchangeName;

    @Value("${spring.rabbitmq.exchange.topic.text}")
    private String textTopicExchangeName;

    @Value("${spring.rabbitmq.exchange.headers.text}")
    private String textHeadersExchangeName;

    @Value("${spring.rabbitmq.queue.text1}")
    private String textQueue1Name;

    @Value("${spring.rabbitmq.queue.text2}")
    private String textQueue2Name;

    @Value("${spring.rabbitmq.queue.text3}")
    private String textQueue3Name;

    @Value("${spring.rabbitmq.queue.text4}")
    private String textQueue4Name;

    @Value("${spring.rabbitmq.exchange.direct.employee}")
    private String employeeDirectExchangeName;

    @Value("${spring.rabbitmq.exchange.fanout.employee}")
    private String employeeFanoutExchangeName;

    @Value("${spring.rabbitmq.exchange.topic.employee}")
    private String employeeTopicExchangeName;

    @Value("${spring.rabbitmq.exchange.headers.employee}")
    private String employeeHeadersExchangeName;

    @Value("${spring.rabbitmq.queue.employee1}")
    private String employeeQueue11Name;

    @Value("${spring.rabbitmq.queue.employee2}")
    private String employeeQueue2Name;

    @Value("${spring.rabbitmq.queue.employee3}")
    private String employeeQueue3Name;

    @Bean
    public SimpleMessageConverter converter() {
        SimpleMessageConverter converter = new SimpleMessageConverter();
        converter.setAllowedListPatterns(List.of("com.robin.rabbitmq.domains.*"));
        return converter;
    }

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

    @Bean
    public HeadersExchange textHeadersExchange() {
        return new HeadersExchange(textHeadersExchangeName, false, true);
    }

    @Bean
    public DirectExchange employeeDirectExchange(){
        return new DirectExchange(employeeDirectExchangeName, false, true);
    }

    @Bean
    public FanoutExchange employeeFanoutExchange(){
        return new FanoutExchange(employeeFanoutExchangeName, false, true);
    }

    @Bean
    public TopicExchange employeeTopicExchange(){
        return new TopicExchange(employeeTopicExchangeName, false, true);
    }

    @Bean
    public HeadersExchange employeeHeadersExchange() {
        return new HeadersExchange(employeeHeadersExchangeName, false, true);
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

    @Bean
    public Queue textQueue4(){
        return new Queue(textQueue4Name, false, false, false, null);
    }

    @Bean
    public Queue employeeQueue1(){
        return new Queue(employeeQueue11Name, false, false, false, null);
    }

    @Bean
    public Queue employeeQueue2(){
        return new Queue(employeeQueue2Name, false, false, false, null);
    }

    @Bean
    public Queue employeeQueue3(){
        return new Queue(employeeQueue3Name, false, false, false, null);
    }

    /**
     * Binding
     */
    //Text Direct Exchange
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

    //Text Fanout Exchange
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

    //Text Topic Exchange
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

    @Bean
    public Binding textQueue3ToTextTopicExchange2(TopicExchange textTopicExchange, Queue textQueue3){
        //A queue can have any number of bindings to different exchanges, even multiple bindings to the same exchange with different parameters
        return BindingBuilder.bind(textQueue3).to(textTopicExchange).with("white.*");
    }

    @Bean
    public Binding textQueue4ToTextTopicExchange(TopicExchange textTopicExchange, Queue textQueue4){
        //When a queue is bound with "#" (hash) binding key - it will receive all the messages, regardless of the routing key - like in fanout exchange.
        return BindingBuilder.bind(textQueue4).to(textTopicExchange).with("#");
    }

    //Text Headers Exchange
    @Bean
    public Binding textQueue1ToTextHeadersExchange(HeadersExchange textHeadersExchange, Queue textQueue1){
        return BindingBuilder.bind(textQueue1).to(textHeadersExchange).whereAny(Map.of("header-test", 123)).match();
    }

    @Bean
    public Binding textQueue2ToTextHeadersExchange(HeadersExchange textHeadersExchange, Queue textQueue2){
        return BindingBuilder.bind(textQueue2).to(textHeadersExchange).whereAny(Map.of("header-test", 456)).match();
    }

    @Bean
    public Binding textQueue3ToTextHeadersExchange(HeadersExchange textHeadersExchange, Queue textQueue3){
        return BindingBuilder.bind(textQueue3).to(textHeadersExchange).whereAny(Map.of("header-test", 789)).match();
    }

    //Employee Direct Exchange
    @Bean
    public Binding employeeQueue1ToEmployeeDirectExchange(DirectExchange employeeDirectExchange, Queue employeeQueue1){
        return BindingBuilder.bind(employeeQueue1).to(employeeDirectExchange).with("e1");
    }

    @Bean
    public Binding employeeQueue2ToEmployeeDirectExchange(DirectExchange employeeDirectExchange, Queue employeeQueue2){
        return BindingBuilder.bind(employeeQueue2).to(employeeDirectExchange).with("e2");
    }

    @Bean
    public Binding employeeQueue3ToEmployeeDirectExchange(DirectExchange employeeDirectExchange, Queue employeeQueue3){
        return BindingBuilder.bind(employeeQueue3).to(employeeDirectExchange).with("e3");
    }

    //Employee Fanout Exchange
    @Bean
    public Binding employeeQueue1ToEmployeeFanoutExchange(FanoutExchange employeeFanoutExchange, Queue employeeQueue1){
        return BindingBuilder.bind(employeeQueue1).to(employeeFanoutExchange);
    }

    @Bean
    public Binding employeeQueue2ToEmployeeFanoutExchange(FanoutExchange employeeFanoutExchange, Queue employeeQueue2){
        return BindingBuilder.bind(employeeQueue2).to(employeeFanoutExchange);
    }

    //Employee Topic Exchange
    @Bean
    public Binding employeeQueue1ToEmployeeTopicExchange(TopicExchange employeeTopicExchange, Queue employeeQueue1){
        return BindingBuilder.bind(employeeQueue1).to(employeeTopicExchange).with("junior.*");
    }

    @Bean
    public Binding employeeQueue2ToEmployeeTopicExchange(TopicExchange employeeTopicExchange, Queue employeeQueue2){
        return BindingBuilder.bind(employeeQueue2).to(employeeTopicExchange).with("senior.*");
    }

    @Bean
    public Binding employeeQueue3ToEmployeeTopicExchange(TopicExchange employeeTopicExchange, Queue employeeQueue3){
        return BindingBuilder.bind(employeeQueue3).to(employeeTopicExchange).with("staff.*");
    }

    //Text Headers Exchange
    @Bean
    public Binding employeeQueue1ToEmployeeHeadersExchange(HeadersExchange employeeHeadersExchange, Queue employeeQueue1){
        return BindingBuilder.bind(employeeQueue1).to(employeeHeadersExchange).whereAny(Map.of("header-test", 123)).match();
    }

    @Bean
    public Binding employeeQueue2ToEmployeeHeadersExchange(HeadersExchange employeeHeadersExchange, Queue employeeQueue2){
        return BindingBuilder.bind(employeeQueue2).to(employeeHeadersExchange).whereAny(Map.of("header-test", 456)).match();
    }

    @Bean
    public Binding employeeQueue3ToEmployeeHeadersExchange(HeadersExchange employeeHeadersExchange, Queue employeeQueue3){
        return BindingBuilder.bind(employeeQueue3).to(employeeHeadersExchange).whereAny(Map.of("header-test", 789)).match();
    }

    /**
     * Consumer
     */

    @Bean
    public TextConsumer textConsumer1(){
        return new TextConsumer();
    }

    @Bean
    public TextConsumer textConsumer2(){
        return new TextConsumer();
    }

    @Bean
    public TextConsumer textConsumer3(){
        return new TextConsumer();
    }

    @Bean
    public EmployeeConsumer employeeConsumer1(){
        return new EmployeeConsumer();
    }

    @Bean
    public EmployeeConsumer employeeConsumer2(){
        return new EmployeeConsumer();
    }

    @Bean
    public EmployeeConsumer employeeConsumer3(){
        return new EmployeeConsumer();
    }
}
