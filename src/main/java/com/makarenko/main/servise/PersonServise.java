package com.makarenko.main.servise;

import com.makarenko.main.model.Person;

/**
 * Общий сервис для работы с пользователем
 */
public interface PersonServise {

    /**
     * Метод для сохранения пользователя в базу данных
     */
    Person createPerson();

    /**
     * Метод для получения пользователя из базы данных по логину
     */
    Person getPerson();

}
