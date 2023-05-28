package com.example.urlhub.service;

import com.example.urlhub.ResourceNotFoundException;
import com.example.urlhub.model.Tag;
import com.example.urlhub.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }
    public Tag getTagById(Long id) {
        return tagRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tag", "id", id));
    }
    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }
    public Tag updateTag(Long id, Tag tagDetails) {
        Tag tag = getTagById(id);
        tag.setName(tagDetails.getName());
        return tagRepository.save(tag);
    }
    public void deleteTag(Long id) {
        Tag tag = getTagById(id);
        tagRepository.delete(tag);
    }
}

