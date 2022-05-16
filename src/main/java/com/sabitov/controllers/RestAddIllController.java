package com.sabitov.controllers;

import com.sabitov.dto.IllDto;
import com.sabitov.models.Ill;
import com.sabitov.services.IllService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class RestAddIllController {

    private final IllService illService;

    @PostMapping("/add_ill/upload")
    public ResponseEntity<?> get(IllDto illDto) {
        String name = illDto.getName();
        System.err.println(name);
        if (name == null) {
            return new ResponseEntity<>("Empty name", HttpStatus.BAD_REQUEST);
        }
        Ill ill = illService.findIllByName(name);
        return new ResponseEntity<>("Added ill: " + ill.getName(), HttpStatus.OK);
    }
}
