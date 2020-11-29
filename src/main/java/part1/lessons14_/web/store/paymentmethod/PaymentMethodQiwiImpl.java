package part1.lessons14_.web.store.paymentmethod;

import java.math.BigDecimal;

public class PaymentMethodQiwiImpl implements PaymentMethod {

    public String walletNumber;

    public PaymentMethodQiwiImpl(String walletNumber) {
        this.walletNumber = walletNumber;
    }

    public String getWalletNumber() {
        return walletNumber;
    }

    public void setWalletNumber(String walletNumber) {
        this.walletNumber = walletNumber;
    }

    public boolean process(BigDecimal sum){
        //Billing provider activities
        return true;
    }

}
