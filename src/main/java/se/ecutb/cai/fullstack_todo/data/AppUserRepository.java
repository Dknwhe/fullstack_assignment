package se.ecutb.cai.fullstack_todo.data;

import org.springframework.data.repository.CrudRepository;
import se.ecutb.cai.fullstack_todo.entity.AppUser;

import java.util.Optional;

public interface AppUserRepository extends CrudRepository <AppUser, Integer> {
    Optional<AppUser>findByUsernameIgnoreCase(String userName);


}
