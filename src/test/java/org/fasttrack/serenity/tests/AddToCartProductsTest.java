package org.fasttrack.serenity.tests;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.pages.Pages;
import org.apache.commons.lang3.RandomStringUtils;
import org.fasttrack.serenity.steps.CartSteps;
import org.fasttrack.serenity.steps.MyAccountSteps;
import org.fasttrack.serenity.steps.NavigationSteps;
import org.fasttrack.serenity.steps.ShopSteps;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class AddToCartProductsTest {

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
    public void searchProductAndUpdateCartTest(){

        String item = "beanie";
        String updatedQuantity = "5";

        navigationSteps.clickOnMeniuByName("Shop");

        navigationSteps.clickOnSearch();

        navigationSteps.searchForItem(item);

        shopSteps.getProductIntoCart(item);

        cartSteps.updateCart(item, updatedQuantity);
    }

    @Test
    public void emptyCartTest(){
        String item = "beanie";
        String updatedQuantity = "0";

        String updatedMessage = "Your cart is currently empty.";

        navigationSteps.clickOnMeniuByName("Shop");

        navigationSteps.clickOnSearch();

        navigationSteps.searchForItem(item);

        shopSteps.getProductIntoCart(item);

        cartSteps.updateCartAndButtonDisappears(item, updatedQuantity);

        cartSteps.checkUpdatedMessageSuccessfully(updatedMessage);
    }

    @Test
    public void emptyCartAndReturnToShopTest(){
        String item = "beanie";
        String updatedQuantity = "0";

        String updatedMessage = "Your cart is currently empty.";

        navigationSteps.clickOnMeniuByName("Shop");

        navigationSteps.clickOnSearch();

        navigationSteps.searchForItem(item);

        shopSteps.getProductIntoCart(item);

        cartSteps.updateCartAndButtonDisappears(item, updatedQuantity);

        cartSteps.checkUpdatedMessageSuccessfully(updatedMessage);

        cartSteps.clickBackToShop();

        navigationSteps.checkPageTitle("Shop");

    }


}
