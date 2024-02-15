package org.prestashop.pages.home;

import com.codeborne.selenide.SelenideElement;
import org.prestashop.pages.checkout.CheckoutPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ShoppingCart {
    public final static SelenideElement root = $(".cart-grid");
    private final SelenideElement proceedToCheckoutLink = root.$(".checkout a");

    public CheckoutPage proceedToCheckout() {
        this.proceedToCheckoutLink.click();
        CheckoutPage.root.should(visible);

        return new CheckoutPage();
    }
}
