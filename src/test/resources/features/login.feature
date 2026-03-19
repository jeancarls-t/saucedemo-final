@login
Feature: Login
  Como usuario de SauceDemo
  Quiero poder iniciar sesion
  Para acceder a la tienda

  Background:
    Given que el usuario esta en la pagina de login

  @smoke
  Scenario: Login exitoso con usuario estandar
    When inicia sesion con usuario "standard_user" y contrasena "secret_sauce"
    Then deberia ver el titulo "Products"

  @negative
  Scenario: Login fallido con usuario bloqueado
    When inicia sesion con usuario "locked_out_user" y contrasena "secret_sauce"
    Then deberia ver el mensaje de error "Epic sadface: Sorry, this user has been locked out."

  @negative
  Scenario: Login fallido con credenciales invalidas
    When inicia sesion con usuario "usuario_invalido" y contrasena "password_incorrecto"
    Then deberia ver el mensaje de error "Epic sadface: Username and password do not match any user in this service"