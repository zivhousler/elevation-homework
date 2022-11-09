package week4.AuthApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import week4.AuthApp.entities.ManipulatedUser;
import week4.AuthApp.entities.User;
import week4.AuthApp.service.AuthenticationService;

import java.io.IOException;

@RestController
@RequestMapping(value = "/user/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    private AuthenticationController() {
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody ManipulatedUser user) throws IOException {
        String email = user.getEmail();
        String password = user.getPassword();

        if (email == null ) {
            throw new IllegalArgumentException("You must include all parameters for such an action: email, password");
        }
        if (!InputValidation.isValidEmail(email)) {
            throw new IllegalArgumentException("Your email address is invalid!");
        }
        if (!InputValidation.isValidPassword(password)) {
            throw new IllegalArgumentException("Your password is invalid!");
        }

        String token = this.authenticationService.login(email, password);
        System.out.println(token);
        return ResponseEntity.ok(token);
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ResponseEntity<User> register(@RequestBody ManipulatedUser user) throws IOException {
        String email = user.getEmail();
        String name = user.getName();
        String password = user.getPassword();

        if (name == null || email == null || password == null) {
            throw new IllegalArgumentException("You must include all parameters for such an action: email, name, password");
        }
        if (!InputValidation.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email address!");
        }
        if (!InputValidation.isValidName(name)) {
            throw new IllegalArgumentException("Invalid name!");
        }
        if (!InputValidation.isValidPassword(password)) {
            throw new IllegalArgumentException("Invalid password!");
        }

        return ResponseEntity.ok(this.authenticationService.register(email, name, password));
    }
}
