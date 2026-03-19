package com.saucedemo.questions;

import com.saucedemo.user_interfaces.CartPage;
import com.saucedemo.user_interfaces.ProductListPage;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class CartQuestions {

    public static Question<Integer> itemCount() {
        return actor -> {
            try {
                String badgeText = ProductListPage.CART_BADGE.resolveFor(actor).getText();
                System.out.println(">>> Badge encontrado con data-test: " + badgeText);
                return Integer.parseInt(badgeText);
            } catch (Exception e) {
                System.out.println(">>> Badge no encontrado, asumiendo 0");
                return 0;
            }
        };
    }

    public static Question<String> priceOf(String productName) {
        return actor -> {
            System.out.println(">>> Buscando precio para: " + productName);

            String precio = Text.of(CartPage.priceFor(productName))
                    .answeredBy(actor);

            System.out.println(">>> Precio encontrado: " + precio);
            return precio;
        };
    }
}