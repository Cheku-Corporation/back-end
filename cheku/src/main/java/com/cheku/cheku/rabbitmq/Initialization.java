package com.cheku.cheku.rabbitmq;

import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;

import org.springframework.context.annotation.Bean;


@Configuration
public class Initialization {

  static final String topicExchangeName = "car";

  static final String queueName1 = "velocities";
  static final String queueName2 = "fluids";
  static final String queueName3 = "car_status";
  static final String queueName4 = "lights_status";
  static final String queueName5 = "tires_status";
  static final String queueName6 = "coordinates";

  @Bean
  Queue queue1() {
    return new Queue(queueName1, false);
  }

  @Bean
  Queue queue2() {
    return new Queue(queueName2, false);
  }

  @Bean
  Queue queue3() {
    return new Queue(queueName3, false);
  }

  @Bean
  Queue queue4() {
    return new Queue(queueName4, false);
  }

  @Bean
  Queue queue5() {
    return new Queue(queueName5, false);
  }

  @Bean
  Queue queue6() {
    return new Queue(queueName6, false);
  }

  @Bean
  TopicExchange exchange() {
    return new TopicExchange(topicExchangeName);
  }

  @Bean
  Binding binding1(Queue queue1, TopicExchange exchange) {
    return BindingBuilder.bind(queue1).to(exchange).with(queueName1);
  }

  @Bean
  Binding binding2(Queue queue2, TopicExchange exchange) {
    return BindingBuilder.bind(queue2).to(exchange).with(queueName2);
  }

  @Bean
  Binding binding3(Queue queue3, TopicExchange exchange) {
    return BindingBuilder.bind(queue3).to(exchange).with(queueName3);
  }

  @Bean
  Binding binding4(Queue queue4, TopicExchange exchange) {
    return BindingBuilder.bind(queue4).to(exchange).with(queueName4);
  }

  @Bean
  Binding binding5(Queue queue5, TopicExchange exchange) {
    return BindingBuilder.bind(queue5).to(exchange).with(queueName5);
  }

  @Bean
  Binding binding6(Queue queue6, TopicExchange exchange) {
    return BindingBuilder.bind(queue6).to(exchange).with(queueName6);
  }

  @Bean
  SimpleMessageListenerContainer container1(ConnectionFactory connectionFactory,
      MessageListenerAdapter listenerAdapter1) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueueNames(queueName1);
    container.setMessageListener(listenerAdapter1);
    return container;
  }

  @Bean
  SimpleMessageListenerContainer container2(ConnectionFactory connectionFactory,
      MessageListenerAdapter listenerAdapter2) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueueNames(queueName2);
    container.setMessageListener(listenerAdapter2);
    return container;
  }

  @Bean
  SimpleMessageListenerContainer container3(ConnectionFactory connectionFactory,
      MessageListenerAdapter listenerAdapter3) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueueNames(queueName3);
    container.setMessageListener(listenerAdapter3);
    return container;
  }

  @Bean
  SimpleMessageListenerContainer container4(ConnectionFactory connectionFactory,
      MessageListenerAdapter listenerAdapter4) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueueNames(queueName4);
    container.setMessageListener(listenerAdapter4);
    return container;
  }

  @Bean
  SimpleMessageListenerContainer container5(ConnectionFactory connectionFactory,
      MessageListenerAdapter listenerAdapter5) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueueNames(queueName5);
    container.setMessageListener(listenerAdapter5);
    return container;
  }

  @Bean
  SimpleMessageListenerContainer container6(ConnectionFactory connectionFactory,
      MessageListenerAdapter listenerAdapter6) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueueNames(queueName6);
    container.setMessageListener(listenerAdapter6);
    return container;
  }

  @Bean
  MessageListenerAdapter listenerAdapter1(Velocity receiver) {
    return new MessageListenerAdapter(receiver, "receiveMessage");
  }

  @Bean
  MessageListenerAdapter listenerAdapter2(Fluids receiver) {
    return new MessageListenerAdapter(receiver, "receiveMessage");
  }
  
  @Bean
  MessageListenerAdapter listenerAdapter3(CarStatus receiver) {
    return new MessageListenerAdapter(receiver, "receiveMessage");
  }

  @Bean
  MessageListenerAdapter listenerAdapter4(LightsStatus receiver) {
    return new MessageListenerAdapter(receiver, "receiveMessage");
  }

  @Bean
  MessageListenerAdapter listenerAdapter5(TiresStatus receiver) {
    return new MessageListenerAdapter(receiver, "receiveMessage");
  }

  @Bean
  MessageListenerAdapter listenerAdapter6(TripCoordinates receiver) {
    return new MessageListenerAdapter(receiver, "receiveMessage");
  }
}