package camt.scott2.backend.dao;

import camt.scott2.backend.entity.Person;

import java.util.List;
import java.util.Optional;

public interface PersonDao extends GenericDao<Person, String> {
    Optional<Person> findByEmail(String email);
    Optional<Person> findByStudentId(String studentId);
    List<Person> findByFirstnameContainingIgnoreCase(String firstname);
    List<Person> findByLastnameContainingIgnoreCase(String lastname);
}