package com.ticket.bugtracker.entity;


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
    private Set<Employee> employeeList = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "manager_id", unique = true)
    private Manager manager;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    private Set<Ticket> tickets = new HashSet<>();


}
