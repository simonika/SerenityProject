package org.fasttrack.serenity.tests;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.pages.Pages;
import net.thucydides.junit.annotations.Qualifier;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.apache.commons.lang3.RandomStringUtils;
import org.fasttrack.serenity.steps.MyAccountSteps;
import org.fasttrack.serenity.steps.NavigationSteps;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.List;

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom(value = "resources/address.csv")
public class MyAccountDetailsParametrizeTest {
    private String testNo;
    private String country;
    private String error;

    public String getTestNo() {
        return testNo;
    }

    public void setTestNo(String testNo) {
        this.testNo = testNo;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Qualifier
    public String getQualifier() {
        return country;
    }

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
    public void myAccountDetailsParametrizesTest(){
        String randomString = RandomStringUtils.randomAlphabetic(7);
        String firstName = "first_name_" + randomString ;
        String lastName = "last_name_" + randomString ;
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

        if(country == null || country.isEmpty()){
            country = myAccountSteps.getSelectedCountry();
        }

        myAccountSteps.checkUpdatedMessageSuccessfully(addressUpdatedMessage);

        List<String> addressDetailsList = Arrays.asList(firstName+" "+lastName, country, streetAddress, city, postCode);
        myAccountSteps.verifyAllUpdatedAddressDetails(addressDetailsList);

        //assert that the register fields are empty
//        Assert.assertTrue("Register's username and paswword should be empty, but actually is:  " + myAccountSteps.isRegisterFormEmpty(),
//                myAccountSteps.isRegisterFormEmpty() == true);
    }

}
