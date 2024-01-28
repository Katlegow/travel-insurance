package com.kmsolutions.travelinsurance.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kmsolutions.travelinsurance.model.dto.DisplayPrice;
import com.kmsolutions.travelinsurance.utils.DateAdapter;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import java.io.Serializable;

@Data
@XmlRootElement(name="Insured")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
public class PolicyInsured implements Serializable {
    @XmlAttribute(name = "ID") private Long id;
    @XmlElement(name = "Firstname") private String firstName;
    @XmlElement(name = "Surname") private String surname;
    @XmlElement(name = "NationalID") private String nationalId;
    @XmlElement(name = "DOB") @XmlJavaTypeAdapter(DateAdapter.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private DateTime dateOfBirth;
    @XmlElement(name = "Gender") private String gender;
    @XmlElement(name = "Residency") private String residency;
    @XmlElement(name = "TravelInformation") private InsuredTravelInformation travelInformation;
    @XmlElement(name = "DisplayPrice") private DisplayPrice displayPrice;
}