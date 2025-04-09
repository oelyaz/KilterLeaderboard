package Klieterboard.controller;

import Klieterboard.entity.Friends;
import Klieterboard.service.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/friends")
public class FriendsController {

    private final FriendsService friendsService;

    @Autowired
    public FriendsController(FriendsService friendsService) {
        this.friendsService = friendsService;
    }

    /**
     * Returns a list of all friends.
     * @return A list of all friends.
     */
    @GetMapping("/all")
    public List<Friends> findAll(){
        return friendsService.findAll();
    }

    /**
     * Returns a list of the usernames of all friends.
     * @return A list of the usernames of all friends.
     */
    @GetMapping("/all/string")
    public List<String> findAllString(){
        return friendsService.findAllString();
    }

    /**
     * Fiends a friend based on their username.
     * @param username username of the requested friend
     * @return The requested friend.
     */
    @GetMapping("/{username}")
    public Friends findByUsername(@PathVariable String username){
        return friendsService.findByUsername(username);
    }

    /**
     * Creates a new friend.
     * @param username username of the friend to be created
     * @return A Response Entity containing the new friend.
     */
    @PostMapping("/create")
    public ResponseEntity<Friends> create(@RequestBody String username){
        if(username == null || username.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        Friends friend = new Friends(username);
        friendsService.insertFriends(friend);
        return ResponseEntity.ok(friend);
    }

    /**
     * Deletes a friend in the database based on the username.
     * @param username username of the friend to be deleted
     * @return A Response Entity containing the deleted friend.
     */
    @DeleteMapping("/{username}")
    public ResponseEntity<Friends> delete(@PathVariable String username){
        Friends delete = friendsService.findByUsername(username);
        if(delete == null){
            return ResponseEntity.notFound().build();
        }
        friendsService.deleteById(delete.getId());
        return ResponseEntity.ok(delete);
    }

}
