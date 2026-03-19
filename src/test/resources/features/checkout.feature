Feature: Checkout
  Como usuario de SauceDemo
  Quiero completar el proceso de compra
  Para recibir mis productos

  Background:
    Given que el usuario esta logueado en la tienda

  @smoke
  Scenario: Completar compra exitosamente
    When procede al checkout
    And ingresa su informacion "Juan", "Prieto", "1127651"
    And finaliza la compra
    Then deberia ver el mensaje "Thank you for your order!"

  @negative
  Scenario: Checkout sin informacion personal
    When procede al checkout
    And intenta continuar sin completar el formulario
    Then deberia ver un mensaje de error "First Name is required"