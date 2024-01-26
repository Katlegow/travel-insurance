package com.kmsolutions.travelinsurance.contracts;

import com.kmsolutions.travelinsurance.model.*;
import com.kmsolutions.travelinsurance.model.dto.*;
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
    JAXBContext policyRequestContext;
    PolicyIssueRequest policyIssueRequest;

    @BeforeEach
    void setup() throws JAXBException, MalformedURLException {
        context = JAXBContext.newInstance(ProductPriceRequest.class);
        responseContext = JAXBContext.newInstance(Response.class);
        policyRequestContext = JAXBContext.newInstance(PolicyIssueRequest.class);


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

        ProductInformation productInformation = new ProductInformation(
                "TestAIR",
                "AirHelp+",
                "ancillary",
                new ProductMedia(new URL("http://assets.hepstar.com/documents/AirHelp_Terms_and_Conditions.pdf"))
        );
        List<CustomerPriceBreakdown> customerPriceBreakdown = List.of(
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
        );
        CustomerPriceBreakdowns customerPriceBreakdowns = new CustomerPriceBreakdowns(
                customerPriceBreakdown
        );
        ProductPriceBreakdown productPriceBreakdown = new ProductPriceBreakdown(
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
        );
        PricedProduct pricedProduct = new PricedProduct(
                productInformation,
                customerPriceBreakdowns,
                productPriceBreakdown
        );
        List<Package> packages = List.of(new Package(
                        1,
                        pricedProduct
                )
        );
        productResponse = new Response(
                new Status(
                        200,
                        "Successful"
                ),
                new ResponseParameters(
                        new PackageSize(
                                1,
                                packages
                        ),
                        null
                )
        );

        //==============================================================================================================
        policyIssueRequest = new PolicyIssueRequest(
                authentication,
                new PolicyRequestParameters(
                        new PolicyRequests(
                                List.of(
                                        new PolicyRequest(
                                                "AirHelp+",
                                                "TestAIR",
                                                new DisplayPrice(
                                                        "ZAR",
                                                        155.56
                                                ),
                                                new PolicyInsureds(
                                                        List.of(
                                                                new PolicyInsured(
                                                                        1L,
                                                                        "Insured First Name",
                                                                        "Insured Surname",
                                                                        "1111111111122",
                                                                        DateTime.parse("1983-09-25"),
                                                                        "male",
                                                                        "ZA",
                                                                        new InsuredTravelInformation(1000.00),
                                                                        new DisplayPrice(
                                                                                "ZAR",
                                                                                155.56
                                                                        )

                                                                )
                                                        )
                                                ),
                                                new ContactInformation("example@gmail.com"),
                                                travelInformation
                                        )
                                )
                        ),
                        new Payment(
                                "Visa",
                                "Cardholder",
                                "4242424242424242",
                                123,
                                "04",
                                2025
                        )
                )
        );

    }

    @Test
    void productPriceRequestDeserializationTest() throws JAXBException, IOException {
        ProductPriceRequest expected = (ProductPriceRequest) context
                .createUnmarshaller()
                        .unmarshal(new ClassPathResource("/com/kmsolutions/travelinsurance/contracts/productPriceRequest.xml").getInputStream());

        assertThat(productPriceRequest).isEqualTo(expected);
    }

    @Test
    void policyRequestDeserializationTest() throws JAXBException, IOException {
        PolicyIssueRequest expected = (PolicyIssueRequest) policyRequestContext
                .createUnmarshaller()
                .unmarshal(new ClassPathResource("/com/kmsolutions/travelinsurance/contracts/policyRequest.xml").getInputStream());

        assertThat(policyIssueRequest).isEqualTo(expected);
    }

    @Test
    void productPriceResponseDeserializationTest() throws JAXBException, IOException {
        Response expected = (Response) responseContext
                .createUnmarshaller()
                .unmarshal(new ClassPathResource("/com/kmsolutions/travelinsurance/contracts/productPriceResponse.xml").getInputStream());

        assertThat(productResponse).isEqualTo(expected);
    }

    @Test
    void productPriceResponseSerializationTest() throws IOException, JAXBException {
        Marshaller mar = responseContext.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        File file = new File("./src/test/resources/com/kmsolutions/travelinsurance/contracts/outDir/productPriceResponse.xml");

        try (FileWriter writer = new XmlWriter(file)){
            mar.marshal(productResponse, writer);
        }

        long actual = Files.mismatch(Path.of(new ClassPathResource("/com/kmsolutions/travelinsurance/contracts/productPriceResponse.xml").getURI()), file.toPath());
        assertThat(actual).isEqualTo(-1L);

        file.delete();
    }

    @Test
    void productPriceRequestSerializationTest() throws IOException, JAXBException {
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        File file = new File("./src/test/resources/com/kmsolutions/travelinsurance/contracts/outDir/productPriceRequest.xml");

        try (FileWriter writer = new XmlWriter(file)){
            mar.marshal(productPriceRequest, writer);
        }

        long actual = Files.mismatch(Path.of(new ClassPathResource("/com/kmsolutions/travelinsurance/contracts/productPriceRequest.xml").getURI()), file.toPath());
        assertThat(actual).isEqualTo(-1L);

        file.delete();
    }

    @Test
    void policyRequestSerializationTest() throws IOException, JAXBException {
        Marshaller mar = policyRequestContext.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        File file = new File("./src/test/resources/com/kmsolutions/travelinsurance/contracts/outDir/productPriceRequest.xml");

        try (FileWriter writer = new XmlWriter(file)){
            mar.marshal(policyIssueRequest, writer);
        }

        long actual = Files.mismatch(Path.of(new ClassPathResource("/com/kmsolutions/travelinsurance/contracts/policyRequest.xml").getURI()), file.toPath());
        assertThat(actual).isEqualTo(-1L);

        file.delete();
    }

    @Test
    void policyResponseDeserializationTest() throws JAXBException, IOException {
        JAXBContext policyResponseContext = JAXBContext.newInstance(PolicyResponse.class);
        PolicyResponse policyResponse = new PolicyResponse(
                new Status(
                        200,
                        "Successful"
                ),
                new PolicyResponseParameters(
                        new PaymentResult(
                                700.0,
                                "EUR"
                        ),
                        new PurchaseResponses(
                                List.of(
                                        new PurchaseResponse(
                                                new PurchaseInformation(
                                                        DateTime.parse("2024-01-24"),
                                                        "AIR-20240124-58058",
                                                        new URL("http://assets.hepstar.com/documents/AirHelp_Terms_and_Conditions.pdf"),
                                                        new Documents(
                                                                new URL("https://uat.gateway.insure/policy/schedule/download/1924d47fb2f0489a8f3d78e76ff68018")
                                                        )
                                                )
                                        )
                                )
                        ),
                        new PurchasesInformation("HSM2024012421095795746")
                )
        );

        PolicyResponse expected = (PolicyResponse) policyResponseContext
                .createUnmarshaller()
                .unmarshal(new ClassPathResource("/com/kmsolutions/travelinsurance/contracts/policyResponse.xml").getInputStream());

        assertThat(policyResponse).isEqualTo(expected);
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
