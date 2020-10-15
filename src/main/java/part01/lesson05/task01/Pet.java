package part01.lesson05.task01;

/**
 * Pet entity
 */
public class Pet {
    private Integer id;
    private String name;
    private Float weight;
    private Person owner;

    /**
     * Constructor
     *
     * @param id - pet's id
     * @param name - pet's name
     * @param weight - pet's weight
     * @param owner - pet's owner
     */
    public Pet(Integer id, String name, Float weight, Person owner) {
        setId(id);
        setName(name);
        setWeight(weight);
        setOwner(owner);
    }

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

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", owner=" + owner +
                '}';
    }
}
