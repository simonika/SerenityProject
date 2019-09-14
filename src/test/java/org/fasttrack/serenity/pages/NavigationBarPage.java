package org.fasttrack.serenity.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;

import java.util.List;

public class NavigationBarPage extends PageObject {

    @FindBy(css = "#primary-menu li a")
    private List<WebElementFacade> navigationMenuList;

    @FindBy(css = "h1.site-title a[href*='fasttrackit']")
    private WebElementFacade logo;

    @FindBy(css = "div.header-search a i")
    private WebElementFacade serachIcon;

    @FindBy(css = "input.search-field")
    private WebElementFacade searchInput;

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
}
