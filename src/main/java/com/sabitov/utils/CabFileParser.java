package com.sabitov.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CabFileParser {

    private final String path = "offices/cab_inf.txt";

    public List<String> getOffices() {
        List<String> offices = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                offices.add(line);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        return offices;
    }
}
