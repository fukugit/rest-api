package com.github.fukugit.restapi.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SampleController {
  @Autowired
  UseCase useCase;

  @GetMapping
  String get() {
    return useCase.get();
  }

}
