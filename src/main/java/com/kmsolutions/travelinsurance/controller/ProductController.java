package com.kmsolutions.travelinsurance.controller;

import com.kmsolutions.travelinsurance.model.dto.InternalProductPriceRequest;
import com.kmsolutions.travelinsurance.model.dto.InternalProductPriceResponse;
import com.kmsolutions.travelinsurance.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/price")
    public ResponseEntity<InternalProductPriceResponse> getProductPrice(@RequestBody InternalProductPriceRequest internalProductPriceRequest) {
        return ResponseEntity.ok(new InternalProductPriceResponse(
                productService.getProductPrice(internalProductPriceRequest.getRequestParameters())
        ));
    }
}
