package com.revature.step;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

//import static com.revature.TestRunner.deletePlanetPage;
import static com.revature.TestRunner.driver;
import static com.revature.TestRunner.planetariumPage;

public class DeletePlanetSteps {

    @Given("the user is on the  Planet1 Planetarium homepage")
    public void the_user_is_on_the_planetarium_homepage() {
        driver.get("http://localhost:8080");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        driver.findElement(By.id("usernameInput")).sendKeys("Batman");
        driver.findElement(By.id("passwordInput")).sendKeys("Iamthenight1939");
        driver.findElement(By.cssSelector("input[type='submit']")).click();
        wait.until(ExpectedConditions.urlContains("/planetarium"));

        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("locationSelect")));
        new Select(dropdown).selectByVisibleText("Moon");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("moonNameInput")));



    }

    @When("the user deletes the planet {string}")
    public void the_User_Deletes_The_Planet(String planetName) {
        planetariumPage.selectLocationAsPlanet();
        planetariumPage.DeletePlanet(planetName);

        planetariumPage.isPlanetVisible(planetName);
    }

    @Then("the moon {string} should be removed from the Planetarium homepage")
    public void theMoonShouldBeRemovedFromThePlanetariumHomepage(String planetName) {
        planetariumPage.isPlanetVisible(planetName);
    }
}
