package com.example.urlhub.controller;

import com.example.urlhub.model.Link;
import com.example.urlhub.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/links")
public class LinkController {
    private final LinkService linkService;

    @Autowired
    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping
    public ResponseEntity<Page<Link>> getAllLinks(Pageable pageable) {
        return ResponseEntity.ok(linkService.getAllLinks(pageable));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Link> getLink(@PathVariable Long id) {
        return ResponseEntity.ok(linkService.getLinkById(id));
    }

    @PostMapping
    public ResponseEntity<Link> createLink(@RequestBody Link link) {
        return ResponseEntity.ok(linkService.createLink(link));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Link> updateLink(@PathVariable Long id, @RequestBody Link link) {
        return ResponseEntity.ok(linkService.updateLink(id, link));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLink(@PathVariable Long id) {
        linkService.deleteLink(id);
        return ResponseEntity.ok().build();
    }
}

