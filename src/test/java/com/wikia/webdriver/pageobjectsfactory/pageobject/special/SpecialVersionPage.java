package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

public class SpecialVersionPage extends WikiBasePageObject {

  public SpecialVersionPage() {
    super();
  }

  public SpecialVersionPage open() {
    driver.get(urlBuilder.getUrlForWikiPage(URLsContent.SPECIAL_VERSION));

    return this;
  }
}
