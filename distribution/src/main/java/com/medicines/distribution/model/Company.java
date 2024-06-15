package com.medicines.distribution.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.*;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Equipment> equipments = new HashSet<Equipment>();

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Appointment> appointments = new HashSet<Appointment>();

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<CompanyAdmin> companyAdmins = new HashSet<>();

    @OneToMany(mappedBy = "reportedCompany", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Report> reports = new ArrayList<>();

    @Column(name = "rating", nullable = false)
    private double rating;

    @Column(name = "working_hours_begin")
    private LocalTime workingHoursStart;

    @Column(name = "working_hours_end")
    private LocalTime workingHoursEnd;

    public Company() {
        super();
    }

    public Company(Integer id, String name, String description, Address address, double rating, LocalTime workingHoursStart, LocalTime workingHoursEnd) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.rating = rating;
        this.workingHoursStart = workingHoursStart;
        this.workingHoursEnd = workingHoursEnd;
        this.reports=new ArrayList<>();
    }



    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
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

    public Set<CompanyAdmin> getCompanyAdmins() {
        return companyAdmins;
    }

    public void setCompanyAdmins(Set<CompanyAdmin> companyAdmins) {
        this.companyAdmins = companyAdmins;
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

    public LocalTime getWorkingHoursStart() {
        return workingHoursStart;
    }

    public void setWorkingHoursStart(LocalTime workingHoursStart) {
        this.workingHoursStart = workingHoursStart;
    }

    public LocalTime getWorkingHoursEnd() {
        return workingHoursEnd;
    }

    public void setWorkingHoursEnd(LocalTime workingHoursEnd) {
        this.workingHoursEnd = workingHoursEnd;
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
