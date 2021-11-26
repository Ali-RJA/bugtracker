package com.ticket.bugtracker.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Department")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonIgnoreProperties({ "employeeList", "tickets" })
public class Department {

    public Department(String deptName) {
        this.deptName = deptName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @Column(name = "dept_name", nullable = false)
    private String deptName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    @JsonBackReference
    private Set<Employee> employeeList = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "manager_id", unique = true)
    @JsonBackReference
    private Manager manager;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    @JsonBackReference
    private Set<Ticket> tickets = new HashSet<>();


}
