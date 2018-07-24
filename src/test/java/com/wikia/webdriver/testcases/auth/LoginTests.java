package com.wikia.webdriver.testcases.auth;

import com.wikia.webdriver.common.contentpatterns.MobileSubpages;
import com.wikia.webdriver.common.contentpatterns.MobileWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.Helios;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.pages.ArticlePage;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.AttachedSignInPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.SignInPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.globalnav.GlobalNavigation;
import org.testng.annotations.Test;

import java.time.Instant;

import static com.wikia.webdriver.common.core.Assertion.assertTrue;
import static org.testng.Assert.assertFalse;

@Execute(onWikia = MobileWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
public class LoginTests extends NewTestTemplate {

  private static final User USER = User.LOGIN_USER;
  private static final User JAPANESE_USER = User.USER_JAPAN;
  private static final User STAFF = User.LOGIN_STAFF;

  private static final String DESKTOP = "auth-login-desktop";
  private static final String MOBILE = "auth-login-mobile";

  private static final String ERROR_MESSAGE =
    "We don't recognize these credentials. Try again or register a new account.";
  private static final String SUBMIT_BUTTON_DISABLED_MSG = "Submit button should be disabled";

  @Test(groups = MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userCanCloseJoinPage() {
    ArticlePage article = openArticleOnMobile();
    String previousTitle = article.getArticleTitle();
    article
      .getGlobalNavigationMobile()
      .clickOnAnonAvatar()
      .close();

    Assertion.assertEquals(article.getArticleTitle(), previousTitle);
  }

  @Test(groups = DESKTOP)
  public void userCanLogInAsStaffOnDesktop() {
    ArticlePageObject article = openArticleOnDesktop();
    loginOnDesktopAs(STAFF);
    article.verifyUserLoggedIn(STAFF);
  }

  @Test(groups = MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userCanLogInAsStaffOnMobile() {
    ArticlePage article = openArticleOnMobile();
    loginOnMobileAs(STAFF);
    article.waitForPageReload();
    article.verifyUserLoggedIn(STAFF);
  }

  @Test(groups = DESKTOP)
  public void japaneseUserCanLogInOnDesktop() {
    ArticlePageObject article = openArticleOnDesktop();
    loginOnDesktopAs(JAPANESE_USER);
    article.verifyUserLoggedIn(JAPANESE_USER);
  }

  @Test(groups = MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void japaneseUserCanLogInOnMobile() {
    ArticlePage article = openArticleOnMobile();
    loginOnMobileAs(JAPANESE_USER);
    article.waitForPageReload();
    article.verifyUserLoggedIn(JAPANESE_USER);
  }

  @Test(groups = DESKTOP)
  @Execute(onWikia = MobileWikis.DISCUSSIONS_5)
  public void userIsRedirectedToDiscussionPageUponLogInFromDiscussionPageOnDesktop() {
    PostsListPage discussionPage = new PostsListPage().open();
    loginOnDesktopFromDiscussionPageAs(USER);
    assertTrue(discussionPage.waitForPageReload().isStringInURL(PostsListPage.PATH),
      "User should be redirected to discussion post list view upon log in");
  }

  @Test(groups = MOBILE)
  @Execute(onWikia = MobileWikis.DISCUSSIONS_5)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userIsRedirectedToDiscussionPageUponLogInFromDiscussionPageOnMobile() {
    PostsListPage discussionPage = new PostsListPage().open();
    loginOnDiscussionMobilePageAs(USER);
    assertTrue(discussionPage.waitForPageReload().isStringInURL(PostsListPage.PATH),
      "User should be redirected to discussion post list view upon log in");
  }

  @Test(groups = DESKTOP)
  public void passwordTogglerChangesPasswordVisibilityOnDesktop() {
    SignInPage signInPage = openLoginPageFromGlobalnavOnDesktop();
    signInPage.typePassword(USER.getPassword());

    Assertion.assertTrue(signInPage.isPasswordMasked(), "password should be masked");
    signInPage.togglePasswordVisibility();
    assertFalse(signInPage.isPasswordMasked(), "password should be readable");
  }

  @Test(groups = MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void passwordTogglerChangesPasswordVisibilityOnMobile() {
    SignInPage signInPage = navigateToSignInOnMobile();
    signInPage.typePassword(USER.getPassword());

    Assertion.assertTrue(signInPage.isPasswordMasked(), "password should be masked");
    signInPage.togglePasswordVisibility();
    assertFalse(signInPage.isPasswordMasked(), "password should be readable");
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.INVALIDATED_TOKEN_USER_1)
  public void userWithoutAValidTokenGetsLoggedOutOnDesktop() {
    ArticlePageObject article = openArticleOnDesktop();
    Helios.deleteAllTokens(User.INVALIDATED_TOKEN_USER_1);
    article.refreshPageAddingCacheBuster();
    article.waitForPageReload();
    assertTrue(article.getGlobalNavigation().isUserLoggedOut());
  }

  @Test(groups = MOBILE)
  @Execute(asUser = User.INVALIDATED_TOKEN_USER_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userWithoutAValidTokenGetsLoggedOutOnMobile() {
    ArticlePage article = openArticleOnMobile();
    Helios.deleteAllTokens(User.INVALIDATED_TOKEN_USER_2);
    article.refreshPageAddingCacheBuster();
    assertTrue(article.isUserLoggedOutMobile());
  }

  @Test(groups = DESKTOP)
  public void nonexistentUserCannotLogInOnDesktop() {
    SignInPage signIn = openLoginPageFromGlobalnavOnDesktop();
    String nonexistingUsername = String.format("QA_%s", Instant.now().getEpochSecond());
    signIn.login(nonexistingUsername, USER.getPassword());
    Assertion.assertEquals(signIn.getError(), ERROR_MESSAGE);
  }

  @Test(groups = MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void nonexistentUserCannotLogInOnMobile() {
    SignInPage signIn = navigateToSignInOnMobile();
    String nonexistingUsername = String.format("QA_%s", Instant.now().getEpochSecond());
    signIn.login(nonexistingUsername, USER.getPassword());
    Assertion.assertEquals(signIn.getError(), ERROR_MESSAGE);
  }

  @Test(groups = DESKTOP)
  public void userCannotLogInWithInvalidPasswordOnDesktop() {
    SignInPage signIn = openLoginPageFromGlobalnavOnDesktop();
    String invalidPassword = String.format("P@55_%s", Instant.now().getEpochSecond());
    signIn.login(USER.getUserName(), invalidPassword);
    Assertion.assertEquals(signIn.getError(), ERROR_MESSAGE);
  }

  @Test(groups = MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userCannotLogInWithInvalidPasswordOnMobile() {
    SignInPage signIn = navigateToSignInOnMobile();
    String invalidPassword = String.format("P@55_%s", Instant.now().getEpochSecond());
    signIn.login(USER.getUserName(), invalidPassword);
    Assertion.assertEquals(signIn.getError(), ERROR_MESSAGE);
  }

  @Test(groups = DESKTOP)
  public void userCannotLogInWithBlankUsernameOnDesktop() {
    SignInPage signIn = openLoginPageFromGlobalnavOnDesktop();
    signIn.typeUsername("").typePassword(USER.getPassword());
    assertTrue(signIn.submitButtonNotClickable(), SUBMIT_BUTTON_DISABLED_MSG);
  }

  @Test(groups = MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userCannotLogInWithBlankUsernameOnMobile() {
    SignInPage signIn = navigateToSignInOnMobile();
    signIn.typeUsername("").typePassword(USER.getPassword());
    assertTrue(signIn.submitButtonNotClickable(), SUBMIT_BUTTON_DISABLED_MSG);
  }

  @Test(groups = DESKTOP)
  public void userCannotLogInWithBlankPasswordOnDesktop() {
    SignInPage signIn = openLoginPageFromGlobalnavOnDesktop();
    signIn.typeUsername(USER.getUserName()).typePassword("");
    assertTrue(signIn.submitButtonNotClickable(), SUBMIT_BUTTON_DISABLED_MSG);
  }

  @Test(groups = MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userCannotLogInWithBlankPasswordOnMobile() {
    SignInPage signIn = navigateToSignInOnMobile();
    signIn.typeUsername(USER.getUserName()).typePassword("");
    assertTrue(signIn.submitButtonNotClickable(), SUBMIT_BUTTON_DISABLED_MSG);
  }

  /**
   * HELPER METHODS
   */

  private ArticlePage openArticleOnMobile() {
    return new ArticlePage().open(MobileSubpages.MAIN_PAGE);
  }

  private ArticlePageObject openArticleOnDesktop() {
    return new ArticlePageObject().open(MobileSubpages.MAIN_PAGE);
  }

  private AttachedSignInPage navigateToSignInOnMobile() {
    return new GlobalNavigation().clickAnonUserAvatar().clickOnRegister().navigateToSignIn();
  }

  private SignInPage openLoginPageFromGlobalnavOnDesktop() {
    return new GlobalNavigation().clickOnSignIn();
  }

  private void loginOnDesktopAs(User user) {
    openLoginPageFromGlobalnavOnDesktop().login(user);
  }

  private void loginOnDesktopFromDiscussionPageAs(User user) {
    new GlobalNavigation().clickOnSignIn().login(user);
  }

  private void loginOnMobileAs(User user) {
    navigateToSignInOnMobile().login(user);
  }

  private void loginOnDiscussionMobilePageAs(User user) {
    navigateToSignInOnMobile().login(user);
  }

}
