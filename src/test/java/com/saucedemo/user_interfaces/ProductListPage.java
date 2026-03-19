package com.saucedemo.user_interfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ProductListPage {

    public static final Target CART_BADGE = Target.the("badge del carrito")
            .located(By.cssSelector("[data-test='shopping-cart-badge']"));

    public static final Target CART_ICON = Target.the("ícono del carrito")
            .located(By.cssSelector("[data-test='shopping-cart-link']"));

    public static Target addToCartButtonFor(String productName) {
        switch(productName) {
            case "Sauce Labs Backpack":
                return Target.the("botón add to cart para Sauce Labs Backpack")
                        .located(By.cssSelector("[data-test='add-to-cart-sauce-labs-backpack']"));
            case "Sauce Labs Bike Light":
                return Target.the("botón add to cart para Sauce Labs Bike Light")
                        .located(By.cssSelector("[data-test='add-to-cart-sauce-labs-bike-light']"));
            case "Sauce Labs Bolt T-Shirt":
                return Target.the("botón add to cart para Sauce Labs Bolt T-Shirt")
                        .located(By.cssSelector("[data-test='add-to-cart-sauce-labs-bolt-t-shirt']"));
            default:
                String productId = productName.toLowerCase()
                        .replace(" ", "-")
                        .replace("'", "")
                        .replace("(", "")
                        .replace(")", "");
                return Target.the("botón add to cart para " + productName)
                        .located(By.cssSelector("[data-test='add-to-cart-" + productId + "']"));
        }
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