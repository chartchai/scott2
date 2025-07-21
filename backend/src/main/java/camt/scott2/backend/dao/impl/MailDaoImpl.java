package camt.scott2.backend.dao.impl;

import camt.scott2.backend.dao.MailDao;
import camt.scott2.backend.entity.Mail;
import camt.scott2.backend.repository.MailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class MailDaoImpl implements MailDao {
    
    private final MailRepository mailRepository;

    @Override
    public Mono<Mail> save(Mail entity) {
        return mailRepository.save(entity);
    }

    @Override
    public Mono<Mail> findById(String id) {
        return mailRepository.findById(id);
    }

    @Override
    public Flux<Mail> findAll() {
        return mailRepository.findAll();
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return mailRepository.deleteById(id);
    }

    @Override
    public Mono<Void> delete(Mail entity) {
        return mailRepository.delete(entity);
    }

    @Override
    public Mono<Boolean> existsById(String id) {
        return mailRepository.existsById(id);
    }

    @Override
    public Mono<Long> count() {
        return mailRepository.count();
    }

    @Override
    public Flux<Mail> findByFrom(String from) {
        return mailRepository.findByFrom(from);
    }

    @Override
    public Flux<Mail> findByRecipientsContaining(String recipient) {
        return mailRepository.findByRecipientsContaining(recipient);
    }

    @Override
    public Flux<Mail> findBySubjectContaining(String subject) {
        return mailRepository.findBySubjectContaining(subject);
    }
}