package com.scrum.challenge;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.format.Formatter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class App	{
	
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
    }
    
    @Bean
    public LocaleResolver localeResolver() {
    	SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
    	sessionLocaleResolver.setDefaultLocale(Locale.US);
    	return sessionLocaleResolver;
    }
    
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
    	LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
    	localeChangeInterceptor.setParamName("locale");
    	return localeChangeInterceptor;
    }
    
    @Bean
    public Formatter<LocalDateTime> formatter() {
    	return new Formatter<LocalDateTime>() {
    		@Override
    		public String print(LocalDateTime object, Locale locale) {
    			return DateTimeFormatter.ISO_DATE_TIME.format(object);
    		}
    		
    		@Override
    		public LocalDateTime parse(String text, Locale locale) throws ParseException {
    			return LocalDateTime.parse(text, DateTimeFormatter.ISO_DATE_TIME);
    		}
    		
    	};
    }
    
}
