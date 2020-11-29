package part1.lessons14_.web.store.paymentmethod;

import java.math.BigDecimal;

public interface PaymentMethod {

    public boolean process(BigDecimal sum);

}
