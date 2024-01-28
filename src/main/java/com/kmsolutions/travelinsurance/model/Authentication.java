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
@XmlRootElement(name = "Authentication")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
public class Authentication implements Serializable {
        @XmlElement(name = "Channel") String channel;
        @XmlElement(name = "Username") String username;
        @XmlElement(name = "Password") String password;
        @XmlElement(name = "Currency") String currency;
}
