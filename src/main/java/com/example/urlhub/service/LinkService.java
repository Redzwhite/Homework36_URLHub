package com.example.urlhub.service;

import com.example.urlhub.ResourceNotFoundException;
import com.example.urlhub.model.Blacklist;
import com.example.urlhub.model.Category;
import com.example.urlhub.model.Link;
import com.example.urlhub.model.Tag;
import com.example.urlhub.repository.BlacklistRepository;
import com.example.urlhub.repository.CategoryRepository;
import com.example.urlhub.repository.LinkRepository;
import com.example.urlhub.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.Set;

@Service
public class LinkService {

    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private BlacklistRepository blacklistRepository;

    public Page<Link> getAllLinks(Pageable pageable) {
        return linkRepository.findAll(pageable);
    }


    public Link getLinkById(Long id) {
        return linkRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Link", "id", id));
    }

    public Link createLink(Link link) {
        Long categoryId = link.getCategory().getId();
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        link.setCategory(category);
        Set<Tag> tags = link.getTags();
        for (Tag tag : tags) {
            Long tagId = tag.getId();
            Tag existingTag = tagRepository.findById(tagId)
                    .orElseThrow(() -> new ResourceNotFoundException("Tag", "id", tagId));
            tag.setName(existingTag.getName());
        }
        link.setTags(tags);
        String linkDomain;
        try {
            URL url = new URL(link.getValue());
            linkDomain = url.getHost();
            String scheme = url.getProtocol();
            if (!"http".equals(scheme) && !"https".equals(scheme)) {
                throw new IllegalArgumentException("Invalid URL scheme. Only HTTP and HTTPS are allowed");
            }
            if (linkDomain.isEmpty()) {
                throw new IllegalArgumentException("Invalid URL format");
            }
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid URL format");
        }
        Blacklist blacklist = blacklistRepository.findByDomain(linkDomain);
        if (blacklist != null) {
            throw new IllegalStateException("Link's domain is in the blacklist");
        }
        return linkRepository.save(link);
    }
    public Link updateLink(Long id, Link linkDetails) {
        Link link = getLinkById(id);
        link.setName(linkDetails.getName());
        link.setValue(linkDetails.getValue());
        link.setCategory(linkDetails.getCategory());
        return linkRepository.save(link);
    }
    public void deleteLink(Long id) {
        Link link = getLinkById(id);
        linkRepository.delete(link);
    }
}

