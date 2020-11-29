package part1.lessons14_.web.store.paymentmethod;

import java.math.BigDecimal;

public class PaymentMethodCreditCardImpl implements PaymentMethod {

    private String cardNumber;
    private String cardName;

    public PaymentMethodCreditCardImpl(String cardNumber, String cardName) {
        this.cardNumber = cardNumber;
        this.cardName = cardName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public boolean process(BigDecimal sum){
        //Billing provider activities
        return true;
    }

}
