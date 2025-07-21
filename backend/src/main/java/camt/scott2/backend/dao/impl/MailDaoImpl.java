package camt.scott2.backend.dao.impl;

import camt.scott2.backend.dao.MailDao;
import camt.scott2.backend.entity.Mail;
import camt.scott2.backend.repository.MailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MailDaoImpl implements MailDao {
    
    private final MailRepository mailRepository;

    @Override
    public Mail save(Mail entity) {
        return mailRepository.save(entity);
    }

    @Override
    public Optional<Mail> findById(String id) {
        return mailRepository.findById(id);
    }

    @Override
    public List<Mail> findAll() {
        return mailRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        mailRepository.deleteById(id);
    }

    @Override
    public void delete(Mail entity) {
        mailRepository.delete(entity);
    }

    @Override
    public boolean existsById(String id) {
        return mailRepository.existsById(id);
    }

    @Override
    public long count() {
        return mailRepository.count();
    }

    @Override
    public List<Mail> findByFrom(String from) {
        return mailRepository.findByFrom(from);
    }

    @Override
    public List<Mail> findByRecipientsContaining(String recipient) {
        return mailRepository.findByRecipientsContaining(recipient);
    }

    @Override
    public List<Mail> findBySubjectContaining(String subject) {
        return mailRepository.findBySubjectContaining(subject);
    }
}