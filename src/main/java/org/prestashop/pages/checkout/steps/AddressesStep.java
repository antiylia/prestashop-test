package org.prestashop.pages.checkout.steps;

import com.codeborne.selenide.SelenideElement;
import org.prestashop.entities.Customer;
import static com.codeborne.selenide.Selenide.$;

public class AddressesStep {
  private final SelenideElement root = $("#checkout-addresses-step");
  private final SelenideElement addressInpt = root.$("input[name='address1']");
  private final SelenideElement zipInpt = root.$("input[name='postcode']");
  private final SelenideElement cityInpt = root.$("input[name='city']");
  private final SelenideElement continueBtn = root.$("button[name='confirm-addresses']");

  public void fill(Customer customer) {
    addressInpt.setValue(customer.getAddress());
    zipInpt.setValue(customer.getZip());
    cityInpt.setValue(customer.getCity());
    continueBtn.click();
  }
}
