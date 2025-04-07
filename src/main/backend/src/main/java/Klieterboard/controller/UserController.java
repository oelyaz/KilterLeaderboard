package Klieterboard.controller;

import Klieterboard.API.KilterApi;
import Klieterboard.entity.Friends;
import Klieterboard.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import Klieterboard.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/users")

public class UserController {

    private final UserService userService;
    private final KilterApi kilterApi;

    @Autowired
    public UserController(UserService userService, KilterApi kilterApi) {
        this.userService = userService;
        this.kilterApi = kilterApi;
        kilterApi.determineToken();
    }

    /**
     * Returns a list of all users.
     * @return a list of all users
     */
    @GetMapping("/all")
    public List<User> findAll(){
        return userService.findAll();
    }

    /**
     * Finds a user based on their username.
     * @param username username of the requested user
     * @return the requested user
     */
    @GetMapping("/{username}")
    public User findByUsername(@PathVariable String username){
        return userService.findByUsername(username);
    }

    /**
     * Finds a user based on their id.
     * @param id id (not kilterId) of the requested user
     * @return the requested user
     */
    @GetMapping("/id/{id}")
    public User findById(@PathVariable Integer id){
        return userService.findById(id);
    }

    /**
     * Creates a new user.
     * @param user user to be created
     * @return a ResponseEntity
     */
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User newUser = new User(user.getKilterId(),user.getUsername());
//        newUser.setKilterId(user.getKilterId());
//        newUser.setUsername(user.getUsername());
        userService.insertUser(newUser);
        return ResponseEntity.ok(newUser);
    }

    /**
     * Updates a specified user.
     * @param user username of the user to be updated
     * @return a ResponseEntity
     */
    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        User update = userService.findByUsername(user.getUsername());

        update.setUsername(user.getUsername());
        update.setScore(user.getScore());
        userService.saveUser(update);
        return ResponseEntity.ok(update);
    }

    /**
     * Updates the score of a specified user.
     * @param username username of the user whose score is to be updated
     * @param score the new score
     * @return a ResponseEntity
     */
    @PatchMapping("/{username}/score")
    public ResponseEntity<User> updateUserScore(@PathVariable String username, @RequestBody Integer score){
        User update = userService.findByUsername(username);

        update.setScore(score);
        userService.saveUser(update);
        return ResponseEntity.ok(update);
    }

    /**
     * Deletes a user in the database based on the user username.
     * @param username username of the user to be deleted
     * @return a ResponseEntity
     */
    @DeleteMapping("/{username}")
    public ResponseEntity<User> deleteUser(@PathVariable String username){
        User delete = userService.findByUsername(username);
        userService.deleteById(delete.getId());
        return ResponseEntity.ok(delete);
    }


    /**
     * Retrieves a list of all friends of a specified user.
     * @param username username of the user whose friends are requested
     * @return A list of all friends of the requested user.
     */
    @GetMapping("/friends/{username}")
    public List<Friends> findAllFriendsFromUser(@PathVariable String username){
        User user = userService.findByUsername(username);
        return kilterApi.getFriends(user.getKilterId());
    }
}
