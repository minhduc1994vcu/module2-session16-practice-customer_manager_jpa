package com.codegym.cms.controller;

import com.codegym.cms.model.Customer;
import com.codegym.cms.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public ModelAndView listCustomer() {
       return new ModelAndView("/customer/list","customers",customerService.findAll());
    }

    @GetMapping("/create")
    public ModelAndView createCustomer() {
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("message", "New customer was created!");
        return modelAndView;
    }
    @GetMapping("/edit/{id}")
    public ModelAndView editCustomer(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/customer/edit");
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }
    @PostMapping("/edit")
    public ModelAndView editCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("/customer/edit");
        modelAndView.addObject("message", "Customer's Information was updated");
        return modelAndView;
    }
    @GetMapping("/delete/{id}")
    public ModelAndView deleteCustomer(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        customerService.remove(id);
        ModelAndView modelAndView = new ModelAndView("/customer/delete");
        modelAndView.addObject("customer", customer);
        modelAndView.addObject("message", "you deleted this customer");
        return modelAndView;

    }
    @GetMapping("/view/{id}")
    public ModelAndView viewCustomer(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/customer/view");
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

}

