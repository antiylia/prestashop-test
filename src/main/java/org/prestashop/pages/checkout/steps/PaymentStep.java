package org.prestashop.pages.checkout.steps;

import com.codeborne.selenide.SelenideElement;
import org.prestashop.entities.Customer;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class PaymentStep {
  private final SelenideElement root = $("#checkout-payment-step");
  private final SelenideElement termsAndConditionsCheckbox = root.$("[name*='terms-and-conditions']");
  public final SelenideElement placeOrderBtn = root.$(".btn[type='submit']");

  public void fill(Customer customer) {
    root.$(withText(customer.getPayment())).click();
    termsAndConditionsCheckbox.click();
    // placeOrderBtn.click(); for now, commented in order not to generate orders
  }
}
