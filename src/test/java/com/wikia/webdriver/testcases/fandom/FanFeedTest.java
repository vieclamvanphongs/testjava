package com.wikia.webdriver.testcases.fandom;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.templates.fandom.FandomTestTemplate;
import com.wikia.webdriver.elements.fandom.components.LoadMore;
import com.wikia.webdriver.elements.fandom.pages.HomePage;

import org.testng.annotations.Test;

@Test(groups = {"Fandom", "Fandom_FanFeed"})
public class FanFeedTest extends FandomTestTemplate {

  @Test
  public void canSeeFanFeed() {
    HomePage homePage = new HomePage().open();

    Assertion.assertTrue(homePage.getFanFeed().isDisplayed(), "Fan Feed is not displayed");
  }

  @Test
  public void canLoadMoreWithCors() {
    // test on www to ensure CORS is working okay
    HomePage wwwHomePage = new HomePage().openWWW();
    LoadMore loadMore = wwwHomePage.getLoadMore();

    Assertion.assertTrue(loadMore.isButtonDisplayed(), "Load more button is not displayed");
    Assertion.assertTrue(loadMore.waitForMorePosts(), "Load more functionality is not working");
  }
}
