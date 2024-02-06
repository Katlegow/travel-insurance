package com.kmsolutions.travelinsurance.controller;

import com.kmsolutions.travelinsurance.model.dto.InternalPolicyRequest;
import com.kmsolutions.travelinsurance.model.dto.InternalPolicyResponse;
import com.kmsolutions.travelinsurance.model.dto.InternalProductPriceRequest;
import com.kmsolutions.travelinsurance.model.dto.InternalProductPriceResponse;
import com.kmsolutions.travelinsurance.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/product")
@CrossOrigin("*")
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

    @PostMapping("/policy/issue")
    public ResponseEntity<InternalPolicyResponse> issuePolicy(@RequestBody InternalPolicyRequest policyRequest) {
        return ResponseEntity.ok(new InternalPolicyResponse(
                productService.issuePolicy(policyRequest.getRequestParameters())
        ));
    }
}
