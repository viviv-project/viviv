package ru.project.viviv.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.project.viviv.model.entity.Friend;
import ru.project.viviv.model.entity.FriendSource;
import ru.project.viviv.model.entity.FriendStatus;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, String> {

    List<Friend> findAllByFriendSource_StatusAndFriendTarget_StatusAndFriendSource_User_Username(FriendStatus friendSourceStatus, FriendStatus friendTargetStatus, String username);
    List<Friend> findAllByFriendTarget_StatusAndFriendSource_StatusAndFriendTarget_User_Username(FriendStatus friendTargetStatus, FriendStatus friendSourceStatus, String username);
    List<Friend> findAllByFriendSource_User_Username(String username);
    List<Friend> findAllByFriendTarget_User_Username(String username);
}
