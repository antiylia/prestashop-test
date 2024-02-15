package org.prestashop.pages;

import java.time.Duration;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.*;

public class BrowserUtils {
  public static void waitLoading() {
    $("#loadingMessage").should(disappear, Duration.ofSeconds(20));
  }

  public static void switchToIframe() {
    switchTo().frame("framelive");
  }
}
