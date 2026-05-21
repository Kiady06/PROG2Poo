package org.HEI.kdot;

public class OrderPlate {
    private int id;
    private Plate plate;
    private int numberPlate;
    private Order order;

    public OrderPlate(int id, Plate plate, int numberPlate, Order order) {
        setId(id);
        setPlate(plate);
        setNumberPlate(numberPlate);
        setOrder(order);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Plate getPlate() {
        return plate;
    }

    public void setPlate(Plate plate) {
        this.plate = plate;
    }

    public int getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(int numberPlate) {
        this.numberPlate = numberPlate;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof OrderPlate that)) return false;

        return getId() == that.getId() && getNumberPlate() == that.getNumberPlate() && getPlate().equals(that.getPlate()) && getOrder().equals(that.getOrder());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getPlate().hashCode();
        result = 31 * result + getNumberPlate();
        result = 31 * result + getOrder().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "OrderPlate{" +
                "id=" + id +
                ", plate=" + plate +
                ", numberPlate=" + numberPlate +
                ", order=" + order +
                '}';
    }

}
