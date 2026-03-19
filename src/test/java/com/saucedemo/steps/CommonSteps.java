package com.saucedemo.steps;
import com.saucedemo.helpers.KeyboardHelper;
import com.saucedemo.helpers.SecurityAlertHelper;
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
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class CommonSteps {

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
                Open.url("https://www.saucedemo.com"),
                Login.withCredentials("standard_user", "secret_sauce")
        );

        theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(ProductListPage.CART_ICON, isVisible())
                        .forNoMoreThan(20).seconds()
        );

        // 🔥 COMENTADOS - Se mantienen por si la nueva estrategia falla
        // SecurityAlertHelper.handlePossibleSecurityAlert(theActorInTheSpotlight());
        // KeyboardHelper.pressEscape(theActorInTheSpotlight());

        System.out.println(">>> Página de productos cargada correctamente");
    }

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
    private void cerrarVentanaSeguridadSiAparece() {
        try {
            // Esperar un par de segundos a que la ventana pueda aparecer
            Thread.sleep(2000);

            // Buscar el botón "Aceptar" en la ventana de seguridad
            Target botonAceptar = Target.the("botón aceptar de seguridad")
                    .locatedBy("//button[contains(text(),'Aceptar')]");

            // Verificar si el botón está visible
            if (botonAceptar.resolveFor(theActorInTheSpotlight()).isVisible()) {
                System.out.println(">>> Ventana de seguridad detectada, cerrando...");
                theActorInTheSpotlight().attemptsTo(
                        Click.on(botonAceptar)
                );
                System.out.println(">>> Ventana de seguridad cerrada");

                // Pequeña pausa después de cerrar
                Thread.sleep(1000);
            } else {
                System.out.println(">>> No hay ventana de seguridad visible");
            }
        } catch (Exception e) {
            // Si no hay ventana o hay error, simplemente continuamos
            System.out.println(">>> No se detectó ventana de seguridad o no se pudo cerrar");
        }
    }
}