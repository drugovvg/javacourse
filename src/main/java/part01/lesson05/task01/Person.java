package part01.lesson05.task01;

public class Person {

    private String name;
    private Integer age;
    public enum Sex {
        MALE,
        FEMALE;
    }
    private Sex sex;

    public Person(String name, Integer age, Sex sex) {
        setName(name);
        setAge(age);
        setSex(sex);
    }

    public Person(String name, Integer age) {
        setName(name);
        setAge(age);
        setSex(Sex.MALE);
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
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




}
