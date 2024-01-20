package com.kmsolutions.travelinsurance.contracts;

import com.kmsolutions.travelinsurance.model.*;
import com.kmsolutions.travelinsurance.model.dto.ProductPriceRequest;
import com.kmsolutions.travelinsurance.model.dto.RequestParameters;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.joda.time.DateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class ProductPriceRequestResponseTest {
    ProductPriceRequest productPriceRequest;
    JAXBContext context;

    @BeforeEach
    void setup() throws JAXBException {
        context = JAXBContext.newInstance(ProductPriceRequest.class);

        Authentication authentication = new Authentication(
                "API",
                "impdistributor",
                "FFRyEGGmMJYHA",
                "ZAR"
        );

        Insured insured = new Insured(
                1L,
                DateTime.parse("1983-09-25"),
                "male",
                "ZA",
                new InsuredTravelInformation(1000.00)
        );

        TravelInformation travelInformation = new TravelInformation(
                DateTime.parse("2024-01-19"),
                DateTime.parse("2024-01-30"),
                1000.00,
                "ZA",
                new DestinationCountry("ZA")
        );

        RequestParameters requestParameters = new RequestParameters(
                new Insureds(List.of(insured)),
                travelInformation
        );

        productPriceRequest = new ProductPriceRequest(
                authentication,
                requestParameters
        );
    }

    @Test
    void productPriceRequestDeserializationTest() throws JAXBException, IOException {
        ProductPriceRequest expected = (ProductPriceRequest) context
                .createUnmarshaller()
                        .unmarshal(new ClassPathResource("/contracts/request.xml").getInputStream());

        assertThat(productPriceRequest).isEqualTo(expected);
    }

    @Test
    void productPriceRequestSerializationTest() throws IOException, JAXBException {
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        File file = new File("./src/test/resources/contracts/outDir/request.xml");

        try (FileWriter writer = new XmlWriter(file)){
            mar.marshal(productPriceRequest, writer);
        }

        long actual = Files.mismatch(Path.of(new ClassPathResource("/contracts/request.xml").getURI()), file.toPath());
        assertThat(actual).isEqualTo(-1L);

        file.delete();
    }

    public static class XmlWriter extends FileWriter {

        public XmlWriter(File file) throws IOException {
            super(file);
        }

        public void write(String str) throws IOException {
            if(!str.isEmpty()) {
                super.write(str);
            }
        }
    }
}
