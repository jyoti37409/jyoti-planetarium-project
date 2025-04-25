package com.revature.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage extends ParentPOM{

    private WebDriverWait alertWait;

    @FindBy(id = "usernameInput")
    private WebElement usernameInput;
    @FindBy(id = "passwordInput")
    private WebElement passwordInput;
    @FindBy(xpath = "//input[3]")
    private WebElement registerButton;

    public RegistrationPage(WebDriver driver, String title) {
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

    public String getTitle(){
        return title;
    }

    public void waitForAlert() {
        new WebDriverWait(driver, Duration.ofSeconds(10)) // ⬅️ was 2, now 5 seconds
                .until(ExpectedConditions.alertIsPresent());
    }



}
