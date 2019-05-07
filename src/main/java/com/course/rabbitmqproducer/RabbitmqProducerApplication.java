package com.course.rabbitmqproducer;

import com.course.rabbitmqproducer.entity.Employee;
import com.course.rabbitmqproducer.producer.*;
import com.course.rabbitmqproducer.producer.picture.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootApplication
//@EnableScheduling
public class RabbitmqProducerApplication implements CommandLineRunner {

    @Autowired
    private HelloRabbitProducer rabbitProducer;

    @Autowired
    private EmployeeJsonProducer employeeJsonProducer;

    @Autowired
    private HumanResourceProducer humanResourceProducer;

    @Autowired
    private PictureSender pictureSender;

    private final List<String> pictureTypes = Arrays.asList("png", "jpg", "svg");
    private final List<String> pictureSources = Arrays.asList("mobile", "web");

    private static final Logger LOG = LoggerFactory.getLogger("RabbitMQProducerApplication");

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqProducerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        pictureSender.sendPictures();
    }

    private void sendJsonMsgs() {
        IntStream.range(0, 5)
                .forEach(i -> {
                    Employee emp = new Employee("emp" + i, "Employee " + i, new Date());
                    try {
                        employeeJsonProducer.sendMessage(emp);
                    } catch (JsonProcessingException e) {
                        LOG.warn(String.format("Error during sending %s. Exception: %s", emp, e));
                    }
                });
    }


    private void sendWithExchange() {
        IntStream.range(0, 5)
                .forEach(i -> {
                    Employee emp = new Employee("emp" + i, "Employee " + i, new Date());
                    try {
                        humanResourceProducer.sendMessage(emp);
                    } catch (JsonProcessingException e) {
                        LOG.warn(String.format("Error during sending %s. Exception: %s", emp, e));
                    }
                });
    }


    private void sendHello() {
        rabbitProducer.sendMessage("Bartosz " + Math.random());
    }
}
