package ru.project.viviv.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.project.viviv.model.entity.Friend;
import ru.project.viviv.model.entity.FriendStatus;
import ru.project.viviv.model.entity.User;
import ru.project.viviv.model.repository.FriendRepository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FriendService {

    @Autowired
    private FriendRepository friendRepository;

    public List<User> findAllUserFriends(@NotNull User user) {
        List<Friend> sourceFriends = friendRepository.findAllByFriendSource_StatusAndFriendTarget_StatusAndFriendSource_User_Username(FriendStatus.ACCEPT, FriendStatus.ACCEPT, user.getUsername());
        List<Friend> targetFriends = friendRepository.findAllByFriendTarget_StatusAndFriendSource_StatusAndFriendTarget_User_Username(FriendStatus.ACCEPT, FriendStatus.ACCEPT, user.getUsername());
        return getUsersFromFT(sourceFriends, targetFriends);
    }

    public List<User> findAllUserRelations(@NotNull User user) {
        List<Friend> sourceFriends = friendRepository.findAllByFriendSource_User_Username(user.getUsername());
        List<Friend> targetFriends = friendRepository.findAllByFriendTarget_User_Username(user.getUsername());
        return getUsersFromFT(sourceFriends, targetFriends);
    }

    private List<User> getUsersFromFT(List<Friend> sourceFriends, List<Friend> targetFriends) {
        List<User> sourceUsers = sourceFriends.stream().map(friend -> friend.getFriendTarget().getUser()).collect(Collectors.toList());
        List<User> targetUsers = targetFriends.stream().map(friend -> friend.getFriendSource().getUser()).collect(Collectors.toList());
        return Stream.concat(sourceUsers.stream(), targetUsers.stream()).collect(Collectors.toList());
    }

    public void saveFriend(@NotNull Friend friend) {
        friendRepository.save(friend);
    }
}
