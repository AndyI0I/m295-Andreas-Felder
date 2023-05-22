package ch.ubs.m295.demo;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class M295Application {

    @Autowired


    public static void main(String[] args) {
        SpringApplication.run(M295Application.class, args);

    }
}