package week4.AuthApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import week4.AuthApp.entities.ManipulatedUser;
import week4.AuthApp.entities.User;
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

    @RequestMapping(value = "updateName", method = RequestMethod.PATCH)
    public ResponseEntity<User> updateName(@RequestBody ManipulatedUser user, @RequestHeader(value = "token") String token) throws IOException {

        String newName = user.getNewName();
        String email = user.getEmail();

        if (newName == null || email == null ) {
            throw new IllegalArgumentException("You must include all parameters for such an action: email, name");
        }
        if (!InputValidation.isValidName(newName)) {
            throw new IllegalArgumentException(String.format("%s is invalid name!", newName));
        }
        if (!InputValidation.isValidEmail(email)) {
            throw new IllegalArgumentException(String.format("%s is invalid email!", email));
        }

        validateToken(email, token);


        return ResponseEntity.ok(userService.updateUserName(email, newName));
    }

    @RequestMapping(value = "updateEmail", method = RequestMethod.PATCH)
    public ResponseEntity<User> updateEmail(@RequestBody ManipulatedUser user, @RequestHeader(value = "token") String token) throws IOException {
        String email = user.getEmail();
        String newEmail = user.getNewEmail();

        if (newEmail == null || email == null) {
            throw new IllegalArgumentException("You must include all parameters for such an action: email, newEmail");
        }
        if (!InputValidation.isValidEmail(newEmail)) {
            throw new IllegalArgumentException(String.format("%s is invalid email!", newEmail));
        }
        if (!InputValidation.isValidEmail(email)) {
            throw new IllegalArgumentException(String.format("%s is invalid new email!", email));
        }

        validateToken(email, token);
        authenticationService.updateTokenEmailKey(email, newEmail);
        return ResponseEntity.ok(userService.updateUserEmail(email, newEmail));
    }

    @RequestMapping(value = "updatePassword", method = RequestMethod.PATCH)
    public ResponseEntity<User> updatePassword(@RequestBody ManipulatedUser user, @RequestHeader(value = "token") String token) throws IOException {
        String email = user.getEmail();
        String newPassword = user.getNewPassword();

        if (email == null || newPassword == null ) {
            throw new IllegalArgumentException("You must include all parameters for such an action: email, newPassword");
        }
        if (!InputValidation.isValidPassword(newPassword)) {
            throw new IllegalArgumentException(String.format("%s is invalid password!", newPassword));
        }

        validateToken(email, token);
        return ResponseEntity.ok(userService.updateUserPassword(email, newPassword));
    }

    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@RequestBody ManipulatedUser user, @RequestHeader(value = "token") String token) throws IOException {
        String email = user.getEmail();

        if (email == null) {
            throw new IllegalArgumentException("You must include all parameters for such an action: email");
        }

        validateToken(email, token);
        userService.deleteUser(email);

        return ResponseEntity.noContent().build();
    }

    private void validateToken(String email, String token) throws IOException {
        if (!authenticationService.isValidToken(email, token)) {
            throw new AccessDeniedException(String.format("User with email address: %s is not logged in!", email));
        }
    }
}

