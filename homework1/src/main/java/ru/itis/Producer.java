package ru.itis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import ru.itis.model.User;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class Producer {
    private final static String EXCHANGE_NAME = "Documents";
    private final static String EXCHANGE_TYPE = "fanout";


    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);

            Scanner scanner = new Scanner(System.in);
            boolean flag = true;
            while (flag) {
                System.out.println("Enter your name: ");
                String name = scanner.nextLine();
                if (name.equals("exit")) {
                    flag = false;
                }
                System.out.println("Enter your surname: ");
                String surname = scanner.nextLine();
                if (surname.equals("exit")) {
                    flag = false;
                }
                System.out.println("Enter your passport number: ");
                String passportNumber = scanner.nextLine();
                if (passportNumber.equals("exit")) {
                    flag = false;
                }
                System.out.println("Enter your age: ");
                String age = scanner.nextLine();
                if (age.equals("exit")) {
                    flag = false;
                }
                System.out.println("Enter passport date: ");
                String passportDate = scanner.nextLine();
                if (passportDate.equals("exit")) {
                    flag = false;
                }

                User user = new User(name, surname, passportNumber, age, passportDate);
                channel.basicPublish(EXCHANGE_NAME, "", null, objectMapper.writeValueAsString(user).getBytes());
            }
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
