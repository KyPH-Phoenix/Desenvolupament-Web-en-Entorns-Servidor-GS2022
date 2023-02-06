package com.liceu.objects;

import com.liceu.objects.interceptor.AlreadyLoggedInterceptor;
import com.liceu.objects.interceptor.LoginInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@SpringBootApplication
public class ObjectsApplication implements WebMvcConfigurer {
	public static void main(String[] args) {
		SpringApplication.run(ObjectsApplication.class, args);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns(List.of("/objects/**", "/settings", "/download/**"));
		registry.addInterceptor(new AlreadyLoggedInterceptor()).addPathPatterns(List.of("/login", "/signup"));
	}
}
