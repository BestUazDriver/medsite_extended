package org.example.config;

import org.example.StartpackEnvironment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import java.util.Locale;

@Configuration
@ComponentScan(basePackages = "org.example")
public class ApplicationConfig {

    @Bean
    public ResourceBundleMessageSource messageSource() {

        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasenames("messages/date");
        source.setUseCodeAsDefaultMessage(true);

        return source;
    }

//    @Bean
//    public Locale locale(){
//        return new Locale("ja_JP");
//    }

}
