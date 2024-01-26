package com.kmsolutions.travelinsurance.model.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kmsolutions.travelinsurance.utils.DateAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import java.net.URL;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "PurchaseInformation")
@XmlAccessorType(XmlAccessType.FIELD)
public class PurchaseInformation {
    @XmlJavaTypeAdapter(DateAdapter.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @XmlElement(name = "PurchaseDate") DateTime purchaseDate;
    @XmlElement(name = "PurchaseNumber") String purchaseNumber;
    @XmlElement(name = "TermsAndConditions") URL termsAndConditions;
    @XmlElement(name = "Documents") Documents documents;
}
