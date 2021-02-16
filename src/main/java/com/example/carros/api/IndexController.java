package com.example.carros.api;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String Get(){
        return "Get";
    }

    @GetMapping("/login/{login}/senha/{senha}")
    public String Get(@PathVariable("login") String login, @PathVariable("senha") String senha){
        return "Login: " + login + "\nSenha: " + senha;
    }
}
