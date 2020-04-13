package se.ecutb.cai.fullstack_todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.ecutb.cai.fullstack_todo.data.TodoItemRepository;
import se.ecutb.cai.fullstack_todo.dto.TodoItemForm;
import se.ecutb.cai.fullstack_todo.entity.AppUser;
import se.ecutb.cai.fullstack_todo.entity.TodoItem;


import java.util.Optional;

@Service
public class TodoItemServiceImpl implements TodoItemService{

    private TodoItemRepository todoItemRepository;

    @Autowired
    public TodoItemServiceImpl(TodoItemRepository todoItemRepository) {
        this.todoItemRepository = todoItemRepository;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public TodoItem create(TodoItemForm itemForm) {
        TodoItem newItem = new TodoItem(
                itemForm.getItemTitle(),
                itemForm.getDescription(),
                itemForm.getDeadline(),
                itemForm.isDoneStatus(),
                itemForm.getReward()
        );

        return todoItemRepository.save(newItem);
    }

    @Override
    public TodoItem save(TodoItem todoItem) {
        return todoItemRepository.save(todoItem);
    }

    @Override
    public Optional<TodoItem> findById(int itemId) {
        return todoItemRepository.findById(itemId);
    }

    @Override
    public Optional<TodoItem> findByTitle(String todoTitle) {
        return todoItemRepository.findByItemTitle(todoTitle);
    }

    @Override
    public Optional<TodoItem> findByAssignee(AppUser appUser) {
        return todoItemRepository.findByAssignee(appUser);
    }

}
