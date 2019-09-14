package org.fasttrack.serenity.steps;

import net.thucydides.core.annotations.StepGroup;
import org.fasttrack.serenity.pages.DictionaryPage;
import net.thucydides.core.annotations.Step;
import org.fasttrack.serenity.pages.MyAccountPage;
import org.fasttrack.serenity.pages.NavigationBarPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;

public class EndUserSteps {

    DictionaryPage dictionaryPage;
    NavigationBarPage navigationBarPage;
    MyAccountPage myAccountPage;

    @Step
    public void enters(String keyword) {
        dictionaryPage.enter_keywords(keyword);
    }

    @Step
    public void starts_search() {
        dictionaryPage.lookup_terms();
    }

    @Step
    public void should_see_definition(String definition) {
        assertThat(dictionaryPage.getDefinitions(), hasItem(containsString(definition)));
    }

    @Step
    public void is_the_home_page() {
        dictionaryPage.open();
    }

    @Step
    public void looks_for(String term) {
        enters(term);
        starts_search();
    }

    @Step
    public void navigate() {
        dictionaryPage.open();
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