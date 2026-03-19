package com.saucedemo.user_interfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CheckoutPage {

    public static final Target CHECKOUT_BUTTON = Target.the("boton checkout")
            .located(By.id("checkout"));

    public static final Target FIRST_NAME = Target.the("nombre")
            .located(By.id("first-name"));

    public static final Target LAST_NAME = Target.the("apellido")
            .located(By.id("last-name"));

    public static final Target POSTAL_CODE = Target.the("codigo postal")
            .located(By.id("postal-code"));

    public static final Target CONTINUE_BUTTON = Target.the("boton continue")
            .located(By.id("continue"));

    public static final Target FINISH_BUTTON = Target.the("boton finish")
            .located(By.id("finish"));

    public static final Target COMPLETE_MESSAGE = Target.the("mensaje de confirmacion")
            .located(By.cssSelector(".complete-header"));

    public static final Target ERROR_MESSAGE = Target.the("mensaje de error")
            .located(By.cssSelector("[data-test='error']"));
}
