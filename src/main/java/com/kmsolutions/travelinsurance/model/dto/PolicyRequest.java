package com.kmsolutions.travelinsurance.model.dto;

import com.kmsolutions.travelinsurance.model.Insureds;
import com.kmsolutions.travelinsurance.model.PolicyInsureds;
import com.kmsolutions.travelinsurance.model.TravelInformation;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "PolicyRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class PolicyRequest {
    @XmlElement(name = "DistributerReference") String distributerReference;
    @XmlElement(name = "ProductID") String productId;
    @XmlElement(name = "DisplayPrice") DisplayPrice displayPrice;
    @XmlElement(name = "Insureds") PolicyInsureds insureds;
    @XmlElement(name = "ContactInformation") ContactInformation contactInformation;
    @XmlElement(name = "TravelInformation") TravelInformation travelInformation;
}
