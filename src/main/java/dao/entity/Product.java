package dao.entity;

import java.util.Objects;

public class Product {
    private int id;
    private int clas;
    private String name;
    private double weight;
    private int callories;
    private double price;

    public Product() {
    }

    public Product(int id, int clas, String name, double weight, int callories, double price) {
        this.id = id;
        this.clas = clas;
        this.name = name;
        this.weight = weight;
        this.callories = callories;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClas() {
        return clas;
    }

    public void setClas(int clas) {
        this.clas = clas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getCallories() {
        return callories;
    }

    public void setCallories(int callories) {
        this.callories = callories;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                clas == product.clas &&
                Double.compare(product.weight, weight) == 0 &&
                callories == product.callories &&
                Double.compare(product.price, price) == 0 &&
                Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clas, name, weight, callories, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", clas=" + clas +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", callories=" + callories +
                ", price=" + price +
                '}';
    }
}
