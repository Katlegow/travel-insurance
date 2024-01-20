package com.kmsolutions.travelinsurance.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "hepstar")
public class IntegrationProperties {
    private String channel;
    private String username;
    private String password;
    private String currency;
}
