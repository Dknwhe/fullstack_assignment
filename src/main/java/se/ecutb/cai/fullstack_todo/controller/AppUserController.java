package se.ecutb.cai.fullstack_todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import se.ecutb.cai.fullstack_todo.data.AppUserRoleRepository;
import se.ecutb.cai.fullstack_todo.data.TodoItemRepository;
import se.ecutb.cai.fullstack_todo.service.AppUserService;

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
}
