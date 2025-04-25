package com.revature.pom;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class AddPlanetPage extends ParentPOM{
    @FindBy(id = "greeting") // or use id/class if available
    private WebElement welcomeMessage;

    @FindBy(id ="planetNameInput")
    private WebElement planetNameInput;

    @FindBy(id = "planetImageInput")
    private WebElement planetImageInput;

    @FindBy(xpath = "//button[contains(@class, 'submit-button')]")
    private WebElement submitbutton;
    public AddPlanetPage(WebDriver driver, String title) {
        super(driver, title);
    }

    public String getWelcomeMessage() {
        WebElement greeting = driver.findElement(By.id("greeting")); // or By.id("greeting") if it’s an ID
        return greeting.getText();
    }


    public void selectLocationAsPlanet() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("locationSelect")));
        Select select = new Select(dropdown);
        select.selectByVisibleText("Planet"); //must match exactly as shown in the UI
    }



    public boolean isWelcomeMessageDisplayed(String username) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(welcomeMessage));
            String actualText = welcomeMessage.getText().trim();
            String expected = "Welcome to the Home Page " + username;
            System.out.println("Comparing greeting: '" + actualText + "' with expected: '" + expected + "'");
            return actualText.equals(expected);  // Use equals for exact match now that we trimmed
        } catch (Exception e) {
            System.out.println("Failed to find greeting element. HTML:");
            System.out.println(driver.getPageSource());
            throw e;
        }
    }

    public void enterPlanetName(String planetName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("planetNameInput")));
        input.clear();
        input.sendKeys(planetName);
    }
    public void uploadPlanetImage(String imagePath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement input = wait.until(ExpectedConditions.visibilityOf(planetImageInput));
        input.sendKeys(imagePath);
    }


    public void clickButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(submitbutton));
        submitbutton.click();
    }

    public boolean isPlanetDisplayed(String planetName) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement planetCell = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//td[normalize-space()='" + planetName + "']")
            ));
            return planetCell.isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println("Planet '" + planetName + "' not found in table.");
            return false;
        }
    }
    public boolean handleUnexpectedAlertIfPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            System.out.println("Unexpected alert: " + alert.getText());
            alert.accept(); // dismisses the alert
            return true;
        } catch (TimeoutException e) {
            return false; // no alert appeared
        }
    }



}
