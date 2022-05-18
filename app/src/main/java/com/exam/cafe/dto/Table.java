package com.exam.cafe.dto;

public class Table {
    private int id, num, floor;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Table(int id, int num, int floor, String status) {
        this.id = id;
        this.num = num;
        this.floor = floor;
        this.status = status;
    }
}
