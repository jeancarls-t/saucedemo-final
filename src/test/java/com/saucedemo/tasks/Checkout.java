package com.saucedemo.tasks;

import com.saucedemo.user_interfaces.CheckoutPage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

public class Checkout {

    public static Performable proceed() {
        return Task.where("{0} procede al checkout",
                Click.on(CheckoutPage.CHECKOUT_BUTTON)
        );
    }

    public static Performable enterInformation(String firstName, String lastName, String postalCode) {
        return Task.where("{0} ingresa su información personal",
                Enter.theValue(firstName).into(CheckoutPage.FIRST_NAME),
                Enter.theValue(lastName).into(CheckoutPage.LAST_NAME),
                Enter.theValue(postalCode).into(CheckoutPage.POSTAL_CODE)
        );
    }

    public static Performable continueCheckout() {
        return Task.where("{0} continúa con el checkout",
                Click.on(CheckoutPage.CONTINUE_BUTTON)
        );
    }

    public static Performable finish() {
        return Task.where("{0} finaliza la compra",
                Click.on(CheckoutPage.FINISH_BUTTON)
        );
    }

    public static Performable tryToContinue() {
        return Task.where("{0} intenta continuar sin datos",
                Click.on(CheckoutPage.CONTINUE_BUTTON)
        );
    }
}