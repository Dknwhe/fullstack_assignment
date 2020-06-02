package se.ecutb.cai.fullstack_todo.data;

import org.springframework.data.repository.CrudRepository;
import se.ecutb.cai.fullstack_todo.entity.AppUserRole;

import java.util.Optional;

public interface AppUserRoleRepository extends CrudRepository <AppUserRole, Integer> {
    Optional<AppUserRole> findByRole(String role);
}
