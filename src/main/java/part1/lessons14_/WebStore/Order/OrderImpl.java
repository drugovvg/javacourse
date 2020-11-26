package part1.lessons14_.WebStore.Order;

import java.math.BigDecimal;
import java.util.Date;

public class OrderImpl implements Order {

    public OrderImpl(Integer id, Integer customerId, Integer paymentId, Integer cartId, Date date) {
        this.id = id;
        this.customerId = customerId;
        this.paymentId = paymentId;
        this.cartId = cartId;
        this.date = date;
    }

    private Integer id;
    private Integer cartId;
    private Date date;
    private Integer customerId;
    private Integer paymentId;
    private enum shippingZone {Free, Asia, Europe};

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

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getTotal() {
        return BigDecimal.valueOf(0);
    }


    public BigDecimal getSubTotal() {
        return BigDecimal.valueOf(0);
    }

    public BigDecimal getShippingPrice() {
        return BigDecimal.valueOf(0);
    }

    public BigDecimal getTax() {
        return BigDecimal.valueOf(0);
    }
}
