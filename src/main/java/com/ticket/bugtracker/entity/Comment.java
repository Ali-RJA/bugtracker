package com.ticket.bugtracker.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comment_table")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Temporal(TemporalType.DATE)
    @Column(name = "comment_date")
    private Date date;

    @Column(name = "comment")
    @Lob
    private String comment;


    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    @JsonManagedReference
    private Ticket ticket;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonManagedReference
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    @JsonManagedReference
    private Manager manager;


}
