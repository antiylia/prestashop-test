package org.example;

import com.codeborne.selenide.Configuration;
import org.example.entities.Customer;
import org.example.entities.Product;
import org.example.pages.BrowserUtils;
import org.example.pages.checkout.CheckoutPage;
import org.example.pages.home.HomePage;
import org.example.pages.home.ProductMiniature;
import org.example.pages.home.Section;
import org.example.pages.home.ShoppingCart;
import org.example.pages.home.modals.ProductAddedToCartModal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class AddingAndCheckoutMoreThatOneProductTest {
  public final String PRODUCT_ADDED_MSG = "Product successfully added to your shopping cart";
  Product tShirt = new Product(1, "Hummingbird Printed T-Shirt");
  Product sweater = new Product(2, "Hummingbird printed sweater");
  Product framedPoster = new Product(3, "The best is yet to come' Framed poster");

  @BeforeAll
  public static void setUp() {
    Configuration.browserSize = "1920x1080";
    Configuration.baseUrl = "https://demo.prestashop.com/";
  }

  @BeforeEach
  public void startTest() {
    open("/");
    BrowserUtils.waitLoading();
    BrowserUtils.switchToIframe();
  }

  @AfterEach
  public void endTest() {
    closeWindow();
  }

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
