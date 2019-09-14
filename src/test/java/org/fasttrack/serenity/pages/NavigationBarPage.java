package org.fasttrack.serenity.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

import java.util.List;

public class NavigationBarPage extends PageObject {

    @FindBy(css = "#primary-menu li a")
    private List<WebElementFacade> navigationMenuList;

    @FindBy(css = "h1.site-title a[href*='fasttrackit']")
    private WebElementFacade logo;

    public void clickOnMenuByName(String menuItem){
        for(WebElementFacade item : navigationMenuList){
            if(item.getText().equalsIgnoreCase(menuItem)){
                waitFor(item).isClickable();
                item.click();
                break;
            }
        }
    }
}
