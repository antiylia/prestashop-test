package org.example.pages.home;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class HomePage {
    public final Section popularProducts = new Section($(byXpath("//h2[contains(text(), 'Popular Products')]/..")));
    public final SelenideElement cartBlock = $("#_desktop_cart");

    public ShoppingCart openCart() {
        this.cartBlock.scrollTo();
        this.cartBlock.click();
        ShoppingCart.root.should(visible);

        return new ShoppingCart();
    }
}
