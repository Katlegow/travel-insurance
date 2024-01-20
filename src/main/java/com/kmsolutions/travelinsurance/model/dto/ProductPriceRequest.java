package com.kmsolutions.travelinsurance.model.dto;

import com.kmsolutions.travelinsurance.model.Authentication;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@XmlRootElement(name = "Request")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
public class ProductPriceRequest {
        @XmlElement(name = "Authentication") Authentication authentication;
        @XmlElement(name = "RequestParameters") RequestParameters requestParameters;
}
