package org.example;

import com.codeborne.selenide.Configuration;
import org.example.pages.BrowserUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;

import static com.codeborne.selenide.Selenide.closeWindow;
import static com.codeborne.selenide.Selenide.open;

public class BaseTest {
  @BeforeAll
  public static void setUp() {
    Configuration.browserSize = "1920x1080";
    Configuration.baseUrl = "https://demo.prestashop.com/";
    SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
  }

  @BeforeEach
  public void startTest() {
    open("/");
    BrowserUtils.waitLoading();
    BrowserUtils.switchToIframe();
  }

  @AfterEach
  public void endTest() {
    closeWindow();
  }
}
