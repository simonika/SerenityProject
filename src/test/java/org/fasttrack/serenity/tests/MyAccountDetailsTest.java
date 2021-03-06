package org.fasttrack.serenity.tests;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.pages.Pages;
import org.apache.commons.lang3.RandomStringUtils;
import org.fasttrack.serenity.steps.MyAccountSteps;
import org.fasttrack.serenity.steps.NavigationSteps;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.List;

@RunWith(SerenityRunner.class)
public class MyAccountDetailsTest {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @ManagedPages(defaultUrl = "http://qa2.fasttrackit.org:8008/")
    public Pages pages;


    @Steps
    public NavigationSteps navigationSteps;

    @Steps
    public MyAccountSteps myAccountSteps;

    String  randomEmailPrefix = RandomStringUtils.randomAlphabetic(7);
    String username = randomEmailPrefix + "@dede.com";
    String password = "MyTest@1234!";

    @Before
    public void registerTest(){
        String message = "Strong";
        navigationSteps.navigate();

        navigationSteps.clickOnMeniuByName("My account");

        myAccountSteps.createRegisterAndCheckStrength(username, password, message);

        myAccountSteps.verifyRegister(randomEmailPrefix);
    }

    @Test
    public void accountAddressTest(){
        String randomString = RandomStringUtils.randomAlphabetic(7);
        String firstName = "first_name_" + randomString ;
        String lastName = "last_name_" + randomString ;
        String country = null;
        String elementToNavigate = "Addresses";
        String streetAddress = "street number "+ randomString;
        String city = "city_"+randomString;
        String county = "county_"+randomString;
        String postCode = RandomStringUtils.randomNumeric(6);
        String phoneNumber = "0"+ RandomStringUtils.randomNumeric(9);
        String addressUpdatedMessage = "Address changed successfully.";

        myAccountSteps.myAccountNavigation(elementToNavigate);

        myAccountSteps.clickOnBillingAddressEdit();

        myAccountSteps.fillBillingAddressDetails(firstName, lastName, country, streetAddress, city, county, postCode, phoneNumber, username);

        if(country == null){
            country = myAccountSteps.getSelectedCountry();
        }

        myAccountSteps.checkUpdatedMessageSuccessfully(addressUpdatedMessage);

        List<String> addressDetailsList = Arrays.asList(firstName+" "+lastName, country, streetAddress, city, postCode);
        myAccountSteps.verifyAllUpdatedAddressDetails(addressDetailsList);

    }

}
