package org.fasttrack.serenity.steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import org.fasttrack.serenity.pages.CartPage;
import org.fasttrack.serenity.pages.NavigationBarPage;

public class CartSteps {
    NavigationBarPage navigationBarPage;
    CartPage cartPage;

    @Step
    public void chooseQuantityForProduct(String item, String itemQuantity_1) {
        cartPage.chooseQuantityForProduct(item, itemQuantity_1);
    }

    @StepGroup
    public void updateCart(String item, String updatedQuantity){
        checkDefaultQuantity();
        chooseQuantityForProduct(item, updatedQuantity);
        clickUpdateCartButton();
        navigationBarPage.refreshBrowser();
        checkUpdatedQuantity(updatedQuantity);
    }

    @Step
    private void checkUpdatedQuantity(String updatedQuantity){
        navigationBarPage.updatedQuantityIntoCart(updatedQuantity);
    }

    @Step
    private void checkDefaultQuantity() {
        navigationBarPage.checkDefaultQuantityIntoCart();
    }

    @Step
    private void clickUpdateCartButton() {
        cartPage.clickUpdateCartButton();
    }

    @Step
    private void clickUpdateCartButtonDisappears() {
        cartPage.clickUpdateCartButtonDisappears();
    }

    @StepGroup
    public void updateCartAndButtonDisappears(String item, String updatedQuantity){
        checkDefaultQuantity();
        chooseQuantityForProduct(item, updatedQuantity);
        clickUpdateCartButtonDisappears();
        navigationBarPage.refreshBrowser();
        checkUpdatedQuantity(updatedQuantity);
    }

    @Step
    public void checkUpdatedMessageSuccessfully(String updatedMessage) {
        cartPage.checkUpdatedMessageSuccessfully(updatedMessage);
    }

    @Step
    public void clickBackToShop() {
        cartPage.clickBackToShop();
    }

    @Step
    public void clickProceedToCheckoutButton() {
        cartPage.clickProceedToCheckoutButton();
    }
}
