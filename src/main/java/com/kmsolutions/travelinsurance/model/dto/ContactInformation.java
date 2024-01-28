package com.kmsolutions.travelinsurance.model.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "ContactInformation")
@XmlAccessorType(XmlAccessType.FIELD)
public class ContactInformation implements Serializable {
    @XmlElement(name = "Email") String email;
}
