package com.example.chickensoup;

import org.jfree.chart.servlet.DisplayChart;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class ChickenSoupApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChickenSoupApplication.class, args);
    }
    @Bean
    public ServletRegistrationBean Servlet() {
        return new ServletRegistrationBean<>(new DisplayChart(),"/chart");
    }
}
