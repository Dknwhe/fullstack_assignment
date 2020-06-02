package se.ecutb.cai.fullstack_todo.data;

import org.springframework.data.repository.CrudRepository;
import se.ecutb.cai.fullstack_todo.entity.AppUser;
import se.ecutb.cai.fullstack_todo.entity.TodoItem;

import java.util.Optional;

public interface TodoItemRepository extends CrudRepository <TodoItem, Integer> {
    Optional<TodoItem> findByAssignee(AppUser appUser);
    Optional<TodoItem> findByItemTitle(String itemTitle);
    Optional<TodoItem> findByItemId(int itemId);



}
