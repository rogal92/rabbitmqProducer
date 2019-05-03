package com.course.rabbitmqproducer.producer.picture;

import com.course.rabbitmqproducer.entity.Picture;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RAWPictureProducer implements PicProducer {

    private static final Logger LOG = LoggerFactory.getLogger("RetryPictureProducer");

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void sendMessage(final Picture picture) {
        String json = "";
        try {
            json = objectMapper.writeValueAsString(picture);
        } catch (JsonProcessingException e) {
            LOG.warn(String.format("Exception caught: %s", e.getMessage()));
        }
        rabbitTemplate.convertAndSend("mypicutre.image", picture.getType(), json);
    }
}
