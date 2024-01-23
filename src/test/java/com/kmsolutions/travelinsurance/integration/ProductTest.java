package com.kmsolutions.travelinsurance.integration;

import com.kmsolutions.travelinsurance.ExternalServiceConfigurations;
import com.kmsolutions.travelinsurance.TestConfigurations;
import com.kmsolutions.travelinsurance.model.*;
import com.kmsolutions.travelinsurance.model.dto.InternalProductPriceRequest;
import com.kmsolutions.travelinsurance.model.dto.InternalProductPriceResponse;
import com.kmsolutions.travelinsurance.model.dto.RequestParameters;
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
        assertThat(response.getBody()).isEqualTo(
                new InternalProductPriceResponse(
                        new Response(
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
                        )
                )
        );
    }
}
