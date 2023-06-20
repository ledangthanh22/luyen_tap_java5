package com.example.demo.controller;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerClassService;
import com.example.demo.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/customer/")
public class CustomerController {

    private Integer pageSize = 5;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerClassService customerClassService;


    @GetMapping("show")
    public String showCustomerList(@RequestParam(value = "page", defaultValue = "0") Integer pageNo, Model model) {

        Page<Customer> page = customerService.findPagination(pageNo, pageSize);

        model.addAttribute("listCustomers", page.getContent());
        model.addAttribute("currentPage", page.getNumber());
        model.addAttribute("totalPages", page.getTotalPages());

        model.addAttribute("customer", new Customer());

        model.addAttribute("listCustomerClass", customerClassService.getAll());
        return "index";
    }

    @PostMapping("save")
    public String saveCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("listCustomerClass", customerClassService.getAll());
            Page<Customer> page = customerService.findPagination(0, pageSize);

            model.addAttribute("listCustomers", page.getContent());
            model.addAttribute("currentPage", page.getNumber());
            model.addAttribute("totalPages", page.getTotalPages());
            return "index";
        }

            customerService.save(customer);
            return "redirect:/customer/show";

    }


    @GetMapping("delete/{code}")
    public String deleteCustomer(@PathVariable("code") String code) {
        customerService.delete(Long.parseLong(code));
        return "redirect:/customer/show";
    }

    @GetMapping("search")
    public String search(@RequestParam(name = "keyword") String keyword,
                         @RequestParam(name = "name") String name,
                         @RequestParam(value = "page", defaultValue = "0") Integer pageNo,
                         Model model) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Customer> page = null;
        if (keyword.isEmpty()) {
            page = customerService.search(null, name, pageable);
        } else {
            page = customerService.search(keyword, name, pageable);
        }
        model.addAttribute("customer", new Customer());

        model.addAttribute("listCustomers", page.getContent());
        model.addAttribute("currentPage", page.getNumber());
        model.addAttribute("totalPages", page.getTotalPages());

        model.addAttribute("listCustomerClass", customerClassService.getAll());
        return "index";
    }
}
