package com.kmsolutions.travelinsurance.model.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "PolicyRequests")
@XmlAccessorType(XmlAccessType.FIELD)
public class PolicyRequests implements Serializable {
    @XmlElement(name = "PolicyRequest") List<PolicyRequest> policyRequest;
}
