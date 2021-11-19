package com.ticket.bugtracker.repo;

import com.ticket.bugtracker.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{



    @PersistenceContext
    private EntityManager em;



    @Override
    public Integer findEmployeeIdByName(String name) {

        String hql = "FROM Employee E where E.firstName = :name";
        Session session = em.unwrap(Session.class);
        Query query = session.createQuery(hql);
        query.setParameter("name", name);
        Employee employee = (Employee) query.uniqueResult();

        return employee.getID();
    }

}
