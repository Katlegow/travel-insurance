package com.kmsolutions.travelinsurance.service;

import com.kmsolutions.travelinsurance.external.HepstarClient;
import com.kmsolutions.travelinsurance.model.dto.PolicyRequestParameters;
import com.kmsolutions.travelinsurance.model.dto.RequestParameters;
import com.kmsolutions.travelinsurance.model.dto.response.PolicyResponse;
import com.kmsolutions.travelinsurance.model.dto.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final HepstarClient hepstarClient;

    @Autowired
    public ProductService(HepstarClient hepstarClient) {
        this.hepstarClient = hepstarClient;
    }

    public Response getProductPrice(RequestParameters requestParameters) {
        return hepstarClient.getProductPrice(requestParameters);
    }

    public PolicyResponse issuePolicy(PolicyRequestParameters policyRequest) {
        return hepstarClient.issuePolicy(policyRequest);
    }
}
