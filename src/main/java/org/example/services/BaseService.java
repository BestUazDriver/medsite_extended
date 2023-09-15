package org.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service("service")
public class BaseService {

    @Autowired
    private Locale locale;

    public String getCurrentLocale(){
        return locale.toString();
    }
}
