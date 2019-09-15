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

    @Before
    public void registerTest(){
        String  randomEmailPrefix = RandomStringUtils.randomAlphabetic(7);
        String username = randomEmailPrefix + "@dede.com";
        String password = "MyTest@1234!";
        navigationSteps.navigate();

        navigationSteps.clickOnMeniuByName("My account");

        myAccountSteps.createRegister(username, password);

        myAccountSteps.verifyRegister(randomEmailPrefix);
    }

    @Test
    public void accountAddressTest(){
        String  randomString = RandomStringUtils.randomAlphabetic(7);
        String firstName = "first_name_" + randomString ;
        String lastName = "last_name_" + randomString ;
        String country = "Jamaica";
        String elementToNavigate = "Addresses";

        myAccountSteps.myAccountNavigation(elementToNavigate);

        myAccountSteps.clickOnBillingAddressEdit();

        myAccountSteps.fillBillingAddressDetails(firstName, lastName, country);

        System.out.println("Country selected: " + myAccountSteps.getSelectedCountry());

        //assert that the register fields are empty
//        Assert.assertTrue("Register's username and paswword should be empty, but actually is:  " + myAccountSteps.isRegisterFormEmpty(),
//                myAccountSteps.isRegisterFormEmpty() == true);
    }

}
