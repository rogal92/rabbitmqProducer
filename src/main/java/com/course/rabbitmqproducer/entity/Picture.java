package com.course.rabbitmqproducer.entity;

import lombok.ToString;
import lombok.Getter;
import lombok.Setter;

@ToString
@Setter
@Getter
public class Picture {
    private String name;
    private String source;
    private String type;
    private long size;
}
