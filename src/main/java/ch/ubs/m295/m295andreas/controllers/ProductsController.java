package ch.ubs.m295.m295andreas.controllers;

import ch.ubs.m295.generated.v1.controller.ProductsApi;
import ch.ubs.m295.generated.v1.dto.Product;
import ch.ubs.m295.m295andreas.dao.DBOrchestrator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductsController implements ProductsApi {

      Logger logger = LoggerFactory.getLogger(UserController.class);
      @Autowired
      private DBOrchestrator dbOrchestrator;

      @Override
      public ResponseEntity<List<Product>> productsGet() {
            logger.info("productsGet");
            return ResponseEntity.ok(dbOrchestrator.getAllProducts());
      }

      @Override
      public ResponseEntity<Void> productsPost(@RequestBody Product product) {
            logger.info("productsPost");
            dbOrchestrator.addProduct(product);
            return ResponseEntity.ok().build();
      }

      @Override
      public ResponseEntity<Product> productsProductIdGet(@PathVariable Integer productId) {
            logger.info("productsProductIdGet");
            return ResponseEntity.ok(dbOrchestrator.getProductById(productId));
      }

      @Override
      public ResponseEntity<Void> productsProductIdDelete(@PathVariable Integer productId) {
            logger.info("productsProductIdDelete");
            dbOrchestrator.deleteProduct(productId);
            return ResponseEntity.ok().build();
      }

      @Override
      public ResponseEntity<Void> productsProductIdPut(@PathVariable Integer productId, @RequestBody Product product) {
            logger.info("productsProductIdPut");
            dbOrchestrator.updateProduct(productId, product);
            return ResponseEntity.ok().build();
      }
}
