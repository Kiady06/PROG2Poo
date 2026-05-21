package org.HEI.kdot;

public class Ingredients {
    private int id;
    private String name;

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Ingredients that)) return false;

        return getId() == that.getId() && getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getName().hashCode();
        return result;
    }

    public Ingredients(int id, String name) {
        setId(id);
        setName(name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Ingredients{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
