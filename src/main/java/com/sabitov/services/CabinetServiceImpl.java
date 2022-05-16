package com.sabitov.services;

import com.sabitov.models.Cabinet;
import com.sabitov.repositories.CabinetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class CabinetServiceImpl implements CabinetService {

    private final CabinetRepository officeRepository;

    @Override
    public List<Cabinet> findAll() {
        return officeRepository.findAll();
    }

    @Override
    public List<Cabinet> findAllByAddress(String address) {
        return officeRepository.findAllByOffice(address);
    }

    @Override
    public Map<Calendar, Boolean> freeScheduleByAddress(String address, Calendar date) {
        List<Cabinet> cabinets = officeRepository.findAllByOffice(address);

        return null;
    }
}
