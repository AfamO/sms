package com.longbridge.sams.school.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/parent")
public class ParentController {

    private  final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/new")
    public String createParent(){
        return "page/parent/add";
    }
}
