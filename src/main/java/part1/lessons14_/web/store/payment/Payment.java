package part1.lessons14_.web.store.payment;

import part1.lessons14_.web.store.paymentmethod.PaymentMethod;

import java.math.BigDecimal;

public interface Payment {

        public void processPayment(BigDecimal sum);
        public void setPaymentMethod(PaymentMethod paymentMethod);

}
