package ru.itis.consumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import ru.itis.model.User;
import ru.itis.service.PDFProducer;
import ru.itis.service.PDFProducerImpl;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class FireConsumer {
    private final static String EXCHANGE_NAME = "Documents";
    private final static String EXCHANGE_TYPE = "fanout";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        PDFProducer pdfProducer = new PDFProducerImpl();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.basicQos(3);
            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);
            String queue = channel.queueDeclare().getQueue();

            channel.queueBind(queue, EXCHANGE_NAME, "");

            DeliverCallback deliverCallback = (consumerTag, message) -> {
                User user = objectMapper.readValue(message.getBody(), User.class);
                pdfProducer.produce("Fire application", "I, " +
                        user.getName() + " " + user.getSurname() + " " + user.getAge() + " y.o. "
                        + " passport number " + user.getPasswordNumber() + " date: " + user.getPassportDate()
                        + " ask for resignation", "Fire");
            };
            channel.basicConsume(queue, false, deliverCallback, consumerTag -> {});

        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
