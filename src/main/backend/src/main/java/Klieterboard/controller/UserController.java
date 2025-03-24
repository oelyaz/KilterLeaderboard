package Klieterboard.controller;

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

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String Hello(){
        return "Hallo";
    }


    @GetMapping("/all")
    public List<User> findAll(){
        return userService.findAll();
    }
    @GetMapping("/{username}")
    public User findByUsername(@PathVariable String username){
        return userService.findByUsername(username);
    }
    @GetMapping("/id/{id}")
    public User findById(@PathVariable Integer id){
        return userService.findById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User newUser = new User(user.getUsername(), user.getScore());
        userService.insertUser(newUser);
        return ResponseEntity.ok(newUser);
    }
    @PutMapping("/{username}")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        User update = userService.findById(user.getId());

        update.setUsername(user.getUsername());
        update.setScore(user.getScore());
        userService.saveUser(update);
        return ResponseEntity.ok(update);
    }
    @PatchMapping("/{username}/score")
    public ResponseEntity<User> updateUserScore(@PathVariable String username, @RequestBody Integer score){
        User update = userService.findByUsername(username);

        update.setScore(score);
        userService.saveUser(update);
        return ResponseEntity.ok(update);
    }


    @DeleteMapping("/{username}")
    public ResponseEntity<User> deleteUser(@PathVariable String username){
        User delete = userService.findByUsername(username);
        userService.deleteById(delete.getId());
        return ResponseEntity.ok(delete);
    }

}
