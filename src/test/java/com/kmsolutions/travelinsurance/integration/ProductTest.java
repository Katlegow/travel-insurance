package com.kmsolutions.travelinsurance.integration;

import com.kmsolutions.travelinsurance.ExternalServiceConfigurations;
import com.kmsolutions.travelinsurance.TestConfigurations;
import com.kmsolutions.travelinsurance.model.*;
import com.kmsolutions.travelinsurance.model.dto.*;
import com.kmsolutions.travelinsurance.model.dto.response.*;
import com.kmsolutions.travelinsurance.model.dto.response.Package;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(value = {TestConfigurations.class, ExternalServiceConfigurations.class})
public class ProductTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldReturnProductPrice() throws MalformedURLException {
        Insured insured = new Insured(
                1L,
                DateTime.parse("1983-09-25T00:00:00.000+0000"),
                "male",
                "ZA",
                new InsuredTravelInformation(1000.00)
        );

        RequestParameters requestParameters = new RequestParameters(
                new Insureds(List.of(insured)),
                new TravelInformation(
                        DateTime.parse("2024-01-19T00:00:00.000+0000"),
                        DateTime.parse("2024-01-30T00:00:00.000+0000"),
                        1000.00,
                        "ZA",
                        new DestinationCountry("ZA")
                )
        );

        ResponseEntity<InternalProductPriceResponse> response = restTemplate
                .postForEntity(
                "/product/price",
                new InternalProductPriceRequest(requestParameters),
                InternalProductPriceResponse.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        PricedProduct pricedProduct = new PricedProduct(
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
        );
        List<Package> packages = List.of(new Package(
                        1,
                pricedProduct
                )
        );
        ResponseParameters responseParameters = new ResponseParameters(
                new PackageSize(
                        1,
                        packages
                ),
                null
        );
        assertThat(response.getBody()).isEqualTo(
                new InternalProductPriceResponse(
                        new Response(
                                new Status(
                                        200,
                                        "Successful"
                                ),
                                responseParameters
                        )
                )
        );
    }

    @Test
    void shouldIssuePolicy() throws MalformedURLException {
        TravelInformation travelInformation = new TravelInformation(
                DateTime.parse("2024-01-19T00:00:00.000+0000"),
                DateTime.parse("2024-01-30T00:00:00.000+0000"),
                1000.00,
                "ZA",
                new DestinationCountry("ZA")
        );

        PolicyRequest e11 = new PolicyRequest(
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
                                        DateTime.parse("1983-09-25T00:00:00.000+0000"),
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
        );
        PolicyRequestParameters policyRequestParameters = new PolicyRequestParameters(
                new PolicyRequests(
                        List.of(
                                e11
                        )
                ),
                new Payment(
                        "Visa",
                        "CardHolder",
                        "4242424242424242",
                        123,
                        "04",
                        2025
                )
        );

        ResponseEntity<InternalPolicyResponse> response = restTemplate
                .postForEntity(
                        "/product/policy/issue",
                        new InternalPolicyRequest(policyRequestParameters),
                        InternalPolicyResponse.class
                );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        // Test Response

        Documents documents = new Documents(
                new URL("https://uat.gateway.insure/policy/schedule/download/1924d47fb2f0489a8f3d78e76ff68018")
        );
        PurchaseInformation purchaseInformation = new PurchaseInformation(
                DateTime.parse("2024-01-24T00:00:00.000+0000"),
                "AIR-20240124-58058",
                new URL("http://assets.hepstar.com/documents/AirHelp_Terms_and_Conditions.pdf"),
                documents
        );
        PurchaseResponse e1 = new PurchaseResponse(
                purchaseInformation
        );
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
                                        e1
                                )
                        ),
                        new PurchasesInformation("HSM2024012421095795746")
                )
        );

        assertThat(response.getBody()).isEqualTo(new InternalPolicyResponse(policyResponse));
    }
}
