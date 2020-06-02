package se.ecutb.cai.fullstack_todo.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appUserId;
    @Column(unique = true)
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate regDate;
    private String password;

    @ManyToMany(
            cascade = {CascadeType.MERGE},
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "app_user_app_user_role",
            joinColumns = @JoinColumn(name = "app_user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<AppUserRole> appUserRoleList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            mappedBy = "assignee")
    private List<TodoItem> todoItemList = new ArrayList<>();

    public AppUser(String email, String firstName, String lastName, LocalDate regDate, String password) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.regDate = regDate;
        this.password = password;
    }

    public AppUser() {
    }

    public boolean addTodo(TodoItem todoItem) {
        if (!todoItemList.contains(todoItem)) {
            todoItem.setAssignee(this);
            return todoItemList.add(todoItem);
        }
        return false;
    }

    public int getAppUserId() {
        return appUserId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public LocalDate getRegDate() {
        return regDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<AppUserRole> getAppUserRoleList() {
        return appUserRoleList;
    }

    public void setAppUserRoleList(List<AppUserRole> appUserRoleList) {
        this.appUserRoleList = appUserRoleList;
    }

    public List<TodoItem> getTodoItemList() {
        return todoItemList;
    }

    public void setTodoItemList(List<TodoItem> todoItemList) {
        this.todoItemList = todoItemList;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "appUserId=" + appUserId +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", regDate=" + regDate +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return appUserId == appUser.appUserId &&
                Objects.equals(email, appUser.email) &&
                Objects.equals(firstName, appUser.firstName) &&
                Objects.equals(lastName, appUser.lastName) &&
                Objects.equals(regDate, appUser.regDate) &&
                Objects.equals(password, appUser.password) &&
                Objects.equals(appUserRoleList, appUser.appUserRoleList) &&
                Objects.equals(todoItemList, appUser.todoItemList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appUserId, email, firstName, lastName, regDate, password, appUserRoleList, todoItemList);
    }
}
