package part1.lessons14_.web.store.order;

import java.math.BigDecimal;

public class FreeShippingZoneOrder extends OrderDecorator {

    public FreeShippingZoneOrder(Order _order){
        super(_order);
    }

    @Override
    public BigDecimal getTotal(){
        return order.getTotal();
    }

    @Override
    public BigDecimal getSubTotal(){
        return order.getSubTotal();
    }

    @Override
    public BigDecimal getShippingPrice(){
        return BigDecimal.valueOf(0);
    }

    @Override
    public BigDecimal getTax(){
        return BigDecimal.valueOf(0);
    }

}
