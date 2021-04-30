package com.hatanaka.ecommerce.checkout.resource.checkout;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutRequest implements Serializable {

    @NotBlank(message = "{notBlank.checkoutRequest.firstName}")
    private String firstName;

    @NotBlank(message = "{notBlank.checkoutRequest.lastName}")
    private String lastName;

    @Email(message = "{notBlank.checkoutRequest.email}")
    private String email;

    @NotBlank(message = "{notBlank.checkoutRequest.address}")
    private String address;

    private String complement;

    @NotBlank(message = "{notBlank.checkoutRequest.country}")
    private String country;

    @NotBlank(message = "{notBlank.checkoutRequest.state}")
    private String state;

    @NotBlank(message = "{notBlank.checkoutRequest.cep}")
    private String cep;

    @NotNull(message = "{notNull.checkoutRequest.saveAddress}")
    private Boolean saveAddress;

    @NotNull(message = "{notNull.checkoutRequest.saveInfo}")
    private Boolean saveInfo;

    @NotBlank(message = "{notBlank.checkoutRequest.paymentMethod}")
    private String paymentMethod;

    @NotBlank(message = "{notBlank.checkoutRequest.cardName}")
    private String cardName;

    @NotBlank(message = "{notBlank.checkoutRequest.cardNumber}")
    @CreditCardNumber(message = "{invalid.checkoutRequest.cardNumber}")
    private String cardNumber;

    @NotNull(message = "{notNull.checkoutRequest.cardDate}")
    @FutureOrPresent(message = "{invalid.checkoutRequest.cardDate}")
    private LocalDate cardDate;

    @NotBlank(message = "{notBlank.checkoutRequest.cardCvv}")
    private String cardCvv;

    @NotNull(message = "{notNull.checkoutRequest.products}")
    @Size(min = 1,message = "{notNull.checkoutRequest.products}")
    private List<String> products;


}

