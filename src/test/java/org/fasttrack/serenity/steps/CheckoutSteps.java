package org.fasttrack.serenity.steps;

import net.thucydides.core.annotations.Step;
import org.fasttrack.serenity.pages.CheckoutPage;

public class CheckoutSteps {
    CheckoutPage checkoutPage;


    @Step
    public void clickPlaceOrderButton(){
        checkoutPage.clickPlaceOrderButton();
    }

    @Step
    public void clickPlaceOrderButtonDisappears(){
        checkoutPage.clickPlaceOrderButtonDisappears();
    }

}
