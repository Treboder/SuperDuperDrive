package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageObjectResult {

    @FindBy(id="success")
    private WebElement success_msg;

    public PageObjectResult(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String getSuccessMessage() {
        return success_msg.getText(); // should read "Success" in case of changes made successfully
    }

}
