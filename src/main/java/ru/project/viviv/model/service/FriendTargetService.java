package ru.project.viviv.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.project.viviv.model.entity.FriendTarget;
import ru.project.viviv.model.repository.FriendTargetRepository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class FriendTargetService {
    @Autowired
    FriendTargetRepository friendTargetRepository;

    public void createFriendTarget(@NotNull FriendTarget friendTarget) {
        friendTargetRepository.save(friendTarget);
    }

    public FriendTarget getFriendTargetById(@NotNull String id) {
        return friendTargetRepository.findById(id).orElse(null);
    }

    public List<FriendTarget> getAllFriendTargets() {
        return friendTargetRepository.findAll();
    }

    public void removeAllFriendTargets() {
        friendTargetRepository.deleteAll();
    }

    public void removeFriendTargetById(@NotNull String id) {
        friendTargetRepository.deleteById(id);
    }
}