package com.robin.rabbitmq.domains;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Data
public class Employee implements Serializable {

    private Integer id;

    private String name;

    private Integer age;
}
