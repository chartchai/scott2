package camt.scott2.backend.dao;

import camt.scott2.backend.entity.Mail;
import reactor.core.publisher.Flux;

public interface MailDao extends GenericDao<Mail, String> {
    Flux<Mail> findByFrom(String from);
    Flux<Mail> findByRecipientsContaining(String recipient);
    Flux<Mail> findBySubjectContaining(String subject);
}