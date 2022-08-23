package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageObjectHomeCredentials {

    @FindBy(id="nav-credentials-tab")
    private WebElement nav_credentials_tab;

    @FindBy(id="add-credential-button")
    private WebElement add_credential_button;

    @FindBy(id="edit-credential-button")
    private WebElement edit_credential_button;

    @FindBy(id="delete-credential-button")
    private WebElement delete_credential_button;

    @FindBy(id="credential-url")
    private WebElement credential_url;

    @FindBy(id="credential-username")
    private WebElement credential_username;

    @FindBy(id="credential-password")
    private WebElement credential_password;

    @FindBy(id="save-credential-button")
    private WebElement save_credential_button;

    @FindBy(id="credentialTableUrl")
    private WebElement credentialTableUrl;

    @FindBy(id="credentialTableUsername")
    private WebElement credentialTableUsername;

    @FindBy(id="credentialTablePassword")
    private WebElement credentialTablePassword;

    public PageObjectHomeCredentials(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void switchToNavCredentialsTab() {
        nav_credentials_tab.click();
    }

    public void clickAddCredentialButtonNew() {
        add_credential_button.click();
    }

    public void clickEditCredentialButton() {
        edit_credential_button.click();
    }

    public void clickDeleteCredentialButton() {
        delete_credential_button.click();
    }

    public void fillCredentialDetailsAndSave(String url, String username, String password) {
        credential_url.clear();
        credential_url.sendKeys(url);
        credential_username.clear();
        credential_username.sendKeys(username);
        credential_password.clear();
        credential_password.sendKeys(password);
        save_credential_button.click();
    }

    public String getCredentialUrlFromDialog() {
        String url = credential_url.getAttribute("value");
        return url;
    }

    public String getCredentialUsernameFromDialog() {
        String user = credential_username.getAttribute("value");
        return user;
    }

    public String getCredentialPasswordFromDialog() {
        String pwd = credential_password.getAttribute("value");
        return pwd;
    }

    public String getCredentialUrlFromTable() {
        String url = credentialTableUrl.getText();
        return url;
    }

    public String getCredentialUsernameFromTable() {
        String user = credentialTableUsername.getText();
        return user;
    }

    public String getCredentialPasswordFromTable() {
        String pwd = credentialTablePassword.getText();
        return pwd;
    }


}
