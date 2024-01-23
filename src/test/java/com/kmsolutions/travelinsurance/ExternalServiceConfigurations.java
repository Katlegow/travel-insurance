package com.kmsolutions.travelinsurance;

import com.kmsolutions.travelinsurance.external.HepstarClient;
import com.kmsolutions.travelinsurance.model.dto.RequestParameters;
import com.kmsolutions.travelinsurance.model.dto.response.Package;
import com.kmsolutions.travelinsurance.model.dto.response.*;
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
                Response productResponse = null;
                try {
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
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                return productResponse;
            }
        };
    }
}
