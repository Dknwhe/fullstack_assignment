package se.ecutb.cai.fullstack_todo.service;

import se.ecutb.cai.fullstack_todo.dto.TodoItemForm;
import se.ecutb.cai.fullstack_todo.entity.AppUser;
import se.ecutb.cai.fullstack_todo.entity.TodoItem;

import java.util.List;
import java.util.Optional;

public interface TodoItemService {

    //  //create, save, search id/title/assignee,
    TodoItem create(TodoItemForm itemForm);
    TodoItem save(TodoItem todoItem);
    Optional<TodoItem> findById(int itemId);
    Optional<TodoItem> findByTitle(String todoTitle);
    Optional<TodoItem> findByAssignee(AppUser appUser);

}
