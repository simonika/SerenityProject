package org.fasttrack.serenity.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ShopPage extends PageObject {

    @FindBy(css = "ul[class^='products'] li")
    private List<WebElementFacade> productList;

    public void getProductIntoCart(String item) {
        element(productList.get(0)).isCurrentlyEnabled();
        for(WebElementFacade product : productList){
            String title = product.findElement(By.cssSelector("h3")).getText().trim();
            WebElement cartButton = product.findElement(By.cssSelector("div.cart a[class*='add_to_cart']"));
            if(title.equalsIgnoreCase(item)
                    && cartButton.isEnabled() && cartButton.isDisplayed()){
                cartButton.click();
                product.findElement(By.cssSelector("div.cart a[class^='added_to_cart']")).click();
                break;
            }

        }
    }
}
