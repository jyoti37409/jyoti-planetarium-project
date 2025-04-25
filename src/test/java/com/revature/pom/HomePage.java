package com.revature.pom;

import com.revature.pom.ParentPOM;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPOM {

    @FindBy(id = "greeting") // or use id/class if available
    private WebElement welcomeMessage;

    public HomePage(WebDriver driver, String title) {
        super(driver, title);
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


}
