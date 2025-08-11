package com.example.demo.persistence.entity;

import java.math.BigDecimal;

import com.mysql.cj.protocol.a.OffsetDateTimeValueEncoder;

import lombok.Data;

@Data
public class EmployeeEntity {
    
    private long id;
    private String name;
    private java.time.OffsetDateTime birthday;
    private BigDecimal salary;

    
}
