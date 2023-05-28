package com.example.urlhub.service;

import com.example.urlhub.ResourceNotFoundException;
import com.example.urlhub.model.Blacklist;
import com.example.urlhub.repository.BlacklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BlacklistService {

    @Autowired
    private BlacklistRepository blacklistRepository;

    public List<Blacklist> getAllBlacklists() {
        return blacklistRepository.findAll();
    }

    public Blacklist getBlacklistById(Long id) {
        return blacklistRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Blacklist", "id", id));
    }

    public Blacklist createBlacklist(Blacklist blacklist) {
        return blacklistRepository.save(blacklist);
    }

    public Blacklist updateBlacklist(Long id, Blacklist blacklistDetails) {
        Blacklist blacklist = getBlacklistById(id);
        blacklist.setDomain(blacklistDetails.getDomain());
        return blacklistRepository.save(blacklist);
    }

    public void deleteBlacklist(Long id) {
        Blacklist blacklist = getBlacklistById(id);
        blacklistRepository.delete(blacklist);
    }
}

