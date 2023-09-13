package models.entities;

public class Order implements Comparable<Order> {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Order order) {
            return this.id == order.id;
        }
        return false;
    }

    @Override
    public int compareTo(Order order) {
        return this.id - order.id;
    }
}
