package com.saucedemo.user_interfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class LoginPage {
    public static final Target USERNAME_FIELD = Target.the("usuario")
            .located(By.id("user-name"));

    public static final Target PASSWORD_FIELD = Target.the("contraseña")
            .located(By.id("password"));

    public static final Target LOGIN_BUTTON = Target.the("boton de login")
            .located(By.id("login-button"));

    public static final Target ERROR_MESSAGE = Target.the("mensaje de error")
            .located(By.cssSelector("[data-test='error']"));

    public static final Target PRODUCTS_TITLE = Target.the("titulo de productos")
            .located(By.cssSelector(".title"));
}
