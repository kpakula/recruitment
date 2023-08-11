package com.kpakula.recruitment.repository;

import com.kpakula.recruitment.model.Contact;
import com.kpakula.recruitment.model.ContactType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> findByUserIdAndType(Long userId, ContactType type);
}
