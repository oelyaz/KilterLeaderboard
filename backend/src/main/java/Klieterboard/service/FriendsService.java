package Klieterboard.service;

import Klieterboard.entity.Friends;
import Klieterboard.repository.IFriendsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendsService implements IFriendsService{

    private final IFriendsRepository friendsRepository;

    @Autowired
    public FriendsService(IFriendsRepository friendsService) {
        this.friendsRepository = friendsService;
    }

    /**
     * Finds a friend based on their username.
     * @param username username of the requested friend
     * @return The requested friend entity.
     */
    @Override
    public Friends findByUsername(String username) {
        return friendsRepository.findByUsername(username);
    }

    /**
     * Finds a friend based on their id.
     * @param id id of the requested friend
     * @return The requested friend entity.
     */
    @Override
    public Friends findById(Integer id) {
        return friendsRepository.findById(id).orElse(null);
    }

    /**
     * Returns a list of all friends saved in the database.
     * @return a list of all friends
     */
    @Override
    public List<Friends> findAll() {
        return friendsRepository.findAll();
    }

    /**
     * Deletes a friend in the database based on the id.
     * @param id  id of the friend to be deleted
     */
    @Override
    public void deleteById(Integer id) {
        friendsRepository.deleteById(id);
    }

    /**
     * Saves a friend in the database.
     * @param friend friend entity to be saved
     * @return The saved friend entity.
     */
    @Override
    public Friends saveFriends(Friends friend) {
        return friendsRepository.save(friend);
    }

    /**
     * Inserts a new friend in the database.
     * @param friend friend to be inserted
     * @return The inserted friend entity.
     */
    @Override
    public Friends insertFriends(Friends friend) {
        return friendsRepository.save(friend);
    }
}
