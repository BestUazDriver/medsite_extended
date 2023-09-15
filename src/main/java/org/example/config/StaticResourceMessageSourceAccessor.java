package org.example.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class StaticResourceMessageSourceAccessor implements InitializingBean {

    @Autowired
    private Locale locale;
    private static ResourceBundleMessageSource messageSource;

    public static ResourceBundleMessageSource get(){
        return messageSource;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("messages/date");
        messageSource.setUseCodeAsDefaultMessage(true);
//        messageSource.setDefaultLocale(locale);
        StaticResourceMessageSourceAccessor.messageSource = messageSource;
    }
}
