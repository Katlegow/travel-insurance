package com.kmsolutions.travelinsurance.model.dto.response;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "ProductDetail")
@XmlAccessorType(XmlAccessType.FIELD)
public class PriceDetail {
    @XmlAttribute(name = "Currency") String currency;
    @XmlAttribute(name = "Type") String type;
    @XmlElement(name = "ExchangeRate") Double exchangeRate;
    @XmlElement(name = "BaseAmount") Double baseAmount;
    @XmlElement(name = "TotalAmount") Double totalAmount;
}
