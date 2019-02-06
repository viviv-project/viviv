package ru.project.viviv.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.project.viviv.model.entity.FriendSource;
import ru.project.viviv.model.repository.FriendSourceRepository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class FriendSourceService {
    @Autowired
    FriendSourceRepository friendSourceRepository;

    public void createFriendSource(@NotNull FriendSource friendSource) {
        friendSourceRepository.save(friendSource);
    }

    public FriendSource getFriendSourceById(@NotNull String id) {
        return friendSourceRepository.findById(id).orElse(null);
    }

    public List<FriendSource> getAllFriendSources() {
        return friendSourceRepository.findAll();
    }

    public void removeAllFriendSources() {
        friendSourceRepository.deleteAll();
    }

    public void removeFriendSourceById(@NotNull String id) {
        friendSourceRepository.deleteById(id);
    }
}