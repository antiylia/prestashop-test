package org.prestashop.pages.checkout.steps;

import com.codeborne.selenide.SelenideElement;
import org.prestashop.entities.Customer;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class ShippingMethodStep {
  private final SelenideElement root = $("#checkout-delivery-step");
  private final SelenideElement continueBtn = root.$("button[name='confirmDeliveryOption']");

  public void fill(Customer customer) {
    root.$(withText(customer.getShippingMethod())).click();
    continueBtn.click();
  }
}
