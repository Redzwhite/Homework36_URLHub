package com.example.urlhub.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.urlhub.model.Link;
import java.util.List;

public interface LinkRepository extends JpaRepository<Link, Long> {
    Page<Link> findAll(Pageable pageable);
    List<Link> findByName(String name);
    List<Link> findByValue(String value);
    List<Link> findByCategoryId(Long categoryId);
}

