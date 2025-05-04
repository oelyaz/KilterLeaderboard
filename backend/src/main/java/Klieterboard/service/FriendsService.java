package Klieterboard.service;

import Klieterboard.entity.Friends;
import Klieterboard.repository.IFriendsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FriendsService {

    private final IFriendsRepository friendsRepository;

    @Autowired
    public FriendsService(IFriendsRepository friendsService) {
        this.friendsRepository = friendsService;
    }

    /**
     * Finds a friend based on their username.
     *
     * @param username username of the requested friend
     * @return The requested friend entity.
     */
    public Friends findByUsername(String username) {
        return friendsRepository.findByUsername(username);
    }

    /**
     * Finds a friend based on their id.
     *
     * @param id id of the requested friend
     * @return The requested friend entity.
     */
    public Friends findById(Integer id) {
        return friendsRepository.findById(id).orElse(null);
    }

    /**
     * Returns a list of all friends saved in the database.
     *
     * @return a list of all friends
     */
    public List<Friends> findAll() {
        return friendsRepository.findAll();
    }

    /**
     * Returns a list of the usernames of all friends saved in the database.
     *
     * @return s a list of the usernames of all friends
     */
    public Set<String> findAllString() {
        Set<String> set = new HashSet<>();
        for (Friends friends : friendsRepository.findAll()) {
            set.add(friends.getUsername());
        }
        return set;
    }

    /**
     * Deletes a friend in the database based on the id.
     *
     * @param id id of the friend to be deleted
     */
    public void deleteById(Integer id) {
        friendsRepository.deleteById(id);
    }

    /**
     * Saves a friend in the database.
     *
     * @param friend friend entity to be saved
     * @return The saved friend entity.
     */
    public Friends saveFriends(Friends friend) {
        return friendsRepository.save(friend);
    }

    /**
     * Inserts a new friend in the database.
     *
     * @param friend friend to be inserted
     * @return The inserted friend entity.
     */
    public Friends insertFriends(Friends friend) {
        return friendsRepository.save(friend);
    }
}
