package com.tonandquangdz.tqmallmobile.Models;

public class Brand {
    public int ID;
    public String Name;
    public String Image;
    public boolean Status;

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
