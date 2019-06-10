package com.codegym.cms.controller;

import com.codegym.cms.model.Customer;
import com.codegym.cms.model.Province;
import com.codegym.cms.service.CustomerService;
import com.codegym.cms.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProvinceService provinceService;

    @GetMapping("/customers")
    public ModelAndView listCustomer(Pageable pageable) {
       return new ModelAndView("/customer/list","customers",customerService.findAll(pageable));
    }

    @GetMapping("/create-customer")
    public ModelAndView createCustomer() {
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping("/create-customer")
    public ModelAndView saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("message", "New customer was created!");
        return modelAndView;
    }
    @GetMapping("/edit-customer/{id}")
    public ModelAndView editCustomer(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/customer/edit");
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }
    @PostMapping("/edit-customer")
    public ModelAndView editCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("/customer/edit");
        modelAndView.addObject("message", "Customer's Information was updated");
        return modelAndView;
    }
    @GetMapping("/delete-customer/{id}")
    public ModelAndView showdeleteCustomer(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
//        customerService.remove(id);
        ModelAndView modelAndView = new ModelAndView("/customer/delete");
        modelAndView.addObject("customer", customer);
        modelAndView.addObject("message", " you wanna delete this customer, don't you?");
        return modelAndView;

    }
    @PostMapping("/delete-customer")
    public ModelAndView deleteCustomer(@RequestParam Long id){
        customerService.remove(id);
        ModelAndView modelAndView = new ModelAndView("/customer/delete");
        modelAndView.addObject("message","Customer was deleted");
        return modelAndView;
    }
    @GetMapping("/view/{id}")
    public ModelAndView viewCustomer(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/customer/view");
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }
    @PostMapping("/customers")
    public ModelAndView listCustomers(@RequestParam("s") Optional<String> s, Pageable pageable){
        Page<Customer> customers;
        if(s.isPresent()){
            customers = customerService.findAllByFirstNameContaining(s.get(), pageable);
        } else {
            customers = customerService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }
    @ModelAttribute("provinces")
    public Iterable<Province> provinces(){
        return provinceService.findAll();
    }

}

