package part1.lessons14_.web.store.payment;

import part1.lessons14_.web.store.paymentmethod.PaymentMethod;

import java.math.BigDecimal;
import java.util.Date;

public class PaymentImpl implements Payment {

    private Integer id;
    private Integer customerId;
    private Integer orderId;
    private Date date;
    private PaymentMethod paymentMethod;
    private boolean processed;
    private String receiptHash;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public String getReceiptHash() {
        return receiptHash;
    }

    public void setReceiptHash(String receiptHash) {
        this.receiptHash = receiptHash;
    }

    public void processPayment(BigDecimal sum) {
        this.getPaymentMethod().process(sum);
    }


}
