package ru.itis.homework3;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Main {
    public static void main(String[] args) {
        MongoClient client = MongoClients.create();
        MongoDatabase database = client.getDatabase("test");
        MongoCollection<?> collection = database.getCollection("users");

        collection.find().forEach(System.out::println);
    }
}
