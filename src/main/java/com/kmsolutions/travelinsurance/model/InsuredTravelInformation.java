package com.kmsolutions.travelinsurance.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@XmlRootElement(name = "TravelInformation")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
public class InsuredTravelInformation implements Serializable {
        @XmlElement(name = "TravelItemValue") Double travelItemValue;
}
