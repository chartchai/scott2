package camt.scott2.backend.dao.impl;

import camt.scott2.backend.dao.PersonDao;
import camt.scott2.backend.entity.Person;
import camt.scott2.backend.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PersonDaoImpl implements PersonDao {
    
    private final PersonRepository personRepository;

    @Override
    public Person save(Person entity) {
        return personRepository.save(entity);
    }

    @Override
    public Optional<Person> findById(String id) {
        return personRepository.findById(id);
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        personRepository.deleteById(id);
    }

    @Override
    public void delete(Person entity) {
        personRepository.delete(entity);
    }

    @Override
    public boolean existsById(String id) {
        return personRepository.existsById(id);
    }

    @Override
    public long count() {
        return personRepository.count();
    }

    @Override
    public Optional<Person> findByEmail(String email) {
        return personRepository.findByEmail(email);
    }

    @Override
    public Optional<Person> findByStudentId(String studentId) {
        return personRepository.findByStudentId(studentId);
    }

    @Override
    public List<Person> findByFirstnameContainingIgnoreCase(String firstname) {
        return personRepository.findByFirstnameContainingIgnoreCase(firstname);
    }

    @Override
    public List<Person> findByLastnameContainingIgnoreCase(String lastname) {
        return personRepository.findByLastnameContainingIgnoreCase(lastname);
    }
}