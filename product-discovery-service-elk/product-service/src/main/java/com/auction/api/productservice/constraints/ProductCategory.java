package com.auction.api.productservice.constraints;

import com.auction.api.productservice.validators.ProductBidEndDateValidator;
import com.auction.api.productservice.validators.ProductCategoryValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Constraint(validatedBy = ProductCategoryValidator.class)
@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Documented
public  @interface ProductCategory {
    String message() default "Product category must be one of these - PAINTING,ORNAMENT,SCULPTOR.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

