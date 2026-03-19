package com.saucedemo.questions;

import com.saucedemo.user_interfaces.CheckoutPage;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class CheckoutQuestions {

    public static Question<String> successMessage() {
        return actor -> Text.of(CheckoutPage.COMPLETE_MESSAGE).answeredBy(actor);
    }

    public static Question<String> errorMessage() {
        return actor -> Text.of(CheckoutPage.ERROR_MESSAGE).answeredBy(actor);
    }
}
