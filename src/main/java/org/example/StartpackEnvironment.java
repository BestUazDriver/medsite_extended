package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
public class StartpackEnvironment {
    @Autowired
    private ResourceBundleMessageSource messageSource;

    public String getLanguage(){
        return "ja_JP";
    }

    public String getMessageSource(){
        return messageSource.toString();
    }
}
