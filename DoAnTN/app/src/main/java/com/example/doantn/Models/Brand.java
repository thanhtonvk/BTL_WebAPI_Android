package com.example.doantn.Models;

public class Brand {
    private int ID;
    private String Name;
    private String Image;
    private boolean Status;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public Brand(int ID, String name, String image, boolean status) {
        this.ID = ID;
        Name = name;
        Image = image;
        Status = status;
    }
}
