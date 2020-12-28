package ru.itis.homework2.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.homework2.model.User;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component(value = "Producer")
public class Producer {
    private final static String EXCHANGE_NAME = "documents";
    private final static String EXCHANGE_TYPE = "fanout";
    private final static String DIRECT_TYPE = "direct";
    private final static String TOPIC_TYPE = "topic";
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private ConnectionFactory connectionFactory;

    private Channel topic;

    @PostConstruct
    public void start() throws IOException, TimeoutException {
        Connection topicConnection = connectionFactory.newConnection();
        topic = topicConnection.createChannel();
        topic.exchangeDeclare(EXCHANGE_NAME, TOPIC_TYPE);

    }

    public void send(User user, String queue) {
        try {
            topic.basicPublish(EXCHANGE_NAME, "documents." + queue, null, objectMapper.writeValueAsString(user).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
