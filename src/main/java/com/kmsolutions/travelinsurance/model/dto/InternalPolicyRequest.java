package com.kmsolutions.travelinsurance.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InternalPolicyRequest {
    PolicyRequestParameters requestParameters;
}
