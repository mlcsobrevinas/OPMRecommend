package com.uplb.mlcsobrevinas.OPMrecommender.Model;

public class Song {

    private String Name, Image, MenuId, Description;

    public Song() {

    }

    public Song(String name, String image, String menuId, String description) {
        Name = name;
        Image = image;
        MenuId = menuId;
        Description = description;
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

    public String getMenuId() {
        return MenuId;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
