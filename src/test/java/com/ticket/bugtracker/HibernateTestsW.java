package com.ticket.bugtracker;

import com.ticket.bugtracker.entity.Employee;
import com.ticket.bugtracker.entity.Ticket;
import com.ticket.bugtracker.repo.EmployeeDAO;
import com.ticket.bugtracker.repo.TicketDAO;
import com.ticket.bugtracker.service.TicketServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Autowired
    private TicketServiceImpl ticketService;

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



    /*
    Tickets:
    - Search wildcards with one letter min in the beginning ***
    - Search by date ***
    - Search by Employee name ***
    - Search by Manager name
    - Search by Department
    - Search by Category
    - Search by open/closed ***

     */





    @Test
    public void testFourthPrint() {
        List<Ticket> tickets = ticketService.findTicketsByEmplFirstName("Mohamed");
        tickets.forEach(t-> {
            Employee e = t.getEmployee();
            System.out.println("NAME: " + e.getFirstName()+ " - ID: " + e.getID() + " - TICKET: "
            + t.getTitle());
        });
    }

    @Test
    public void testDateTicket() throws ParseException {
        Date from = new SimpleDateFormat("yyyy-MM-dd").parse("2021-11-19");
        Date to = new SimpleDateFormat("yyyy-MM-dd").parse("2021-11-20");
        List<Ticket> tickets = ticketService.findTicketsByDate(from, to);
        tickets.forEach(t-> {
            System.out.println(t.getTitle());
        });

    }

    @Test
    public void testTicketWILDCARD() {
        String wc = "My";
        List<Ticket> tickets = ticketService.findTicketByWildCard(wc);
        tickets.forEach(t-> {
            System.out.println(t.getTitle());
        });

    }

    @Test
    public void testTicketDefault() {
        List<Ticket> tickets = ticketService.findTicketsDefault(2,2);
        tickets.forEach(t-> {
            System.out.println(t.getId());
        });

    }
    @Test
    public void testOpenClosedTickets() {
        List<Ticket> tickets = ticketService.findOpenTicketsOrClosed(false);
        System.out.println(tickets.size());
        tickets.forEach(t-> {
            System.out.println("ID IS ---->  "+t.getId());
        });
    }

    @Test
    public void testCategory() {
        List<Ticket> tickets = ticketService.findTicketsByCategory("dog");
        tickets.forEach(t-> {
            System.out.println("ID IS ---->  "+t.getId());
        });
    }



}
