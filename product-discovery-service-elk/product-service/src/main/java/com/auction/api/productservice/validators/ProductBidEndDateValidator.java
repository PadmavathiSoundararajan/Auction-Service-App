package com.auction.api.productservice.validators;

import com.auction.api.productservice.constraints.ProductBidEndDate;
import com.auction.api.productservice.utils.CommonUtility;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProductBidEndDateValidator implements ConstraintValidator<ProductBidEndDate, String> {
    @Override
    public boolean isValid(final String valueToValidate, final ConstraintValidatorContext context) {
        return CommonUtility.isTodayBeforeOrAfterTheValueDate(valueToValidate, CommonUtility.BEFORE_AFTER_ENUM.BEFORE);
    }
}

