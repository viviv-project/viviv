package ru.project.viviv.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.project.viviv.model.entity.Friend;
import ru.project.viviv.model.entity.FriendSource;
import ru.project.viviv.model.entity.FriendStatus;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, String> {

    List<Friend> findAllByFriendSource_Status(FriendStatus status);
}
