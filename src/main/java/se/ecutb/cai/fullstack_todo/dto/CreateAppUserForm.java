package se.ecutb.cai.fullstack_todo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CreateAppUserForm {

    @NotBlank(message = "Username pls..")
    @Size(min = 2, max = 255, message = "Username required minimum 2 letter and max 255")
    private String username;

    @NotBlank(message = "First name*")
    @Size(min = 2, max = 255, message = "First name!")
    private String firstName;

    @NotBlank(message = "Last name*")
    @Size(min = 2, max = 255, message = "Common last name!")
    private String lastName;

    @NotBlank(message = "Password*")
    //http://regexlib.com/Search.aspx?k=password&c=-1&m=4&ps=20
    @Pattern(regexp = "^(?=.*[0-9]+.*)(?=.*[a-zA-Z]+.*)[0-9a-zA-Z]{6,}$\", message = \"Must contain at least one letter, one number, and be longer than six characters.")
    private String password;

    @NotBlank(message = "Password Confirm*")
    private String passwordConfirm;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    private boolean admin;

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
