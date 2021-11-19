package com.ticket.bugtracker.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "ticket_table")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "ticket_title")
    private String title;

    @Temporal(TemporalType.DATE)
    @Column(name = "ticket_date")
    private Date date;


    @Column(name = "ticket_description")
    @Lob
    private String description;

    @Column(name="open")
    @Type(type= "org.hibernate.type.NumericBooleanType")
    private Boolean open=true;

    @ManyToOne
    @JoinColumn(name = "employee_closer", nullable = true)
    private Employee employeeCloser;

    @ManyToOne
    @JoinColumn(name = "manager_closer", nullable = true)
    private Manager managerCloser;

    @Column(name = "ticket_priority")
    @Min(1)
    @Max(10)
    private int priority;

    @Column(name = "ticket_category")
    private String category = "";

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn()
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "ticket")
    private Set<Comment> comments = new HashSet<>();


}
