package com.kmsolutions.travelinsurance.contracts;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.context.annotation.Import;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
@Import({TestConfigurations.class})
public class InternalProductPriceJsonTest {
    @Autowired
    JacksonTester<InternalProductPriceRequest> productPriceRequestJson;
    @Autowired
    JacksonTester<InternalProductPriceResponse> productPriceResponseJson;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void productPriceSerializationTest() throws IOException {
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

        System.out.println(objectMapper.writeValueAsString(new InternalProductPriceRequest(requestParameters)));

        assertThat(productPriceRequestJson.write(new InternalProductPriceRequest(requestParameters)))
                .isStrictlyEqualToJson("expected.json");
    }

    @Test
    void productPriceResponseSerializationTest() throws IOException {
        Response productResponse = new Response(
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

        System.out.println(objectMapper.writeValueAsString(new InternalProductPriceResponse(productResponse)));

        assertThat(productPriceResponseJson.write(new InternalProductPriceResponse(productResponse)))
                .isStrictlyEqualToJson("expectedResponse.json");
    }

    @Test
    void productPriceDeSerializationTest() throws IOException {
        String actual = """
                    {
                      "requestParameters": {
                        "insureds": {
                          "insured": [
                            {
                              "id": 1,
                              "dateOfBirth": "1983-09-25",
                              "gender": "male",
                              "residency": "ZA",
                              "travelInformation": {
                                "travelItemValue": 1000
                              }
                            }
                          ]
                        } ,
                        "travelInformation": {
                          "startDate": "2024-01-19",
                          "endDate": "2024-01-30",
                          "bookingValue": 1000,
                          "departureCountry": "ZA",
                          "destinationCountry": {
                            "destinationCountry": "ZA"
                          }
                        }
                      }
                    }
                """;

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

        assertThat(productPriceRequestJson.parse(actual)).isEqualTo(new InternalProductPriceRequest(requestParameters));
    }
}
