package com.ticket.bugtracker.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
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
    @JsonManagedReference
    private Employee employeeCloser;

    @ManyToOne
    @JoinColumn(name = "manager_closer", nullable = true)
    @JsonManagedReference
    private Manager managerCloser;

    @Column(name = "ticket_priority")
    @Min(1)
    @Max(10)
    private int priority;

    @Column(name = "ticket_category")
    private String category = "";

    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonManagedReference
    @JsonIgnoreProperties({"id"})
    private Department department;

    @ManyToOne
    @JoinColumn()
    @JsonManagedReference // includes this object
    @JsonIgnoreProperties({"department","manager"})
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    @JsonManagedReference
    @JsonIgnoreProperties({"department"}) // ignores specific properties of this object
    private Manager manager;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "ticket")
    @JsonBackReference // ignores this object completely
    private Set<Comment> comments = new HashSet<>();


}
