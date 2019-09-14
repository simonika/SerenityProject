package org.fasttrack.serenity.steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import org.fasttrack.serenity.pages.MyAccountPage;

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
}
