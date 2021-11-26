package com.ticket.bugtracker.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Manager")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Manager extends Person {

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "manager")
    @JsonBackReference
    private Set<Employee> employeeSet = new HashSet<>();


    @OneToOne(cascade = CascadeType.ALL, mappedBy = "manager", optional = true) // change to false optional
    private Department department;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "manager")
    @JsonBackReference
    private Set<Ticket> tickets = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "manager")
    @JsonBackReference
    private Set<Comment> comments;

    public void addTicket(Ticket ticket) {
        if (ticket != null) {
            tickets.add(ticket);
        }
    }

}
