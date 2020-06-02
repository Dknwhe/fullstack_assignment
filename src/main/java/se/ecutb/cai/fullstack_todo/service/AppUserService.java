package se.ecutb.cai.fullstack_todo.service;

import se.ecutb.cai.fullstack_todo.entity.AppUser;


import java.time.LocalDate;


public interface AppUserService {
    AppUser registerAppUser(String firstName,
                            String lastName,
                            String email,
                            String password,
                            LocalDate regDate,
                            boolean isAdmin);

}
