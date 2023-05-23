package ch.ubs.m295.demo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppLogger {

      Logger logger = LoggerFactory.getLogger(AppLogger.class);
      public AppLogger() {
            logger.info("AppLogger created");
            logger.error("AppLogger created");
            logger.debug("AppLogger created");
            logger.trace("AppLogger created");
            logger.warn("AppLogger created");
      }
}
