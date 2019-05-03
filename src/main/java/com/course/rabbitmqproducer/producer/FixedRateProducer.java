package com.course.rabbitmqproducer.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class FixedRateProducer {

    private static final Logger LOG = LoggerFactory.getLogger("FixedRateProducer");

    private int i;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Scheduled(fixedRate = 500)
    public void sendMessage() {
        i++;
        LOG.info("Msg number: " + i);
        rabbitTemplate.convertAndSend("course.fixedrate", "Fixed rate: " + i);
    }
}
