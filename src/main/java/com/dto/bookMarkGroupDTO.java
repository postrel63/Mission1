package com.dto;

import java.util.Date;

public class bookMarkGroupDTO {
    private int id;
    private String name;
    private int sequence;
    private String addDate;
    private String modifyDate;

    public bookMarkGroupDTO(){};

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

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public bookMarkGroupDTO(int id, String name, int sequence, String addDate, String modifyDate) {
        this.id = id;
        this.name = name;
        this.sequence = sequence;
        this.addDate = addDate;
        this.modifyDate = modifyDate;
    }

    public bookMarkGroupDTO(int id, String name){
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "bookMarkGroupDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sequence=" + sequence +
                ", addDate='" + addDate + '\'' +
                ", modifyDate='" + modifyDate + '\'' +
                '}';
    }
}
