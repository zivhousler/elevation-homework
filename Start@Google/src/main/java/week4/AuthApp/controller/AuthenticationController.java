package week4.AuthApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import week4.AuthApp.entities.ManipulatedUser;
import week4.AuthApp.service.AuthenticationService;

import java.io.IOException;

@RestController
@RequestMapping(value = "/user/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    private AuthenticationController() {
    }

    @RequestMapping(value = "login", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> login(@RequestBody ManipulatedUser user) throws IOException {
        String email = user.getEmail();
        String password = user.getPassword();

        if (email == null || password == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You must include all parameters for such an action: email, password");
        }
        if (!InputValidation.isValidEmail(email)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Could not log you in. Your email address is invalid.");
        }
        if (!InputValidation.isValidPassword(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Could not log you in. Your password is invalid.");
        }

        String token = this.authenticationService.login(email, password);
        System.out.println(token);
        return ResponseEntity.ok(token);
    }

    @RequestMapping(value = "register", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> register(@RequestBody ManipulatedUser user) throws IOException {
        String email = user.getEmail();
        String name = user.getName();
        String password = user.getPassword();

        if (name == null || email == null || password == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You must include all parameters for such an action: email, name, password");
        }
        if (!InputValidation.isValidEmail(email)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid email address!");
        }
        if (!InputValidation.isValidName(name)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid name!");
        }
        if (!InputValidation.isValidPassword(password)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid password!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(this.authenticationService.register(email, name, password));
    }
}
