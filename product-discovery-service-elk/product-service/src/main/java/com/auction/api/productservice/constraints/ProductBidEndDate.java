package com.auction.api.productservice.constraints;

import com.auction.api.productservice.validators.ProductBidEndDateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = ProductBidEndDateValidator.class)
@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Documented
public  @interface ProductBidEndDate {
    String message() default "The Bid end date must be greater than current date.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
