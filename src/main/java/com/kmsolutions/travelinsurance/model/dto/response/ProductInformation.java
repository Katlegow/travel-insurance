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
@XmlRootElement(name = "ProductInformation")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductInformation {
    @XmlElement(name = "ProductCode") String productCode;
    @XmlElement(name = "ProductIdentifier") String productIdentifier;
    @XmlElement(name = "Category") String category;
    @XmlElement(name = "ProductMedia") ProductMedia productMedia;
}
