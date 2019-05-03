package com.course.rabbitmqproducer.producer.picture;

import com.course.rabbitmqproducer.entity.Picture;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class HDRPictureProducer implements PicProducer{

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void sendMessage(final Picture picture) {
        String jsonForPicture = null;
        try {
            jsonForPicture = objectMapper.writeValueAsString(picture);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        rabbitTemplate.convertAndSend("x.picture2", getRoutingKey(picture), jsonForPicture);
    }

    private String getRoutingKey(final Picture picture) {
        StringBuilder sb = new StringBuilder();
        sb.append(picture.getSource())
                .append('.');

        if (picture.getSize() > 4000)
            sb.append("large");
        else
            sb.append("small");

        sb.append('.');
        sb.append(picture.getType());

        return sb.toString();
    }
}
