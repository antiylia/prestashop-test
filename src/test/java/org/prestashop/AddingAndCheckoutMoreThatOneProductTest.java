package org.prestashop;

import org.prestashop.entities.Customer;
import org.prestashop.entities.Product;
import org.prestashop.pages.checkout.CheckoutPage;
import org.prestashop.pages.home.HomePage;
import org.prestashop.pages.home.ProductMiniature;
import org.prestashop.pages.home.Section;
import org.prestashop.pages.home.ShoppingCart;
import org.prestashop.pages.home.modals.ProductAddedToCartModal;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Condition.text;

public class AddingAndCheckoutMoreThatOneProductTest extends BaseTest {
  public final String PRODUCT_ADDED_MSG = "Product successfully added to your shopping cart";
  Product tShirt = new Product(1, "Hummingbird Printed T-Shirt");
  Product sweater = new Product(2, "Hummingbird printed sweater");
  Product framedPoster = new Product(3, "The best is yet to come' Framed poster");

  @Test
  public void userCanAddMoreThatOneProductToCart() {
    Product[] products = { tShirt, framedPoster };

    HomePage homePage = new HomePage();
    Section popularProducts = homePage.popularProducts;
    popularProducts.root.scrollTo();

    for (var product : products) {
      ProductMiniature miniature = popularProducts.getProductMiniature(product);
      ProductAddedToCartModal productAddedToCartModal = miniature.openQuickView().addToCart();

      productAddedToCartModal.heading.shouldHave(text(PRODUCT_ADDED_MSG));
      productAddedToCartModal.productName.shouldHave(text(product.getName()));
      productAddedToCartModal.close();
    }
    homePage.cartBlock.shouldHave(text("Cart (%s)".formatted(products.length)));
  }

  @Test
  public void userCanProceedToCheckoutOrder() {
    Product[] products = { sweater, framedPoster };
    Customer customer = new Customer();

    HomePage homePage = new HomePage();
    addProductsToCart(homePage, products);

    ShoppingCart cart = homePage.openCart();
    CheckoutPage checkoutPage = cart.proceedToCheckout();
    checkoutPage.checkoutOrder(customer);

    checkoutPage.paymentStep.placeOrderBtn.should(interactable);
  }

  // Currently done via UI, should be improved using API
  private void addProductsToCart(HomePage homePage, Product[] products) {
    for (var product : products) {
      ProductMiniature miniature = homePage.popularProducts.getProductMiniature(product);
      ProductAddedToCartModal productAddedToCartModal = miniature.openQuickView().addToCart();
      productAddedToCartModal.close();
    }
  }
}
