package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageObjectHomeFiles {

    @FindBy(id="nav-files-tab")
    private WebElement nav_files_tab;

    @FindBy(id="fileUpload")
    private WebElement files_fileChooser;

    @FindBy(id="uploadButton")
    private WebElement files_uploadButton;

    @FindBy(id="filesTableFileName")
    private WebElement files_TableFileName;

    public PageObjectHomeFiles(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void chooseFileAndClickUploadButton(String fileName) {
        files_fileChooser.sendKeys(fileName);
        files_uploadButton.click();
    }

    public String getFileName() {
        return files_TableFileName.getText();
    }

}
