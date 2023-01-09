package com.liceu.ExempleSpring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@org.springframework.context.annotation.Configuration
@ComponentScan("com.esliceu.springdi")
public class Configuration {
    @Bean(name = "myservice")
    public MyService getService(){
        return new MyService();
    }
}