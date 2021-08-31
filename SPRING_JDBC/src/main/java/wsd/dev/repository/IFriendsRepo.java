package wsd.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import wsd.dev.entity.Friend;

@Repository (value = "Friend")
public interface IFriendsRepo extends JpaRepository<Friend, Integer> {

}
