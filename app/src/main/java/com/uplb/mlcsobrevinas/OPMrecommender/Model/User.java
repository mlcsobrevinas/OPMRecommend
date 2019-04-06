package com.uplb.mlcsobrevinas.OPMrecommender.Model;

public class User {
    private String Name;
    private String Password;
    private Integer Extro;
    private Integer Agre;
    private Integer Cons;
    private Integer Neuro;
    private Integer Openn;
    private String Phone;

    public User() {
    }

    public User(String name, String password, Integer extro, Integer agre, Integer cons, Integer neuro, Integer openn) {
        Name = name;
        Password = password;
        Extro = extro;
        Agre = agre;
        Cons = cons;
        Neuro = neuro;
        Openn = openn;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Integer getExtro() {
        return Extro;
    }

    public void setExtro(Integer extro) {
        Extro = extro;
    }

    public Integer getAgre() {
        return Agre;
    }

    public void setAgre(Integer agre) {
        Agre = agre;
    }

    public Integer getCons() {
        return Cons;
    }

    public void setCons(Integer cons) {
        Cons = cons;
    }

    public Integer getNeuro() {
        return Neuro;
    }

    public void setNeuro(Integer neuro) {
        Neuro = neuro;
    }

    public Integer getOpenn() {
        return Openn;
    }

    public void setOpenn(Integer openn) {
        Openn = openn;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
