package org.fasttrack.serenity.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MyAccountPage extends PageObject {

    public MyAccountPage(WebDriver driver){
        super(driver);
    }

    WebDriverWait wait = new WebDriverWait(getDriver(), 15);

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

    @FindBy(id = "billing_country")
    private WebElementFacade countryDropdown;

    @FindBy(id = "billing_address_1")
    private WebElementFacade streetAddressInput;

    @FindBy(id = "billing_city")
    private WebElementFacade addressCityInput;

    @FindBy(id = "billing_state")
    private WebElementFacade addressStateDropdown;

    @FindBy(id = "billing_postcode")
    private WebElementFacade addressPostCodeInput;

    @FindBy(id = "billing_phone")
    private WebElementFacade addressPhoneInput;

    @FindBy(id = "billing_email")
    private WebElementFacade addressEmailInput;

    @FindBy(css = "button[name='save_address']")
    private WebElementFacade saveAddressButton;

    @FindBy(css = "label[for='reg_password']")
    private WebElementFacade passwordLabelElement;

    @FindBy(css = "div.woocommerce-message")
    private WebElementFacade addressUpdateElement;

    @FindBy(xpath = "//div[contains(@class, 'Address')]/header/h3[contains(text(), 'Billing address')]/../../address")
    private WebElementFacade billingAddressElement;

    @FindBy(css = "ul.woocommerce-error li")
    private List<WebElementFacade> addressErrorsList;

    private String selectedCountry = "";

    public void fillRegisterUsername(String text){
        typeInto(registerUsername, text);
    }

    public void fillRegisterPassword(String text) {
        typeInto(registerPassword, text);
    }

    public void clickRegisterButton() {
        element(registerButton).withTimeoutOf(Duration.ofSeconds(20)).waitUntilClickable();
        element(registerButton).click();
    }

    public void verifyRegister(String randomEmailPrefix) {
//        element(registerConfirmation).waitUntilEnabled();
        element(registerConfirmation).withTimeoutOf(Duration.ofSeconds(30)).waitUntilEnabled();
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
//        element(passwordCheckElement).withTimeoutOf(Duration.ofSeconds(20)).waitUntilVisible();
        element(passwordLabelElement).waitUntilVisible();
        passwordCheckElement.click();
        waitFor(ExpectedConditions.visibilityOf(passwordCheckElement));
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

    public void fillStreetAddress(String streetAddress) {
        typeInto(streetAddressInput, streetAddress);
    }

    public void fillAddressCity(String city) {
        typeInto(addressCityInput, city);
    }

    public void selectOrFillAddressState(String county) {
        element(addressStateDropdown).isClickable();
        if(addressStateDropdown.isCurrentlyEnabled() && addressStateDropdown.getAttribute("outerHTML").contains("select")) {
            Select select = new Select(addressStateDropdown);
            int stateOptions = select.getOptions().size();
            select.selectByIndex(getRandomNumberBetween(0, stateOptions));
        }else if(addressStateDropdown.isCurrentlyEnabled() && addressStateDropdown.getAttribute("outerHTML").contains("input")){
            typeInto(addressStateDropdown, county);
        }
    }

    public void fillAddressPostCode(String postCode) {
        element(addressPostCodeInput).waitUntilClickable();
        int i = 1;
        do {
            typeInto(addressPostCodeInput, postCode);
            i++;
        }while(!addressPostCodeInput.getAttribute("value").equals(postCode) || i > 5);
    }

    public void fillAddressPhone(String phone) {
        typeInto(addressPhoneInput, phone);
    }

    public void verifyEmail(String email) {
        element(addressEmailInput).waitUntilVisible();
        String value = addressEmailInput.getAttribute("value");
        Assert.assertTrue("The email: " + email + ", not actually: " + value,
               value.equals(email));

    }

    public void clickSaveAddressButton() {
        clickOn(saveAddressButton);
    }

    public void checkUpdatedMessageSuccessfully(String addressUpdatedMessage) {
        wait.until(ExpectedConditions.visibilityOf(addressUpdateElement));
//        element(addressUpdateElement).waitUntilVisible();
        String value = addressUpdateElement.getText();
        Assert.assertTrue("The address update message : " + addressUpdatedMessage + ", not actually: " + value,
                value.equals(addressUpdatedMessage));
    }

    public void verifyAddressDetail(String detail) {
        boolean foundDetail = false;
        List<String> list = Arrays.asList(billingAddressElement.getText().split("\\n"));
        for(String element : list){
            if(element.trim().equals(detail)) {
                foundDetail = true;
            }
        }

        Assert.assertTrue("The address detail : " + detail + ", wasn't found ! ",
                foundDetail == true);
    }

    public void checkAddressDetailError(String error) {
        for(WebElementFacade item : addressErrorsList){
            if(item.getText().equals(error)){
                System.out.println("element: "+ item.getText());
                break;
            }
        }
    }
}
