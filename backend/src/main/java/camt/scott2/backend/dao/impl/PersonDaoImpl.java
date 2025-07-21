package camt.scott2.backend.dao.impl;

import camt.scott2.backend.dao.PersonDao;
import camt.scott2.backend.entity.Person;
import camt.scott2.backend.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class PersonDaoImpl implements PersonDao {
    
    private final PersonRepository personRepository;

    @Override
    public Mono<Person> save(Person entity) {
        return personRepository.save(entity);
    }

    @Override
    public Mono<Person> findById(String id) {
        return personRepository.findById(id);
    }

    @Override
    public Flux<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return personRepository.deleteById(id);
    }

    @Override
    public Mono<Void> delete(Person entity) {
        return personRepository.delete(entity);
    }

    @Override
    public Mono<Boolean> existsById(String id) {
        return personRepository.existsById(id);
    }

    @Override
    public Mono<Long> count() {
        return personRepository.count();
    }

    @Override
    public Mono<Person> findByEmail(String email) {
        return personRepository.findByEmail(email);
    }

    @Override
    public Mono<Person> findByStudentId(String studentId) {
        return personRepository.findByStudentId(studentId);
    }

    @Override
    public Flux<Person> findByFirstnameContainingIgnoreCase(String firstname) {
        return personRepository.findByFirstnameContainingIgnoreCase(firstname);
    }

    @Override
    public Flux<Person> findByLastnameContainingIgnoreCase(String lastname) {
        return personRepository.findByLastnameContainingIgnoreCase(lastname);
    }
}