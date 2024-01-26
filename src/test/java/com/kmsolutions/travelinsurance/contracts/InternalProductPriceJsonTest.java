package com.kmsolutions.travelinsurance.contracts;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kmsolutions.travelinsurance.TestConfigurations;
import com.kmsolutions.travelinsurance.model.*;
import com.kmsolutions.travelinsurance.model.dto.*;
import com.kmsolutions.travelinsurance.model.dto.response.*;
import com.kmsolutions.travelinsurance.model.dto.response.Package;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
@ActiveProfiles("test")
@Import({TestConfigurations.class})
public class InternalProductPriceJsonTest {
    @Autowired
    JacksonTester<InternalProductPriceRequest> productPriceRequestJson;
    @Autowired
    JacksonTester<InternalProductPriceResponse> productPriceResponseJson;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    JacksonTester<InternalPolicyRequest> policyRequestJacksonTester;
    @Autowired
    JacksonTester<InternalPolicyResponse> policyResponseJson;

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
                .isStrictlyEqualToJson("expectedProductPriceRequest.json");
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
                .isStrictlyEqualToJson("expectedProductPriceResponse.json");
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

    @Test
    void policyRequestSerializationTest() throws IOException {
        TravelInformation travelInformation = new TravelInformation(
                DateTime.parse("2024-01-19T00:00:00.000+0000"),
                DateTime.parse("2024-01-30T00:00:00.000+0000"),
                1000.00,
                "ZA",
                new DestinationCountry("ZA")
        );

        PolicyRequestParameters policyRequestParameters = new PolicyRequestParameters(
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
                                )
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

        System.out.println(objectMapper.writeValueAsString(new InternalPolicyRequest(policyRequestParameters)));

        assertThat(policyRequestJacksonTester.write(new InternalPolicyRequest(policyRequestParameters)))
                .isStrictlyEqualToJson("expectedPolicyRequest.json");
    }

    @Test
    void policyDeserializationTest() throws IOException {
        String actual = """
                    {
                       "requestParameters": {
                         "policyRequests": {
                           "policyRequest": [
                             {
                               "distributerReference": "AirHelp+",
                               "productId": "TestAIR",
                               "displayPrice": {
                                 "currency": "ZAR",
                                 "displayPrice": 155.56
                               },
                               "insureds": {
                                 "insured": [
                                   {
                                     "id": 1,
                                     "firstName": "Insured First Name",
                                     "surname": "Insured Surname",
                                     "nationalId": "1111111111122",
                                     "dateOfBirth": "1983-09-25",
                                     "gender": "male",
                                     "residency": "ZA",
                                     "travelInformation": {
                                       "travelItemValue": 1000.0
                                     },
                                     "displayPrice": {
                                       "currency": "ZAR",
                                       "displayPrice": 155.56
                                     }
                                   }
                                 ]
                               },
                               "contactInformation": {
                                 "email": "example@gmail.com"
                               },
                               "travelInformation": {
                                 "startDate": "2024-01-19",
                                 "endDate": "2024-01-30",
                                 "bookingValue": 1000.0,
                                 "departureCountry": "ZA",
                                 "destinationCountry": {
                                   "destinationCountry": "ZA"
                                 }
                               }
                             }
                           ]
                         },
                         "payment": {
                           "cardType": "Visa",
                           "cardHolder": "CardHolder",
                           "cardNumber": "4242424242424242",
                           "cvc": 123,
                           "expireMonth": "04",
                           "expireYear": 2025
                         }
                       }
                     }
                """;

        TravelInformation travelInformation = new TravelInformation(
                DateTime.parse("2024-01-19T00:00:00.000+0000"),
                DateTime.parse("2024-01-30T00:00:00.000+0000"),
                1000.00,
                "ZA",
                new DestinationCountry("ZA")
        );

        PolicyRequestParameters policyRequestParameters = new PolicyRequestParameters(
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
                                )
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

        assertThat(policyRequestJacksonTester.parse(actual)).isEqualTo(new InternalPolicyRequest(policyRequestParameters));
    }

    @Test
    void policyResponseSerializationTest() throws IOException {
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
                                                        DateTime.parse("2024-01-24T00:00:00.000+0000"),
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

        System.out.println(objectMapper.writeValueAsString(new InternalPolicyResponse(policyResponse)));

        assertThat(policyResponseJson.write(new InternalPolicyResponse(policyResponse)))
                .isStrictlyEqualToJson("expectedPolicyResponse.json");
    }
}
