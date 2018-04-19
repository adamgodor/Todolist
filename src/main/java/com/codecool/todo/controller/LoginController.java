package com.codecool.todo.controller;

import com.codecool.todo.model.Customer;
import com.codecool.todo.service.CustomerService;
import com.codecool.todo.service.ToDoService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import javax.servlet.http.HttpSession;

@Controller
@Scope("session")
public class LoginController {

    @Autowired
    CustomerService customerService;

    @Autowired
    ToDoService toDoService;

    @PostMapping(value = "/login")
    public String login(HttpServletRequest request, HttpSession session, Model model){
        System.out.println("now we are at login");
        Customer customer = customerService.getCustomerByName(request.getParameter("name"));
        if(customer != null && customerService.checkPasword(customer.getName(), customer.getPsw())){
            session.setAttribute("name", customer.getName());
            return "redirect:/todopage";
        } else {
            System.out.println("error");
            return "redirect:/";
        }
    }

    @PostMapping(value = "/register")
    public String regist(HttpServletRequest request, HttpSession session, Model model){
        Customer customer = new Customer(request.getParameter("name"), request.getParameter("password"));
        customerService.save(customer);
        return "redirect:/";
    }

    @GetMapping(value = "/")
    public String renderIndexPage(){
        return "indexpage";
    }

}
