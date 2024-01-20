package com.kmsolutions.travelinsurance;

import com.kmsolutions.travelinsurance.properties.IntegrationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({IntegrationProperties.class})
public class TravelInsuranceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelInsuranceApplication.class, args);
    }

}
