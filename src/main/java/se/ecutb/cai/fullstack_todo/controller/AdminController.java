package se.ecutb.cai.fullstack_todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import se.ecutb.cai.fullstack_todo.data.AppUserRepository;
import se.ecutb.cai.fullstack_todo.data.TodoItemRepository;
import se.ecutb.cai.fullstack_todo.dto.CreateAppUserForm;
import se.ecutb.cai.fullstack_todo.dto.TodoItemForm;
import se.ecutb.cai.fullstack_todo.entity.AppUser;
import se.ecutb.cai.fullstack_todo.service.AppUserService;
import se.ecutb.cai.fullstack_todo.service.TodoItemService;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class AdminController {


    private TodoItemService todoItemService;
    private AppUserService appUserService;
    private AppUserRepository appUserRepository;
    private TodoItemRepository todoItemRepository;

    @Autowired
    public AdminController(TodoItemService todoItemService, AppUserService appUserService, AppUserRepository appUserRepository, TodoItemRepository todoItemRepository) {
        this.todoItemService = todoItemService;
        this.appUserService = appUserService;
        this.appUserRepository = appUserRepository;
        this.todoItemRepository = todoItemRepository;
    }

    @GetMapping("/register")
    public String getRegisterView(Model model) {
        model.addAttribute("form", new CreateAppUserForm());
        return "register";
    }

    @PostMapping("register/process")
    public String formProcess(@Valid @ModelAttribute("form") CreateAppUserForm form, BindingResult bindingResult) {
        if (appUserRepository.findByEmailIgnoreCase(form.getEmail()).isPresent()) {
            FieldError error = new FieldError("form", "email", "Email is already in use " + form.getEmail());
            bindingResult.addError(error);
        }

        if (!form.getPassword().equals(form.getPasswordConfirm())) {
            FieldError error = new FieldError("form", "passwordConfirm", "Password didn't match");
            bindingResult.addError(error);
        }

        if (bindingResult.hasErrors()) {
            return "register";
        }

        appUserService.registerAppUser(form.getFirstName(), form.getFirstName(), form.getEmail(), form.getPassword(), LocalDate.now(), form.isAdmin());
        return "index";
    }

    @GetMapping("/users")
    public String getUsersView(Model model) {
        model.addAttribute("appUserList", appUserRepository.findAll());
        return "users";
    }

    @GetMapping("/users/assign/{email}")
    public String getAssignView(@PathVariable String email, Model model) {
        model.addAttribute("email", email);
        model.addAttribute("todoItemList", todoItemRepository.findAll());
        return "assign";
    }

    @PostMapping("/users/assign/process/{id}")
    public String processAssignView(@PathVariable int id, @ModelAttribute("email") String email) {
        AppUser appUser = appUserRepository.findByEmailIgnoreCase(email).get();
        appUser.addTodo(
                todoItemRepository.findByItemId(id).get());
        appUserRepository.save(appUser);
        return "redirect:/users";
    }

    @GetMapping("/create/todo")
    public String getCreateTodoView(Model model) {
        model.addAttribute("form", new TodoItemForm());
        return "create-todo";
    }

    @PostMapping("/process/todo")
    public String processCreateTodoView(@Valid @ModelAttribute("form") TodoItemForm form, BindingResult bindingResult) {
        if (todoItemRepository.findByItemTitle(form.getItemTitle()).isPresent()) {
            FieldError error = new FieldError("form", "itemTitle",
                    "TodoItem with title " + form.getItemTitle() + " already exists");
            bindingResult.addError(error);
        }

        if (LocalDate.parse(form.getDeadline()).isBefore(LocalDate.now().plusDays(1))) {
            FieldError error = new FieldError("form", "deadline", "Deadline can't be before today");
            bindingResult.addError(error);
        }

        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors().toString());
            return "create-todo";
        }

        todoItemService.create(form.getItemTitle(), form.getDescription(), LocalDate.parse(form.getDeadline()), form.getReward());
        return "redirect:/users";
    }
}
