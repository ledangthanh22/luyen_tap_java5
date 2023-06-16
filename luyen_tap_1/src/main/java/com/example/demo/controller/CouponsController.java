package com.example.demo.controller;

import com.example.demo.entity.Coupons;
import com.example.demo.service.CouponsService;
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

import java.sql.Date;

@Controller
@RequestMapping("/coupons/")
public class CouponsController {

    private Integer pageSize = 5;

    @Autowired
    private CouponsService couponsService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("show")
    public String showCouponsList(@RequestParam(value = "page", defaultValue = "0") Integer pageNo, Model model) {
//        Lay ra danh sach phieu giam gia theo trang
        Page<Coupons> page = couponsService.findPagination(pageNo, pageSize);

        model.addAttribute("listCoupons", page.getContent());
        model.addAttribute("currentPage",page.getNumber()); //     So trang
        model.addAttribute("totalPages",page.getTotalPages()); //   Tong so trang

        model.addAttribute("listCustomers", customerService.getAll());
        return "index";
    }

    @GetMapping("edit/{id}")
    public String showEditForm(@PathVariable("id") String id,Model model){
        model.addAttribute("coupons",couponsService.getOne(id));
        model.addAttribute("listCustomers", customerService.getAll());
        return "update-coupons";
    }

    @PostMapping("update")
    public String updateCoupons(@Valid @ModelAttribute("coupons") Coupons coupons, BindingResult result,Model model){
        if (result.hasErrors()){
            model.addAttribute("listCustomers", customerService.getAll());
            return "update-coupons";
        }
        couponsService.save(coupons);
        return "redirect:/coupons/show";
    }

    @GetMapping("delete/{id}")
    public String showEditForm(@PathVariable("id") String id){
        couponsService.delete(id);
        return "redirect:/coupons/show";
    }

    @GetMapping("search")
    public String findByStartDayAndCustomer(@RequestParam(value = "page",defaultValue = "0") Integer pageNo,
                                            @RequestParam(name = "startDay") String startDay,
                                            @RequestParam(name = "endDay") String endDay,
                                            @RequestParam(name = "customer") String code, Model model){
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<Coupons> page = null;
        if (startDay.isEmpty() && !endDay.isEmpty()){
            page = couponsService.search(null,Date.valueOf(endDay),code,pageable);
        } else if (!startDay.isEmpty() && endDay.isEmpty()){
            page = couponsService.search(Date.valueOf(startDay),null,code,pageable);
        }else if (!startDay.isEmpty() && !endDay.isEmpty()){
            page = couponsService.search(Date.valueOf(startDay),Date.valueOf(endDay),code,pageable);
        }else if (startDay.isEmpty() && endDay.isEmpty()){
            page = couponsService.search(null,null,code,pageable);
        }
        model.addAttribute("listCoupons", page.getContent());
        model.addAttribute("currentPage",page.getNumber()); //     So trang
        model.addAttribute("totalPages",page.getTotalPages()); //   Tong so trang

        model.addAttribute("listCustomers", customerService.getAll());
        return "index";
    }



}
