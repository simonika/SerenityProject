package org.fasttrack.serenity.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CartPage extends PageObject {

    public CartPage(WebDriver driver){
        super(driver);
    }

    WebDriverWait wait = new WebDriverWait(getDriver(), 15);

    @FindBy(css = "tr[class$='cart_item']")
    private List<WebElement> cartRows;

    @FindBy(css = "button[name='update_cart']")
    private WebElement updateCartButton;

    @FindBy(css = "p.cart-empty")
    private WebElement cartMessageElement;

    @FindBy(css = "a.button.wc-backward")
    private WebElement backToShopButton;

    @FindBy(css = "a[class^='checkout-button']")
    private WebElement proceedToCheckoutButton;

    public void chooseQuantityForProduct(String item, String itemQuantity_1) {
        wait.until(ExpectedConditions.visibilityOfAllElements(cartRows));
        for(WebElement row : cartRows){
            if(row.findElement(By.cssSelector("td[class$='name']")).getText().equalsIgnoreCase(item)){
                WebElement input= row.findElement(By.cssSelector("td[class$='quantity'] input"));
                typeInto(input, itemQuantity_1);
                break;
            }
        }
    }

    public void clickUpdateCartButton() {
        clickOn(updateCartButton);
        wait.until(ExpectedConditions.attributeToBe(updateCartButton, "disabled", "true"));
    }

    public void clickUpdateCartButtonDisappears() {
        clickOn(updateCartButton);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("button[name='update_cart']")));
    }

    public void checkUpdatedMessageSuccessfully(String updatedMessage) {
      element(cartMessageElement).waitUntilVisible();
        Assert.assertTrue("The cart update message : " + updatedMessage + ", not actually: " + cartMessageElement.getText(),
                cartMessageElement.getText().equals(updatedMessage));
    }

    public void clickBackToShop() {
        clickOn(backToShopButton);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("a.button.wc-backward")));
    }

    public void clickProceedToCheckoutButton() {
        clickOn(proceedToCheckoutButton);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("a[class^='checkout-button']")));
    }
}
