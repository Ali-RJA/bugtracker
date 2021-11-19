package com.ticket.bugtracker;

import com.ticket.bugtracker.entity.Employee;
import com.ticket.bugtracker.entity.Ticket;
import com.ticket.bugtracker.repo.EmployeeDAO;
import com.ticket.bugtracker.repo.TicketDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@SpringBootTest
public class HibernateTestsW {
//    private EntityManagerFactory entityManagerFactory;
//
//    @Autowired
//    public HibernateTestsW(EntityManagerFactory entityManagerFactory) {
//        this.entityManagerFactory = entityManagerFactory;
//    }
//

    public EmployeeDAO getEmployeeDAO() {
        return employeeDAO;
    }

    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Autowired
    private EmployeeDAO employeeDAO;


    public TicketDAO getTicketDAO() {
        return ticketDAO;
    }

    public void setTicketDAO(TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }

    @Autowired
    private TicketDAO ticketDAO;

@Autowired
EntityManager entityManager;
    @Test
    public void testSecondPrint() {
        List<Ticket> list = ticketDAO.findTicketByEmployeeId(3);
        list.forEach(e -> {
            System.out.println(e.getTitle());
        });
    }

    @Test
    public void testThirdPrint() {
            List<Ticket> tickets = ticketDAO.findTicketByName("Mohamed");
            tickets.forEach(e -> {
                System.out.println(e.getTitle());
            });

    }





}
