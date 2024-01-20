package com.kmsolutions.travelinsurance.model;

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

@Data
@XmlRootElement(name = "TravelInformation")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
public class TravelInformation{
        @XmlElement(name = "StartDate") @XmlJavaTypeAdapter(DateAdapter.class)
        DateTime startDate;
        @XmlElement(name = "EndDate") @XmlJavaTypeAdapter(DateAdapter.class)
        DateTime endDate;
        @XmlElement(name = "BookingValue") Double bookingValue;
        @XmlElement(name = "DepartureCountry") String departureCountry;
        @XmlElement(name = "CoverCountries") DestinationCountry destinationCountry;
}
