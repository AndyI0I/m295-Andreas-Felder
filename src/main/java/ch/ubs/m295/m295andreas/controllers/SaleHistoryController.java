package ch.ubs.m295.m295andreas.controllers;

import ch.ubs.m295.generated.v1.controller.SalehistoryApi;
import ch.ubs.m295.generated.v1.dto.Purchase;
import ch.ubs.m295.m295andreas.dao.DBOrchestrator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaleHistoryController implements SalehistoryApi {

      Logger logger = LoggerFactory.getLogger(UserController.class);
      @Autowired
      private DBOrchestrator dbOrchestrator;

      @Override
      public ResponseEntity<Purchase> salehistorySaleHistoryIdGet(@PathVariable Integer saleHistoryId) {
            logger.info("salehistorySaleHistoryIdGet called");
            return ResponseEntity.ok(dbOrchestrator.getPurchaseById(saleHistoryId));
      }
}
