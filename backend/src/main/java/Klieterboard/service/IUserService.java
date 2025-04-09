package Klieterboard.service;

import Klieterboard.entity.User;

import java.util.List;

public interface IUserService {

    User findByUsername(String username);
    User findById(Integer id);
    User findByKilterId(String kilterId);
    List<User> findAll();
    List<String> findAllString();
    void deleteById(Integer id);
    User saveUser(User user);
    User insertUser(User user);
    List<String> getFriends(User user);
    void insertFriends(User user);
    User createKilterUser(String username);
    User updateGradeAndScore(User user);

}
