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
@UseTestDataFrom(value = "resources/addressNegative.csv")
public class MyAccountDetailsNegativeTest {
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
    public void myAccountDetailsNegativeTest(){
        String elementToNavigate = "Addresses";

        List<String> listOfErrors = Arrays.asList(error.split("[||]"));

        myAccountSteps.myAccountNavigation(elementToNavigate);

        myAccountSteps.clickOnBillingAddressEdit();
        
        myAccountSteps.clickSaveAddressButton();

        myAccountSteps.checkAddressUpdateErrors(listOfErrors);


    }

}
