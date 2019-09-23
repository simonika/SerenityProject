package org.fasttrack.serenity.steps;

import net.thucydides.core.annotations.Step;
import org.fasttrack.serenity.pages.ShopPage;

public class ShopSteps {

    ShopPage shopPage;

    @Step
    public void getProductIntoCart(String item) {
        shopPage.getProductIntoCart(item);
    }
}
