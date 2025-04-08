package Klieterboard.service;

import Klieterboard.API.KilterApi;
import Klieterboard.entity.Friends;
import Klieterboard.entity.User;
import Klieterboard.repository.IFriendsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Klieterboard.repository.IUserRepository;

import java.util.List;

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

    public List<String> getFriends(User user) {
        return kilterApi.getFriends(user.getKilterId());
    }

    public void insertFriends(User user){
        List<String> list = kilterApi.getFriends(user.getKilterId());
        for(String friend : list){
            if(friendsRepository.findByUsername(friend) == null) {
                friendsRepository.save(new Friends(friend));
            }
        }
    }
}
