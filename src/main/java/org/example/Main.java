package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean(name = "locale")
    public Locale locale() {
        String[] localeSplitted = new StartpackEnvironment().getLanguage().split("_");
        return new Locale(localeSplitted[0], localeSplitted[1]);
    }

    @Bean
    public FixedLocaleResolver localeResolver(){
        FixedLocaleResolver fixedLocaleResolver = new FixedLocaleResolver();
        fixedLocaleResolver.setDefaultLocale(locale());
        return fixedLocaleResolver;
    }
}