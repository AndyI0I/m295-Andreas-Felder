package ch.ubs.m295.m295andreas.controllers;

import ch.ubs.m295.generated.v1.controller.UsersApi;
import ch.ubs.m295.generated.v1.dto.User;
import ch.ubs.m295.m295andreas.dao.DBOrchestrator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersController implements UsersApi {

      Logger logger = LoggerFactory.getLogger(UserController.class);
      @Autowired
      private DBOrchestrator dbOrchestrator;

      @Override
      public ResponseEntity<List<User>> usersGet() {
            logger.info("usersGet called");
            return ResponseEntity.ok(dbOrchestrator.getAllUsers());
      }

      @Override
      public ResponseEntity<Void> usersPost(@RequestBody User user) {
            logger.info("usersPost called");
            dbOrchestrator.addUser(user);
            return ResponseEntity.ok().build();
      }
}
