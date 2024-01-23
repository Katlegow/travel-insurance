package com.kmsolutions.travelinsurance.model.dto.response;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "PackageSize")
@XmlAccessorType(XmlAccessType.FIELD)
public class PackageSize {
    @XmlAttribute(name = "count") Integer count;
    @XmlElement(name = "Package")
    List<Package> packages;
}
