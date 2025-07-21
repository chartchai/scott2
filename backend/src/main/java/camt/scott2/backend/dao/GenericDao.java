package camt.scott2.backend.dao;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GenericDao<T, ID> {
    Mono<T> save(T entity);
    Mono<T> findById(ID id);
    Flux<T> findAll();
    Mono<Void> deleteById(ID id);
    Mono<Void> delete(T entity);
    Mono<Boolean> existsById(ID id);
    Mono<Long> count();
}