package com.revature.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class DeletePlanetPage extends ParentPOM {
    @FindBy(id = "greeting")
    private WebElement welcomeMessage;
    @FindBy(id = "deleteInput")
    private WebElement deletePlanet;
    @FindBy(id = "deleteButton")
    private WebElement deletePlanetBtn;

    public DeletePlanetPage(WebDriver driver, String title) {
        super(driver, title);
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
    public void selectLocationAsPlanet() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("locationSelect")));
        new Select(dropdown).selectByVisibleText("Planet");
    }

    public void DeletePlanet(String planetName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        WebElement deletePlanetInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("deleteInput")));
        deletePlanetInput.sendKeys(planetName);
        deletePlanetInput.click();

    }

    public boolean isPlanetVisible(String planetName) {
        List<WebElement> planetEntries = driver.findElements(By.xpath("//td[text()='" + planetName + "']"));
        return !planetEntries.isEmpty();
    }
}