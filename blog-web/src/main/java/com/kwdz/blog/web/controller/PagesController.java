package com.kwdz.blog.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pages/")
public class PagesController {

    @GetMapping("sb2")
    public String sb2() {
        return "sb2/index";
    }
}
