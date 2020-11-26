package part1.lessons14_.WebStore.Order;

import java.math.BigDecimal;

public class EuropeShippingZoneOrder extends OrderDecorator {

    public EuropeShippingZoneOrder(Order _order){
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
        return order.getShippingPrice().multiply(BigDecimal.valueOf(1.15));
    }

    @Override
    public BigDecimal getTax(){
        return order.getTax().multiply(BigDecimal.valueOf(1.1));
    }

}
