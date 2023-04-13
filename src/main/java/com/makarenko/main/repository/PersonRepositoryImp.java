package com.makarenko.main.repository;

import com.makarenko.main.model.Person;
import com.makarenko.main.util.ConnectionManager;
import lombok.extern.slf4j.Slf4j;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static com.makarenko.main.util.Constants.*;

@Slf4j
public class PersonRepositoryImp implements PersonRepository {

    @Override
    public boolean createPerson(Person person){
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement =
                    connection.prepareStatement(PERSON_REPOSITORY_COMMAND_1);
            statement.setString(ONE, person.getUsername());
            statement.setBytes(TWO, person.getPassword());
            statement.setInt(THREE, person.getAge());
            statement.setString(FOUR, person.getRole());
            statement.setBytes(FIVE, person.getSalt());
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            log.warn(LOG_CREATE_PERSON_ERROR + person.getUsername() + SPACING + e);
            return false;
        }
    }

    @Override
    public List<Person> getAllPersons() {
        List<Person> persons = new ArrayList<>();
        try(Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement(PERSON_REPOSITORY_COMMAND_2);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt(MOVE_REPOSITORY_ID);
                String username = resultSet.getString(PERSON_REPOSITORY_NAME);
                byte[] password = resultSet.getBytes(PERSON_REPOSITORY_PASSWORD);
                int age = resultSet.getInt(PERSON_REPOSITORY_AGE);
                String role = resultSet.getString(PERSON_REPOSITORY_ROLE);
                byte[] salt = resultSet.getBytes(PERSON_REPOSITORY_SALT);
                Person person = new Person(id, username, password, age, role, salt);
                persons.add(person);
            }
        } catch (SQLException e) {
            log.warn(LOG_LIST_PERSON_ERROR + SPACING + e);
            throw new RuntimeException(e);
        }
        return persons;
    }

    @Override
    public Person getPersonByUsername(String username) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement(PERSON_REPOSITORY_COMMAND_3);
            statement.setString(ONE, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                int id = resultSet.getInt(MOVE_REPOSITORY_ID);
                byte[] password = resultSet.getBytes(PERSON_REPOSITORY_PASSWORD);
                int age = resultSet.getInt(PERSON_REPOSITORY_AGE);
                String role = resultSet.getString(PERSON_REPOSITORY_ROLE);
                byte[] salt = resultSet.getBytes(PERSON_REPOSITORY_SALT);
                return new Person(id, username, password, age, role, salt);
            }
        } catch (SQLException e) {
            log.warn(LOG_GET_PERSON_ERROR + username + SPACING + e);
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean updatePerson(Person person, Integer id) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement =
                    connection.prepareStatement(PERSON_REPOSITORY_COMMAND_4);
            statement.setString(ONE, person.getUsername());
            statement.setBytes(TWO, person.getPassword());
            statement.setInt(THREE, person.getAge());
            statement.setString(FOUR, person.getRole());
            statement.setBytes(FIVE, person.getSalt());
            statement.setInt(SIX, id);
            statement.execute();
            return true;
        } catch (SQLException e) {
            log.warn(LOG_ROLE_PERSON + person.getUsername() + LOG_NOT_UPDATE + e);
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deletePersonById(int id) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement = connection.prepareStatement(PERSON_REPOSITORY_COMMAND_5);
            statement.setInt(ONE, id);
            statement.execute();
            return true;
        } catch (SQLException e) {
            log.warn(LOG_USER + LOG_ID_PERSON + id + LOG_NOT_DELETE+ e);
            e.printStackTrace();
        }
        return false;
    }
}
