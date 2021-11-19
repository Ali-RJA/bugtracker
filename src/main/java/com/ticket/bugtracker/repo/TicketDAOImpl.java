package com.ticket.bugtracker.repo;

import com.ticket.bugtracker.entity.Ticket;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class TicketDAOImpl implements TicketDAO{

    @PersistenceContext
    private EntityManager em;

    public EmployeeDAO getEmployeeDAO() {
        return employeeDAO;
    }

    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Autowired
    EmployeeDAO employeeDAO;

    @Override
    public List<Ticket> findTicketByEmployeeId(Integer id) {

        String q = "FROM ticket_table E where E.employee="+id;
        Session session = em.unwrap(Session.class);
        Query query = session.createQuery(q);
        List<Ticket> list = query.getResultList();
        return list;
    }

    @Override
    public List<Ticket> findTicketByName(String[] names) {
        String q = "FROM ticket_table E where E.=";
        Session session = em.unwrap(Session.class);
        Query query = session.createQuery(q);
        List<Ticket> list = query.getResultList();
        return list;
    }

    @Override
    public List<Ticket> findTicketByName(String name) {

        Integer id = employeeDAO.findEmployeeIdByName(name);
        List<Ticket> tickets = findTicketByEmployeeId(id);
        return tickets;
    }
}
