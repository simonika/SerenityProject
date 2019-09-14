package org.fasttrack.serenity.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class MyAccountPage extends PageObject {

    @FindBy(id = "reg_email")
    private WebElementFacade registerUsername;

    @FindBy(id = "reg_password")
    private WebElementFacade registerPassword;

    @FindBy(css = "button[value$='Register']")
    private WebElementFacade registerButton;

    public void fillRegisterUsername(String text){
        element(registerUsername).type(text);
    }

    public void fillRegisterPassword(String text) {
        element(registerPassword).type(text);
    }

    public void clickRegisterButton() {
     element(registerButton).click();
    }
}
