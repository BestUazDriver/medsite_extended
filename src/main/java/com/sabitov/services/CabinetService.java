package com.sabitov.services;

import com.sabitov.models.Cabinet;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

public interface CabinetService {
    List<Cabinet> findAll();
    List<Cabinet> findAllByAddress(String address);
    Map<Calendar, Boolean> freeScheduleByAddress(String address, Calendar date);

}
