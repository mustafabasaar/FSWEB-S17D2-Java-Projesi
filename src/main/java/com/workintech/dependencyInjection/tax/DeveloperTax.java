package com.workintech.dependencyInjection.tax;
import org.springframework.stereotype.Component;

@Component
public class DeveloperTax implements Taxable{
    private static final double SIMPLE_TAX_RATE = 0.15;
    private static final double MIDDLE_TAX_RATE = 0.20;
    private static final double UPPER_TAX_RATE = 0.25;

    @Override
    public double getSimpleTaxRate() {

        return SIMPLE_TAX_RATE ;
    }

    @Override
    public double getMiddleTaxRate() {

        return MIDDLE_TAX_RATE;
    }

    @Override
    public double getUpperTaxRate() {

        return UPPER_TAX_RATE;
    }


}

