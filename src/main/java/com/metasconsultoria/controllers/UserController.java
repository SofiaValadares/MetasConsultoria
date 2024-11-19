package com.metasconsultoria.controllers;

import com.metasconsultoria.entities.User;
import com.metasconsultoria.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() throws SQLException {
        List<User> users = UserService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) throws SQLException {
        User user = UserService.getUser(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<String> createUser(@RequestBody User user) throws SQLException {
        UserService.insertUser(user.getName(), user.getEmail(), user.getPassword());
        return ResponseEntity.ok("Usuário criado com sucesso.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@RequestBody User user) throws SQLException {
        UserService.updateUser(user);
        return ResponseEntity.ok("Usuário atualizado com sucesso.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) throws SQLException {
        User user = UserService.getUser(id);
        if (user != null) {
            UserService.deleteUser(user);
            return ResponseEntity.ok("Usuário deletado com sucesso.");
        }
        return ResponseEntity.notFound().build();
    }
}
