package ch.ubs.m295.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AbstractRestController {

      @GetMapping("/test")
      protected static <T> ResponseEntity<T> getRespond (T result){
            return ResponseEntity.ok(result);
      }
}
