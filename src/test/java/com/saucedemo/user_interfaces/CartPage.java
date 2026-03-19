package com.saucedemo.user_interfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CartPage {

    public static final CartPage CART_PAGE = new CartPage();

    public static Target removeButtonFor(String productName) {
        return Target.the("botón remove para " + productName)
                .located(By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='cart_item']//button"));
    }

    public static Target priceFor(String productName) {
        return Target.the("precio de " + productName)
                .located(By.xpath("//div[contains(text(),'" + productName + "')]/ancestor::div[@class='cart_item']//div[@class='inventory_item_price']"));
    }

    public static Target productInCart(String productName) {
        return Target.the("producto " + productName + " en carrito")
                .located(By.xpath("//div[text()='" + productName + "']"));
    }
}