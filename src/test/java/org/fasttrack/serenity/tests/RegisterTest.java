package org.fasttrack.serenity.tests;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.pages.Pages;
import org.fasttrack.serenity.steps.EndUserSteps;
import org.fasttrack.serenity.steps.NavigationSteps;
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

    @Test
    public void registerTest(){
        String username = "andrei@dede.com";
        String password = "abcd1234!";
        navigationSteps.navigate();

        navigationSteps.clickOnMeniuByName("My account");

        navigationSteps.createRegister(username, password);
        System.out.println();

    }
}
