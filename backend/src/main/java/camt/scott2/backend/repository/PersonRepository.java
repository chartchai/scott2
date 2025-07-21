package camt.scott2.backend.repository;

import camt.scott2.backend.entity.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends MongoRepository<Person, String> {
    Optional<Person> findByEmail(String email);
    Optional<Person> findByStudentId(String studentId);
    List<Person> findByFirstnameContainingIgnoreCase(String firstname);
    List<Person> findByLastnameContainingIgnoreCase(String lastname);
}