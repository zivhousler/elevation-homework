package week4.AuthApp.entities;

public class ManipulatedUser {

    private String email;
    private String newEmail;
    private String name;
    private String newName;
    private String token;
    private String password;
    private String newPassword;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "ManipulatedUser{" +
                "email='" + email + '\'' +
                ", newEmail='" + newEmail + '\'' +
                ", name='" + name + '\'' +
                ", newName='" + newName + '\'' +
                ", password='" + password + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }

    public ManipulatedUser(String email, String newEmail, String name, String newName, String password, String newPassword) {
        this.email = email;
        this.newEmail = newEmail;
        this.name = name;
        this.newName = newName;
        this.password = password;
        this.newPassword = newPassword;
    }


    public ManipulatedUser() {
    }

}
