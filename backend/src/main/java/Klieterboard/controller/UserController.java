package Klieterboard.controller;

import Klieterboard.API.KilterApi;
import Klieterboard.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import Klieterboard.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
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
     * Finds a user based on their kilterId.
     * @param kilterId kilterId of the requested user
     * @return the requested user
     */
    @GetMapping("/kilterId/{kilterId}")
    public User findByKilterId(@PathVariable String kilterId){
        return userService.findByKilterId(kilterId);
    }

    /**
     * Creates a new user.
     * @param user user to be created
     * @return a ResponseEntity
     */
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user){
        if(user == null || user.getKilterId() == null || user.getUsername() == null){
            return ResponseEntity.badRequest().build();
        }
        User newUser = new User(user.getKilterId(),user.getUsername());
        if(user.getScore() != null) newUser.setScore(user.getScore());
        if(user.getName()!= null) newUser.setName(user.getName());
        if(user.getGrade()!= null) newUser.setGrade(user.getGrade());

        if(findByUsername(newUser.getUsername()) != null) return ResponseEntity.badRequest().build();
        if(findByKilterId(newUser.getKilterId()) != null) return ResponseEntity.badRequest().build();

        userService.insertUser(newUser);
        return ResponseEntity.ok(newUser);
    }

    /**
     * Updates a specified user.
     * @param user username of the user to be updated
     * @return a ResponseEntity
     */
    @PutMapping("/update/{username}")
    public ResponseEntity<User> updateUser(@PathVariable String username, @RequestBody User user){
        User update = userService.findByUsername(username);
        if(update == null){
            return ResponseEntity.notFound().build();
        }
        if(user.getUsername() != null) update.setUsername(user.getUsername());
        if(user.getScore() != null) update.setScore(user.getScore());
        if(user.getName()!= null) update.setName(user.getName());
        if(user.getGrade()!= null) update.setGrade(user.getGrade());
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
        if(update == null){
            return ResponseEntity.notFound().build();
        }
        update.setScore(score);
        userService.saveUser(update);
        return ResponseEntity.ok(update);
    }

    /**
     * Updates the grade of a specified user.
     * @param username username of the user whose grade is to be updated
     * @param grade the new grade
     * @return
     */
    @PatchMapping("/{username}/grade")
    public ResponseEntity<User> updateUserGrade(@PathVariable String username, @RequestBody Integer grade){
        User update = userService.findByUsername(username);
        if(update == null){
            return ResponseEntity.notFound().build();
        }
        update.setGrade(grade);
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
        if(delete == null){return ResponseEntity.notFound().build();}
        userService.deleteById(delete.getId());
        return ResponseEntity.ok(delete);
    }


    /**
     * Retrieves a list of all friends of a specified user.
     * @param username username of the user whose friends are requested
     * @return A list of all friends of the requested user.
     */
    @GetMapping("/friends/{username}")
    public List<String> findAllFriendsFromUser(@PathVariable String username){
        User user = userService.findByUsername(username);
        if(user == null) return List.of("This user is not on the leaderboard.");
        return kilterApi.getFriends(user.getKilterId());
    }
}
