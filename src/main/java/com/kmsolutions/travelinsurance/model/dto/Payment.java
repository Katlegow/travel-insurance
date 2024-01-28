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
@XmlRootElement(name = "Payment")
@XmlAccessorType(XmlAccessType.FIELD)
public class Payment implements Serializable {
    @XmlElement(name = "CardType") String cardType;
    @XmlElement(name = "CardHolder") String cardHolder;
    @XmlElement(name = "CardNumber") String cardNumber;
    @XmlElement(name = "CardVerificationCode") Integer cvc;
    @XmlElement(name = "ExpireMonth") String expireMonth;
    @XmlElement(name = "ExpireYear") Integer expireYear;
}
