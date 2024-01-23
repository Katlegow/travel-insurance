package com.kmsolutions.travelinsurance.model.dto.response;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "Package")
@XmlAccessorType(XmlAccessType.FIELD)
public class Package {
    @XmlAttribute(name = "ID") Integer id;
    @XmlElement(name = "PricedProduct") PricedProduct pricedProduct;
}
