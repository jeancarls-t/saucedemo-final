package com.saucedemo.tasks;

import com.saucedemo.helpers.BrowserHelper;
import com.saucedemo.user_interfaces.ProductListPage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class AddToCart {

    public static Performable product(String productName) {
        return Task.where("{0} agrega el producto " + productName + " al carrito",
                actor -> {
                    System.out.println(">>> Intentando agregar: " + productName);

                    String currentUrl = BrowserHelper.getCurrentUrl(actor);
                    System.out.println(">>> URL actual antes de agregar: " + currentUrl);

                    actor.attemptsTo(
                            WaitUntil.the(ProductListPage.addToCartButtonFor(productName), isVisible())
                                    .forNoMoreThan(10).seconds()
                    );

                    actor.attemptsTo(
                            Click.on(ProductListPage.addToCartButtonFor(productName))
                    );

                    System.out.println(">>> Click realizado para: " + productName);

                    actor.attemptsTo(
                            WaitUntil.the(ProductListPage.CART_BADGE, isVisible())
                                    .forNoMoreThan(10).seconds()
                    );
                }
        );
    }

    public static Performable products(String... productNames) {
        return Task.where("{0} agrega múltiples productos al carrito",
                actor -> {
                    for (String productName : productNames) {
                        actor.attemptsTo(AddToCart.product(productName));
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
    }
}