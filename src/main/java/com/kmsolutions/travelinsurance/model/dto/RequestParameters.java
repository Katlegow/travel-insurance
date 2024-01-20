package com.kmsolutions.travelinsurance.model.dto;

import com.kmsolutions.travelinsurance.model.Insured;
import com.kmsolutions.travelinsurance.model.Insureds;
import com.kmsolutions.travelinsurance.model.TravelInformation;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@XmlRootElement(name = "RequestParameters")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
public class RequestParameters {
        @XmlElement(name = "Insureds") Insureds insureds;
        @XmlElement(name = "TravelInformation") TravelInformation travelInformation;
}
