package com.example.urlhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.urlhub.model.Category;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByName(String name);
}

