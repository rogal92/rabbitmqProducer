package com.course.rabbitmqproducer.producer.picture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureSender {

    @Autowired
    private HDRPictureProducer hdrPictureProducer;

    @Autowired
    private RAWPictureProducer rawPictureProducer;

    @Autowired
    private PicturePreparator picturePreparator;

    public void sendPictures() {
        picturePreparator.sendpictures(10, hdrPictureProducer);
    }

    public void sendmyPictures() {
        picturePreparator.sendpictures(8, rawPictureProducer);
    }
}
