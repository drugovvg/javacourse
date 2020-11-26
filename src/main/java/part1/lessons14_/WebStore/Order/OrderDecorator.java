package part1.lessons14_.WebStore.Order;


import java.math.BigDecimal;

public class OrderDecorator implements Order {
    protected  Order order;

    public OrderDecorator(Order _order){
        order = _order;
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
        return order.getShippingPrice();
    }

    @Override
    public BigDecimal getTax(){
        return order.getTax();
    }

}
