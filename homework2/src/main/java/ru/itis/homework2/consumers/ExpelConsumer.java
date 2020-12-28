package ru.itis.homework2.consumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import ru.itis.homework2.model.User;
import ru.itis.homework2.service.PDFProducer;
import ru.itis.homework2.service.PDFProducerImpl;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Service
@DependsOn("Producer")
public class ExpelConsumer {
    private final static String EXCHANGE_NAME = "documents";
    private final static String ROUTING_KEY = "documents.expel";

    @PostConstruct
    public void start() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        PDFProducer pdfProducer = new PDFProducerImpl();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.basicQos(3);
            String queue = channel.queueDeclare().getQueue();

            channel.queueBind(queue, EXCHANGE_NAME, ROUTING_KEY);

            DeliverCallback deliverCallback = (consumerTag, message) -> {
                User user = objectMapper.readValue(message.getBody(), User.class);
                pdfProducer.produce("Academical vacation application", "I, " +
                        user.getName() + " " + user.getSurname() + " " + user.getAge() + " y.o. "
                        + " passport number " + user.getPasswordNumber() + " date: " + user.getPassportDate()
                        + " application for the leave.", "Expel");
            };
            channel.basicConsume(queue, true, deliverCallback, consumerTag -> {
            });

        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
