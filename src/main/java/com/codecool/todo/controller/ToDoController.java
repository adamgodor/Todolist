package com.codecool.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ToDoController {



    @GetMapping(value = "/todopage")
    public String renderTodoPage(){
        return "todopage";
    }
}
