package com.kmsolutions.travelinsurance.model.dto;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "DisplayPrice")
@XmlAccessorType(XmlAccessType.FIELD)
public class DisplayPrice implements Serializable {
    @XmlAttribute(name = "Currency") String currency;
    @XmlValue Double displayPrice;
}
