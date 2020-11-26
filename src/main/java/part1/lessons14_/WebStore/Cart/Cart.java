    package part1.lessons14_.WebStore.Cart;

import part1.lessons14_.WebStore.Order.Order;
import part1.lessons14_.WebStore.Product.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface Cart {

    public Map<Integer, ProductCartItem> getProductCartItemMap();

    public void addProduct(Product product, int qty);

    public void removeProduct(Product product, int qty);

    public BigDecimal getSubTotal();

    public void archive();


}