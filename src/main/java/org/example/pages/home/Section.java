package org.example.pages.home;

import com.codeborne.selenide.SelenideElement;
import org.example.entities.Product;

public class Section {
    public final SelenideElement root;

    public Section(SelenideElement root) {
        this.root = root;
    }

    public ProductMiniature getProductMiniature(Product product) {
        String productLocator = "[data-id-product='%s']".formatted(product.getId());
        return new ProductMiniature(this.root.$(productLocator));
    }
}
