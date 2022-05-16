package com.sabitov.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Map;

@Data
@Entity
public class Cabinet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int number;

    private String office;

    @Transient
    private Map<Calendar, Boolean> schedule;

}
