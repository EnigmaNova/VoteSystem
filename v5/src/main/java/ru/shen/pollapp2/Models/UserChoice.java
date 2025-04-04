package ru.shen.pollapp2.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "userchoices", uniqueConstraints = @UniqueConstraint(columnNames = { "user_id", "container_id" }))
public class UserChoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "container_id", nullable = false)
    private Container container;

    @ManyToOne
    @JoinColumn(name = "choice", nullable = false)
    private Answer answer;

    public UserChoice(){}

    public UserChoice(User user, Container container, Answer answer){
        this.user = user;
        this.container = container;
        this.answer = answer;
    }

    public Long getId(){return id;}
    
    public User getUser(){return user;}
    public void setUser(User user){this.user = user;}

    public Container getContainer(){return container;}
    public void setContainer(Container container){this.container = container;}

    public Answer getAnswer(){return answer;}
    public void setAnswer(Answer answer){this.answer = answer;}
}
