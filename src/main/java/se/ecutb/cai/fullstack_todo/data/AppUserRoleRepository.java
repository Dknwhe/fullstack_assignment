package se.ecutb.cai.fullstack_todo.data;

import org.springframework.data.repository.CrudRepository;
import se.ecutb.cai.fullstack_todo.entity.AppUserRole;
import se.ecutb.cai.fullstack_todo.entity.Roles;

import java.util.Optional;

public interface AppUserRoleRepository extends CrudRepository <AppUserRole, Integer> {
    Optional<AppUserRole> findByRole(Roles role);
}
