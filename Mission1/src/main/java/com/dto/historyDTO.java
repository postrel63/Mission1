package com.dto;

public class historyDTO {

    private int id;
    private String x;
    private String y;
    private String date;

    public historyDTO(){}
    public historyDTO(int id, String x, String y, String date) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "historyDTO{" +
                "id=" + id +
                ", x='" + x + '\'' +
                ", y='" + y + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
