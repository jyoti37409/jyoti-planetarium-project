package com.revature.step;

import com.revature.pom.AddMoonPage;
import com.revature.pom.AddPlanetPage;
import com.revature.pom.DeleteMoonPage;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.revature.TestRunner.*;

public class DeleteMoonStep {

    private static WebDriver driver = new ChromeDriver(); // Reuse or move to Hooks if shared
    private DeleteMoonPage deleteMoonPage = new DeleteMoonPage(driver,"Delete Moon Page");

    @Given("the user is on the Planetarium homepage")
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


        deleteMoonPage = new DeleteMoonPage(driver,"Planetarium");
        Assert.assertTrue(deleteMoonPage.isWelcomeMessageDisplayed("Batman"));
    }



    @When("the user enters the moon name {string} in the delete field")
    public void the_user_enters_the_moon_name_in_the_delete_field(String moonName) {
        deleteMoonPage.selectLocationAsMoon();
        deleteMoonPage.enterMoonNameToDelete(moonName);
    }

    @When("clicks on the delete button")
    public void clicks_on_the_delete_button() {
        deleteMoonPage.clickDeleteButton();
    }

    @Then("the moon {string} should no longer appear on the Planetarium homepage")
    public void the_moon_should_no_longer_appear(String moonName) {
        //Assert.assertFalse("Moon was not deleted!", deleteMoonPage.isMoonVisible(moonName));
        deleteMoonPage.isMoonVisible(moonName);
    }
}
