package part1.lessons14_.web.store.customer;

import part1.lessons14_.web.store.paymentmethod.PaymentMethod;

public class CustomerImpl implements Customer {

    private Integer id;
    private String name;
    private Integer age;
    private String phone;
    private PaymentMethod curPaymentMethod;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public PaymentMethod getCurPaymentMethod() {
        return curPaymentMethod;
    }

    public void setCurPaymentMethod(PaymentMethod curPaymentMethod) {
        this.curPaymentMethod = curPaymentMethod;
    }

}
