@api
Feature: Parabank API Tests
  Como tester de API
  Quiero verificar los endpoints de Parabank
  Para asegurar operaciones bancarias

  Background:
    Given la API base es "https://parabank.parasoft.com/parabank"

  @api-smoke
  Scenario: Verificar que el servicio de cuentas está disponible
    When el usuario hace una peticion GET a "/services/bank/accounts/12345"
    Then la respuesta debe tener codigo 200

  @api-positive
  Scenario: Consultar cuentas de un cliente
    Given el usuario consulta las cuentas del cliente "12212"
    Then la respuesta debe contener una lista de cuentas

  @api-positive
  Scenario: Verificar transferencia fallida por saldo insuficiente
    Given el usuario intenta transferir 1000.0 desde cuenta "12456" a cuenta "12345"
    Then la respuesta debe tener codigo 400

  @api-negative
  Scenario: Consultar cuenta inexistente
    Given el usuario consulta la cuenta con ID "999999"
    Then la respuesta debe tener codigo 400