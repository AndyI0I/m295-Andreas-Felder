package ch.ubs.m295.m295andreas.controllers;

import ch.ubs.m295.generated.v1.controller.UserApi;
import ch.ubs.m295.generated.v1.dto.User;
import ch.ubs.m295.m295andreas.dao.DBOrchestrator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements UserApi {

      Logger logger = LoggerFactory.getLogger(UserController.class);
      @Autowired
      private DBOrchestrator dbOrchestrator;

      @Override
      public ResponseEntity<User> userUserIdGet(@PathVariable Integer userId) {
            logger.info("userUserIdGet called with userId: " + userId);
            return ResponseEntity.ok(dbOrchestrator.getUserById(userId));
      }

      @Override
      public ResponseEntity<Void> userPost(@RequestBody User user) {
            logger.info("userPost called with user: " + user);
            dbOrchestrator.addUser(user);
            return ResponseEntity.ok().build();
      }

      @Override
      public ResponseEntity<Void> userUserIdDelete(@PathVariable Integer userId) {
            logger.info("userUserIdDelete called with userId: " + userId);
            dbOrchestrator.deleteUser(userId);
            return ResponseEntity.ok().build();
      }

      @Override
      public ResponseEntity<Void> userUserIdPut(@PathVariable Integer userId, @RequestBody User user) {
            logger.info("userUserIdPut called with userId: " + userId + " and user: " + user);
            dbOrchestrator.updateUser(userId, user);
            return ResponseEntity.ok().build();
      }

}
