package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageObjectResult {

    @FindBy(id="success")
    private WebElement success_msg;

    @FindBy(id="error-specific-message")
    private WebElement error_message;


    public PageObjectResult(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String getSuccessMessage() {
        return success_msg.getText(); // should read "Success" in case of changes made successfully
    }

    public String getErrorMessage() {
        return error_message.getText();
    }


}
