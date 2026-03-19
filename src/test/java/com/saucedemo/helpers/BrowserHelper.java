package com.saucedemo.helpers;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.WebDriver;

public class BrowserHelper {

    public static String getCurrentUrl(Actor actor) {
        return BrowseTheWeb.as(actor).getDriver().getCurrentUrl();
    }

    public static String getPageTitle(Actor actor) {
        return BrowseTheWeb.as(actor).getDriver().getTitle();
    }

    public static void waitForPageLoad(Actor actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        driver.manage().timeouts().pageLoadTimeout(java.time.Duration.ofSeconds(20));
    }
}