package part1.lessons14_.WebStore.Cart;

import part1.lessons14_.WebStore.Product.Product;

public class ProductCartItemImpl implements ProductCartItem {

    private Product product;

    private int qty;

    public ProductCartItemImpl(Product product, int qty) {
        this.product = product;
        this.qty = qty;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }


}
