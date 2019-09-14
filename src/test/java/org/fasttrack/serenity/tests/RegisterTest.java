package org.fasttrack.serenity.tests;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.pages.Pages;
import org.apache.commons.lang3.RandomStringUtils;
import org.fasttrack.serenity.steps.MyAccountSteps;
import org.fasttrack.serenity.steps.NavigationSteps;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class RegisterTest {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @ManagedPages(defaultUrl = "http://qa2.fasttrackit.org:8008/")
    public Pages pages;


    @Steps
    public NavigationSteps navigationSteps;

    @Steps
    public MyAccountSteps myAccountSteps;

    @Test
    public void registerTest(){
        String  randomEmailPrefix = RandomStringUtils.randomAlphabetic(7);
        String username = randomEmailPrefix + "@dede.com";
        String password = "MyTest@1234!";
        navigationSteps.navigate();

        navigationSteps.clickOnMeniuByName("My account");

        myAccountSteps.createRegister(username, password);

        myAccountSteps.verifyRegister(randomEmailPrefix);
    }

    @Test
    public void registerAndLogOutTest(){
        String  randomEmailPrefix = RandomStringUtils.randomAlphabetic(7);
        String username = randomEmailPrefix + "@dede.com";
        String password = "MyTest@1234!";
        navigationSteps.navigate();

        navigationSteps.clickOnMeniuByName("My account");

        myAccountSteps.createRegister(username, password);

        myAccountSteps.verifyRegister(randomEmailPrefix);

        myAccountSteps.logoutFromConfirmationMessage();

        //assert that the register fields are empty
        Assert.assertTrue("Register's username and paswword should be empty, but actually is:  " + myAccountSteps.isRegisterFormEmpty(),
                myAccountSteps.isRegisterFormEmpty() == true);
    }

    @Test
    public void registerAndLogOutFromMyAccountNavigationTest(){
        String  randomEmailPrefix = RandomStringUtils.randomAlphabetic(7);
        String username = randomEmailPrefix + "@dede.com";
        String password = "MyTest@1234!";
        String elementToNavigate = "LogOut";
        navigationSteps.navigate();

        navigationSteps.clickOnMeniuByName("My account");

        myAccountSteps.createRegister(username, password);

        myAccountSteps.verifyRegister(randomEmailPrefix);

        myAccountSteps.myAccountNavigation(elementToNavigate);

        //assert that the register fields are empty
        Assert.assertTrue("Register's username and paswword should be empty, but actually is:  " + myAccountSteps.isRegisterFormEmpty(),
                myAccountSteps.isRegisterFormEmpty() == true);
    }

    @Test
    public void registerLogOutAndLoginTest(){
        String  randomEmailPrefix = RandomStringUtils.randomAlphabetic(7);
        String username = randomEmailPrefix + "@dede.com";
        String password = "MyTest@1234!";
        navigationSteps.navigate();

        navigationSteps.clickOnMeniuByName("My account");

        myAccountSteps.createRegister(username, password);

        myAccountSteps.verifyRegister(randomEmailPrefix);

        myAccountSteps.logoutFromConfirmationMessage();

        myAccountSteps.login(username, password);

        //assertion that we can see the username being logged in
        myAccountSteps.verifyRegister(randomEmailPrefix);
    }

    @Test
    public void checkWeakPasswordTest(){
        String  randomEmailPrefix = RandomStringUtils.randomAlphabetic(7);
        String username = randomEmailPrefix + "@dede.com";
        String password = "MyTest";
        String message = "Weak - Please enter a stronger password.";
        navigationSteps.navigate();

        navigationSteps.clickOnMeniuByName("My account");

        myAccountSteps.fillRegisterUsername(username);
        myAccountSteps.fillRegisterPassword(password);

        myAccountSteps.verifyPasswordStrength(message);
        myAccountSteps.registerButtonIsDisabled();
    }

    @Test
    public void checkMediumPasswordTest(){
        String  randomEmailPrefix = RandomStringUtils.randomAlphabetic(7);
        String username = randomEmailPrefix + "@dede.com";
        String password = "MyTest@12";
        String message = "Medium";
        navigationSteps.navigate();

        navigationSteps.clickOnMeniuByName("My account");

        myAccountSteps.fillRegisterUsername(username);
        myAccountSteps.fillRegisterPassword(password);

        myAccountSteps.verifyPasswordStrength(message);
        myAccountSteps.registerButtonIsEnabled();
    }

    @Test
    public void checkStrongPasswordTest(){
        String  randomEmailPrefix = RandomStringUtils.randomAlphabetic(7);
        String username = randomEmailPrefix + "@dede.com";
        String password = "MyTest@1234!";
        String message = "Strong";
        navigationSteps.navigate();

        navigationSteps.clickOnMeniuByName("My account");

        myAccountSteps.fillRegisterUsername(username);
        myAccountSteps.fillRegisterPassword(password);

        myAccountSteps.verifyPasswordStrength(message);
        myAccountSteps.registerButtonIsEnabled();
    }
}
