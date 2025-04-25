package com.revature.pom;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddMoonPage extends ParentPOM {

    @FindBy(id = "greeting")
    private WebElement welcomeMessage;

    @FindBy(id = "moonNameInput")
    private WebElement moonNameInput;

    @FindBy(id = "moonImageInput")
    private WebElement moonImageInput;

    @FindBy(xpath = "//button[contains(@class, 'submit-button')]")
    private WebElement submitMoon;

    public AddMoonPage(WebDriver driver, String title) {
        super(driver, title);
    }

    public String getWelcomeMessage() {
        return driver.findElement(By.id("greeting")).getText();
    }

    public boolean isWelcomeMessageDisplayed(String username) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(welcomeMessage));
            String actualText = welcomeMessage.getText().trim();
            String expected = "Welcome to the Home Page " + username;
            System.out.println("Comparing greeting: '" + actualText + "' with expected: '" + expected + "'");
            return actualText.equals(expected);
        } catch (Exception e) {
            System.out.println("Failed to find greeting element. HTML:");
            System.out.println(driver.getPageSource());
            throw e;
        }
    }

    public void selectLocationAsMoon() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("locationSelect")));
        new Select(dropdown).selectByVisibleText("Moon");
    }

    public void enterMoonName(String moonName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("moonNameInput")));
        input.clear();
        input.sendKeys(moonName);
    }
    @FindBy(id = "orbitedPlanetInput")
    private WebElement orbitedPlanetInput;

    public void enterOrbitedPlanetId(int planetId) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("orbitedPlanetInput")));
        input.clear();
        input.sendKeys(String.valueOf(planetId));
    }

    public void uploadMoonImage(String imagePath){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("moonImageInput")));
        input.clear();
        input.sendKeys(imagePath);

    }


    public void clickButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(submitMoon));
        submitMoon.click();
    }

    public boolean isMoonDisplayed(String moonName) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement moonCell = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//td[normalize-space()='" + moonName + "']")));
            return moonCell.isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println("Moon '" + moonName + "' not found in table.");
            return false;
        }
    }
}
