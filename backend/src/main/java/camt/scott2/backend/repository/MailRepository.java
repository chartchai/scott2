package camt.scott2.backend.repository;

import camt.scott2.backend.entity.Mail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MailRepository extends MongoRepository<Mail, String> {
    List<Mail> findByFrom(String from);
    List<Mail> findByRecipientsContaining(String recipient);
    List<Mail> findBySubjectContaining(String subject);
}