package com.revature;

import com.revature.pom.*;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

/*
    We are going to let Junit be the test runner for our Cucumber/Selenium
    testing, but it needs to run Cucumber tests. That is what the
    RunWith annotation is for: it tells Junit to allow Cucumber to execute
    the tests.

    You also need to tell Cucumber a few pieces of information:
    - where are the feature files
    - where are the glue methods
    - what plugins do you want to use

    You configure this information using the @CucumberOptions
    annotation
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        glue = "com.revature.step",
        plugin = {"pretty","html:src/test/resources/reports/Cucumber-Report.html"}
)
public class TestRunner {

    // here is where we will create our test resources: make them static for easy access in our steps
    public static WebDriver driver;
    public static LoginPage loginPage;
    public static RegistrationPage registrationPage;
    public static HomePage homePage;
    public static AddPlanetPage addPlanetPage;
    public static AddMoonPage addMoonPage;
    public static DeleteMoonPage deleteMoonPage;
    public static DeletePlanetPage deletePlanetPage;


    @BeforeClass
    public static void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();

        loginPage = new LoginPage(driver, "Planetarium Login");
        registrationPage = new RegistrationPage(driver, "Account Creation");
        homePage = new HomePage(driver, "Planetarium Home");

        addPlanetPage = new AddPlanetPage(driver, "Add Planet");
        addMoonPage = new AddMoonPage(driver, "Add Moon");
        deleteMoonPage = new DeleteMoonPage(driver, "Delete Moon");
        deletePlanetPage = new DeletePlanetPage(driver, "Delete Planet");
    }


    @AfterClass
    public static void tearDown(){
        if(driver != null) driver.quit();
    }

}
