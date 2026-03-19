package com.saucedemo.steps;

import com.saucedemo.helpers.BrowserHelper;
import com.saucedemo.questions.CartQuestions;
import com.saucedemo.tasks.Login;
import com.saucedemo.tasks.NavigateToCart;
import com.saucedemo.tasks.RemoveFromCart;
import com.saucedemo.user_interfaces.LoginPage;
import com.saucedemo.user_interfaces.ProductListPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.es.Dado;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class CommonSteps {

    // ✅ ÚNICO MÉTODO @Before que recibe el Scenario
    @Before
    public void beforeScenario(Scenario scenario) {
        System.out.println(">>> ===== INICIO DE ESCENARIO: " + scenario.getName() + " =====");

        // 1. Inicializar el escenario SIEMPRE
        OnStage.setTheStage(new OnlineCast());
        theActorCalled("Usuario");

        // 2. Hacer login SOLO si NO es escenario de login
        if (!scenario.getSourceTagNames().contains("@login")) {
            queElUsuarioEstaLogueado();
        }

        // 3. Limpiar carrito SOLO para escenarios de carrito o checkout
        if (scenario.getSourceTagNames().contains("@cart") ||
                scenario.getSourceTagNames().contains("@checkout")) {
            limpiarCarrito();
        }
    }

    @After
    public void closeBrowser() {
        System.out.println(">>> CERRANDO NAVEGADOR <<<");
        OnStage.drawTheCurtain();
    }

    @Dado("que el usuario esta logueado en la tienda")
    public void queElUsuarioEstaLogueado() {
        System.out.println(">>> EJECUTANDO LOGIN COMPLETO <<<");

        theActorInTheSpotlight().attemptsTo(
                Open.url("https://www.saucedemo.com")
        );

        theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(LoginPage.USERNAME_FIELD, isVisible())
                        .forNoMoreThan(15).seconds()
        );

        theActorInTheSpotlight().attemptsTo(
                Login.withCredentials("standard_user", "secret_sauce")
        );

        theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(ProductListPage.CART_ICON, isVisible())
                        .forNoMoreThan(20).seconds()
        );

        System.out.println(">>> LOGIN EXITOSO");
    }

    // Método para limpiar el carrito
    private void limpiarCarrito() {
        try {
            System.out.println(">>> LIMPIANDO CARRITO");
            theActorInTheSpotlight().attemptsTo(
                    NavigateToCart.fromHeader()
            );

            int items = CartQuestions.itemCount().answeredBy(theActorInTheSpotlight());
            System.out.println(">>> Items en carrito: " + items);

            for (int i = 0; i < items; i++) {
                theActorInTheSpotlight().attemptsTo(
                        net.serenitybdd.screenplay.actions.Click.on(
                                net.serenitybdd.screenplay.targets.Target.the("botón remove")
                                        .locatedBy("//button[contains(text(),'Remove')]")
                        )
                );
                try { Thread.sleep(1000); } catch (InterruptedException e) {}
            }
            System.out.println(">>> CARRITO LIMPIO");
        } catch (Exception e) {
            System.out.println(">>> No se pudo limpiar carrito: " + e.getMessage());
        }
    }
    @Dado("que el usuario esta en la pagina de login")
    public void queElUsuarioEstaEnLaPaginaDeLogin() {
        System.out.println(">>> ABRIENDO PAGINA DE LOGIN <<<");

        theActorInTheSpotlight().attemptsTo(
                Open.url("https://www.saucedemo.com")
        );

        theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(LoginPage.USERNAME_FIELD, isVisible())
                        .forNoMoreThan(15).seconds()
        );

        System.out.println(">>> PAGINA DE LOGIN CARGADA");
    }
}