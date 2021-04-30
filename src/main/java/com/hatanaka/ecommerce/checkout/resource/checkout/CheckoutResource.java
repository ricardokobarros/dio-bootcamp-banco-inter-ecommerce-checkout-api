package com.hatanaka.ecommerce.checkout.resource.checkout;

import com.hatanaka.ecommerce.checkout.entity.CheckoutEntity;
import com.hatanaka.ecommerce.checkout.service.CheckoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/v1/checkout")
@RequiredArgsConstructor
public class CheckoutResource {

    private final CheckoutService checkoutService;

    @GetMapping("/status/{checkoutCode}")
    public ResponseEntity<CheckoutStatusResponse> getStatusByCheckoutCode(@PathVariable UUID checkoutCode) {
        final CheckoutEntity checkoutEntity = checkoutService.getStatusByCheckoutCode(checkoutCode.toString()).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        });
        final CheckoutStatusResponse checkoutStatusResponse = CheckoutStatusResponse.builder()
                .code(checkoutEntity.getCode())
                .status(checkoutEntity.getStatus())
                .build();
        return ResponseEntity.ok(checkoutStatusResponse);
    }

    @PostMapping("/")
    public ResponseEntity<CheckoutResponse> create(@Valid @RequestBody CheckoutRequest checkoutRequest) {
        final CheckoutEntity checkoutEntity = checkoutService.create(checkoutRequest).orElseThrow();
        final CheckoutResponse checkoutResponse = CheckoutResponse.builder()
                .code(checkoutEntity.getCode())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(checkoutResponse);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        Map<String, Object> response = Map.of("message", "Validation failed",
                "fields", errors);
        return ResponseEntity.badRequest().body(response);
    }
}
