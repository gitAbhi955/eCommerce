package com.app.ecom_application;



import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userService.fetchAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/api/users")
    public ResponseEntity<String> create(@RequestBody User user) {
         userService.addUser(user);
         return ResponseEntity.ok("User is added succesfully");
    }

    @GetMapping("/api/users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/api/users/{Id}")
    public ResponseEntity<String> update(@PathVariable Long Id, @RequestBody User user) {
        boolean update = userService.update(Id, user);
        if (update)
            return ResponseEntity.ok("User is updated succesfully");
        else
            return ResponseEntity.notFound().build();
    }

    public void getProducts(){
        
    }
}
