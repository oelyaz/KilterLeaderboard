package Klieterboard.repository;

import Klieterboard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer > {

        User findByUsername(String username);
        User findByKilterId(String kilterId);

        @Query("select u from User u where u.score = (select max(u2.score) from User u2) ")
        List<User> findUserWithMaxScore();
}
