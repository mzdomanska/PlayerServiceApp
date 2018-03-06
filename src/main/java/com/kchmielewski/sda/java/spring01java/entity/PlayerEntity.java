package com.kchmielewski.sda.java.spring01java.entity;

import javax.persistence.*;

@Entity
@Table(name = "player", catalog = "postgresql")
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;

    public PlayerEntity() {}

    public PlayerEntity(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public PlayerEntity(Integer id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
