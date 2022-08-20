package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageObjectHomeFiles {

    @FindBy(id="nav-files-tab")
    private WebElement nav_files_tab;

    @FindBy(id="uploadButton")
    private WebElement files_uploadButton;

    @FindBy(id="filesTable")
    private WebElement filesTable;

    public PageObjectHomeFiles(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

}
