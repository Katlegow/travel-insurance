package com.kmsolutions.travelinsurance.model.dto.response;

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
@XmlRootElement(name = "ResponseParameters")
@XmlAccessorType(XmlAccessType.FIELD)
public class PolicyResponseParameters {
    @XmlElement(name = "PaymentResult") PaymentResult paymentResult;
    @XmlElement(name = "PurchaseResponses") PurchaseResponses purchaseResponses;
    @XmlElement(name = "PurchasesInformation") PurchasesInformation purchasesInformation;
}
