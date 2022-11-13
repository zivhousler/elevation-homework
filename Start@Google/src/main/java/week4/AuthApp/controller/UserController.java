package week4.AuthApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import week4.AuthApp.entities.ManipulatedUser;
import week4.AuthApp.service.AuthenticationService;
import week4.AuthApp.service.UserService;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping(value = "/user/action/")
public class UserController {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserService userService;


    private UserController() {
    }

    @RequestMapping(value = "updateName", method = RequestMethod.PATCH, consumes = "application/json")
    public ResponseEntity<?> updateName(@RequestBody ManipulatedUser user, @RequestHeader(value = "token") String token) throws IOException {

        String newName = user.getNewName();
        String email = user.getEmail();

        if (newName == null || email == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You must include all parameters for such an action: email, name, password");
        }
        if (!InputValidation.isValidName(newName)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid name!");
        }
        if (!InputValidation.isValidEmail(email)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid email address!");
        }

        try {
            validateToken(email, token);
            return ResponseEntity.ok(userService.updateUserName(email, newName));
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("The token is wrong or not up to date.");
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email address: %s does not exist");
        }
    }


    @RequestMapping(value = "updateEmail", method = RequestMethod.PATCH, consumes = "application/json")
    public ResponseEntity<?> updateEmail(@RequestBody ManipulatedUser user, @RequestHeader(value = "token") String token) throws IOException {
        // ResponseEntity<BaseReponse<UserToPresent>> -> UserToPresent is a class that returns only specific data out of the user.

        String email = user.getEmail();
        String newEmail = user.getNewEmail();

        if (newEmail == null || email == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You must include all parameters for such an action: email, newEmail");
        }
        if (!InputValidation.isValidEmail(newEmail)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Could not log you in. Your new email address is invalid.");
        }
        if (!InputValidation.isValidEmail(email)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Could not log you in. Your email address is invalid.");
        }

        try {
            validateToken(email, token);
            authenticationService.updateTokenEmailKey(email, newEmail);
            return ResponseEntity.ok(userService.updateUserEmail(email, newEmail));
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("The token is wrong or not up to date.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This email address already exists!");
        }
    }

    @RequestMapping(value = "updatePassword", method = RequestMethod.PATCH, consumes = "application/json")
    public ResponseEntity<?> updatePassword(@RequestBody ManipulatedUser user, @RequestHeader(value = "token") String token) throws IOException {
        String email = user.getEmail();
        String newPassword = user.getNewPassword();

        if (email == null || newPassword == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You must include all parameters for such an action: email, newPassword");
        }
        if (!InputValidation.isValidEmail(email)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Could not log you in. Your email address is invalid.");
        }
        if (!InputValidation.isValidPassword(newPassword)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Could not log you in. Your password is invalid.");
        }

        try {
            validateToken(email, token);
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("The token is wrong or not up to date.");
        }

        return ResponseEntity.ok(userService.updateUserPassword(email, newPassword));
    }

    @RequestMapping(value = "delete", method = RequestMethod.DELETE, consumes = "application/json")
    public ResponseEntity<?> deleteUser(@RequestBody ManipulatedUser user, @RequestHeader(value = "token") String token) throws IOException {
        String email = user.getEmail();

        if (email == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You must include all parameters for such an action: email");
        }

        try {
            validateToken(email, token);
            userService.deleteUser(email);
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("The token is wrong or not up to date.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Email address does not match any user.");
        }

        return ResponseEntity.noContent().build();
    }

    private void validateToken(String email, String token) throws IOException {
        if (!authenticationService.isValidToken(email, token)) {
            throw new AccessDeniedException(String.format("User with email address: %s is not logged in!", email));
        }
    }
}

