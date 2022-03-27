package com.springbootmongodb;

import com.springbootmongodb.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/*
* Our class MdbSpringBootApplication implements the CommandLineRunner interface to run the spring application. ItemRepository is Autowired, allowing Spring to find it automatically. Spring initializes the Application Context using the @SpringBootApplication annotation. We also activate the Mongo Repositories using @EnableMongoRepositories
* */
@SpringBootApplication
@EnableMongoRepositories
public class SpringBootMongodbApplication implements CommandLineRunner {
    @Autowired
    ItemRepository groceryItemRepo;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMongodbApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
