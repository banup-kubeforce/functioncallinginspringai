package org.ibm.functionsinspringai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@EnableConfigurationProperties(WeatherConfigProperties.class)
@SpringBootApplication
public class FunctionsInSpringAiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FunctionsInSpringAiApplication.class, args);
    }

}
