package com.ticket.bugtracker.repo;

import com.ticket.bugtracker.entity.Ticket;

public class TicketChecks {

    public boolean ticketBothEmpty(Ticket ticket) {
        if ((ticket.getEmployee() == null && ticket.getManager() == null)) {
            System.out.println("Ticket must assign either" +
                    " an employee or a manager!");
            return true;
        }

        return false;


    }

    public boolean ticketBothTaken(Ticket ticket) {
        if ((ticket.getEmployee() != null && ticket.getManager() != null)) {
            System.out.println("Ticket can't be assigned to both" +
                    " a manager and an employee! ");
            return true;
        }
        return false;


    }

    public boolean ticketBothClosed(Ticket ticket) {
        if (ticket.getEmployeeCloser() != null &&
                ticket.getManagerCloser() != null) {
            System.out.println("Ticket can't have 2 closers!");
            return true;
        }
        return false;
    }


}
