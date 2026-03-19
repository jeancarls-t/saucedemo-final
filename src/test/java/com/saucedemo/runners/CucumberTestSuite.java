package com.saucedemo.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"com.saucedemo.steps", "com.saucedemo.hooks"},
        plugin = {"pretty", "html:target/cucumber"}
)
public class CucumberTestSuite {
}