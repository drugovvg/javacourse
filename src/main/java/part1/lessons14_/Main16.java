package part1.lessons14_;

import part1.lessons14_.web.store.cart.Cart;
import part1.lessons14_.web.store.cart.CartImpl;
import part1.lessons14_.web.store.order.AsiaShippingZoneOrder;
import part1.lessons14_.web.store.order.Order;
import part1.lessons14_.web.store.order.OrderImpl;

import java.util.Date;

public class Main16 {

    public static void main(String[] args) {

        // Decorator

        Order order = new OrderImpl(3,4,5,6, new Date());
        System.out.println("Shipping Price: " + order.getShippingPrice());

        order = new AsiaShippingZoneOrder(order);
        System.out.println("Shipping Price for Asia: " + order.getShippingPrice());

    }


}
