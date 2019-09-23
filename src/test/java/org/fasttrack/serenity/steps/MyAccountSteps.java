package org.fasttrack.serenity.steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import org.fasttrack.serenity.pages.MyAccountPage;

import java.util.List;

public class MyAccountSteps {

    MyAccountPage myAccountPage;


    @Step
    public void fillRegisterUsername(String text){
        myAccountPage.fillRegisterUsername(text);
    }

    @Step
    public void fillRegisterPassword(String text){
        myAccountPage.fillRegisterPassword(text);
    }

    @Step
    public void clickRegisterButton(){
        myAccountPage.clickRegisterButton();
    }

    @StepGroup
    public void createRegister(String username, String password) {
        fillRegisterUsername(username);
        fillRegisterPassword(password);
        clickRegisterButton();
    }

    @StepGroup
    public void createRegisterAndCheckStrength(String username, String password, String message) {
        fillRegisterUsername(username);
        fillRegisterPassword(password);
        verifyPasswordStrength(message);
        clickRegisterButton();
    }

    @StepGroup
    public boolean isRegisterFormEmpty() {
       return isRegisterUsernameEmpty() && isRegisterPasswordEmpty();
    }

    @Step
    private boolean isRegisterPasswordEmpty() {
        return myAccountPage.isRegisterPasswordEmpty();
    }

    @Step
    private boolean isRegisterUsernameEmpty() {
        return myAccountPage.isRegisterUsernameEmpty();
    }

    @Step
    public void verifyRegister(String randomEmailPrefix) {
        myAccountPage.verifyRegister(randomEmailPrefix);
    }

    @Step
    public void logoutFromConfirmationMessage() {
        myAccountPage.logoutFromConfirmationMessage();
    }

    @Step
    public void myAccountNavigation(String elementToNavigate) {
        myAccountPage.myAccountNavigation(elementToNavigate);
    }

    @StepGroup
    public void login(String username, String password) {
        fillLoginUsername(username);
        fillLoginPassword(password);
        clickLoginButton();
    }

    @Step
    private void clickLoginButton() {
        myAccountPage.clickLoginButton();
    }

    @Step
    private void fillLoginPassword(String password) {
        myAccountPage.fillLoginPassword(password);
    }

    @Step
    private void fillLoginUsername(String username) {
        myAccountPage.fillLoginUsername(username);
    }

    @Step
    public void verifyPasswordStrength(String message) {
        myAccountPage.verifyPasswordStrength(message);
    }

    @Step
    public void registerButtonIsDisabled() {
        myAccountPage.registerButtonIsDisabled();
    }

    @Step
    public void registerButtonIsEnabled() {
        myAccountPage.registerButtonIsEnabled();
    }

    @Step
    public void clickOnBillingAddressEdit() {
        myAccountPage.clickOnBillingAddressEdit();
    }

    @StepGroup
    public void fillBillingAddressDetails(String firstName, String lastName, String country, String streetAddress, String city, String county, String postCode, String phone, String email) {
        fillFirstNameInput(firstName);
        fillLastNameInput(lastName);
        if(country == null || country.isEmpty()) {
            selectRandomCountryFromDropdown();
        }else{
            selectFromCountryDropdown(country);
        }
        fillStreetAddress(streetAddress);
        fillAddressCity(city);
        selectOrFillAddressState(county);
        fillAddressPostCode(postCode);
        fillAddressPhone(phone);
        verifyEmail(email);
        clickSaveAddressButton();

    }
    @StepGroup
    public void fillBillingAddressDetailsCheckout(String firstName, String lastName, String country, String streetAddress, String city, String county, String postCode, String phone, String email) {
        fillFirstNameInput(firstName);
        fillLastNameInput(lastName);
        if(country == null || country.isEmpty()) {
            selectRandomCountryFromDropdown();
        }else{
            selectFromCountryDropdown(country);
        }
        fillStreetAddress(streetAddress);
        fillAddressCity(city);
        selectOrFillAddressState(county);
        fillAddressPostCode(postCode);
        fillAddressPhone(phone);
        verifyEmail(email);

    }
    @Step
    public void clickSaveAddressButton() {
        myAccountPage.clickSaveAddressButton();
    }

    @Step
    private void verifyEmail(String email) {
        myAccountPage.verifyEmail(email);
    }

    @Step
    private void fillAddressPhone(String phone) {
        myAccountPage.fillAddressPhone(phone);
    }

    @Step
    private void fillAddressPostCode(String postCode) {
        myAccountPage.fillAddressPostCode(postCode);
    }

    @Step
    private void selectOrFillAddressState(String county) {
        myAccountPage.selectOrFillAddressState(county);
    }

    @Step
    private void fillAddressCity(String city) {
        myAccountPage.fillAddressCity(city);
    }

    @Step
    private void fillStreetAddress(String streetAddress) {
        myAccountPage.fillStreetAddress(streetAddress);
    }

    @Step
    public String getSelectedCountry(){
        return  myAccountPage.getSelectedCountry();
    }

    @Step
    private void selectFromCountryDropdown(String country) {
        myAccountPage.selectFromCountryDropdown(country);
    }

    @Step
    public void selectRandomCountryFromDropdown(){
        myAccountPage.selectRandomCountryFromDropdown();
    }

    @Step
    private void fillLastNameInput(String lastName) {
        myAccountPage.fillLastNameInput(lastName);
    }

    @Step
    private void fillFirstNameInput(String firstName) {
        myAccountPage.fillFirstNameInput(firstName);
    }

    @Step
    public void checkUpdatedMessageSuccessfully(String addressUpdatedMessage) {
        myAccountPage.checkUpdatedMessageSuccessfully(addressUpdatedMessage);
    }

    @Step
    public void verifyAllUpdatedAddressDetails(List<String> addressDetailsList) {
        for(String detail : addressDetailsList){
            myAccountPage.verifyAddressDetail(detail);
        }
    }

    @Step
    public void checkAddressUpdateErrors(List<String> listOfErrors) {
        for(String error : listOfErrors) {
            if(error.length() > 0)
            myAccountPage.checkAddressDetailError(error);
        }
    }
}
