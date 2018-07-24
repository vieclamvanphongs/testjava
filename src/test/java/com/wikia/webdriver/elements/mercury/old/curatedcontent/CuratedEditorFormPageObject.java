package com.wikia.webdriver.elements.mercury.old.curatedcontent;

import com.wikia.webdriver.elements.mercury.old.curatedcontent.curatededitorform.ItemFormPageObject;
import com.wikia.webdriver.elements.mercury.old.curatedcontent.imageupload.UploadImageModalComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class CuratedEditorFormPageObject extends BasePageObject {

  @FindBy(css = "input#label")
  protected WebElement displayNameField;
  @FindBy(css = "input#title")
  protected WebElement pageNameField;

  @FindBy(css = ".sub-head--done")
  protected WebElement doneButton;

  @FindBy(css = ".curated-content-editor-remove")
  protected WebElement deleteItemButton;
  @FindBy(css = ".curated-content-editor-photo")
  protected WebElement imageField;

  public ItemFormPageObject clickDoneButton() {
    waitForPageReload();
    wait.forElementVisible(doneButton);
    doneButton.click();
    waitForDeleteButtonToBeVisible();

    return new ItemFormPageObject();
  }

  public UploadImageModalComponentObject clickOnImage() {
    wait.forElementVisible(imageField);
    imageField.click();

    return new UploadImageModalComponentObject(driver);
  }

  public CuratedEditorFormPageObject typeDisplayName(String displayName) {
    wait.forElementVisible(displayNameField);
    displayNameField.sendKeys(displayName);

    return this;
  }

  public CuratedEditorFormPageObject typePageName(String pageName) {
    wait.forElementVisible(pageNameField);
    pageNameField.sendKeys(pageName);

    return this;
  }

  private void waitForDeleteButtonToBeVisible() {
    wait.forElementVisible(deleteItemButton);
  }
}
