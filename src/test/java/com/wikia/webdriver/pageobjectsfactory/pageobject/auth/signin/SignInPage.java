package com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin;

import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.FormPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.forgotpassword.ForgotPasswordPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register.RegisterPage;

public interface SignInPage extends FormPage {

  ForgotPasswordPage clickForgotPasswordLink();

  SignInPage typePassword(String password);

  SignInPage typeUsername(String password);

  void login(String username, String password);

  void login(User user);

  void close();

  RegisterPage navigateToRegister();

  String getError();

  boolean isPasswordMasked();

  void togglePasswordVisibility();
}
