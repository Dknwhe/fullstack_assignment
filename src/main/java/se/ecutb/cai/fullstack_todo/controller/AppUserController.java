package se.ecutb.cai.fullstack_todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import se.ecutb.cai.fullstack_todo.data.AppUserRoleRepository;
import se.ecutb.cai.fullstack_todo.data.TodoItemRepository;
import se.ecutb.cai.fullstack_todo.dto.CreateAppUserForm;
import se.ecutb.cai.fullstack_todo.entity.AppUser;
import se.ecutb.cai.fullstack_todo.service.AppUserService;

import javax.validation.Valid;


@Controller
public class AppUserController {

    private AppUserRoleRepository appUserRoleRepository;
    private AppUserService appUserService;
    private TodoItemRepository todoItemRepository;

    @Autowired
    public AppUserController(AppUserRoleRepository appUserRoleRepository, AppUserService appUserService, TodoItemRepository todoItemRepository) {
        this.appUserRoleRepository = appUserRoleRepository;
        this.appUserService = appUserService;
        this.todoItemRepository = todoItemRepository;
    }
    //local/index
    @GetMapping(("/index"))
     public String index() {
        return "index";
     }


    @GetMapping("users/register/form")
    public String register(Model model) {
        model.addAttribute("form", new CreateAppUserForm());
        return "register-form";
    }

    @PostMapping("users/register/process")
    public String formProcess(@Valid @ModelAttribute("form") CreateAppUserForm form, BindingResult bindingResult) {
        if (appUserService.findByUserName(form.getUsername()).isPresent()) {
            FieldError error = new FieldError("form", "username", "Username is already in use " + form.getUsername());
            bindingResult.addError(error);
        }

        if (!form.getPassword().equals(form.getPasswordConfirm())) {
            FieldError error = new FieldError("form", "passwordConfirm", "Password didn't match");
            bindingResult.addError(error);
        }

        if (bindingResult.hasErrors()) {
            return "/register-form";
        }

        AppUser user = appUserService.registerAppUser(form);
        if (form.isAdmin()) {
            return "redirect:/users/" + user.getUserId();
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/login")
    public String getLoginForm() {
        return "login-form";
    }

     @GetMapping("/accessdenied")
    public String getAccessDenied() {
        return "acess-denied";
     }
}

