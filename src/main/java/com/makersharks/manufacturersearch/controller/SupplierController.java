package com.makersharks.manufacturersearch.controller;

import com.makersharks.manufacturersearch.entity.Supplier;
import com.makersharks.manufacturersearch.service.SupplierService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping("/query")
    public List<Supplier> querySuppliers(
            @RequestParam @NonNull String location,
            @RequestParam @NonNull Supplier.NatureOfBusiness natureOfBusiness,
            @RequestParam @NonNull Supplier.ManufacturingProcess manufacturingProcess,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        if (location.isEmpty()) {
            throw new IllegalArgumentException("Location cannot be empty");
        }

        PageRequest pageable = PageRequest.of(page, size);
        return supplierService.findSuppliers(location, natureOfBusiness, manufacturingProcess, pageable).getContent();
    }

    //TODO: Security Best Practices
    /*Use HTTPS: Ensure that the API is accessed over HTTPS in production environments to encrypt data in transit.

    Implement Rate Limiting: To protect against DDoS attacks, implement rate limiting using tools like Bucket4j or Spring Security's built-in support.

    Sanitize Inputs: Ensure inputs are sanitized to prevent SQL injection and XSS attacks.

    Use CSRF Protection: If the API is being used by a web client, ensure CSRF protection is enabled.

    Secure Endpoints with Authentication: Use JWT (JSON Web Tokens) or OAuth2 to secure API endpoints, ensuring only authenticated users can access the data.

    Audit Logging: Log sensitive operations and access to ensure auditability and traceability.

    Use Secure Headers: Implement security headers like X-Content-Type-Options, X-XSS-Protection, X-Frame-Options, and Content-Security-Policy.*/
}
