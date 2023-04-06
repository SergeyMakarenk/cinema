package com.makarenko.main.repository;

import com.makarenko.main.model.Ticket;
import java.util.List;

public interface TicketRepository {
    void createAllTickets(List<Ticket> tickets);
    boolean deleteAllTicketsByIdMovie(int idMovie);
    List<Ticket> getAllTicketsByIdMovie(int idMovie);
    List<Ticket> getAllTicketsByIdPerson(Integer idPerson);
    boolean buyTicket(int idMovie, int idPerson, int place);
    boolean recoverTicketByIdTicket(Integer idTicket);
}
