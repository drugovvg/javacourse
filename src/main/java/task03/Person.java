package task03;

public class Person {
    private int age;
    private String name;
    private Sex sex;

    public Person(int age, Sex sex, String name) {
        setAge(age);
        setName(name);
        setSex(sex);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age > 100 || age < 0)
            throw new IllegalArgumentException("Person's age should be in the range from 0 to 100. " +
                    + age + " received as an argument.");

        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }
}
