package org.fasttrack.serenity.tests;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.pages.Pages;
import org.apache.commons.lang3.RandomStringUtils;
import org.fasttrack.serenity.steps.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.List;

@RunWith(SerenityRunner.class)
public class CheckoutProductsTest {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @ManagedPages(defaultUrl = "http://qa2.fasttrackit.org:8008/")
    public Pages pages;


    @Steps
    public NavigationSteps navigationSteps;

    @Steps
    public MyAccountSteps myAccountSteps;

    @Steps
    public ShopSteps shopSteps;

    @Steps
    public CartSteps cartSteps;

    @Steps
    public CheckoutSteps checkoutSteps;

    String  randomEmailPrefix = RandomStringUtils.randomAlphabetic(7);
    String username = randomEmailPrefix + "@dede.com";
    String password = "MyTest@1234!";

    @Before
    public void registerTest(){

        navigationSteps.navigate();

        navigationSteps.clickOnMeniuByName("My account");

        myAccountSteps.createRegister(username, password);

        myAccountSteps.verifyRegister(randomEmailPrefix);
    }


    @Test
    public void checkoutUpdateCartTest(){
        String item = "beanie";
        String updatedQuantity = "2";

        navigationSteps.clickOnMeniuByName("Shop");

        navigationSteps.clickOnSearch();

        navigationSteps.searchForItem(item);

        shopSteps.getProductIntoCart(item);

        cartSteps.updateCart(item, updatedQuantity);

        cartSteps.clickProceedToCheckoutButton();

        navigationSteps.checkPageTitle("Checkout");

        System.out.println();
    }

    @Test
    public void checkoutWithMissingMandatoryBillingDetailsTest(){
        String item = "beanie";
        String updatedQuantity = "2";

        List<String> listOfErrors = Arrays.asList("Billing First name is a required field.",
                "Billing Last name is a required field.",
                "Billing Street address is a required field.",
                "Billing Town / City is a required field.",
                "Billing Postcode / ZIP is a required field.",
                "Billing Phone is a required field.");

        navigationSteps.clickOnMeniuByName("Shop");

        navigationSteps.clickOnSearch();

        navigationSteps.searchForItem(item);

        shopSteps.getProductIntoCart(item);

        cartSteps.updateCart(item, updatedQuantity);

        cartSteps.clickProceedToCheckoutButton();

        navigationSteps.checkPageTitle("Checkout");

        checkoutSteps.clickPlaceOrderButton();

        myAccountSteps.checkAddressUpdateErrors(listOfErrors);
    }

    @Test
    public void checkoutFixMissingMandatoryBillingDetailsTest(){
        String item = "beanie";
        String updatedQuantity = "2";


        List<String> listOfErrors = Arrays.asList("Billing First name is a required field.",
                "Billing Last name is a required field.",
                "Billing Street address is a required field.",
                "Billing Town / City is a required field.",
                "Billing Postcode / ZIP is a required field.",
                "Billing Phone is a required field.");

        String randomString = RandomStringUtils.randomAlphabetic(7);
        String firstName = "first_name_" + randomString ;
        String lastName = "last_name_" + randomString ;
        String country = null;
        String streetAddress = "street number "+ randomString;
        String city = "city_"+randomString;
        String county = "county_"+randomString;
        String postCode = RandomStringUtils.randomNumeric(6);
        String phoneNumber = "0"+ RandomStringUtils.randomNumeric(9);
        String orderMessage = "Order received";

        navigationSteps.clickOnMeniuByName("Shop");

        navigationSteps.clickOnSearch();

        navigationSteps.searchForItem(item);

        shopSteps.getProductIntoCart(item);

        cartSteps.updateCart(item, updatedQuantity);

        cartSteps.clickProceedToCheckoutButton();

        navigationSteps.checkPageTitle("Checkout");

        checkoutSteps.clickPlaceOrderButton();

        myAccountSteps.checkAddressUpdateErrors(listOfErrors);

        myAccountSteps.fillBillingAddressDetailsCheckout(firstName, lastName, country, streetAddress, city, county, postCode, phoneNumber, username);

        if(country == null){
            country = myAccountSteps.getSelectedCountry();
        }

        checkoutSteps.clickPlaceOrderButtonDisappears();

        navigationSteps.checkPageTitle(orderMessage);
    }


}
