package org.prestashop.pages.home.modals;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class QuickViewModal {
  public final static SelenideElement root = $(".quickview");
  private final SelenideElement addToCartBtn = root.$("[data-button-action='add-to-cart']");

  public ProductAddedToCartModal addToCart() {
    this.addToCartBtn.click();
    root.should(disappear);
    ProductAddedToCartModal.root.should(visible);
    return new ProductAddedToCartModal();
  }
}
