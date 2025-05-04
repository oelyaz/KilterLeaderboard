package Klieterboard.controller;

import Klieterboard.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import Klieterboard.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@CrossOrigin(origins = "http://localhost:5173")
@Controller
@RestController
@RequestMapping("/users")

public class UserController {

    private final UserService userService;
    private static LocalDateTime lastUpdate = LocalDateTime.now();

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Returns a list of all users.
     *
     * @return a list of all users
     */
    @CrossOrigin
    @GetMapping("/")
    public List<User> findAll() {
        return userService.findAll();
    }

    /**
     * Returns a list with the usernames of all users.
     *
     * @return A list with the usernames of all users.
     */
    @CrossOrigin
    @GetMapping("/allString")
    public Set<String> findAllString() {
        return userService.findAllString();
    }


    /**
     * Finds a user based on their username.
     *
     * @param username username of the requested user
     * @return the requested user
     */
    @CrossOrigin
    @GetMapping("/{username}")
    public User findByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    /**
     * Finds a user based on their id.
     *
     * @param id id (not kilterId) of the requested user
     * @return the requested user
     */
    @CrossOrigin
    @GetMapping("/id/{id}")
    public User findById(@PathVariable Integer id) {
        return userService.findById(id);
    }

    /**
     * Finds a user based on their kilterId.
     *
     * @param kilterId kilterId of the requested user
     * @return the requested user
     */
    @CrossOrigin
    @GetMapping("/kilterId/{kilterId}")
    public User findByKilterId(@PathVariable String kilterId) {
        return userService.findByKilterId(kilterId);
    }

//    /**
//     * Creates a new user.
//     * @param user user to be created
//     * @return a ResponseEntity
//     */
//    @PostMapping("/create")
//    public ResponseEntity<User> createUser(@RequestBody User user){
//        if(user == null || user.getKilterId() == null || user.getUsername() == null){
//            return ResponseEntity.badRequest().build();
//        }
//        User newUser = new User(user.getKilterId(),user.getUsername());
//        if(user.getScore() != null) newUser.setScore(user.getScore());
//        if(user.getName()!= null) newUser.setName(user.getName());
//        if(user.getGrade()!= null) newUser.setGrade(user.getGrade());
//
//        if(findByUsername(newUser.getUsername()) != null) return ResponseEntity.badRequest().build();
//        if(findByKilterId(newUser.getKilterId()) != null) return ResponseEntity.badRequest().build();
//
//        userService.insertUser(newUser);
//        return ResponseEntity.ok(newUser);
//    }

    /**
     * Inserts a kilter-user to the database.
     *
     * @param username username of the user to be inserted
     * @return a ResponseEntity
     */
    @CrossOrigin
    @PostMapping("/{username}")
    public ResponseEntity<User> createKilterUser(@PathVariable String username) {
        if (username == null) {
            return ResponseEntity.badRequest().build();
        }
        User user = userService.createKilterUser(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }

//    /**
//     * Updates a specified user.
//     * @param user username of the user to be updated
//     * @return a ResponseEntity
//     */
//    @PutMapping("/{username}")
//    public ResponseEntity<User> updateUser(@PathVariable String username, @RequestBody User user){
//        User update = userService.findByUsername(username);
//        if(update == null){
//            return ResponseEntity.notFound().build();
//        }
//        if(user.getUsername() != null) update.setUsername(user.getUsername());
//        if(user.getScore() != null) update.setScore(user.getScore());
//        if(user.getName()!= null) update.setName(user.getName());
//        if(user.getGrade()!= null) update.setGrade(user.getGrade());
//        userService.saveUser(update);
//        return ResponseEntity.ok(update);
//    }

    /**
     * Updates the score of a specified user.
     *
     * @param username username of the user whose score is to be updated
     * @return a ResponseEntity
     */
    @CrossOrigin
    @PatchMapping("/{username}/update")
    public ResponseEntity<User> updateScore(@PathVariable String username) {
        User update = userService.findByUsername(username);
        if (update == null) {
            return ResponseEntity.notFound().build();
        }
        update = userService.updateScore(update);
        return ResponseEntity.ok(update);
    }

    /**
     * Updates the score of all users. <br>
     * Doesn't update if the last update is less than 5 minutes ago.
     *
     * @return a ResponseEntity
     */
    @CrossOrigin
    @PatchMapping("/update")
    public ResponseEntity<String> update() {
        if (lastUpdate.plusMinutes(5).isBefore(LocalDateTime.now())) {
            for (User user : userService.findAll()) {
                userService.updateScore(user);
            }
            lastUpdate = LocalDateTime.now();
            return ResponseEntity.ok("ok");
        }
        return ResponseEntity.badRequest().body("The last update was less than 5 minutes ago. Please try again later");
    }

//    /**
//     * Deletes a user in the database based on the user username.
//     * @param username username of the user to be deleted
//     * @return a ResponseEntity
//     */
//    @DeleteMapping("/{username}")
//    public ResponseEntity<User> deleteUser(@PathVariable String username){
//        User delete = userService.findByUsername(username);
//        if(delete == null){return ResponseEntity.notFound().build();}
//        userService.deleteById(delete.getId());
//        return ResponseEntity.ok(delete);
//    }


    /**
     * Retrieves a list of all friends of a specified user.
     *
     * @param username username of the user whose friends are requested
     * @return A list of the usernames of all friends of the requested user.
     */
    @CrossOrigin
    @GetMapping("/friends/{username}")
    public List<String> findAllFriendsFromUser(@PathVariable String username) {
        User user = userService.findByUsername(username);
        if (user == null) return List.of("This user is not on the leaderboard.");
        return userService.getFriends(user);
    }

//    /**
//     * Inserts all friends of the specified user in the database.
//     * @param username username of the user whose friends should be added to the database
//     * @return a ResponseEntity
//     */
//    @PostMapping("/friends/{username}")
//    public ResponseEntity<String> putAllFriendsInDB(@PathVariable String username){
//        User user = userService.findByUsername(username);
//        if(user == null) return ResponseEntity.notFound().build();
//        userService.insertFriends(user);
//        return ResponseEntity.ok().build();
//    }

}
