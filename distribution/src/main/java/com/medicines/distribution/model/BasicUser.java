package com.medicines.distribution.model;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "basic_user")
public class BasicUser{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "profession", nullable = false)
    private String profession;

    @Column(name = "penalty", nullable = false)
    private Integer penalty;

    public BasicUser() {
        super();
    }

    public BasicUser(User user, String name, String surname, Address address, String phone, String profession, Integer penalty) {
        this.user = user;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.profession = profession;
        this.penalty = penalty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getPenalty() {
        return penalty;
    }

    public void setPenalty(Integer penalty) {
        this.penalty = penalty;
    }
}
