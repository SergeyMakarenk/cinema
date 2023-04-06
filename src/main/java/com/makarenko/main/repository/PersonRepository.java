package com.makarenko.main.repository;

import com.makarenko.main.model.Person;
import java.util.List;

public interface PersonRepository {
    boolean createPerson(Person person);
    List<Person> getAllPersons();
    Person getPersonByUsername(String username);
    boolean updatePerson(Person person, Integer id);
    boolean deletePersonById(int id);
}
