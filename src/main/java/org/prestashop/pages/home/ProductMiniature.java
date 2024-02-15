package org.prestashop.pages.home;

import com.codeborne.selenide.SelenideElement;
import org.prestashop.pages.home.modals.QuickViewModal;

import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Condition.visible;

public class ProductMiniature {
  private final SelenideElement root;
  private final SelenideElement quickViewLink;

  public ProductMiniature(SelenideElement root) {
    this.root = root;
    this.quickViewLink = this.root.$("a.quick-view");
  }

  public QuickViewModal openQuickView() {
    this.root.hover();
    this.quickViewLink.should(interactable);
    this.quickViewLink.click();
    QuickViewModal.root.should(visible);

    return new QuickViewModal();
  }
}
