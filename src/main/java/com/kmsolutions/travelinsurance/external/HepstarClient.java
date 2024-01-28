package com.kmsolutions.travelinsurance.external;

import com.kmsolutions.travelinsurance.model.dto.PolicyRequestParameters;
import com.kmsolutions.travelinsurance.model.dto.RequestParameters;
import com.kmsolutions.travelinsurance.model.dto.response.PolicyResponse;
import com.kmsolutions.travelinsurance.model.dto.response.Response;

public interface HepstarClient {
    Response getProductPrice(RequestParameters requestParameters);
    PolicyResponse issuePolicy(PolicyRequestParameters policyIssueRequest);
}
