package org.example.pages.checkout.steps;

import com.codeborne.selenide.SelenideElement;
import org.example.entities.Customer;
import static com.codeborne.selenide.Selenide.$;
public class PersonalInformationStep {
  private final SelenideElement root = $("#checkout-personal-information-step");
  private final SelenideElement firstnameInpt = root.$("input[name='firstname']");
  private final SelenideElement lastnameInpt = root.$("input[name='lastname']");
  private final SelenideElement emailInpt = root.$("input[name='email']");
  private final SelenideElement termsAndConditionsCheckbox = root.$("input[name='psgdpr']");
  private final SelenideElement customerPrivacyCheckbox = root.$("input[name='customer_privacy']");
  private final SelenideElement continueBtn = root.$("button[name='continue']");

  public void fill(Customer customer) {
    firstnameInpt.setValue(customer.getFirstName());
    lastnameInpt.setValue(customer.getLastName());
    emailInpt.setValue(customer.getEmail());
    termsAndConditionsCheckbox.click();
    customerPrivacyCheckbox.click();
    continueBtn.click();
  }
}
