package com.auction.bidding.validators;

import com.auction.bidding.constraints.ProductBidEndDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class ProductBidEndDateValidator implements ConstraintValidator<ProductBidEndDate, String> {
    private ProductBidEndDate validator;

    public void initialize(ProductBidEndDate ProductBidEndDate) {
        this.validator = ProductBidEndDate;
    }

    @Override
    public boolean isValid(final String valueToValidate, final ConstraintValidatorContext context) {
        String date[] = valueToValidate.split("[\\/-]");
        int day = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int year = Integer.parseInt(date[2]);
        LocalDate today = LocalDate.now();
        return today.isBefore(LocalDate.of(day, month, year));
    }
}

