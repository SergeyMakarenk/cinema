package com.makarenko.main.servise;

/**
 * Сервис для работы с пользователем с правами MANAGER,
 * расширяет интерфейс пользователя USER
 */

public interface PersonManagerServise extends PersonUserServise{

    /**
     * Метод для редактирования даты и времени показа фильма
     */
    boolean redactMovie();

    /**
     * Метод для покупки или сдачи билетов других пользователей
     */
    boolean buyOrRecoverTicketOfUser();
}
