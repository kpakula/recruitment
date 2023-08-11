package com.kpakula.recruitment.repository;

import com.kpakula.recruitment.model.UserDump;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserDumpRepository extends JpaRepository<UserDump, UUID> { }
