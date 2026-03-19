package com.saucedemo.helpers;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

public class SecurityAlertHelper {

    public static void handlePossibleSecurityAlert(Actor actor) {
        System.out.println(">>> Verificando ventana de seguridad DESPUÉS de carga de página...");

        try {
            // Esperar un momento para que la ventana se muestre
            Thread.sleep(3000);

            // ESTRATEGIA 1: ESCAPE (cierra la mayoría de ventanas)
            System.out.println(">>> Estrategia 1: Enviando ESC");
            new Actions(BrowseTheWeb.as(actor).getDriver())
                    .sendKeys(Keys.ESCAPE)
                    .perform();
            Thread.sleep(1000);

            // Verificar si la ventana sigue presente (por el texto en la página)
            String pageSource = BrowseTheWeb.as(actor).getDriver().getPageSource();
            if (pageSource.contains("Cambia tu contraseña") || pageSource.contains("cambies tu contraseña")) {
                System.out.println(">>> Ventana aún presente, estrategia 2: ENTER");

                // ESTRATEGIA 2: ENTER
                new Actions(BrowseTheWeb.as(actor).getDriver())
                        .sendKeys(Keys.ENTER)
                        .perform();
                Thread.sleep(1000);
            }

            // ESTRATEGIA 3: TAB + ENTER (navegación por teclado)
            if (pageSource.contains("Cambia tu contraseña")) {
                System.out.println(">>> Ventana aún presente, estrategia 3: TAB + ENTER");
                Actions actions = new Actions(BrowseTheWeb.as(actor).getDriver());
                actions.sendKeys(Keys.TAB).perform();
                Thread.sleep(500);
                actions.sendKeys(Keys.ENTER).perform();
                Thread.sleep(1000);
            }

            // Verificación final
            pageSource = BrowseTheWeb.as(actor).getDriver().getPageSource();
            if (!pageSource.contains("Cambia tu contraseña")) {
                System.out.println(">>> ✅ Ventana de seguridad cerrada exitosamente");
            } else {
                System.out.println(">>> ⚠️ La ventana de seguridad aún podría estar presente");
            }

        } catch (Exception e) {
            System.out.println(">>> Error al manejar ventana de seguridad: " + e.getMessage());
        }
    }
}