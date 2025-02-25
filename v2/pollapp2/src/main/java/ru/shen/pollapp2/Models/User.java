package ru.shen.pollapp2.Models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Users")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    public User(){}

    public User(String login, String name){
        this.login = login;
        this.username = name;
    }

    public Long getId(){return id;}
    
    public String getLogin(){return login;}
    public void setLogin(String login){this.login = login;}

    public void setusername(String name){this.username = name;}
    public void setPassword(String password){this.password = password;}

    public String getUsername(){return username;}
    public String getPassword(){return password;}


}
