package com.auction.api.productservice.validators;

import com.auction.api.productservice.constants.ProductCategoryEnum;
import com.auction.api.productservice.constraints.ProductCategory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class ProductCategoryValidator implements ConstraintValidator<ProductCategory, String> {
    @Override
    public boolean isValid(final String valueToValidate, final ConstraintValidatorContext context) {
        return Arrays.stream(ProductCategoryEnum.values()).anyMatch((category) -> category.toString().toLowerCase().equalsIgnoreCase(valueToValidate));
    }
}

