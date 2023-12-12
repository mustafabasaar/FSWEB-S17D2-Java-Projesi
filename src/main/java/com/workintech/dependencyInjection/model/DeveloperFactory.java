package com.workintech.dependencyInjection.model;

import com.workintech.dependencyInjection.tax.Taxable;

public class DeveloperFactory {
    public static Developer createDeveloper(Developer developer, Taxable taxable){
        Developer createdDeveloper=null;
        if(developer.getExperience().name().equalsIgnoreCase("JuniorDeveloper")){
            createdDeveloper=new JuniorDeveloper(developer.getId(),developer.getName(),(developer.getSalary()-(developer.getSalary()*taxable.getSimpleTaxRate())/100));
        }
        else if(developer.getExperience().name().equalsIgnoreCase("MidDeveloper")){
            createdDeveloper=new MidDeveloper(developer.getId(),developer.getName(),(developer.getSalary()-(developer.getSalary()*taxable.getMiddleTaxRate())/100));
        }
        else if(developer.getExperience().name().equalsIgnoreCase("SeniorDeveloper")){
            createdDeveloper=new JuniorDeveloper(developer.getId(),developer.getName(),(developer.getSalary()-(developer.getSalary()*taxable.getUpperTaxRate())/100));
        }
        return createdDeveloper;
    }
}
