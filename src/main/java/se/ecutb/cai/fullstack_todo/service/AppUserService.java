package se.ecutb.cai.fullstack_todo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import se.ecutb.cai.fullstack_todo.dto.CreateAppUserForm;
import se.ecutb.cai.fullstack_todo.entity.AppUser;


import java.util.Optional;

public interface AppUserService {
        //create, save, search, delete
    AppUser registerAppUser(CreateAppUserForm userForm);
    AppUser save(AppUser appUser);
    Optional<AppUser> findById(int userId);
    Optional<AppUser> findByUserName(String username);
    AppUser delete(AppUser appUser);

}
