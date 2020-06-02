package se.ecutb.cai.fullstack_todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.ecutb.cai.fullstack_todo.data.TodoItemRepository;
import se.ecutb.cai.fullstack_todo.dto.TodoItemForm;
import se.ecutb.cai.fullstack_todo.entity.AppUser;
import se.ecutb.cai.fullstack_todo.entity.TodoItem;


import java.time.LocalDate;
import java.util.Optional;

@Service
public class TodoItemServiceImpl implements TodoItemService {

    private TodoItemRepository todoItemRepository;

    @Autowired
    public TodoItemServiceImpl(TodoItemRepository todoItemRepository) {
        this.todoItemRepository = todoItemRepository;
    }

    @Override
    public TodoItem create(String title, String description, LocalDate deadline, double reward) {
        return todoItemRepository.save(new TodoItem(title, description, deadline, false, reward, null));
    }
}
