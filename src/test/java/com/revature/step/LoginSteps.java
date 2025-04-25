package com.revature.step;

import static com.revature.TestRunner.*;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.Alert;

public class LoginSteps {

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        loginPage.goToLoginPage();
    }

    @When("the user provides a valid username {string} while login")
    public void the_user_provides_a_valid_username_while_login(String username) {
        loginPage.enterUsername(username);
    }

    @And("the user provides a valid password {string} while login")
    public void the_user_provides_a_valid_password_while_login(String password) {
        loginPage.enterPassword(password);
    }

    @When("the user clicks the {string} button")
    public void the_user_clicks_the_button(String buttonText) {
        if (buttonText.equalsIgnoreCase("login")) {
            loginPage.clickLogin();
        } else {
            throw new IllegalArgumentException("Unsupported button: " + buttonText);
        }
    }

    @And("clicks the {string} button")
    public void clicks_the_button(String buttonText) {
        if (buttonText.equalsIgnoreCase("login")) {
            loginPage.clickLogin();
        } else {
            throw new IllegalArgumentException("Unsupported button: " + buttonText);
        }
    }

    @Then("the user should see a welcome message with username {string}")
    public void the_user_should_see_a_welcome_message(String username) {
        Assert.assertTrue(homePage.isWelcomeMessageDisplayed(username));
    }

    @Then("the user should be redirected to the Planetarium home page")
    public void the_user_should_be_redirected_to_the_Planetarium_home_page() {
        new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5))
                .until(d -> driver.getCurrentUrl().contains("/planetarium"));

        System.out.println("Current URL: " + driver.getCurrentUrl());
        System.out.println("Current Title: " + driver.getTitle());

        Assert.assertTrue("User is not on the expected Planetarium home URL", homePage.isAtHomeUrl());
    }

    @When("the user enters an invalid {string} or {string}")
    public void the_user_enters_an_invalid_or(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @Then("the user should see an error message {string}")
    public void the_user_should_see_an_error_message(String expectedMessage) {
        loginPage.waitForAlert();
        Alert alert = driver.switchTo().alert();
        String actualMessage = alert.getText();
        alert.accept();
        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @Then("the user should remain on the login page")
    public void the_user_should_remain_on_the_login_page() {
        Assert.assertTrue(loginPage.isAtLoginPage());
    }

    @When("the user provides an invalid username {string}")
    public void theUserProvidesAnInvalidUsername(String invalidusername) {
        loginPage.enterUsername(invalidusername);

    }

    @And("provides an invalid password {string}")
    public void providesAnInvalidPassword(String invalidpassword) {
        loginPage.enterPassword(invalidpassword);
    }
}