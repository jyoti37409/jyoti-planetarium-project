package com.revature.step;

import com.revature.pom.AddMoonPage;
import com.revature.pom.AddPlanetPage;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

import static com.revature.TestRunner.*;

public class AddMoonSteps {

    @Given("the user is on the planetarium homepage and selects Moon")
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

        addPlanetPage = new AddPlanetPage(driver, "Planetarium");
        addMoonPage = new AddMoonPage(driver, "Planetarium");
        Assert.assertTrue(addPlanetPage.isWelcomeMessageDisplayed("Batman"));
    }

    @When("the user provides a valid moon name {string}, planet ID {int}, and image {string}")
    @When("the user provides an invalid moon name {string}, planet ID {int}, and image {string}")
    public void the_user_provides_a_moon_name_and_planet_id_and_image(String moonName, int planetId, String imagePath) {
        addMoonPage.selectLocationAsMoon();
        addMoonPage.enterMoonName(moonName);
        addMoonPage.enterOrbitedPlanetId(planetId);
        if (imagePath != null && !imagePath.trim().isEmpty()) {
            addMoonPage.uploadMoonImage(imagePath);
        }
        addMoonPage.clickButton();
    }

    @Then("the moon name {string} should be saved on the Planetarium Home Page")
    public void the_moon_name_should_be_saved_on_the_planetarium_home_page(String moonName) {
        try {
            System.out.println("Pausing for 5 seconds to observe the page...");
            Thread.sleep(1000);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            try {
                Alert alert = wait.until(ExpectedConditions.alertIsPresent());
                String alertText = alert.getText();
                System.out.println("Alert during verification: " + alertText);
                alert.accept();
                Assert.fail("Test failed due to unexpected alert: " + alertText);
            } catch (TimeoutException ignored) {}

            Assert.assertTrue("Moon name not found on homepage!", addMoonPage.isMoonDisplayed(moonName));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("an moon alert should appear saying {string}")
    public void an_alert_should_appear_saying(String expectedAlertText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String actualText = alert.getText();
        System.out.println("Alert: " + actualText);
        Assert.assertEquals(expectedAlertText, actualText);
        alert.accept();
    }
}
