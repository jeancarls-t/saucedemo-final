@cart
Feature: Carrito de compras
  Como usuario de SauceDemo
  Quiero gestionar mi carrito de compras
  Para poder comprar productos

  Background:
    Given que el usuario esta logueado en la tienda

  @regression
  Scenario: Verificar detalles del producto en el carrito
    Given que el carrito tiene el producto "Sauce Labs Backpack"
    Then el precio del producto debe ser "$29.99"

  @smoke
  Scenario: Agregar multiples productos al carrito
    When agrega los productos "Sauce Labs Backpack" y "Sauce Labs Bike Light" al carrito
    Then el carrito debe mostrar 2 productos

  @regression
  Scenario: Eliminar un producto del carrito
    Given que el carrito tiene 2 productos
    When elimina el producto "Sauce Labs Backpack" del carrito
    Then el carrito debe mostrar 1 producto

  @regression
  Scenario: Agregar un producto al carrito
    When agrega el producto "Sauce Labs Backpack" al carrito
    Then el carrito debe mostrar 1 producto

  @smoke
  Scenario: Verificar badge
    When agrega el producto "Sauce Labs Backpack" al carrito
    Then verifico badge

