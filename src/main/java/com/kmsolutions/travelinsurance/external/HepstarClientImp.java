package com.kmsolutions.travelinsurance.external;

import com.kmsolutions.travelinsurance.model.Authentication;
import com.kmsolutions.travelinsurance.model.dto.PolicyIssueRequest;
import com.kmsolutions.travelinsurance.model.dto.PolicyRequestParameters;
import com.kmsolutions.travelinsurance.model.dto.ProductPriceRequest;
import com.kmsolutions.travelinsurance.model.dto.RequestParameters;
import com.kmsolutions.travelinsurance.model.dto.response.PolicyResponse;
import com.kmsolutions.travelinsurance.model.dto.response.Response;
import com.kmsolutions.travelinsurance.properties.IntegrationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
@Profile("production")
public class HepstarClientImp implements HepstarClient {
    private final IntegrationProperties properties;
    private final RestTemplate restTemplate;

    @Autowired
    public HepstarClientImp(IntegrationProperties properties, RestTemplate restTemplate) {
        this.properties = properties;
        this.restTemplate = restTemplate;
    }

    @Override
    public Response getProductPrice(RequestParameters requestParameters) {
        ProductPriceRequest request = new ProductPriceRequest(getAuth(), requestParameters);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML_VALUE);
        HttpEntity<ProductPriceRequest> requestHttpEntity = new HttpEntity<>(request, headers);
        ResponseEntity<Response> response = restTemplate.exchange(
                "https://uat.gateway.insure/products/priced",
                HttpMethod.POST,
                requestHttpEntity,
                Response.class
        );
        return response.getBody();
    }

    @Override
    public PolicyResponse issuePolicy(PolicyRequestParameters policyIssueRequest) {
        Authentication auth = getAuth();
        PolicyIssueRequest request = new PolicyIssueRequest(auth, policyIssueRequest);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML_VALUE);
        headers.add(HttpHeaders.ACCEPT, "*/*");

        HttpEntity<PolicyIssueRequest> requestHttpEntity = new HttpEntity<>(request, headers);
        ResponseEntity<PolicyResponse> response;

        try {
            response = restTemplate.exchange(
                    "https://uat.gateway.insure/policy/issue",
                    HttpMethod.POST,
                    requestHttpEntity,
                    PolicyResponse.class
            );
        } catch (HttpClientErrorException ex) {
            throw new RuntimeException(ex);
        }

        return response.getBody();
    }

    private Authentication getAuth() {
        return new Authentication(
                properties.getChannel(),
                properties.getUsername(),
                properties.getPassword(),
                properties.getCurrency()
        );
    }
}
