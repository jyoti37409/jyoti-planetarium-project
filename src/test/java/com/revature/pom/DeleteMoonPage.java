package com.revature.pom;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class DeleteMoonPage extends ParentPOM {

    @FindBy(id = "deleteInput")
    private WebElement deleteInput;

    @FindBy(id = "deleteButton")
    private WebElement deleteButton;

    @FindBy(id = "locationSelect")
    private WebElement locationSelect;

    // XPath to find moon entries in table
    @FindBy(xpath = "//table[@id='moonTable']//td")
    private List<WebElement> moonEntries;

    public DeleteMoonPage(WebDriver driver, String title) {
        super(driver, title);
    }
    public String getWelcomeMessage() {
        WebElement greeting = driver.findElement(By.id("greeting")); // or By.id("greeting") if it’s an ID
        return greeting.getText();

    }



    public void selectLocationAsMoon() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("locationSelect")));
        new Select(dropdown).selectByVisibleText("Moon");
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

    public void enterMoonNameToDelete(String moonName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(deleteInput));
        deleteInput.clear();
        deleteInput.sendKeys(moonName);
    }

    public void clickDeleteButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(deleteButton)).click();
    }


    @FindBy(id = "greeting")
    private WebElement welcomeMessage;




    public boolean isMoonVisible(String moonName) {
        List<WebElement> visibleMoons = driver.findElements(By.xpath("//table[@id='moonTable']//td[text()='" + moonName + "']"));
        return !visibleMoons.isEmpty();
    }
}
