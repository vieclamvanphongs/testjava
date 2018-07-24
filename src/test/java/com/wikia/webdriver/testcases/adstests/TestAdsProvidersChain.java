package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.dataprovider.mobile.MobileAdsDataProvider;
import com.wikia.webdriver.common.driverprovider.UseUnstablePageLoadStrategy;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

public class TestAdsProvidersChain extends TemplateNoFirstLoad {

  private static final Dimension MOBILE_SIZE = new Dimension(414, 736);
  private static final Dimension DESKTOP_SIZE = new Dimension(1900, 900);

  @UseUnstablePageLoadStrategy
  @Test(dataProviderClass = AdsDataProvider.class, dataProvider = "providersChainOasis", groups = {
      "AdsProvidersChainOasis", "Ads"})
  public void adsProvidersChainOasis(
      String wikiName, String article, String slotName, String providers
  ) {
    adsProvidersChain(wikiName, article, slotName, providers, DESKTOP_SIZE);
  }

  @UseUnstablePageLoadStrategy
  @Test(dataProviderClass = MobileAdsDataProvider.class, dataProvider = "providersChainMercury", groups = {
      "AdsProvidersChainMercury", "Ads"})
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void adsProvidersChainMercury(
      String wikiName, String article, String slotName, String providers
  ) {
    adsProvidersChain(wikiName, article, slotName, providers, MOBILE_SIZE);
  }

  private void adsProvidersChain(
      String wikiName, String article, String slotName, String providers, Dimension browserDimension
  ) {
    String url = UrlBuilder.createUrlBuilderForWiki(wikiName).getUrlForPath(article);

    new AdsBaseObject(driver, url, browserDimension).verifyProvidersChain(slotName, providers);
  }
}
