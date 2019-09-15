package org.fasttrack.serenity.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MyAccountPage extends PageObject {

    @FindBy(id = "reg_email")
    private WebElementFacade registerUsername;

    @FindBy(id = "reg_password")
    private WebElementFacade registerPassword;

    @FindBy(css = "button[value$='Register']")
    private WebElementFacade registerButton;

    @FindBy(css = "div.woocommerce-MyAccount-content")
    private WebElementFacade registerConfirmation;

    @FindBy(css = "div[class*='MyAccount'] a[href*='customer-logout']")
    private WebElementFacade registerLogout;

    @FindBy(css = "nav[class$='MyAccount-navigation'] li a")
    private List<WebElementFacade> myAccountNavigationList;

    @FindBy(css = "div#customer_login #username")
    private WebElementFacade loginUsername;

    @FindBy(css = "div#customer_login #password")
    private WebElementFacade loginPassword;

    @FindBy(css = "div#customer_login button[name='login']")
    private WebElementFacade loginButton;

    @FindBy(css = "div[class*='password-strength']")
    private WebElementFacade passwordCheckElement;

    @FindBy(css = "a[href$='address=billing']")
    private WebElementFacade billingAddressEditButton;

    @FindBy(id = "billing_first_name")
    private WebElementFacade firstNameInput;

    @FindBy(id = "billing_last_name")
    private WebElementFacade lastNameInput;

//    @FindBy(xpath = "//*[@id='billing_country']")
    @FindBy(id = "billing_country")
    private WebElementFacade countryDropdown;

    private String selectedCountry = "";

    public void fillRegisterUsername(String text){
        typeInto(registerUsername, text);
    }

    public void fillRegisterPassword(String text) {
        typeInto(registerPassword, text);
    }

    public void clickRegisterButton() {
        element(registerButton).withTimeoutOf(Duration.ofSeconds(10)).waitUntilClickable();
        element(registerButton).click();
    }

    public void verifyRegister(String randomEmailPrefix) {
        element(registerConfirmation).waitUntilEnabled();
        String actualMessage = registerConfirmation.getText();
        Assert.assertTrue("The confirmation message should contain: " + randomEmailPrefix + ", not actually: " + actualMessage,
                actualMessage.contains(randomEmailPrefix));
    }

    public void logoutFromConfirmationMessage() {
        element(registerLogout).waitUntilEnabled();
        registerLogout.click();
    }

    public boolean isRegisterUsernameEmpty() {
        element(registerUsername).waitUntilVisible();
        return registerUsername.getAttribute("value").length() == 0;
    }

    public boolean isRegisterPasswordEmpty() {
        element(registerPassword).waitUntilVisible();
        return registerPassword.getAttribute("value").length() == 0;
    }

    public void myAccountNavigation(String elementToNavigate) {
        for(WebElementFacade item : myAccountNavigationList){
            if(item.getText().equalsIgnoreCase(elementToNavigate)){
                item.click();
                break;
            }
        }
    }

    public void fillLoginUsername(String username) {
        typeInto(loginUsername, username);
    }

    public void fillLoginPassword(String password) {
        typeInto(loginPassword, password);
    }

    public void clickLoginButton() {
        element(loginButton).waitUntilClickable();
        loginButton.click();
    }

    public void verifyPasswordStrength(String message) {
        element(passwordCheckElement).waitUntilVisible();
        String actualMessage = passwordCheckElement.getText();
        Assert.assertTrue("The password strength message should be: " + message + ", not actually: " + actualMessage,
                actualMessage.equalsIgnoreCase(message));
    }

    public void registerButtonIsDisabled() {
        element(registerButton).waitUntilVisible();
        Assert.assertTrue("The register button shouldn't be enabled: " + registerButton.isEnabled(),
                registerButton.isDisabled() == true);

    }

    public void registerButtonIsEnabled() {
        element(registerButton).waitUntilVisible();
        Assert.assertTrue("The register button shouldn't be disabled: " + registerButton.isDisplayed(),
                registerButton.isEnabled() == true);
    }

    public void clickOnBillingAddressEdit() {
        element(billingAddressEditButton).waitUntilVisible();
        billingAddressEditButton.click();
    }

    private int getRandomNumberBetween(int minNumber, int maxNumber) {
        if (minNumber >= maxNumber) {
            throw new IllegalArgumentException("maxNumber must be greater than minNumber");
        }

        Random r = new Random();
        return r.nextInt((maxNumber - minNumber) + 1) + minNumber;
    }

    public void selectFromCountryDropdown(String country) {
        selectFromDropdown(countryDropdown, country);
    }

    public void selectRandomCountryFromDropdown(){
        Select select = new Select(countryDropdown);
        int countryOptions = select.getOptions().size();
        select.selectByIndex(getRandomNumberBetween(0, countryOptions));
        selectedCountry = select.getFirstSelectedOption().getText();
    }

    public String getSelectedCountry(){
        return selectedCountry;
    }

    public void fillLastNameInput(String lastName) {
        typeInto(lastNameInput, lastName);
    }

    public void fillFirstNameInput(String firstName) {
        typeInto(firstNameInput, firstName);
    }
}
