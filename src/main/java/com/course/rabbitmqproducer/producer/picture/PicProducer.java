package com.course.rabbitmqproducer.producer.picture;

import com.course.rabbitmqproducer.entity.Picture;

public interface PicProducer {

    void sendMessage(final Picture picture);
}
