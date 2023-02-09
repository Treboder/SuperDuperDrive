package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObjectHomeNotes {

    @FindBy(id="nav-notes-tab")
    private WebElement nav_notes_tab;

    @FindBy(id="add-note-button")
    private WebElement add_note_button;

    @FindBy(id="edit-note-button")
    private WebElement edit_note_button;

    @FindBy(id="delete-note-button")
    private WebElement delete_note_button;

    @FindBy(id="note-id")
    private WebElement note_id;

    @FindBy(id="note-title")
    private WebElement note_title;

    @FindBy(id="note-description")
    private WebElement note_description;

    @FindBy(id="save-note-button")
    private WebElement save_note_button;

    @FindBy(id="notesTableTitle")
    private WebElement notesTableTitle;

    @FindBy(id="notesTableDescription")
    private WebElement notesTableDescription;

    public PageObjectHomeNotes(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void switchToNavNotesTab() {
        nav_notes_tab.click();
    }

    public void clickAddNoteButtonNew() {
        add_note_button.click();
    }

    public void clickEditNoteButton() {
        edit_note_button.click();
    }

    public void clickDeleteNoteButton() {
        delete_note_button.click();
    }

    public void fillNoteDetailsAndSave(String title, String desc) {
        note_title.clear();
        note_title.sendKeys(title);
        note_description.clear();
        note_description.sendKeys(desc);
        save_note_button.click();
    }

    public String getNoteTitle() {
        String title = notesTableTitle.getText();
        return title;
    }

    public String getNoteDescription() {
        String desc = notesTableDescription.getText();
        return desc;
    }

}
