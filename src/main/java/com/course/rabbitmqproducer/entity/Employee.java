package com.course.rabbitmqproducer.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.Setter

@AllArgsConstructor
@Getter
@Setter
public class Employee {
    @JsonProperty("emplooyee_id")
    private String employeeId;
    private String name;
    @JsonProperty("birth_date")
    @JsonFormat(pattern = "yyyy-MM--dd'T'HH:mm:ssZ", timezone = "Europe/Poland")
    private Date birthDate;
}
