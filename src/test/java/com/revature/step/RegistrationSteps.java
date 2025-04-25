package com.revature.step;

import static com.revature.TestRunner.*;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.Alert;

public class RegistrationSteps {

    @When("the user clicks the register link")
    public void the_user_clicks_the_register_link() {
        loginPage.clickRegistrationLink();
    }

    @When("the user provides username {string} while registering")
    public void the_user_provides_username_while_registering(String username) {
        registrationPage.enterUsername(username);
    }

    @When("the user provides password {string} while registering")
    public void the_user_provides_password_while_registering(String password) {
        registrationPage.enterPassword(password);
    }

    @When("the user clicks the register button")
    public void the_user_clicks_the_register_button(){
        registrationPage.clickRegisterButton();
    }

    @Then("an alert should appear saying {string}")
    public void an_alert_should_appear_saying(String expectedMessage) {
        registrationPage.waitForAlert();
        Alert alert = driver.switchTo().alert();
        String actualMessage = alert.getText();
        alert.accept();
        System.out.println("this expected message :" + expectedMessage);
        System.out.println("this actual message :"+ actualMessage);
        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @And("the user should be redirected to the login page")
    public void the_user_should_be_redirected_to_the_login_page() {
        Assert.assertEquals(loginPage.getTitle(),driver.getTitle());
    }

    @Then("the user should remain on the register page")
    public void the_user_should_remain_on_the_register_page() {
        Assert.assertEquals(registrationPage.getTitle(),driver.getTitle());
    }
}