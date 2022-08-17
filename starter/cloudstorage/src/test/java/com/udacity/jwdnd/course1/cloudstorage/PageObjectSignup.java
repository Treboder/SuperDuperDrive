package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageObjectSignup {

    @FindBy(id="inputFirstName")
    private WebElement inputFirstName;

    @FindBy(id="inputLastName")
    private WebElement inputLastName;

    @FindBy(id="inputUsername")
    private WebElement inputUser;

    @FindBy(id="inputPassword")
    private WebElement inputPassword;

    @FindBy(id="buttonSignUp")
    private WebElement submitButton;

    @FindBy(id="success-msg")
    private WebElement successMessage; // You successfully signed up! Please continue to the login page.

    @FindBy(id="error-msg")
    private WebElement errorMessage;

    @FindBy(id="login-link")
    private WebElement loginLink;

    public PageObjectSignup(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void signup(String firstName, String lastName, String user, String pwd) {
        inputFirstName.sendKeys(firstName);
        inputLastName.sendKeys(lastName);
        inputUser.sendKeys(user);
        inputPassword.sendKeys(pwd);
        submitButton.click();
    }

    public String getSuccessMessage() {
        return successMessage.getText();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

}












