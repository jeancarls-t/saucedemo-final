package com.saucedemo.steps.api;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.rest.SerenityRest;
import org.hamcrest.Matchers;

public class ParabankApiSteps {

    private static final String BASE_URL = "https://parabank.parasoft.com/parabank";

    @Dado("la API base es {string}")
    public void setBaseUrl(String url) {
        System.out.println(">>> Usando API Base URL: " + BASE_URL);
    }

    @Cuando("el usuario hace una peticion GET a {string}")
    public void peticionGet(String endpoint) {
        String fullUrl = BASE_URL + endpoint;
        System.out.println(">>> GET a: " + fullUrl);
        SerenityRest.get(fullUrl);
    }

    @Entonces("la respuesta debe tener codigo {int}")
    public void verificarCodigo(int codigoEsperado) {
        int codigoReal = SerenityRest.then().extract().statusCode();
        System.out.println(">>> Código recibido: " + codigoReal + ", esperado: " + codigoEsperado);
        SerenityRest.then().statusCode(codigoEsperado);
    }

    @Dado("el usuario consulta las cuentas del cliente {string}")
    public void consultarCuentasCliente(String customerId) {
        String fullUrl = BASE_URL + "/services/bank/customers/" + customerId + "/accounts";
        System.out.println(">>> GET a: " + fullUrl);
        SerenityRest.get(fullUrl);
    }

    @Entonces("la respuesta debe contener una lista de cuentas")
    public void verificarListaCuentas() {
        SerenityRest.then().statusCode(200);
        SerenityRest.then().body("accounts.account", Matchers.notNullValue());
        System.out.println(">>> Lista de cuentas verificada correctamente");
    }

    @Dado("el usuario realiza una transferencia de {double} desde cuenta {string} a cuenta {string}")
    public void realizarTransferencia(double amount, String fromAccount, String toAccount) {
        String fullUrl = BASE_URL + "/services/bank/transfer";
        System.out.println(">>> POST a: " + fullUrl);
        System.out.println(">>> Transferencia: " + amount + " desde " + fromAccount + " a " + toAccount);

        SerenityRest.given()
                .param("fromAccountId", fromAccount)
                .param("toAccountId", toAccount)
                .param("amount", amount)
                .post(fullUrl);
    }

    @Dado("el usuario intenta transferir {double} desde cuenta {string} a cuenta {string}")
    public void intentarTransferencia(double amount, String fromAccount, String toAccount) {
        String fullUrl = BASE_URL + "/services/bank/transfer";
        System.out.println(">>> POST a: " + fullUrl);
        System.out.println(">>> Intentando transferencia fallida: " + amount + " desde " + fromAccount + " a " + toAccount);

        SerenityRest.given()
                .param("fromAccountId", fromAccount)
                .param("toAccountId", toAccount)
                .param("amount", amount)
                .post(fullUrl);

        int codigo = SerenityRest.then().extract().statusCode();
        System.out.println(">>> CÓDIGO DE RESPUESTA DE TRANSFERENCIA: " + codigo);
    }

    @Dado("el usuario consulta la cuenta con ID {string}")
    public void consultarCuentaPorId(String accountId) {
        String fullUrl = BASE_URL + "/services/bank/accounts/" + accountId;
        System.out.println(">>> GET a: " + fullUrl);
        SerenityRest.get(fullUrl);
    }
}