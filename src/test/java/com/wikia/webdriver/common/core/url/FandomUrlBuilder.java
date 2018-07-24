package com.wikia.webdriver.common.core.url;

import static com.wikia.webdriver.common.core.configuration.EnvType.PROD;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.configuration.EnvType;

public class FandomUrlBuilder extends BaseUrlBuilder {

  private static final String FANDOM_HOSTNAME = "fandom.wikia.com";
  private static final String ARTICLE_PATH = "articles";
  private static final String TOPICS_PATH = "topics";

  public FandomUrlBuilder() {
    super(Configuration.getEnv());
  }

  public String getFandomUrl() {
    return getFandomUrl(envType);
  }

  public String getFandomUrl(EnvType envType) {
    String hostname = FANDOM_HOSTNAME;
    if (!envType.equals(PROD)) {
      hostname = env + "." + hostname;
    }

    return HTTP_PREFIX + hostname + "/";
  }

  public String getFandomPageUrl(String path) {
    return addPathToUrl(getFandomUrl(), path);
  }

  public String getUrlForFandomArticlePage(String pageTitle) {
    return getFandomUrl() + ARTICLE_PATH + "/" + pageTitle;
  }

  public String getUrlForFandomTopic(String topic) {
    return getFandomUrl() + TOPICS_PATH + "/" + topic;
  }

  public String getUrlForFandomVideoPage(String videoPage) {
    return getFandomUrl() + videoPage;
  }
}
