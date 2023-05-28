package com.example.urlhub.controller;
import com.example.urlhub.model.Blacklist;
import com.example.urlhub.service.BlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/blacklist")
public class BlacklistController {
    private final BlacklistService blacklistService;

    @Autowired
    public BlacklistController(BlacklistService blacklistService) {
        this.blacklistService = blacklistService;
    }

    @GetMapping
    public ResponseEntity<List<Blacklist>> getAllBlacklists() {
        return ResponseEntity.ok(blacklistService.getAllBlacklists());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blacklist> getBlacklist(@PathVariable Long id) {
        return ResponseEntity.ok(blacklistService.getBlacklistById(id));
    }

    @PostMapping
    public ResponseEntity<Blacklist> createBlacklist(@RequestBody Blacklist blacklist) {
        return ResponseEntity.ok(blacklistService.createBlacklist(blacklist));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Blacklist> updateBlacklist(@PathVariable Long id, @RequestBody Blacklist blacklist) {
        return ResponseEntity.ok(blacklistService.updateBlacklist(id, blacklist));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlacklist(@PathVariable Long id) {
        blacklistService.deleteBlacklist(id);
        return ResponseEntity.ok().build();
    }
}


