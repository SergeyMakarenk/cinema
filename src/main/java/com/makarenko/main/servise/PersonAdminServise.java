package com.makarenko.main.servise;

/**
 * Сервис для работы с пользователем с правами MANAGER
 * расширяет интерфейс пользователя USER и MANAGER
 */

public interface PersonAdminServise extends PersonManagerServise{

    /**
     * Метод для удаления или изменения уровеня доступа других пользователей
     */
    void deleteRedactPerson();

    /**
     * Метод для удаления фильма с показа или добавления фильма в показ
     */
    void deleteCreateMovie();
}
