package part1.lessons14_;

import part1.lessons14_.web.store.cart.Cart;
import part1.lessons14_.web.store.cart.CartImpl;

public class Main15 {

    public static void main(String[] args) {

        // Builder
        Cart cart = new CartImpl.Builder().
                withId(2).
                withCustomerId(3).
                withArchived(true).
                build();

        System.out.println(cart);

    }


}
