package com.app.ecom_application;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final List<User> userList =new ArrayList<User>();

    private long nextId = 1L;

    public List<User> fetchAllUsers() {
        return userList;
    }

    public void addUser(User user) {
        user.setId(nextId++);
        userList.add(user);
    }

    public Optional<User> getUserById(Long userId) {
       return userList.stream().filter(user -> user.getId().equals(userId)).findFirst();
    }

    public boolean update(Long id, User user) {
        userList.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .map(user1 -> {
                    user1.setFirstName(user.getFirstName());
                    user1.setLastName(user.getLastName());
                    return true;
                }).orElse(false);

        return false;
    }
}
