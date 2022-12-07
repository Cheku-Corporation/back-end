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

import com.cheku.cheku.rabbitmq.Velocity;
import com.cheku.cheku.rabbitmq.Fluids;

@Configuration
public class Initialization {

    static final String topicExchangeName = "";

  static final String queueName1 = "velocities";
  static final String queueName2 = "fluids";
  static final String queueName3 = "location";
  static final String queueName4 = "miscelaneous";

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
  MessageListenerAdapter listenerAdapter1(Velocity receiver) {
    return new MessageListenerAdapter(receiver, "receiveMessage");
  }

  @Bean
  MessageListenerAdapter listenerAdapter2(Fluids receiver) {
    return new MessageListenerAdapter(receiver, "receiveMessage");
  }
}