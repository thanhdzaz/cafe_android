package com.exam.cafe.dto;

public class Menu {
    private int id,price;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Menu(int id, String name, int price) {
        this.id = id;
        this.price = price;
        this.name = name;
    }
}
