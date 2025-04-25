package com.revature.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ParentPOM {

    protected WebDriver driver;
    protected String title;

    public ParentPOM(WebDriver driver, String title){
        this.driver = driver;
        this.title = title;
        PageFactory.initElements(driver, this);
    }
}
