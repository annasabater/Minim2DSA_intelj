package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

public class User {
    String idUser;
    String name;
    String surname;
    String email;
    String password;

    public User() {};
    public User(String name, String surname, String email, String password){
        this.idUser = RandomUtils.getId();
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }
    public String getIdUser(){return idUser;}
    public void setIdUser(String idUser){this.idUser = idUser;}
    public String getName(){return name;}
    public void setName(String name){this.name = name;}
    public String getSurname(){return surname;}
    public void setSurname(String name){this.surname = surname;}
    public String getEmail(){return email;}
    public void setEmail(String email){this.email = email;}
    public String getPassword(){return password;}
    public void setPassword(String password){this.password = password;}


}
