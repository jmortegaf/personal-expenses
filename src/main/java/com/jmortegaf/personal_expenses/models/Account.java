package com.jmortegaf.personal_expenses.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountName;
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;



    public Account(String accountName, User user){
        this.accountName=accountName;
        this.user=user;
        this.active=false;
    }

}
