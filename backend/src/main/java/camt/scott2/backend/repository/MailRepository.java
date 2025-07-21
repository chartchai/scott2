package camt.scott2.backend.repository;

import camt.scott2.backend.entity.Mail;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface MailRepository extends ReactiveMongoRepository<Mail, String> {
    Flux<Mail> findByFrom(String from);
    Flux<Mail> findByRecipientsContaining(String recipient);
    Flux<Mail> findBySubjectContaining(String subject);
}