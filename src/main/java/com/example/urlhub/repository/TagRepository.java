package com.example.urlhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.urlhub.model.Tag;
import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findByName(String name);
}
