package com.kmsolutions.travelinsurance.contracts;

import com.kmsolutions.travelinsurance.model.*;
import com.kmsolutions.travelinsurance.model.dto.ProductPriceRequest;
import com.kmsolutions.travelinsurance.model.dto.RequestParameters;
import com.kmsolutions.travelinsurance.model.dto.response.*;
import com.kmsolutions.travelinsurance.model.dto.response.Package;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.joda.time.DateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class ExternalProductPriceRequestResponseTest {
    ProductPriceRequest productPriceRequest;
    Response productResponse;
    JAXBContext context;
    JAXBContext responseContext;

    @BeforeEach
    void setup() throws JAXBException, MalformedURLException {
        context = JAXBContext.newInstance(ProductPriceRequest.class);
        responseContext = JAXBContext.newInstance(Response.class);

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

        //==============================================================================================================

        productResponse = new Response(
                new Status(
                        200,
                        "Successful"
                ),
                new ResponseParameters(
                        new PackageSize(
                                1,
                                List.of(new Package(
                                                1,
                                                new PricedProduct(
                                                        new ProductInformation(
                                                                "TestAIR",
                                                                "AirHelp+",
                                                                "ancillary",
                                                                new ProductMedia(new URL("http://assets.hepstar.com/documents/AirHelp_Terms_and_Conditions.pdf"))
                                                        ),
                                                        new CustomerPriceBreakdowns(
                                                                List.of(
                                                                        new CustomerPriceBreakdown(
                                                                                "EUR",
                                                                                "Original",
                                                                                null,
                                                                                7.00,
                                                                                7.00
                                                                        ),
                                                                        new CustomerPriceBreakdown(
                                                                                "ZAR",
                                                                                "ConvertedCurrency",
                                                                                22.47462828,
                                                                                157.33,
                                                                                157.33
                                                                        )
                                                                )
                                                        ),
                                                        new ProductPriceBreakdown(
                                                                List.of(
                                                                        new PriceDetail(
                                                                                "EUR",
                                                                                "Original",
                                                                                null,
                                                                                7.00,
                                                                                7.00
                                                                        ),
                                                                        new PriceDetail(
                                                                                "ZAR",
                                                                                "ConvertedCurrency",
                                                                                22.47462828,
                                                                                157.33,
                                                                                157.33
                                                                        )
                                                                )
                                                        )
                                                )
                                        )
                                )
                        ),
                        null
                )
        );
    }

    @Test
    void productPriceRequestDeserializationTest() throws JAXBException, IOException {
        ProductPriceRequest expected = (ProductPriceRequest) context
                .createUnmarshaller()
                        .unmarshal(new ClassPathResource("/com/kmsolutions/travelinsurance/contracts/request.xml").getInputStream());

        assertThat(productPriceRequest).isEqualTo(expected);
    }

    @Test
    void productPriceResponseDeserializationTest() throws JAXBException, IOException {
        Response expected = (Response) responseContext
                .createUnmarshaller()
                .unmarshal(new ClassPathResource("/com/kmsolutions/travelinsurance/contracts/response.xml").getInputStream());

        assertThat(productResponse).isEqualTo(expected);
    }

    @Test
    void productPriceResponseSerializationTest() throws IOException, JAXBException {
        Marshaller mar = responseContext.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        File file = new File("./src/test/resources/com/kmsolutions/travelinsurance/contracts/outDir/response.xml");

        try (FileWriter writer = new XmlWriter(file)){
            mar.marshal(productResponse, writer);
        }

        long actual = Files.mismatch(Path.of(new ClassPathResource("/com/kmsolutions/travelinsurance/contracts/response.xml").getURI()), file.toPath());
        assertThat(actual).isEqualTo(-1L);

        file.delete();
    }

    @Test
    void productPriceRequestSerializationTest() throws IOException, JAXBException {
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        File file = new File("./src/test/resources/com/kmsolutions/travelinsurance/contracts/outDir/request.xml");

        try (FileWriter writer = new XmlWriter(file)){
            mar.marshal(productPriceRequest, writer);
        }

        long actual = Files.mismatch(Path.of(new ClassPathResource("/com/kmsolutions/travelinsurance/contracts/request.xml").getURI()), file.toPath());
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
