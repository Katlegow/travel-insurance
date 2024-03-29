package com.kmsolutions.travelinsurance.model.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "RequestParameters")
@XmlAccessorType(XmlAccessType.FIELD)
public class PolicyRequestParameters implements Serializable {
    @XmlElement(name = "PolicyRequests") PolicyRequests policyRequests;
    @XmlElement(name = "Payment") Payment payment;
}
