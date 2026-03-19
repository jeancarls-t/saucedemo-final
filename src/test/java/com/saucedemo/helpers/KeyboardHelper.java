package com.saucedemo.helpers;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

public class KeyboardHelper {

    public static void pressEscape(Actor actor) {
        try {
            System.out.println(">>> Enviando tecla ESC");
            new Actions(BrowseTheWeb.as(actor).getDriver())
                    .sendKeys(Keys.ESCAPE)
                    .perform();
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println(">>> Error al enviar ESC: " + e.getMessage());
        }
    }

    public static void pressEnter(Actor actor) {
        try {
            System.out.println(">>> Enviando tecla ENTER");
            new Actions(BrowseTheWeb.as(actor).getDriver())
                    .sendKeys(Keys.ENTER)
                    .perform();
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println(">>> Error al enviar ENTER: " + e.getMessage());
        }
    }

    public static void pressTabAndEnter(Actor actor) {
        try {
            System.out.println(">>> Enviando TAB + ENTER");
            Actions actions = new Actions(BrowseTheWeb.as(actor).getDriver());
            actions.sendKeys(Keys.TAB).perform();
            Thread.sleep(500);
            actions.sendKeys(Keys.ENTER).perform();
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println(">>> Error en TAB+ENTER: " + e.getMessage());
        }
    }
}
