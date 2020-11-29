package part1.lessons14_;

import part1.lessons14_.web.store.order.AsiaShippingZoneOrder;
import part1.lessons14_.web.store.order.Order;
import part1.lessons14_.web.store.order.OrderImpl;
import part1.lessons14_.web.store.payment.Payment;
import part1.lessons14_.web.store.payment.PaymentImpl;
import part1.lessons14_.web.store.paymentmethod.PaymentMethodCreditCardImpl;
import part1.lessons14_.web.store.paymentmethod.PaymentMethodQiwiImpl;

import java.math.BigDecimal;
import java.util.Date;

public class Main17 {

    public static void main(String[] args) {

        // Strategy

        Payment payment = new PaymentImpl();
        payment.setPaymentMethod(new PaymentMethodCreditCardImpl("4444333322221111", "JOHN SNOW"));
        payment.processPayment(BigDecimal.valueOf(1000));

        payment.setPaymentMethod(new PaymentMethodQiwiImpl("1234567890"));
        payment.processPayment(BigDecimal.valueOf(2000));

    }


}
