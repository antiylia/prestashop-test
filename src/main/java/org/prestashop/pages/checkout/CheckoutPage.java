package org.prestashop.pages.checkout;

import com.codeborne.selenide.SelenideElement;
import org.prestashop.entities.Customer;
import org.prestashop.pages.checkout.steps.AddressesStep;
import org.prestashop.pages.checkout.steps.PaymentStep;
import org.prestashop.pages.checkout.steps.PersonalInformationStep;
import org.prestashop.pages.checkout.steps.ShippingMethodStep;

import static com.codeborne.selenide.Selenide.$;

public class CheckoutPage {
  public final static SelenideElement root = $("body#checkout");
  private final PersonalInformationStep personalInfoStep = new PersonalInformationStep();
  private final AddressesStep addressesStep = new AddressesStep();
  private final ShippingMethodStep shippingMethodStep = new ShippingMethodStep();
  public final PaymentStep paymentStep = new PaymentStep();

  public void checkoutOrder(Customer customer) {
    personalInfoStep.fill(customer);
    addressesStep.fill(customer);
    shippingMethodStep.fill(customer);
    paymentStep.fill(customer);
  }
}
