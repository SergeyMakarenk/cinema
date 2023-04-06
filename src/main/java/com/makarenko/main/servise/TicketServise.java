package com.makarenko.main.servise;

import com.makarenko.main.model.Person;
import com.makarenko.main.model.Ticket;

import java.util.List;

public interface TicketServise {

    /**
     * Метод по созданию билетов на фильм
     *
     */
    boolean createAllTickets(int idMovie);

    /**
     * Метод удаления всех билетов фильма по его id
     */
    boolean deleteAllTicketsByIdMovie(int idMovie);

    /**
     * Метод просмотра билетов на фильм
     */
    List<Ticket> getAllTickets(Integer idMovie);

    /**
     * Метод покупки билета пользователем
     */
    boolean buyTicket(Person person, List<Integer> idMovie);

    /**
     * Метод возврата билета пользователем
     */
    boolean recoverTicket(Integer idPerson);

    /**
     * Метод просмотра всех билетов пользователя
     */
    void showAllTicketsByIdPerson(Integer idPerson);
}
