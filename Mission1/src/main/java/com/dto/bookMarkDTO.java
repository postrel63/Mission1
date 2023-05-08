package com.dto;

import java.util.Date;

public class bookMarkDTO {
    private int id;
    private String name;
    private String wifiName;
    private String addDate;


    public bookMarkDTO(){};

    public bookMarkDTO(int id, String name, String wifiName, String addDate) {
        this.id = id;
        this.name = name;
        this.wifiName = wifiName;
        this.addDate = addDate;
    }

    @Override
    public String toString() {
        return "bookMarkDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", wifiName='" + wifiName + '\'' +
                ", addDate='" + addDate + '\'' +
                '}';
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

    public String getWifiName() {
        return wifiName;
    }

    public void setWifiName(String wifiName) {
        this.wifiName = wifiName;
    }

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }
}
