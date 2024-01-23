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
@XmlRootElement(name = "PricedProduct")
@XmlAccessorType(XmlAccessType.FIELD)
public class PricedProduct {
    @XmlElement(name = "ProductInformation") ProductInformation productInformation;
    @XmlElement(name = "CustomerPriceBreakdowns") CustomerPriceBreakdowns customerPriceBreakdowns;
    @XmlElement(name = "ProductPriceBreakdown") ProductPriceBreakdown productPriceBreakdown;
}
