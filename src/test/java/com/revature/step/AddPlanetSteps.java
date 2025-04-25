package com.revature.step;

import static com.revature.TestRunner.*;

import com.revature.pom.AddPlanetPage;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddPlanetSteps {

    @Given("the user is on the planetarium homepage and selects Planet")
    public void the_user_is_on_the_planetarium_homepage() {
        driver.get("http://localhost:8080");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

        // Login steps
        driver.findElement(By.id("usernameInput")).sendKeys("Batman");
        driver.findElement(By.id("passwordInput")).sendKeys("Iamthenight1939");
        driver.findElement(By.cssSelector("input[type='submit']")).click();

        // Wait for redirect to homepage
        wait.until(ExpectedConditions.urlContains("/planetarium"));

        // Wait for dropdown to be ready and select "Planet"
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("locationSelect")));
        new Select(dropdown).selectByVisibleText("Planet");

        // Now wait for the planet name input
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("planetNameInput")));

        // Initialize the page object
        addPlanetPage = new AddPlanetPage(driver, "Planetarium");
        Assert.assertTrue(addPlanetPage.isWelcomeMessageDisplayed("Batman"));
    }

    @When("the user provides a valid planet name {string}")
    public void the_user_provides_a_valid_planet_name(String planetName) {
        addPlanetPage.selectLocationAsPlanet();
        addPlanetPage.enterPlanetName(planetName);
        addPlanetPage.clickButton();
    }

    @When("the user provides a valid planet name {string} and image path {string}")
    public void the_user_provides_a_valid_planet_name_and_image_path(String planetName, String imagePath) {
        addPlanetPage.selectLocationAsPlanet();
        addPlanetPage.enterPlanetName(planetName);
        addPlanetPage.uploadPlanetImage(imagePath);

        try {
            addPlanetPage.clickButton(); // Try to submit
        } catch (UnhandledAlertException e) {
            // Handle unexpected alerts
            Alert alert = driver.switchTo().alert();
            System.out.println("Unexpected alert: " + alert.getText());
            alert.accept();
            System.out.println("Submission failed due to validation alert.");
        }
    }


    @Then("the planet name {string} should be saved on the Planetarium Home Page")
    public void the_planet_name_should_be_saved_on_the_planetarium_home_page(String planetName) {
        try {
            System.out.println("Pausing for 5 seconds to observe the page...");
            Thread.sleep(1000);

            // Check for alert again before assertion
            if (isAlertPresent()) {
                Alert alert = driver.switchTo().alert();
                System.out.println(" Alert present during verification: " + alert.getText());
                alert.accept();
                Assert.fail("Test failed due to validation alert: " + alert.getText());
            }

            Assert.assertTrue(addPlanetPage.isPlanetDisplayed(planetName));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException ex) {
            return false;
        }
    }


    @When("the user provides a invalid planet name {string} and image path {string}")
    public void the_user_provides_a_invalid_planet_name_and_image_path(String planetName, String imagePath) {
        addPlanetPage.selectLocationAsPlanet();
        addPlanetPage.enterPlanetName(planetName);

        if (!imagePath.isEmpty()) {
            addPlanetPage.uploadPlanetImage(imagePath);
        }

        try {
            addPlanetPage.clickButton();
        } catch (UnhandledAlertException e) {
            Alert alert = driver.switchTo().alert();
            System.out.println("Unexpected alert: " + alert.getText());
            alert.accept();
        }
    }


}
