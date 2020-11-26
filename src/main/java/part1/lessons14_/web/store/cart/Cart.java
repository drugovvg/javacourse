    package part1.lessons14_.web.store.cart;

import part1.lessons14_.web.store.product.Product;

import java.math.BigDecimal;
import java.util.Map;

public interface Cart {

    public Map<Integer, ProductCartItem> getProductCartItemMap();

    public void addProduct(Product product, int qty);

    public void removeProduct(Product product, int qty);

    public BigDecimal getSubTotal();

    public void archive();


}