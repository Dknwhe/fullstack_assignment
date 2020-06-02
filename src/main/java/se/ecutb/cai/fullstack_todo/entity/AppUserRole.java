package se.ecutb.cai.fullstack_todo.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class AppUserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appRoleId;
    @Column(unique = true)
    private String role;

    public AppUserRole() {
    }

    public AppUserRole(String role){
        this.role = role;
    }

    public int getAppRoleId() {
        return appRoleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "AppUserRole{" +
                "appRoleId=" + appRoleId +
                ", role='" + role + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUserRole that = (AppUserRole) o;
        return appRoleId == that.appRoleId &&
                Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appRoleId, role);
    }
}
