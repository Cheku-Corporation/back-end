// package com.cheku.cheku.rabbitmq;

// import java.io.IOException;
// import java.util.concurrent.TimeoutException;

// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;

// import com.rabbitmq.client.Channel;
// import com.rabbitmq.client.Connection;
// import com.rabbitmq.client.ConnectionFactory;
// import com.rabbitmq.client.DeliverCallback;

// @Component
// public class Recv implements CommandLineRunner{
//     private final static String QUEUE_NAME = "car_0";

//     public void recv_msg() throws IOException, TimeoutException{
//         ConnectionFactory factory = new ConnectionFactory();
//         factory.setHost("localhost");
//         Connection connection = factory.newConnection();
//         Channel channel = connection.createChannel();

//         channel.queueDeclare(QUEUE_NAME, false, false, false, null);

//         System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

//         DeliverCallback deliverCallback = (consumerTag, delivery) -> {
//             String message = new String(delivery.getBody(), "UTF-8");
//             System.out.println(" [x] Received '" + message + "'");
//         };
//         channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
//     }

//     @Override
//     public void run(String... args) throws Exception {
//         recv_msg();
        
//     }
// }