package com.kmsolutions.travelinsurance;

import com.kmsolutions.travelinsurance.external.HepstarClient;
import com.kmsolutions.travelinsurance.model.dto.PolicyRequestParameters;
import com.kmsolutions.travelinsurance.model.dto.RequestParameters;
import com.kmsolutions.travelinsurance.model.dto.response.Package;
import com.kmsolutions.travelinsurance.model.dto.response.*;
import org.joda.time.DateTime;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Configuration
@Profile("test")
public class ExternalServiceConfigurations {

    @Bean
    public HepstarClient productPrice() {
        return new HepstarClient() {
            @Override
            public Response getProductPrice(RequestParameters requestParameters) {
                Response productResponse;
                try {
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
                    List<PriceDetail> priceDetail = List.of(
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
                    );
                    ProductInformation productInformation = new ProductInformation(
                            "TestAIR",
                            "AirHelp+",
                            "ancillary",
                            new ProductMedia(new URL("http://assets.hepstar.com/documents/AirHelp_Terms_and_Conditions.pdf"))
                    );
                    List<Package> packages = List.of(new Package(
                                    1,
                                    new PricedProduct(
                                            productInformation,
                                            new CustomerPriceBreakdowns(
                                                    customerPriceBreakdown
                                            ),
                                            new ProductPriceBreakdown(
                                                    priceDetail
                                            )
                                    )
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
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                return productResponse;
            }

            @Override
            public PolicyResponse issuePolicy(PolicyRequestParameters policyIssueRequest) {
                PolicyResponse policyResponse;
                try {
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
                    policyResponse = new PolicyResponse(
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
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                return policyResponse;
            }
        };
    }
}
