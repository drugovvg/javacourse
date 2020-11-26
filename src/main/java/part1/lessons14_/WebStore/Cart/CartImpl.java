package part1.lessons14_.WebStore.Cart;

import part1.lessons14_.WebStore.Order.Order;
import part1.lessons14_.WebStore.Product.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class CartImpl implements Cart {

    private Integer id;
    private Integer customerId;
    private Map<Integer, ProductCartItem> productCartItemMap;
    private boolean archived;

    public CartImpl(Integer id, Integer customerId, Map<Integer, ProductCartItem> productCartItemMap, boolean archived) {
        this.id = id;
        this.customerId = customerId;
        this.productCartItemMap = productCartItemMap;
        this.archived = archived;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Map<Integer, ProductCartItem> getProductCartItemMap() {
        return productCartItemMap;
    }

    public void setProductCartItemMap(Map<Integer, ProductCartItem> productCartItemMap) {
        this.productCartItemMap = productCartItemMap;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public void addProduct(Product product) {
        this.addProduct(product, 1);
    }

    public void addProduct(Product product, int qty) {
        if(this.getProductCartItemMap().containsKey(product.getId())) {
            qty = this.getProductCartItemMap().get(product.getId()).getQty() + qty;
        }
        this.getProductCartItemMap().put(product.getId(), new ProductCartItemImpl(product, qty));
    }

    public void removeProduct(Product product) {
       this.removeProduct(product, 1);
    }

    public void removeProduct(Product product, int qty) {
        if(this.getProductCartItemMap().containsKey(product.getId())) {
            int difference = this.getProductCartItemMap().get(product.getId()).getQty() - qty;
            if(difference <= 0) {
                this.getProductCartItemMap().remove(product.getId());
            } else {
                this.getProductCartItemMap().put(product.getId(), new ProductCartItemImpl(product, difference));
            }
        }
    }

    public BigDecimal getSubTotal() {
        BigDecimal sum = new BigDecimal(0);
        this.getProductCartItemMap().forEach((k, v) -> {
            sum.add(v.getProduct().getPrice().multiply(new BigDecimal(v.getQty())));
        });
        return sum;
    }

    public void archive() {
        this.setArchived(true);
    }



}
