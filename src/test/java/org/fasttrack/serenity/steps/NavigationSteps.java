package org.fasttrack.serenity.steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.steps.ScenarioSteps;
import org.fasttrack.serenity.pages.MyAccountPage;
import org.fasttrack.serenity.pages.NavigationBarPage;
import org.openqa.selenium.WebDriver;

public class NavigationSteps extends ScenarioSteps {

    NavigationBarPage navigationBarPage;
    MyAccountPage myAccountPage;

    @Step
    public void navigate() {
        myAccountPage.open();
        getDriver().manage().window().maximize();
    }

    @Step
    public void clickOnMeniuByName(String meniuToClick){
        navigationBarPage.clickOnMenuByName(meniuToClick);
    }

    @Step
    public void clickOnSearch() {
        navigationBarPage.clickOnSearch();
    }
    @Step
    public void searchForItem(String item) {
        navigationBarPage.searchForItem(item);
    }
}
