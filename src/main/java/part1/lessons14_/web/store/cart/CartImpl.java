package part1.lessons14_.web.store.cart;

import part1.lessons14_.web.store.product.Product;

import java.math.BigDecimal;
import java.util.Map;

public class CartImpl implements Cart {

    private Integer id;
    private Integer customerId;
    private Map<Integer, ProductCartItem> productCartItemMap;
    private boolean archived;

    public CartImpl(Builder builder) {
        id = builder.id;
        customerId = builder.customerId;
        productCartItemMap =  builder.productCartItemMap;
        archived = builder.archived;
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

    @Override
    public String toString() {
        return "CartImpl{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", productCartItemMap=" + productCartItemMap +
                ", archived=" + archived +
                '}';
    }

    public static class Builder {
        private Integer id;
        private Integer customerId;
        private Map<Integer, ProductCartItem> productCartItemMap;
        private boolean archived;

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withCustomerId(Integer customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder withProductCartItemMap(Map<Integer, ProductCartItem> productCartItemMap) {
            this.productCartItemMap = productCartItemMap;
            return this;
        }

        public Builder withArchived(boolean archived) {
            this.archived = archived;
            return this;
        }

        public CartImpl build() {
            return new CartImpl(this);
        }
    }

    public static Builder builder(){
        return new Builder();
    }

}
