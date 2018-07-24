package com.wikia.webdriver.elements.fandom.components;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TextEditor extends BasePageObject {

  @FindBy(css = "#content_ifr")
  private WebElement editorIframe;

  @FindBy(css = "#tinymce")
  private WebElement textEditor;

  public TextEditor type(String text) {
    try {
      driver.switchTo().frame(editorIframe);
      textEditor.sendKeys(text);
    } finally {
      driver.switchTo().defaultContent();
    }

    return this;
  }
}
