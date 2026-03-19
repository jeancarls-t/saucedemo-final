package com.saucedemo.tasks;

import com.saucedemo.user_interfaces.ProductListPage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class RemoveFromCart {

    public static Performable product(String productName) {
        return Task.where("{0} elimina el producto " + productName + " del carrito",
                actor -> {
                    actor.attemptsTo(
                            WaitUntil.the(ProductListPage.removeButtonFor(productName), isVisible())
                                    .forNoMoreThan(10).seconds()
                    );

                    actor.attemptsTo(
                            Click.on(ProductListPage.removeButtonFor(productName))
                    );

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
        );
    }
}