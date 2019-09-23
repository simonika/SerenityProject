package org.fasttrack.serenity.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CheckoutPage extends PageObject {

    public CheckoutPage(WebDriver driver){
        super(driver);
    }

    WebDriverWait wait = new WebDriverWait(getDriver(), 15);

    @FindBy(id = "place_order")
    private WebElementFacade placeOrderButton;

    @FindBy(css = "ul.woocommerce-error li")
    private List<WebElementFacade> addressErrorsList;

    public void clickPlaceOrderButton(){
        clickOn(placeOrderButton);
    }

    public void clickPlaceOrderButtonDisappears(){
        clickOn(placeOrderButton);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#place_order")));
    }


}
