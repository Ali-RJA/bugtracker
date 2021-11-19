package com.ticket.bugtracker.repo;

import com.ticket.bugtracker.entity.Employee;
import com.ticket.bugtracker.entity.Ticket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Repository
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TicketDAOImpl implements TicketDAO{

    @PersistenceContext
    private EntityManager em;


    @Override
    public List<Ticket> findTicketsByEmployeeIds(List<Employee> employees) {
        String q = "FROM ticket_table E where E.employee in :ids";
        Session session = em.unwrap(Session.class);
        Query query = session.createQuery(q);
        query.setParameter("ids", employees);
        List<Ticket> list = query.getResultList();
        return list;
    }

    @Override
    public List<Ticket> findTicketsByDate(Date from, Date to) {
        String q = "FROM ticket_table E where E.date between :from AND :to";
        Session session = em.unwrap(Session.class);
        Query query = session.createQuery(q);
        query.setParameter("from", from);
        query.setParameter("to", to);
        List<Ticket> list = query.getResultList();
        return list;
    }

    @Override
    public List<Ticket> findTicketByWildCard(String wc) {
        String q = "FROM ticket_table E where E.title like '" + wc+"%'";
        Session session = em.unwrap(Session.class);
        Query query = session.createQuery(q);
        List<Ticket> list = query.getResultList();
        return list;
    }

    @Override
    public List<Ticket> findTicketsDefault(int start, int max) {
        String q = "FROM ticket_table E";
        Session session = em.unwrap(Session.class);
        Query query = session.createQuery(q);
        query.setFirstResult(start);
        query.setMaxResults(max);
        List<Ticket> list = query.getResultList();
        return list;
    }

    @Override
    public List<Ticket> findOpenTicketsOrClosed(boolean open) {
        String q = "FROM ticket_table E where E.open is " + open;
        Session session = em.unwrap(Session.class);
        Query query = session.createQuery(q);
        query.setMaxResults(50);
        List<Ticket> list = query.getResultList();
        return list;
    }

    @Override
    public List<Ticket> findTicketsByCategory(String category) {
        String q = "FROM ticket_table where ticket_table.category = " + category;
        Session session = em.unwrap(Session.class);
        Query query = session.createQuery(q);
        query.setMaxResults(50);
        List<Ticket> list = query.getResultList();
        return list;
    }

}
