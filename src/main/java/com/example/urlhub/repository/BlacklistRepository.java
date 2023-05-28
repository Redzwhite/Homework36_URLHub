package com.example.urlhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.urlhub.model.Blacklist;

public interface BlacklistRepository extends JpaRepository<Blacklist, Long> {
    Blacklist findByDomain(String domain);
}

