package com.kchmielewski.sda.java.spring01java.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Player {

    private Integer id;

    @NotEmpty(message = "{validation.player.firstName}")
    @Size(min=2,max=16)
    private String firstName;
    @NotEmpty(message = "{validation.player.lastName}")
    @Size(min=2,max = 16)
    private String lastName;

    public Player(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Player(Integer id, String firstName, String lastName) {
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

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (firstName != null ? !firstName.equals(player.firstName) : player.firstName != null) return false;
        return lastName != null ? lastName.equals(player.lastName) : player.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }
}
