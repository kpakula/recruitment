package com.kpakula.recruitment.repository;

import com.kpakula.recruitment.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> { }
