package com.estate.back.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {

    @Value("${deploy.env}")
    private String DEPLOY_ENV;

    @GetMapping("/")
    public String serverStatus() {
        return DEPLOY_ENV + " 환경 실행 중...";
    }

}
