package se.ecutb.cai.fullstack_todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import se.ecutb.cai.fullstack_todo.data.AppUserRepository;
import se.ecutb.cai.fullstack_todo.data.AppUserRoleRepository;
import se.ecutb.cai.fullstack_todo.data.TodoItemRepository;
import se.ecutb.cai.fullstack_todo.entity.AppUser;
import se.ecutb.cai.fullstack_todo.entity.AppUserRole;
import se.ecutb.cai.fullstack_todo.entity.TodoItem;
import se.ecutb.cai.fullstack_todo.service.AppUserService;

import java.time.LocalDate;

public class Seeder implements CommandLineRunner {

    private AppUserRoleRepository appUserRoleRepository;
    private AppUserService appUserService;
    private TodoItemRepository todoItemRepository;
    private AppUserRepository appUserRepository;

    @Autowired
    public Seeder(AppUserRoleRepository appUserRoleRepository, AppUserService appUserService, TodoItemRepository todoItemRepository, AppUserRepository appUserRepository) {
        this.appUserRoleRepository = appUserRoleRepository;
        this.appUserService = appUserService;
        this.todoItemRepository = todoItemRepository;
        this.appUserRepository = appUserRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        appUserRoleRepository.save(new AppUserRole("ADMIN"));
        appUserRoleRepository.save(new AppUserRole("USER"));
        AppUser user = appUserService.registerAppUser("User",
                "User",
                "User@Gmail.com",
                "user",
                LocalDate.now(),
                false);
        AppUser admin = appUserService.registerAppUser("Admin",
                "Admin",
                "Admin@Gmail.com",
                "admin",
                LocalDate.now(),
                true);
        TodoItem todo = new TodoItem("Test","Test", LocalDate.of(2021,01,01),false,200,null);
        TodoItem todo2 = new TodoItem("Test2","Test", LocalDate.of(2021,01,01),false,200,null);
        todoItemRepository.save(todo);
        todoItemRepository.save(todo2);
    }
}
