package com.makarenko.main.servise;

import com.makarenko.main.model.Person;

/**
 * Сервис для работы с пользователем с правами USER
 */

public interface PersonUserServise {

    /**
     * Метод для просматра мероприятия (фильмы)
     */
    boolean viewMovie();

    /**
     * Метод по покупке и возврату билетов
     */
    void buyOrRecoverTicket(Person person);

    /**
     * Метод просматра купленных билетов
     */
    void viewTickets(Person person);

}
