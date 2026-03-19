package com.saucedemo.steps;

import com.saucedemo.tasks.Login;
import com.saucedemo.questions.LoginQuestions;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class LoginSteps {

    @Cuando("inicia sesion con usuario {string} y contrasena {string}")
    public void iniciaSesionConUsuarioYContrasena(String usuario, String contrasena) {
        theActorInTheSpotlight().attemptsTo(
                Login.withCredentials(usuario, contrasena)
        );
    }

    @Entonces("deberia ver el titulo {string}")
    public void deberiaVerElTitulo(String tituloEsperado) {
        theActorInTheSpotlight().should(
                seeThat(LoginQuestions.productsTitle(), equalTo(tituloEsperado))
        );
    }

    @Entonces("deberia ver el mensaje de error {string}")
    public void deberiaVerElMensajeDeError(String mensajeEsperado) {
        theActorInTheSpotlight().should(
                seeThat(LoginQuestions.errorMessage(), containsString(mensajeEsperado))
        );
    }
}