package com.kmsolutions.travelinsurance.model.dto.response;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "ResponseParameters")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseParameters {
    @XmlElement(name = "PackageSize") PackageSize packageSize;
    @XmlElement(name = "BookingFees") Double bookingFees;
}
