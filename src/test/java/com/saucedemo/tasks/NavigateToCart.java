package com.saucedemo.tasks;

import com.saucedemo.user_interfaces.ProductListPage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

public class NavigateToCart {
    public static Performable fromHeader() {
        return Task.where("{0} navega al carrito",
                Click.on(ProductListPage.CART_ICON)
        );
    }
}