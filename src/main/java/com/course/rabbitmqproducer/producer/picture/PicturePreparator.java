package com.course.rabbitmqproducer.producer.picture;

import com.course.rabbitmqproducer.entity.Picture;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

@Component
class PicturePreparator {

    private final List<String> pictureSources = Arrays.asList("mobile", "web");
    private final List<String> pictureTypes = Arrays.asList("png", "jpg", "svg");

    void sendpictures(final int amountOfPictures, final PicProducer picProducer) {
        IntStream.range(BigDecimal.ZERO.intValue(), amountOfPictures)
                .forEach(value -> this.prepareAndSendSinglePic(value, picProducer));
    }

    private void prepareAndSendSinglePic(final int pictureNumber, final PicProducer picProducer) {
        final Picture p = new Picture();
        p.setName("Picture " + pictureNumber);
        p.setSize(ThreadLocalRandom.current().nextLong(100, 900));
        p.setSource(pictureSources.get(pictureNumber % pictureSources.size()));
        p.setType(pictureTypes.get(pictureNumber % pictureTypes.size()));
        picProducer.sendMessage(p);
    }
}
