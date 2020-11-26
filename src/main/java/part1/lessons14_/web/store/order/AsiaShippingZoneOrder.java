package part1.lessons14_.web.store.order;

import java.math.BigDecimal;

public class AsiaShippingZoneOrder extends OrderDecorator {

    public AsiaShippingZoneOrder(Order _order){
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
        return order.getShippingPrice().add(BigDecimal.valueOf(200));
    }

    @Override
    public BigDecimal getTax(){
        return order.getTax().multiply(BigDecimal.valueOf(1.1));
    }

}
