package com.revature.pom;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PlanetariumPage extends ParentPOM{
    private WebDriverWait alertWait;

    @FindBy(id = "usernameInput")
    private WebElement usernameInput;
    @FindBy(id = "passwordInput")
    private WebElement passwordInput;
    @FindBy(xpath = "//input[3]")
    private WebElement registerButton;
    @FindBy(id = "greeting") // or use id/class if available
    private WebElement welcomeMessage;
    @FindBy(id = "moonNameInput")
    private WebElement moonNameInput;
    @FindBy(id = "moonImageInput")
    private WebElement moonImageInput;
    @FindBy(xpath = "//button[contains(@class, 'submit-button')]")
    private WebElement submitMoon;
    @FindBy(id ="planetNameInput")
    private WebElement planetNameInput;
    @FindBy(id = "planetImageInput")
    private WebElement planetImageInput;
    @FindBy(xpath = "//button[contains(@class, 'submit-button')]")
    private WebElement submitbutton;
    @FindBy(id = "deleteInput")
    private WebElement deleteInput;
    @FindBy(id = "deleteButton")
    private WebElement deleteButton;
    @FindBy(id = "locationSelect")
    private WebElement locationSelect;
    // XPath to find moon entries in table
    @FindBy(xpath = "//table[@id='moonTable']//td")
    private List<WebElement> moonEntries;
    private WebElement deletePlanet;
    @FindBy(id = "deleteButton")
    private WebElement deletePlanetBtn;


    public PlanetariumPage(WebDriver driver, String title) {
        super(driver, title);
        alertWait = new WebDriverWait(driver, Duration.ofSeconds(2));
    }
    public void enterUsername(String username){
        usernameInput.sendKeys(username);
    }

    public void enterPassword(String password){
        passwordInput.sendKeys(password);
    }

    public void clickRegisterButton(){
        registerButton.click();
    }
    public void waitForAlertforReg() {
        new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5))
                .until(org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent());
    }
    @FindBy(xpath = "//input[@value='Login']")
    private WebElement loginButton;
    public void clickLogin() {
        loginButton.click();
    }
    public void loginAs(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
    public void waitForAlert() {
        new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5))
                .until(org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent());
    }
    public boolean isAtLoginPage() {
        return driver.getTitle().equals(title);
    }

    // note: make this a reference to your login page
    private String url = String.format("http://%s/", System.getenv("PLANETARIUM_URL"));

    @FindBy(tagName = "a")
    private WebElement registrationLink;

    public void goToLoginPage(){
        driver.get(url);
    }

    public void clickRegistrationLink(){
        registrationLink.click();
    }

    // can use this to validate we have returned to the login page after a successful registration
    public String getTitle(){
        return title;
    }


    public boolean isWelcomeMessageDisplayed(String username) {
        return welcomeMessage.getText().contains("Welcome to the Home Page " + username);
    }

    public boolean isAt() {
        return driver.getTitle().equals(title);
    }
    public boolean isAtHomeUrl() {
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Checking Home URL: " + currentUrl);
        return currentUrl.endsWith("/planetarium");
    }
    public String getWelcomeMessage() {
        return driver.findElement(By.id("greeting")).getText();
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
    public void selectLocationAsPlanet() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("locationSelect")));
        Select select = new Select(dropdown);
        select.selectByVisibleText("Planet"); //must match exactly as shown in the UI
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

    public boolean isMoonVisible(String moonName) {
        List<WebElement> visibleMoons = driver.findElements(By.xpath("//table[@id='moonTable']//td[text()='" + moonName + "']"));
        return !visibleMoons.isEmpty();
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
