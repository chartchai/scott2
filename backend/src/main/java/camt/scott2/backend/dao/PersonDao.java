package camt.scott2.backend.dao;

import camt.scott2.backend.entity.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonDao extends GenericDao<Person, String> {
    Mono<Person> findByEmail(String email);
    Mono<Person> findByStudentId(String studentId);
    Flux<Person> findByFirstnameContainingIgnoreCase(String firstname);
    Flux<Person> findByLastnameContainingIgnoreCase(String lastname);
}