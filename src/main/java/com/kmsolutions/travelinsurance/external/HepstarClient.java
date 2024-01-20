package com.kmsolutions.travelinsurance.external;

import com.kmsolutions.travelinsurance.properties.IntegrationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HepstarClient {
    private final IntegrationProperties properties;
    private final RestTemplate restTemplate;

    @Autowired
    public HepstarClient(IntegrationProperties properties, RestTemplate restTemplate) {
        this.properties = properties;
        this.restTemplate = restTemplate;
    }
}
