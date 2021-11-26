package com.ticket.bugtracker.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "Employee")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Employee extends Person {

    @ManyToOne
    @JoinColumn(name = "manager_id")
    @JsonManagedReference
    private Manager manager;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = true) // change to false later
    private Department department;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "employee")
    @JsonBackReference
    private Set<Ticket> tickets = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "employee")
    @JsonBackReference
    private Set<Comment> comments = new HashSet<>();

    public void addTicket(Ticket ticket) {
        if (ticket != null) {
            tickets.add(ticket);
        }
    }

}
