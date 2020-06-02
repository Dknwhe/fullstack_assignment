package se.ecutb.cai.fullstack_todo.service;


import se.ecutb.cai.fullstack_todo.entity.TodoItem;

import java.time.LocalDate;


public interface TodoItemService {

    TodoItem create(String title,
                    String description,
                    LocalDate deadline,
                    double reward);


}
