package com.anerkenung.springboot.controller;


import com.anerkenung.springboot.entity.CourseDescriptionEntitiy;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

@CrossOrigin("http://localhost:4200")
@Controller
public class MainController{
    @RequestMapping({"/home"})
    public String index() { return "index.html"; }



}
