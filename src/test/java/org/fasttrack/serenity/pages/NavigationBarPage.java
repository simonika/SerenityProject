package org.fasttrack.serenity.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class NavigationBarPage extends PageObject {

    public NavigationBarPage(WebDriver driver){
        super(driver);
    }

    @FindBy(css = "#primary-menu li a")
    private List<WebElementFacade> navigationMenuList;

    @FindBy(css = "h1.site-title a[href*='fasttrackit']")
    private WebElementFacade logo;

    @FindBy(css = "div.header-search a i")
    private WebElementFacade serachIcon;

    @FindBy(css = "input.search-field")
    private WebElementFacade searchInput;

    @FindBy(css = "span.cart-count")
    private WebElementFacade cartQuantityCounterElement;

    @FindBy(css = "h1[class$='page-title'], [class='entry-title']")
    private WebElementFacade pageTitleElement;

    public void clickOnMenuByName(String menuItem){
        for(WebElementFacade item : navigationMenuList){
            if(item.getText().equalsIgnoreCase(menuItem)){
                waitFor(item).isClickable();
                item.click();
                break;
            }
        }
    }

    public void clickOnSearch() {
        element(serachIcon).waitUntilClickable();
        serachIcon.click();
    }

    public void searchForItem(String item) {
        typeInto(searchInput, item);
        selectFromDropdown(getDriver().findElement(By.cssSelector("select.select-search-type")), "Product");
//        Select select = new Select(getDriver().findElement(By.cssSelector("select.select-search-type")));
//        select.selectByVisibleText("Product");
        getDriver().findElement(By.cssSelector("button.searchsubmit")).click();
        System.out.println();
    }

    public void checkDefaultQuantityIntoCart() {
        element(cartQuantityCounterElement).isCurrentlyEnabled();
        Assert.assertTrue("The default quantity should be: '1', not: " + cartQuantityCounterElement.getText(), cartQuantityCounterElement.getText().equals("1"));
    }

    public void updatedQuantityIntoCart(String updatedQuantity) {
        element(cartQuantityCounterElement).isCurrentlyEnabled();
        Assert.assertTrue("The updated quantity should be: '"+updatedQuantity+"', not: " + cartQuantityCounterElement.getText(), cartQuantityCounterElement.getText().equals(updatedQuantity));
    }

    public void refreshBrowser() {
        getDriver().navigate().refresh();
    }

    public void checkPageTitle(String title) {
        element(pageTitleElement).isCurrentlyEnabled();
        Assert.assertTrue("The page title should be: '"+title+"', not: " + pageTitleElement.getText(), pageTitleElement.getText().equals(title));

    }
}
