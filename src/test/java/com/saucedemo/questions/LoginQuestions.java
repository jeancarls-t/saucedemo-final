package com.saucedemo.questions;

import com.saucedemo.user_interfaces.LoginPage;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class LoginQuestions {

    public static Question<String> productsTitle() {
        return actor -> Text.of(LoginPage.PRODUCTS_TITLE).answeredBy(actor);
    }

    public static Question<String> errorMessage() {
        return actor -> Text.of(LoginPage.ERROR_MESSAGE).answeredBy(actor);
    }
}
