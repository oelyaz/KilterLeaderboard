package Klieterboard.repository;

import Klieterboard.entity.Friends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFriendsRepository extends JpaRepository<Friends, Integer> {

    Friends findByUsername(String username);
}
