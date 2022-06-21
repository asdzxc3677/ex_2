package com.its.ex.controller;

import com.its.ex.dto.TestDTO;
import com.its.ex.service.TestService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;
    //TestController 는 TestService를 호출
    //TestService 는 TestRepository를 호출
    //의존 관계를 만들어 주세요
    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/save-form")
    public String saveForm(){
        return "save";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute TestDTO testDTO){
        testService.save(testDTO);
        return "index";

    }

}
