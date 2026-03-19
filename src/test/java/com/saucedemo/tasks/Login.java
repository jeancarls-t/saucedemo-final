package com.saucedemo.tasks;

import com.saucedemo.user_interfaces.LoginPage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

public class Login {
    public static Performable withCredentials(String username, String password) {
        return Task.where("{0} inicia sesión con usuario: " + username,
                Enter.theValue(username).into(LoginPage.USERNAME_FIELD),
                Enter.theValue(password).into(LoginPage.PASSWORD_FIELD),
                Click.on(LoginPage.LOGIN_BUTTON)
        );
    }
}