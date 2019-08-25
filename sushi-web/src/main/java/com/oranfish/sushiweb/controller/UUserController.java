package com.oranfish.sushiweb.controller;

import com.oranfish.sushiservice.service.UUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UUserController {

    @Autowired
    private UUserService uUserService;

    @GetMapping("/user/list")
    public Object list(){
        return uUserService.list();
    }

}
