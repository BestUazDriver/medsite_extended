package org.example.services;

import org.example.config.StaticResourceMessageSourceAccessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Locale;

public class StaticMethodsClass {
    public static String getBean(){
        return StaticResourceMessageSourceAccessor.get().toString();
    }
}
