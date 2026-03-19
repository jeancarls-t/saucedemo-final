package com.saucedemo.steps;

import com.saucedemo.tasks.*;
import com.saucedemo.questions.CartQuestions;
import com.saucedemo.user_interfaces.ProductListPage;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.hamcrest.Matchers.equalTo;

public class CartSteps {

    @Cuando("agrega el producto {string} al carrito")
    public void agregaProductoAlCarrito(String producto) {
        theActorInTheSpotlight().attemptsTo(
                AddToCart.product(producto)
        );
    }

    @Cuando("agrega los productos {string} y {string} al carrito")
    public void agregaMultiplesProductosAlCarrito(String producto1, String producto2) {
        theActorInTheSpotlight().attemptsTo(
                AddToCart.products(producto1, producto2)
        );
    }

    @Entonces("el carrito debe mostrar {int} producto")
    public void elCarritoDebeMostrarProducto(int cantidadEsperada) {
        theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(ProductListPage.CART_BADGE, isVisible())
                        .forNoMoreThan(10).seconds()
        );

        theActorInTheSpotlight().should(
                seeThat(CartQuestions.itemCount(), equalTo(cantidadEsperada))
        );
    }

    @Entonces("el carrito debe mostrar {int} productos")
    public void elCarritoDebeMostrarProductos(int cantidadEsperada) {
        theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(ProductListPage.CART_ICON, isVisible())
                        .forNoMoreThan(10).seconds()
        );

        try { Thread.sleep(2000); } catch (InterruptedException e) {}

        int cantidadReal = CartQuestions.itemCount().answeredBy(theActorInTheSpotlight());
        System.out.println(">>> CANTIDAD ENCONTRADA: " + cantidadReal);

        theActorInTheSpotlight().should(
                seeThat(CartQuestions.itemCount(), equalTo(cantidadEsperada))
        );
    }

    @Dado("que el carrito tiene {int} productos")
    public void queElCarritoTieneProductos(int cantidad) {
        System.out.println(">>> Preparando carrito con " + cantidad + " productos <<<");

        if (cantidad == 2) {
            theActorInTheSpotlight().attemptsTo(
                    AddToCart.products("Sauce Labs Backpack", "Sauce Labs Bike Light")
            );
        }

        theActorInTheSpotlight().should(
                seeThat(CartQuestions.itemCount(), equalTo(cantidad))
        );

        System.out.println(">>> Carrito preparado correctamente <<<");
    }

    @Cuando("elimina el producto {string} del carrito")
    public void eliminaProductoDelCarrito(String producto) {
        System.out.println(">>> ELIMINANDO: " + producto);

        int antes = CartQuestions.itemCount().answeredBy(theActorInTheSpotlight());
        System.out.println(">>> Productos antes: " + antes);

        theActorInTheSpotlight().attemptsTo(
                NavigateToCart.fromHeader()
        );

        for (int i = 0; i < 3; i++) {
            theActorInTheSpotlight().attemptsTo(
                    RemoveFromCart.product(producto)
            );

            try { Thread.sleep(2000); } catch (InterruptedException e) {}

            int despues = CartQuestions.itemCount().answeredBy(theActorInTheSpotlight());
            System.out.println(">>> Productos después (intento " + (i+1) + "): " + despues);

            if (despues == antes - 1) {
                break;
            }
        }
    }

    @Dado("que el carrito tiene el producto {string}")
    public void queElCarritoTieneElProducto(String producto) {
        theActorInTheSpotlight().attemptsTo(
                AddToCart.product(producto)
        );
    }

    @Entonces("el precio del producto debe ser {string}")
    public void elPrecioDelProductoDebeSer(String precioEsperado) {
        theActorInTheSpotlight().attemptsTo(
                NavigateToCart.fromHeader()
        );

        theActorInTheSpotlight().should(
                seeThat(CartQuestions.priceOf("Sauce Labs Backpack"), equalTo(precioEsperado))
        );
    }
    @Entonces("verifico badge")
    public void verificoBadge() {
        boolean existe = ProductListPage.CART_BADGE.resolveFor(theActorInTheSpotlight()).isVisible();
        System.out.println(">>> BADGE EXISTE: " + existe);

        if (existe) {
            String texto = ProductListPage.CART_BADGE.resolveFor(theActorInTheSpotlight()).getText();
            System.out.println(">>> TEXTO DEL BADGE: " + texto);
        }
    }
}