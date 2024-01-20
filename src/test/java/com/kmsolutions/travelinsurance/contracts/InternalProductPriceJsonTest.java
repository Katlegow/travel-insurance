package com.kmsolutions.travelinsurance.contracts;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kmsolutions.travelinsurance.TestConfigurations;
import com.kmsolutions.travelinsurance.model.*;
import com.kmsolutions.travelinsurance.model.dto.InternalProductPriceRequest;
import com.kmsolutions.travelinsurance.model.dto.RequestParameters;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.context.annotation.Import;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
@Import({TestConfigurations.class})
public class InternalProductPriceJsonTest {
    @Autowired
    JacksonTester<InternalProductPriceRequest> productPriceRequestJson;
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
