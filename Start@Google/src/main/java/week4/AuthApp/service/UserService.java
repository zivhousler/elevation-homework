package week4.AuthApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import week4.AuthApp.entities.User;
import week4.AuthApp.repository.UserRepository;

import java.io.IOException;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private UserService(){
    }

    public User updateUserName(String email, String name) throws IOException {
        Optional<User> user = userRepository.getByEmail(email);

        if (user.isPresent()) {
            user.get().setName(name);
            return userRepository.updated(user.get());
        } else {
            throw new IllegalArgumentException(String.format("Email address: %s does not exist", email));
        }
    }

    public User updateUserEmail(String email, String newEmail) throws IOException {
        Optional<User> user = userRepository.getByEmail(email);

        if (user.isPresent()) {
            user.get().setEmail(newEmail);
            return userRepository.updated(user.get());
        } else {
            throw new IllegalArgumentException(String.format("Email address %s does not match any user", email));
        }
    }

    public User updateUserPassword(String email, String password) throws IOException {
        Optional<User> user = userRepository.getByEmail(email);

        if (user.isPresent()) {
            user.get().setPassword(password);
            return userRepository.updated(user.get());
        } else {
            throw new IllegalArgumentException(String.format("Email address %s does not match any user", email));
        }
    }

    public void deleteUser(String email) throws IOException {
        Optional<User> user = userRepository.getByEmail(email);

        if (user.isPresent()) {
            userRepository.delete(user.get());
        } else {
            throw new IllegalArgumentException(String.format("Email address %s does not match any user", email));
        }
    }
}
