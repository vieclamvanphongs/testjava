package com.wikia.webdriver.common.core.imageutilities;

import org.openqa.selenium.WebElement;

public class ImageHelper {

  private static final String START_TOKEN = "Wikia-Visualization-Main%2C";
  private static final String STOP_TOKEN = ".";

  private ImageHelper() {

  }

  /**
   * Method fetches specific string related to an image by storing index start position and finish
   * position, and then selects characters in between those indexes by using substring method.
   */
  public static String getImageId(WebElement image) {
    String imageUrl = image.getAttribute("src");

    int indexComparisonStart = imageUrl.indexOf(START_TOKEN) + START_TOKEN.length();
    int indexComparisonFinish = imageUrl.substring(indexComparisonStart).indexOf(STOP_TOKEN);

    return imageUrl.substring(indexComparisonStart, indexComparisonStart + indexComparisonFinish);
  }
}
