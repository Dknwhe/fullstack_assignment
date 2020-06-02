package se.ecutb.cai.fullstack_todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.ecutb.cai.fullstack_todo.data.AppUserRepository;
import se.ecutb.cai.fullstack_todo.entity.AppUser;


@Controller
public class AppUserController {

    private AppUserRepository appUserRepository;

    @Autowired
    public AppUserController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @RequestMapping(value = "/details/{email}")
    public String getLoanView(Model model, @PathVariable("email") String email, @AuthenticationPrincipal UserDetails caller) {
        if (caller == null || !appUserRepository.findByEmailIgnoreCase(email).isPresent()) {
            return "access-denied";
        }
        if (email.equals(caller.getUsername()) || caller.getAuthorities().stream().anyMatch(
                auth -> auth.getAuthority().equals("ADMIN"))) {
            AppUser user = appUserRepository.findByEmailIgnoreCase(email).orElseThrow(
                    () -> new IllegalArgumentException("User could not be found")
            );
            model.addAttribute("appUserList", appUserRepository.findAll());
            model.addAttribute("todoList", user.getTodoItemList());
            return "user-details";
        } else {
            return "access-denied";
        }
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }
}

