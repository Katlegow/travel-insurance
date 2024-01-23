package com.kmsolutions.travelinsurance.model.dto.response;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "ProductPriceBreakdown")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductPriceBreakdown {
    @XmlElement(name = "PriceDetail")
    List<PriceDetail> priceDetail;
}
