package camt.scott2.backend.repository;

import camt.scott2.backend.entity.Person;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface PersonRepository extends ReactiveMongoRepository<Person, String> {
    Mono<Person> findByEmail(String email);
    Mono<Person> findByStudentId(String studentId);
    Flux<Person> findByFirstnameContainingIgnoreCase(String firstname);
    Flux<Person> findByLastnameContainingIgnoreCase(String lastname);
}