package com.saucedemo.user_interfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ProductListPage {

    // Selector principal (data-test)
    public static final Target CART_ICON = Target.the("ícono del carrito (data-test)")
            .located(By.cssSelector("[data-test='shopping-cart-link']"));

    // 🔥 SELECTORES ALTERNATIVOS PARA CI
    public static final Target CART_ICON_CLASS = Target.the("ícono del carrito (clase)")
            .located(By.cssSelector(".shopping_cart_link"));

    public static final Target CART_ICON_XPATH = Target.the("ícono del carrito (xpath)")
            .located(By.xpath("//a[@class='shopping_cart_link']"));

    // Badge del carrito
    public static final Target CART_BADGE = Target.the("badge del carrito")
            .located(By.cssSelector("[data-test='shopping-cart-badge']"));

    public static Target addToCartButtonFor(String productName) {
        String productId = productName.toLowerCase()
                .replace(" ", "-")
                .replace("'", "")
                .replace("(", "")
                .replace(")", "");

        return Target.the("botón add to cart para " + productName)
                .located(By.cssSelector("[data-test='add-to-cart-" + productId + "']"));
    }

    public static Target removeButtonFor(String productName) {
        String productId = productName.toLowerCase()
                .replace(" ", "-")
                .replace("'", "")
                .replace("(", "")
                .replace(")", "");

        return Target.the("botón remove para " + productName)
                .located(By.cssSelector("[data-test='remove-" + productId + "']"));
    }
}