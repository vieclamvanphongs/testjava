package com.wikia.webdriver.common.dataprovider;

import com.wikia.webdriver.pageobjectsfactory.componentobject.addtable.TableBuilderComponentObject.Alignment;

import org.testng.annotations.DataProvider;

public class ArticleFeaturesCRUDDataProvider {

  private ArticleFeaturesCRUDDataProvider() {
  }

  @DataProvider
  public static final Object[][] getTableProperties() {
    return new Object[][]{{2, 500, 50, 3, 5, Alignment.RIGHT}};
  }
}
