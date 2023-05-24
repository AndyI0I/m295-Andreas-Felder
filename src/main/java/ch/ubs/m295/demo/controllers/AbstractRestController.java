package ch.ubs.m295.demo.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public abstract class AbstractRestController {

      protected static <T> ResponseEntity<T> getResponseFromThis(T body) {
            return ResponseEntity.ok(body);
      }
}
