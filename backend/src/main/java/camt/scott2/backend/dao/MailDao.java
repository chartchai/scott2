package camt.scott2.backend.dao;

import camt.scott2.backend.entity.Mail;

import java.util.List;

public interface MailDao extends GenericDao<Mail, String> {
    List<Mail> findByFrom(String from);
    List<Mail> findByRecipientsContaining(String recipient);
    List<Mail> findBySubjectContaining(String subject);
}