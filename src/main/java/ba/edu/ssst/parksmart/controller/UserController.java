package ba.edu.ssst.parksmart.controller;

import ba.edu.ssst.parksmart.model.User;
import ba.edu.ssst.parksmart.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = {"http://localhost:4200", "https://parksmart-sarajevo.netlify.app"})
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody java.util.Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");

        return userService.findByEmail(email)
                .map(user -> {
                    if (userService.checkPassword(password, user.getPassword())) {
                        return ResponseEntity.ok(user);
                    } else {
                        return ResponseEntity.status(401).body((Object)"Invalid credentials");
                    }
                })
                .orElse(ResponseEntity.status(401).body("User not found"));
    }
}
