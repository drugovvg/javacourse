package part1.lessons14_.WebStore.Order;

import java.math.BigDecimal;

public interface Order {

    public BigDecimal getTotal();
    public BigDecimal getSubTotal();
    public BigDecimal getShippingPrice();
    public BigDecimal getTax();

}
