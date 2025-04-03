package Klieterboard.service;

import Klieterboard.entity.Friends;
import java.util.List;

public interface IFriendsService {

    Friends findByUsername(String username);
    Friends findById(Integer id);
    List<Friends> findAll();
    void deleteById(Integer id);
    Friends saveFriends(Friends user);
    Friends insertFriends(Friends user);
}
