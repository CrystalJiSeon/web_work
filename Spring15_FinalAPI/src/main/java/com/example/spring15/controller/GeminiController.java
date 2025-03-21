package com.example.spring15.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring15.service.GeminiService;
import com.example.spring15.service.GeminiService2;

import reactor.core.publisher.Mono;

@RestController
public class GeminiController {
	@Autowired private GeminiService service;
	@Autowired private GeminiService2 service2;
	
	@PostMapping("/gemini/quiz")
	public Mono<String> quiz(@RequestBody Map<String, String> map) { //map에는 quiz라는 키값으로 문제, answer라는 키값으로 답이 전달된다
	
	    return service.quiz2(map);
	} 
	
}
