package com.medicines.distribution.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Equipment> equipments = new HashSet<Equipment>();

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Appointment> appointments = new HashSet<Appointment>();

    @Column(name = "rating", nullable = false)
    private double rating;

    public Company() {
        super();
    }

    public Company(Integer id, String name, String description, Address address, double rating) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.rating = rating;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setEquipments(Set<Equipment> equipments) {
        this.equipments = equipments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Address getAddress() {
        return address;
    }

    public Set<Equipment> getEquipments() {
        return equipments;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public double getRating() {
        return rating;
    }

    public void addEquipment(Equipment equipment){
        equipments.add(equipment);
        equipment.setCompany(this);
    }

    public void removeEquipment(Equipment equipment){
        equipments.remove(equipment);
        equipment.setCompany(null);
    }

    public void addAppointment(Appointment appointment){
        appointments.add(appointment);
        appointment.setCompany(this);
    }

    public void removeAppointment(Appointment appointment){
        appointments.remove(appointment);
        appointment.setCompany(null);
    }

}
