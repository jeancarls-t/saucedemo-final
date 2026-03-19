package com.saucedemo.steps;

import com.saucedemo.tasks.*;
import com.saucedemo.questions.CheckoutQuestions;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import static org.hamcrest.Matchers.containsString;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;

public class CheckoutSteps {

    @Dado("tiene productos en el carrito")
    public void tieneProductosEnElCarrito() {
        theActorInTheSpotlight().attemptsTo(
                AddToCart.product("Sauce Labs Backpack")
        );
    }

    @Cuando("procede al checkout")
    public void procedeAlCheckout() {
        theActorInTheSpotlight().attemptsTo(
                NavigateToCart.fromHeader(),
                Checkout.proceed()
        );
    }

    @Y("ingresa su informacion {string}, {string}, {string}")
    public void ingresaSuInformacion(String nombre, String apellido, String codigoPostal) {
        theActorInTheSpotlight().attemptsTo(
                Checkout.enterInformation(nombre, apellido, codigoPostal),
                Checkout.continueCheckout()
        );
    }

    @Y("finaliza la compra")
    public void finalizaLaCompra() {
        theActorInTheSpotlight().attemptsTo(
                Checkout.finish()
        );
    }

    @Entonces("deberia ver el mensaje {string}")
    public void deberiaVerElMensaje(String mensajeEsperado) {
        theActorInTheSpotlight().should(
                seeThat(CheckoutQuestions.successMessage(), equalTo(mensajeEsperado))
        );
    }

    @Y("intenta continuar sin completar el formulario")
    public void intentaContinuarSinCompletarElFormulario() {
        theActorInTheSpotlight().attemptsTo(
                Checkout.tryToContinue()
        );
    }

    @Entonces("deberia ver un mensaje de error {string}")
    public void deberiaVerUnMensajeDeError(String mensajeEsperado) {
        theActorInTheSpotlight().should(
                seeThat(CheckoutQuestions.errorMessage(), containsString(mensajeEsperado))
        );
    }
}