package com.kmsolutions.travelinsurance.model.dto;

import com.kmsolutions.travelinsurance.model.dto.response.PolicyResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InternalPolicyResponse {
    PolicyResponse response;
}
