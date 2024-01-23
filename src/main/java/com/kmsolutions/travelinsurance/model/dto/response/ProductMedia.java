package com.kmsolutions.travelinsurance.model.dto.response;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URL;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "ProductMedia")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductMedia {
    @XmlElement(name = "TermsAndConditions")
    URL termsAndConditions;
}
