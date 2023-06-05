package ch.ubs.m295.m295andreas.controllers;

import ch.ubs.m295.generated.v1.controller.ProductApi;
import ch.ubs.m295.generated.v1.dto.User;
import ch.ubs.m295.m295andreas.dao.DBOrchestrator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController implements ProductApi {

      Logger logger = LoggerFactory.getLogger(UserController.class);
      @Autowired
      private DBOrchestrator dbOrchestrator;

      @Override
      public ResponseEntity<List<User>> productProductIdUserGet(@PathVariable Integer productId) {
            logger.info("productProductIdUserGet called with productId: " + productId);
            List<User> users = new ArrayList<>();
            dbOrchestrator.getAllUsers().forEach(user -> {
                  user.getPurchaseHistory().forEach(purchase -> {
                        purchase.getProducts().forEach(product -> {
                              if (product.getId().equals(productId)) {
                                    users.add(user);
                              }
                        });
                  });
            });
            return ResponseEntity.ok(users);
      }

}
