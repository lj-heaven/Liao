package com.example.base.entity;

public class BookDemo {
    private String name;
    private int price;
    public BookDemo(String name, int price) {
        this.name = name;
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", price=" + price +
                "}";
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        final BookDemo bookDemo = (BookDemo) obj;
        if (this == bookDemo) {
            return true;
        } else {
            return (this.name.equals(bookDemo.name) && this.price == bookDemo.price);
        }
    }
    @Override
    public int hashCode() {
        int hashno = 7;
        hashno = 13 * hashno + (name == null ? 0 : name.hashCode());
        hashno = 13 * hashno + price;
        return hashno;
//        return Objects.hash(name,price);
    }
}

