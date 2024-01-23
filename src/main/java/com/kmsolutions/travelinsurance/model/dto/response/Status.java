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
@XmlRootElement(name = "Status")
@XmlAccessorType(XmlAccessType.FIELD)
public class Status {
    @XmlElement(name = "Code") Integer code;
    @XmlElement(name = "Description") String description;
}
