package org.fasttrack.serenity.steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import org.fasttrack.serenity.pages.MyAccountPage;
import org.fasttrack.serenity.pages.NavigationBarPage;

public class NavigationSteps {

    NavigationBarPage navigationBarPage;
    MyAccountPage myAccountPage;

    @Step
    public void navigate() {
        myAccountPage.open();
    }

    @Step
    public void clickOnMeniuByName(String meniuToClick){
        navigationBarPage.clickOnMenuByName(meniuToClick);
    }

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
    }
}
