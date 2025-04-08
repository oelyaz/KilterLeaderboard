package Klieterboard.service;

import Klieterboard.entity.User;

import java.util.List;

public interface IUserService {

    User findByUsername(String username);
    User findById(Integer id);
    List<User> findAll();
    void deleteById(Integer id);
    User saveUser(User user);
    User insertUser(User user);

}
