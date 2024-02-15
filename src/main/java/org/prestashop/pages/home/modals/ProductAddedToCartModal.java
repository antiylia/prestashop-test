package org.prestashop.pages.home.modals;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;

public class ProductAddedToCartModal {
  public final static SelenideElement root = $("#blockcart-modal");
  public final SelenideElement heading = root.$("h4");
  private final SelenideElement closeBtn = root.$("[aria-label='Close']");
  public final SelenideElement productName = root.$(".product-name");

  public void close() {
    this.closeBtn.click();
    root.should(disappear);
  }
}
