package com.wikia.webdriver.testcases.visualeditor.entrypoint;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.Editor;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.EditorPref;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

import org.testng.annotations.Test;

public class VisualEditorEntryTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();

  @Test(groups = {"VisualEditorEntryTests", "VisualEditorEntryTest_001",
                  "categoryEntry"}, dataProviderClass = VisualEditorDataProvider.class, dataProvider = "categoryEntryPoints", enabled = false)
  @RelatedIssue(issueID = "XW-3676")
  public void VisualEditorEntryTest_001_Category(
      boolean isRTEext,
      boolean isVEext,
      VisualEditorDataProvider.EditorPref editorPref,
      VisualEditorDataProvider.Editor expectedEditor
  ) {
    wikiURL = UrlBuilder.createUrlBuilderForWiki(VisualEditorDataProvider.getTestWiki(isRTEext,
                                                                                      isVEext
    )).getUrl();
    ArticlePageObject article = new ArticlePageObject();
    article.loginAs(credentials.getUserBaseOnEditorPref(editorPref),
                    credentials.getPassBaseOnEditorPref(editorPref),
                    wikiURL
    );
    article.open(URLsContent.CATEGORY_PAGE);
    article.verifyMainEditEditor(expectedEditor);
  }

  @Test(groups = {"VisualEditorEntryTests", "VisualEditorEntryTest_002",
                  "createAPageEntry"}, dataProviderClass = VisualEditorDataProvider.class, dataProvider = "createAPageEntryPoints", enabled = false)
  @RelatedIssue(issueID = "XW-3677")
  public void VisualEditorEntryTest_002_CreateAPage(
      boolean isRTEext, boolean isVEext, EditorPref editorPref, Editor expectedEditor
  ) {
    wikiURL = UrlBuilder.createUrlBuilderForWiki(VisualEditorDataProvider.getTestWiki(isRTEext,
                                                                                      isVEext
    )).getUrl();
    ArticlePageObject article = new ArticlePageObject();
    String articleName = PageContent.ARTICLE_NAME_PREFIX + BasePageObject.getTimeStamp();
    article.loginAs(credentials.getUserBaseOnEditorPref(editorPref),
                    credentials.getPassBaseOnEditorPref(editorPref),
                    wikiURL
    );
    article.open(articleName);
    article.verifyCreateAPageEditor(expectedEditor);
  }

  @Test(groups = {"VisualEditorEntryTests", "VisualEditorEntryTest_003",
                  "listEntry"}, dataProviderClass = VisualEditorDataProvider.class, dataProvider = "listEntryPoints", enabled = false)
  @RelatedIssue(issueID = "XW-3678")
  public void VisualEditorEntryTest_003_List(
      boolean isRTEext, boolean isVEext, EditorPref editorPref, Editor expectedEditor
  ) {
    wikiURL = UrlBuilder.createUrlBuilderForWiki(VisualEditorDataProvider.getTestWiki(isRTEext,
                                                                                      isVEext
    )).getUrl();
    ArticlePageObject article = new ArticlePageObject();
    article.loginAs(credentials.getUserBaseOnEditorPref(editorPref),
                    credentials.getPassBaseOnEditorPref(editorPref),
                    wikiURL
    );
    article.open(URLsContent.LIST_PAGE);
    article.verifyMainEditEditor(expectedEditor);
  }

  @Test(groups = {"VisualEditorEntryTests", "VisualEditorEntryTest_004",
                  "articleEntry"}, dataProviderClass = VisualEditorDataProvider.class, dataProvider = "mainEditEntryPoints", enabled = false)
  @RelatedIssue(issueID = "XW-3675")
  public void VisualEditorEntryTest_004_Article(
      boolean isRTEext, boolean isVEext, EditorPref editorPref, Editor expectedEditor
  ) {
    wikiURL = UrlBuilder.createUrlBuilderForWiki(VisualEditorDataProvider.getTestWiki(isRTEext,
                                                                                      isVEext
    )).getUrl();
    ArticlePageObject article = new ArticlePageObject();
    article.loginAs(credentials.getUserBaseOnEditorPref(editorPref),
                    credentials.getPassBaseOnEditorPref(editorPref),
                    wikiURL
    );
    article.open(URLsContent.TESTINGPAGE);
    article.verifyMainEditEditor(expectedEditor);
  }

  @Test(groups = {"VisualEditorEntryTests", "VisualEditorEntryTest_005",
                  "redLinkEntry"}, dataProviderClass = VisualEditorDataProvider.class, dataProvider = "redLinkEntryPoints", enabled = false)
  @RelatedIssue(issueID = "XW-3679")
  public void VisualEditorEntryTest_005_redLink(
      boolean isRTEext, boolean isVEext, EditorPref editorPref, Editor expectedEditor
  ) {
    wikiURL = UrlBuilder.createUrlBuilderForWiki(VisualEditorDataProvider.getTestWiki(isRTEext,
                                                                                      isVEext
    )).getUrl();
    ArticlePageObject article = new ArticlePageObject();
    article.loginAs(credentials.getUserBaseOnEditorPref(editorPref),
                    credentials.getPassBaseOnEditorPref(editorPref),
                    wikiURL
    );
    article.open(URLsContent.TESTINGPAGE);
    article.verifyRedLinkEditor(expectedEditor);
  }

  @Test(groups = {"VisualEditorEntryTests", "VisualEditorEntryTest_006",
                  "sectionEditEntry"}, dataProviderClass = VisualEditorDataProvider.class, dataProvider = "sectionEditEntryPoints", enabled = false)
  @RelatedIssue(issueID = "XW-3680")
  public void VisualEditorEntryTest_006_sectionEdit(
      boolean isRTEext, boolean isVEext, EditorPref editorPref, Editor expectedEditor
  ) {
    wikiURL = UrlBuilder.createUrlBuilderForWiki(VisualEditorDataProvider.getTestWiki(isRTEext,
                                                                                      isVEext
    )).getUrl();
    ArticlePageObject article = new ArticlePageObject();
    article.loginAs(credentials.getUserBaseOnEditorPref(editorPref),
                    credentials.getPassBaseOnEditorPref(editorPref),
                    wikiURL
    );
    article.open(URLsContent.TESTINGPAGE);
    article.verifySectionEditEditor(expectedEditor);
  }

  @Test(groups = {"VisualEditorEntryTests", "VisualEditorEntryTest_007",
                  "templateEntry"}, dataProviderClass = VisualEditorDataProvider.class, dataProvider = "templateEntryPoints", enabled = false)
  @RelatedIssue(issueID = "XW-3681")
  public void VisualEditorEntryTest_007_template(
      boolean isRTEext, boolean isVEext, EditorPref editorPref, Editor expectedEditor
  ) {
    wikiURL = UrlBuilder.createUrlBuilderForWiki(VisualEditorDataProvider.getTestWiki(isRTEext,
                                                                                      isVEext
    )).getUrl();
    ArticlePageObject article = new ArticlePageObject();
    article.loginAs(credentials.getUserBaseOnEditorPref(editorPref),
                    credentials.getPassBaseOnEditorPref(editorPref),
                    wikiURL
    );
    article.open(URLsContent.TEMPLATE_PAGE);
    article.verifyMainEditEditor(expectedEditor);
  }

  @Test(groups = {"VisualEditorEntryTests", "VisualEditorEntryTest_008",
                  "urlActionEditEntry"}, dataProviderClass = VisualEditorDataProvider.class, dataProvider = "urlActionEditEntryPoints")
  public void VisualEditorEntryTest_008_urlActionEdit(
      boolean isRTEext, boolean isVEext, EditorPref editorPref, Editor expectedEditor
  ) {
    wikiURL = UrlBuilder.createUrlBuilderForWiki(VisualEditorDataProvider.getTestWiki(isRTEext,
                                                                                      isVEext
    )).getUrl();
    ArticlePageObject article = new ArticlePageObject();
    article.loginAs(credentials.getUserBaseOnEditorPref(editorPref),
                    credentials.getPassBaseOnEditorPref(editorPref),
                    wikiURL
    );
    article.verifyURLActionEditEditor(expectedEditor, URLsContent.TESTINGPAGE, wikiURL);
  }

  @Test(groups = {"VisualEditorEntryTests", "VisualEditorEntryTest_009",
                  "urlVEActionEditEntry"}, dataProviderClass = VisualEditorDataProvider.class, dataProvider = "urlVEActionEditEntryPoints")
  public void VisualEditorEntryTest_009_urlVEActionEdit(
      boolean isRTEext, boolean isVEext, EditorPref editorPref, Editor expectedEditor
  ) {
    wikiURL = UrlBuilder.createUrlBuilderForWiki(VisualEditorDataProvider.getTestWiki(isRTEext,
                                                                                      isVEext
    )).getUrl();
    ArticlePageObject article = new ArticlePageObject();
    article.loginAs(credentials.getUserBaseOnEditorPref(editorPref),
                    credentials.getPassBaseOnEditorPref(editorPref),
                    wikiURL
    );
    article.verifyURLVEActionEditEditor(expectedEditor, wikiURL);
  }
}
