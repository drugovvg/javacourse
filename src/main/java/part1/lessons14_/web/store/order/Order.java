package part1.lessons14_.web.store.order;

import java.math.BigDecimal;

public interface Order {

    public BigDecimal getTotal();
    public BigDecimal getSubTotal();
    public BigDecimal getShippingPrice();
    public BigDecimal getTax();

}
