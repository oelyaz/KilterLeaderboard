package Klieterboard.service;

import Klieterboard.API.KilterApi;
import Klieterboard.entity.*;
import Klieterboard.projectRepository.Logbook;
import Klieterboard.repository.IFriendsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Klieterboard.repository.IUserRepository;

import java.util.*;

@Service
public class UserService implements IUserService{

    private final IUserRepository userRepository;
    private final IFriendsRepository friendsRepository;
    private final KilterApi kilterApi;

    @Autowired
    public UserService(IUserRepository userRepository, IFriendsRepository friendsRepository, KilterApi kilterApi) {
        this.userRepository = userRepository;
        this.friendsRepository = friendsRepository;
        this.kilterApi = kilterApi;
        kilterApi.determineToken();
    }

    /**
     * Finds a user based on their username
     * @param username username of the requested user
     * @return the requested user entity
     */
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Finds a user based on their id
     * @param id id of the requested user
     * @return the requested user entity
     */
    @Override
    public User findById(Integer id){
        return userRepository.findById(id).orElse(null);
    }

    /**
     * Finds a user based on their kilterId
     * @param kilterId kilterId of the requested user
     * @return the requested user entity
     */
    @Override
    public User findByKilterId(String kilterId) {
        return userRepository.findByKilterId(kilterId);
    }

    /**
     * Returns a List of all users saved in the database
     * @return a List of all users
     */
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Returns a list with the usernames of all users.
     * @return A list with the usernames of all users.
     */
    @Override
    public Set<String> findAllString(){
        Set<String> set = new HashSet<>();
        for(User user : userRepository.findAll()){
            set.add(user.getUsername());
        }
        return set;
    }

    /**
     * Deletes a user in the database based on the user id
     * @param id id of the user to be deleted
     */
    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    /**
     * Saves a user in the database
     * @param user user entity to be saved
     * @return the saved user entity
     */
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Inserts a new user
     * @param user user to be inserted
     * @return the inserted user entity
     */
    @Override
    public User insertUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Retrieves a list of all friends of a specified user.
     * @param user user whose friends are requested
     * @return A list of the usernames of all friends of the requested user.
     */
    @Override
    public List<String> getFriends(User user) {
        return insertFriends(user);
    }

    /**
     * Inserts all friends of the specified user in the database.
     * @param user user whose friends should be added to the database
     */
    @Override
    public List<String> insertFriends(User user){
        List<String> list = kilterApi.getFriends(user.getKilterId());
        for(String friend : list){
            if(friendsRepository.findByUsername(friend) == null) {
                friendsRepository.save(new Friends(friend));
            }
        }
        return list;
    }

    /**
     * Inserts a kilter-user to the database.
     * @param username username of the user to be inserted
     * @return the inserted user entity
     */
    @Override
    public User createKilterUser(String username){
        if(friendsRepository.findByUsername(username) == null) {
            return null;
        }

        User user = kilterApi.searchUser(username);
        if(user == null) {
            return null;
        }
        Logbook logbook = kilterApi.getLogBook(user.getKilterId());
        if(logbook == null) {
            user.setScore(0);
            user.setGrade(0);

        }else{
            user.setGrade(logbook.getLastDifficulty());
            user.setScore(logbook.determineScore(logbook.getAverageTopDifficulty(10)));
        }
        insertFriends(user);
        userRepository.save(user);
        return user;

    }

    /**
     * Updates the score and the grade of the specified user.
     * @param user user whose score and grade should be updated
     * @return the updated user entity
     */
    @Override
    public User updateGradeAndScore(User user){
        Logbook logbook = kilterApi.getLogBook(user.getKilterId());
        user.setGrade(logbook.getLastDifficulty());
        user.setScore(logbook.determineScore(logbook.getAverageTopDifficulty(10)));
        userRepository.save(user);
        return user;
    }
}
