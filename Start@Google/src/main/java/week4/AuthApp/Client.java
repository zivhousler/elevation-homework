//
//package week4.AuthApp;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import week4.AuthApp.controller.AuthenticationController;
//import week4.AuthApp.controller.UserController;
//
//import java.io.IOException;
//import java.nio.file.AccessDeniedException;
//
//public class Client {
//    @Autowired
//    private AuthenticationController authenticationController;
//    @Autowired
//    private UserController userController;
//    private String token;
//
//    public Client() throws IOException {
//    }
//
//
//    public boolean login(String email, String password) {
//        this.token = this.authenticationController.login(email, password);
//        boolean success = this.token != null;
//        return success;
//    }
//
//    public void register(String email, String name, String password) {
//        this.authenticationController.register(email, name, password);
//    }
//
//    public void updateUserName(String email, String name) throws IOException {
//        if (this.token == null) {
//            throw new AccessDeniedException(String.format("User with email address: %s is not logged in!", email));
//        } else {
//            this.userController.updateUserName(email, name, this.token);
//        }
//    }
//
//    public void updateUserEmail(String email, String newEmail) throws IOException {
//        if (this.token == null) {
//            throw new AccessDeniedException(String.format("User with email address: %s is not logged in!", email));
//        } else {
//            this.userController.updateUserEmail(email, newEmail, this.token);
//        }
//    }
//
//    public void updateUserPassword(String email, String password) throws IOException {
//        if (this.token == null) {
//            throw new AccessDeniedException(String.format("User with email address: %s is not logged in!", email));
//        } else {
//            this.userController.updateUserPassword(email, password, this.token);
//        }
//    }
//
//    public void deleteUser(String email) throws IOException {
//        if (this.token == null) {
//            throw new AccessDeniedException(String.format("User with email address: %s is not logged in!", email));
//        } else {
//            this.userController.deleteUser(email, this.token);
//        }
//    }
//}